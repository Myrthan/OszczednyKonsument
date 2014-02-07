package OszczednyKonsument.client;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;

import javax.swing.JComboBox;

import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JSpinner;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import java.awt.FlowLayout;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import net.miginfocom.swing.MigLayout;

import javax.swing.JPasswordField;

import OszczednyKonsument.DataBaseModel.DataBaseGet;
import OszczednyKonsument.DataBaseModel.DataBaseUpdate;
import OszczednyKonsument.DataBaseModel.Opinia;
import OszczednyKonsument.DataBaseModel.Recenzja;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.Action;

public class ProductPanel extends JPanel {
	private JTextField mark;
	private Integer idProduktu;
	private Integer idKlienta;
	private JTextPane comment;
	DataInputStream in; 
	DataOutputStream out;
	private final Action action = new SwingAction();
	public ProductPanel(Integer produktId, Integer idKlienta,DataInputStream in, DataOutputStream out) {
		this.in=in;
		this.out=out;
		this.idKlienta=idKlienta;
		this.idProduktu=produktId;
		refresh();
	}
	
	
	private List<Recenzja> getRecenzjeList(Integer produktId) {
		List<Recenzja> list=new LinkedList<Recenzja>();
		try{
			out.writeUTF("RECENZJE");
			out.writeInt(produktId);
			int size=in.readInt();
			for(int i=0; i<size; ++i){
				list.add(new Recenzja(in.readUTF(),in.readUTF()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			return list;
		}
	}


	private List<Opinia> getOpinieList(Integer produktId) {
		List<Opinia> opinie= new LinkedList<Opinia>();
		try{
			out.writeUTF("OPINIE");
			out.writeInt(produktId);
			out.flush();
			int size=in.readInt();
			for(int i=0; i<size; ++i){
				opinie.add(new Opinia(in.readUTF(),in.readUTF(),in.readInt(),new Timestamp(in.readLong())));
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		finally{
			return opinie;
		}
		
	}


	public static void createAndShowGUI(Integer i,DataInputStream in, DataOutputStream out) {
		JFrame frame = new JFrame("Oszczedny Konsument");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ProductPanel newContentPane = new ProductPanel(i,2,in,out);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SSLSocket socket;
				System.setProperty("javax.net.ssl.trustStore","LsKeystore");
	    		System.setProperty("javax.net.ssl.trustStorePassword","admin12");
				Integer portNr = 30002;
				SSLSocketFactory mySocketFactory=(SSLSocketFactory)SSLSocketFactory.getDefault();
				try {
					socket=(SSLSocket)mySocketFactory.createSocket("localhost", portNr);
					createAndShowGUI(6,new DataInputStream(socket.getInputStream()),
							new DataOutputStream(socket.getOutputStream()));
				}catch(IOException e){
					
				}
			}
		});
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Publikuj");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			boolean b=false;
			Integer i=new Integer(0);
			try{
				i=new Integer(mark.getText());
				if(i>7 || i<1)
					b=true;
			}
			catch(Exception f){
				b=true;
			}
			finally{
				if (b) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							JOptionPane.showMessageDialog(ProductPanel.this,
									"Wpisz poprawną liczbę!");
							
						}
					});
					return;
				}
			}
			if(comment.getText().length()==0){
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						JOptionPane.showMessageDialog(ProductPanel.this,
								"Nie możesz publikować pustych komentarzy");
					}
				});
				return;
			}
			commitOpinia(comment.getText(),i);
			refresh();
		}
	}
	void commitOpinia(String comment, Integer mark){
		try {
			out.writeUTF("INSERTOPINIA");
			out.writeUTF(comment);
			out.writeInt(mark);
			out.writeInt(idProduktu);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void refresh(){
		removeAll();
		List<Opinia> opinie= getOpinieList(idProduktu);
		List<Recenzja> recenzje=getRecenzjeList(idProduktu);
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane_1, null);
		if (recenzje.size() == 0) {
			JTextPane txtpnBrakRecenzji = new JTextPane();
			txtpnBrakRecenzji.setText("Brak recenzji");
			scrollPane_1.setViewportView(txtpnBrakRecenzji);
			JLabel lblAu = new JLabel("au");
			scrollPane_1.setColumnHeaderView(lblAu);
			txtpnBrakRecenzji.setEditable(false);
		}
		else{
			for(Recenzja r: recenzje){
				JTextPane txtpn = new JTextPane();
				txtpn.setText(r.recenzja);
				scrollPane_1.setViewportView(txtpn);
				JLabel lblAu = new JLabel(r.autor);
				scrollPane_1.setColumnHeaderView(lblAu);
				txtpn.setEditable(false);
			}
		}

		for(Opinia o: opinie){
			JScrollPane scrollPane_2 = new JScrollPane();
			panel.add(scrollPane_2);
			
			JTextPane txtpn1 = new JTextPane();
			txtpn1.setText(o.komentarz);
			scrollPane_2.setViewportView(txtpn1);
			
			JLabel lblPrastaruszek = new JLabel(o.nick);
			scrollPane_2.setColumnHeaderView(lblPrastaruszek);
			txtpn1.setEditable(false);
		}
		JScrollPane scrollPane3 = new JScrollPane();
		panel.add(scrollPane3);
		JTextPane txtpn = new JTextPane();
		txtpn.setText("");
		scrollPane3.setViewportView(txtpn);
		JLabel lblPrastaruszekx = new JLabel("Wpisz komentarz: ");
		scrollPane3.setColumnHeaderView(lblPrastaruszekx);
		comment=txtpn;
		
		JScrollPane scr=new JScrollPane();
		panel.add(scr);
		JLabel lbl=new JLabel("Wpisz ocenę (1-6):");
		scr.setColumnHeaderView(lbl);
		
		mark = new JTextField();
		scr.setViewportView(mark);
		mark.setColumns(10);
		JButton commitButton=new JButton("Publikuj");
		commitButton.setAction(action);
		panel.add(commitButton);
		revalidate();
	}
}
