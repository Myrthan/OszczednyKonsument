package OszczednyKonsument.DataBaseModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import OszczednyKonsument.DataBase.Database;

/**
 * Klasa odpowiedzialna za modyfikowanie BD.
 * 
 * @author myrthan
 *
 */
public class DataBaseUpdate {

	@SuppressWarnings("javadoc")
	public static boolean  insertKlient(String imie, String nazwisko, Integer wiek,
			String adres, String miasto, String kod_pocztowy, String nick,
			Date data_rejestracji, String haslo, String email) {
		try {
			PreparedStatement prepStmt = Database.getConnection()
					.prepareStatement(
							"insert into klienci values (NULL, ?, ?,"+ wiek+",?,?,?,?,?,?,?);");
			prepStmt.setString(1, nazwisko);
			prepStmt.setString(2, imie);
			prepStmt.setString(3, adres);
			prepStmt.setString(4, miasto);
			prepStmt.setString(5, kod_pocztowy);
			prepStmt.setString(6, nick);
			prepStmt.setDate(7, data_rejestracji);
			prepStmt.setString(8, haslo);
			prepStmt.setString(9, email);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawianiu klienta");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("javadoc")	
	public boolean insertProducent(String nazwa, String branza, Integer numer_kontaktowy) {
		try {
			PreparedStatement prepStmt = Database.getConnection()
					.prepareStatement(
							"insert into Producenci values (?, ?, ?);");
			prepStmt.setString(1, nazwa);
			prepStmt.setString(2, branza);
			prepStmt.setInt(3, numer_kontaktowy);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawianiu producenta");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("javadoc")	
	public boolean insertTyp_produktu(String	branza,String typ) {
		try {
			PreparedStatement prepStmt = Database.getConnection()
					.prepareStatement(
							"insert into typ_produktu values (?, ?);");
			prepStmt.setString(1, branza);
			prepStmt.setString(2, typ);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawianiu typu produktu");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("javadoc")
	public boolean insertHistoria_wyszukiwania(Integer	id_wysz, Date timestamp, Integer id_klienta) {
		try {
			PreparedStatement prepStmt = Database.getConnection()
					.prepareStatement(
							"insert into historia_wyszukiwania values (?, ?, ?);");
			prepStmt.setInt(1, id_wysz);
			prepStmt.setDate(2, timestamp);
			prepStmt.setInt(3, id_klienta);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawianiu do historia_wyszukiwania");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("javadoc")
	public boolean insertProdukt(String nazwa,String producent,String typ) {
		try {
			PreparedStatement prepStmt = Database.getConnection()
					.prepareStatement(
							"insert into produkty values (NULL,?, ?, ?);");
			prepStmt.setString(1, nazwa);
			prepStmt.setString(2, producent);
			prepStmt.setString(3, typ);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawianiu produktu");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("javadoc")
	public boolean insertRecenzja(String recenzja,String autor,Integer id_produkt) {
		try {
			PreparedStatement prepStmt = Database.getConnection()
					.prepareStatement(
							"insert into recenzje values (NULL,?, ?, ?);");
			prepStmt.setString(1, recenzja);
			prepStmt.setString(2, autor);
			prepStmt.setInt(3, id_produkt);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawianiu recenzji");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("javadoc")
	public boolean insertUlubione(Integer id_klient, Integer produkt) {
		try {
			PreparedStatement prepStmt = Database.getConnection()
					.prepareStatement(
							"insert into ulubione values (?,?);");
			prepStmt.setInt(1, id_klient);
			prepStmt.setInt(2, produkt);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawaniu do ulubione");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("javadoc")
	public boolean insertWyszukiwane_produkty(Integer id_klienta,Integer id_produktu,Integer id_wysz) {
		try {
			PreparedStatement prepStmt = Database.getConnection()
					.prepareStatement(
							"insert into wyszukiwane_produkty values (?,?,?);");
			prepStmt.setInt(1, id_klienta);
			prepStmt.setInt(2, id_produktu);
			prepStmt.setInt(3, id_wysz);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawaniu do wyszukiwane produkty");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("javadoc")
	public boolean insertOpinia(String komentarz,Integer ocena,Integer produkt, Integer klient) {
		try {
			PreparedStatement prepStmt = Database.getConnection()
					.prepareStatement(
							"insert into opinie values (NULL,?,?,?,?);");
			prepStmt.setString(1,komentarz);
			prepStmt.setInt(2, ocena);
			prepStmt.setInt(3, produkt);
			prepStmt.setInt(4, klient);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawaniu do opinie");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("javadoc")
	public boolean insertWlasciciel_sklepu(String	nazwa,Integer numer_kontaktowy) {
		try {
			PreparedStatement prepStmt = Database.getConnection()
					.prepareStatement(
							"insert into właściciele_sklepów values (?,?);");
			prepStmt.setString(1,nazwa);
			prepStmt.setInt(2, numer_kontaktowy);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawaniu do właściciele_sklepów");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("javadoc")
	public boolean insertSklep(String nazwa,String adres,String miasto,
			String kod_pocztowy,String godziny_otwarcia,
			Integer numer_kontaktowy,String wlasciciel) {
		try {
			PreparedStatement prepStmt = Database.getConnection()
					.prepareStatement(
							"insert into sklepy values (NULL,?,?,?,?,?,?,?);");
			prepStmt.setString(1, nazwa);
			prepStmt.setString(2, adres);
			prepStmt.setString(3, miasto);
			prepStmt.setString(4, kod_pocztowy);
			prepStmt.setString(5, godziny_otwarcia);
			prepStmt.setInt(6, numer_kontaktowy);
			prepStmt.setString(7, wlasciciel);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawaniu do sklepów");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("javadoc")
	public boolean insertProdukt_do_sprzedazy(Double cena,Integer id_produktu,Integer id_sklepu) {
		try {
			PreparedStatement prepStmt = Database.getConnection()
					.prepareStatement(
							"insert into produkty_do_sprzedaży values (NULL,?,?,?);");
			prepStmt.setDouble(1, cena);
			prepStmt.setInt(2, id_produktu);
			prepStmt.setInt(3, id_sklepu);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawaniu do produktów do sprzedazy");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
