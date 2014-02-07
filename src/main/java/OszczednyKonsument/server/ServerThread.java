package OszczednyKonsument.server;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.SSLSocket;

import OszczednyKonsument.DataBaseModel.DataBaseGet;
import OszczednyKonsument.DataBaseModel.DataBaseUpdate;
import OszczednyKonsument.DataBaseModel.Opinia;
import OszczednyKonsument.DataBaseModel.Produkt;
import OszczednyKonsument.DataBaseModel.Recenzja;
import OszczednyKonsument.DataBaseModel.SerachResult;

public class ServerThread extends Thread {
	Socket socket;
	String login;
	Integer idKlient;

	ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			OutputStream outputStream = socket.getOutputStream();
			DataOutputStream out = new DataOutputStream(outputStream);
			InputStream inputStream = socket.getInputStream();
			DataInputStream in = new DataInputStream(inputStream);
			String command;
			while (true) {
				command = in.readUTF();
				switch (command) {
				case "LOGIN":
					String login = in.readUTF();
					char[] pass = in.readUTF().toCharArray();
					int b = DataBaseGet.checkPassword(login, pass);
					if (b != -1) {
						idKlient = b;
					}
					out.writeBoolean(b != -1);
					out.flush();
					if (b != -1) {
						this.login = login;
					}
					break;
				case "NICKEXIST":
					login = in.readUTF();
					out.writeBoolean(DataBaseGet.checkIfNickExists(login));
					out.flush();
					break;
				case "REGISTER":
					String nazwisko;
					String imie;
					Integer wiek;
					String adres;
					String miasto;
					String kod_pocztowy;
					String nick;
					char[] haslo;
					String email;
					nazwisko = in.readUTF();
					imie = in.readUTF();
					wiek = in.readInt();
					adres = in.readUTF();
					miasto = in.readUTF();
					kod_pocztowy = in.readUTF();
					nick = in.readUTF();
					haslo = in.readUTF().toCharArray();
					email = in.readUTF();
					if (nazwisko.equals(""))
						nazwisko = null;
					if (imie.equals(""))
						imie = null;
					if (wiek.equals(-1))
						wiek = null;
					if (adres.equals(""))
						adres = null;
					if (miasto.equals(""))
						miasto = null;
					boolean be;
					if (kod_pocztowy.equals(""))
						kod_pocztowy = null;
					be = DataBaseUpdate.insertKlient(imie, nazwisko, wiek,
							adres, miasto, kod_pocztowy, nick, new Date(-1),
							new String(haslo), email);
					out.writeBoolean(be);
					break;
				case "RECENZJE":
					// System.out.println("wisze");
					Integer id_prod = in.readInt();
					// System.out.println("odwisze");
					List<Recenzja> rec = DataBaseGet.selectRecenzje(id_prod);
					out.writeInt(rec.size());
					out.flush();
					for (Recenzja r : rec) {
						out.writeUTF(r.recenzja);
						out.flush();
						out.writeUTF(r.autor);
						out.flush();
					}
					break;
				case "OPINIE":
					id_prod = in.readInt();
					List<Opinia> opinie = new LinkedList<Opinia>();
					opinie = DataBaseGet.selectOpinie(id_prod);
					out.writeInt(opinie.size());
					out.flush();
					for (Opinia o : opinie) {
						out.writeUTF(o.nick);
						out.flush();
						out.writeUTF(o.komentarz);
						out.flush();
						out.writeInt(o.ocena);
						out.flush();
						out.writeLong(o.time.getTime());
						out.flush();
					}
					break;
				case "INSERTOPINIA":
					String komentarz;
					Integer ocena;
					Integer produkt;
					Integer klient;
					komentarz=in.readUTF();
					ocena=in.readInt();
					produkt=in.readInt();
					/*if(this.idKlient==null){
						out.close();
						in.close();
					}*/
					DataBaseUpdate.insertOpinia(komentarz, ocena, produkt, idKlient);
					out.writeUTF("A");
					out.flush();
					break;
				case "SELECT_PRODUKTY":
					List<Produkt> selectProdukty = DataBaseGet.selectProdukty();
					out.writeInt(selectProdukty.size());
					out.flush();
					if(selectProdukty.size() == 0)
						break;
					out.writeInt(selectProdukty.get(0).size);
					out.flush();
					for (Produkt r : selectProdukty) {
						out.writeUTF(r.id_produkt.toString());
						out.flush();
						out.writeUTF(r.nazwa);
						out.flush();
						out.writeUTF(r.producent);
						out.flush();
						out.writeUTF(r.typ);
						out.flush();
						//out.writeUTF("\n");
						//out.flush();
					}
					break;
				case "SERACHQUERY":
					int size = in.readInt();
					if (size == 0)
						break;
					Integer[] w = new Integer[size];
					for(int i = 0; i < size; i ++)
						w[i] = in.readInt();
					List<SerachResult> serachRes = DataBaseGet.serachQuery(w);
					int size2 = serachRes.size();
					out.writeInt(serachRes.size());
					out.flush();
					for(int i = 0; i < size2;i++) {
						out.writeInt(serachRes.get(i).id_sklep);
						out.flush();
						out.writeDouble(serachRes.get(i).resultSum);
						out.flush();
					}
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
