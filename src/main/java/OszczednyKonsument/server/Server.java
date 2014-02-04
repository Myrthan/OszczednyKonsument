package OszczednyKonsument.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;

import OszczednyKonsument.DataBase.Database;
import OszczednyKonsument.DataBaseModel.DataBaseGet;

/**
 * Server
 * 
 * @author myrthan
 *
 */
public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		try {
			ServerSocket listener = new ServerSocket(17373);
			while(!Thread.currentThread().isInterrupted()) {
				
			}
			listener.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		
		// TODO Auto-generated method stub
		//System.out.println(DataBaseGet.selectProdukty());
		System.out.println(DataBaseGet.selectProdukty());
		System.out.println(DataBaseGet.selectOpinie(12));
		System.out.println(DataBaseGet.selectOpinie("Ożywi"));
		System.out.println(DataBaseGet.selectRecenzje(6));
		System.out.println(DataBaseGet.selectRecenzje("Ewy-coś"));
	}

}
