package OszczednyKonsument.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.util.LinkedList;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;


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
	public static void main(String args[]){	
		try{
	    	System.setProperty("javax.net.ssl.keyStore","LsKeystore");
			System.setProperty("javax.net.ssl.keyStorePassword","admin12");
			SSLServerSocketFactory SocketFactory=(SSLServerSocketFactory)SSLServerSocketFactory.getDefault();

			System.out.println("Server succesfully started.");
			SSLServerSocket welcomeSocket=(SSLServerSocket)SocketFactory.createServerSocket(30002);
						//welcomeSocket.setEnabledCipherSuites(new String[] {"TLS_RSA_WITH_AES_128_CBC_SHA"});

			while(true){
				SSLSocket connectionSocket=(SSLSocket)welcomeSocket.accept();
				/*for(String x : connectionSocket.getEnabledCipherSuites()){
					System.out.println(x);
				}*/
				new Thread(new ServerThread(connectionSocket)).start();
			}
		}
		catch(IOException e){
			System.out.println(e+"tata");
		}
	}

}
