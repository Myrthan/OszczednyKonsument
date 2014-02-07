package OszczednyKonsument.client;
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

import javax.swing.Action;

public class ProductPanel extends JPanel {
	private JTextField mark;
	private Integer idProduktu;
	private Integer idKlienta;
	private JTextPane comment;
	private final Action action = new SwingAction();
	public ProductPanel(Integer produktId, Integer idKlienta) {
		this.idKlienta=idKlienta;
		this.idProduktu=produktId;
		refresh();
	}
	
	
	private List<Recenzja> getRecenzjeList(Integer produktId) {
		return DataBaseGet.selectRecenzje(produktId);
	}


	private List<Opinia> getOpinieList(Integer produktId) {
		return DataBaseGet.selectOpinie(produktId);
	}


	public static void createAndShowGUI(Integer i,String nick) {
		JFrame frame = new JFrame("Oszczedny Konsument");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ProductPanel newContentPane = new ProductPanel(i,2);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI(6,"Prastaruszek");
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
		DataBaseUpdate.insertOpinia(comment, mark, idProduktu, idKlienta);
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
