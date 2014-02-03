package OszczednyKonsument.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;

import OszczednyKonsument.DataBase.Database;
import OszczednyKonsument.DataBaseModel.DataBaseGet;


public class Client {
	private Socket connection = null;
	private Reader in;
	private Writer out;
	public Client() {
		//net();
		
		
	}
	public void net() {
		String host = "127.0.0.1";
		try {
			connection = new Socket(host, 17373);
			in = new InputStreamReader(connection.getInputStream());
			out = new OutputStreamWriter(connection.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Client();
	}

}
