package OszczednyKonsument.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import OszczednyKonsument.DataBase.Database;
import OszczednyKonsument.DataBaseModel.DataBaseGet;


public class Client {
	private Socket connection = null;
	private Reader in;
	private Writer out;
	public Client() {
		//net();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//ClientApp.createAndShowGUI(in,out);
			}
		});
		
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
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SSLSocket socket;
				System.setProperty("javax.net.ssl.trustStore","LsKeystore");
	    		System.setProperty("javax.net.ssl.trustStorePassword","admin12");
				Integer portNr = 30002;
				SSLSocketFactory mySocketFactory=(SSLSocketFactory)SSLSocketFactory.getDefault();
				try {
					socket=(SSLSocket)mySocketFactory.createSocket("localhost", portNr);
					Logger.createAndShowGUI(new DataInputStream(socket.getInputStream()),
					new DataOutputStream(socket.getOutputStream()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
