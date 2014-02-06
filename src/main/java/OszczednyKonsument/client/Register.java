package OszczednyKonsument.client;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.Arrays;

import javax.swing.Action;

import OszczednyKonsument.DataBaseModel.DataBaseGet;
import OszczednyKonsument.DataBaseModel.DataBaseUpdate;

public class Register extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_e_m;
	private JTextField textField_imie;
	private JTextField textField_wiek;
	private JTextField textField_adres;
	private JTextField textField_miasto;
	private JTextField textField_kod;
	private JTextField textField_nick;
	private final Action action = new SwingAction();
	private JTextField textField_nazw;
	public Register() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNick = new JLabel("nick*: ");
		GridBagConstraints gbc_lblNick = new GridBagConstraints();
		gbc_lblNick.anchor = GridBagConstraints.EAST;
		gbc_lblNick.insets = new Insets(0, 0, 5, 5);
		gbc_lblNick.gridx = 0;
		gbc_lblNick.gridy = 2;
		add(lblNick, gbc_lblNick);
		
		textField_nick = new JTextField();
		GridBagConstraints gbc_textField_nick = new GridBagConstraints();
		gbc_textField_nick.insets = new Insets(0, 0, 5, 0);
		gbc_textField_nick.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_nick.gridx = 1;
		gbc_textField_nick.gridy = 2;
		add(textField_nick, gbc_textField_nick);
		textField_nick.setColumns(10);
		
		JLabel lblHaso = new JLabel("hasło*: ");
		GridBagConstraints gbc_lblHaso = new GridBagConstraints();
		gbc_lblHaso.anchor = GridBagConstraints.EAST;
		gbc_lblHaso.insets = new Insets(0, 0, 5, 5);
		gbc_lblHaso.gridx = 0;
		gbc_lblHaso.gridy = 3;
		add(lblHaso, gbc_lblHaso);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 3;
		add(passwordField, gbc_passwordField);
		
		JLabel lblPotwierdHaso = new JLabel("potwierdź hasło: ");
		GridBagConstraints gbc_lblPotwierdHaso = new GridBagConstraints();
		gbc_lblPotwierdHaso.insets = new Insets(0, 0, 5, 5);
		gbc_lblPotwierdHaso.anchor = GridBagConstraints.EAST;
		gbc_lblPotwierdHaso.gridx = 0;
		gbc_lblPotwierdHaso.gridy = 4;
		add(lblPotwierdHaso, gbc_lblPotwierdHaso);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 1;
		gbc_passwordField_1.gridy = 4;
		add(passwordField_1, gbc_passwordField_1);
		
		JLabel lblEmail = new JLabel("e-mail*: ");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 5;
		add(lblEmail, gbc_lblEmail);
		
		textField_e_m = new JTextField();
		GridBagConstraints gbc_textField_e_m = new GridBagConstraints();
		gbc_textField_e_m.insets = new Insets(0, 0, 5, 0);
		gbc_textField_e_m.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_e_m.gridx = 1;
		gbc_textField_e_m.gridy = 5;
		add(textField_e_m, gbc_textField_e_m);
		textField_e_m.setColumns(10);
		
		JLabel lblImie = new JLabel("imie: ");
		lblImie.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblImie = new GridBagConstraints();
		gbc_lblImie.anchor = GridBagConstraints.EAST;
		gbc_lblImie.insets = new Insets(0, 0, 5, 5);
		gbc_lblImie.gridx = 0;
		gbc_lblImie.gridy = 6;
		add(lblImie, gbc_lblImie);
		
		textField_imie = new JTextField();
		GridBagConstraints gbc_textField_imie = new GridBagConstraints();
		gbc_textField_imie.insets = new Insets(0, 0, 5, 0);
		gbc_textField_imie.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_imie.gridx = 1;
		gbc_textField_imie.gridy = 6;
		add(textField_imie, gbc_textField_imie);
		textField_imie.setColumns(10);
		
		JLabel lblNazwisko = new JLabel("nazwisko: ");
		GridBagConstraints gbc_lblNazwisko = new GridBagConstraints();
		gbc_lblNazwisko.anchor = GridBagConstraints.EAST;
		gbc_lblNazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazwisko.gridx = 0;
		gbc_lblNazwisko.gridy = 7;
		add(lblNazwisko, gbc_lblNazwisko);
		
		textField_nazw = new JTextField();
		GridBagConstraints gbc_textField_nazw = new GridBagConstraints();
		gbc_textField_nazw.insets = new Insets(0, 0, 5, 0);
		gbc_textField_nazw.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_nazw.gridx = 1;
		gbc_textField_nazw.gridy = 7;
		add(textField_nazw, gbc_textField_nazw);
		textField_nazw.setColumns(10);
		
		JLabel lblWiek = new JLabel("wiek: ");
		GridBagConstraints gbc_lblWiek = new GridBagConstraints();
		gbc_lblWiek.anchor = GridBagConstraints.EAST;
		gbc_lblWiek.insets = new Insets(0, 0, 5, 5);
		gbc_lblWiek.gridx = 0;
		gbc_lblWiek.gridy = 8;
		add(lblWiek, gbc_lblWiek);
		
		textField_wiek = new JTextField();
		GridBagConstraints gbc_textField_wiek = new GridBagConstraints();
		gbc_textField_wiek.insets = new Insets(0, 0, 5, 0);
		gbc_textField_wiek.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_wiek.gridx = 1;
		gbc_textField_wiek.gridy = 8;
		add(textField_wiek, gbc_textField_wiek);
		textField_wiek.setColumns(10);
		
		JLabel lblAdres = new JLabel("adres: ");
		GridBagConstraints gbc_lblAdres = new GridBagConstraints();
		gbc_lblAdres.anchor = GridBagConstraints.EAST;
		gbc_lblAdres.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdres.gridx = 0;
		gbc_lblAdres.gridy = 9;
		add(lblAdres, gbc_lblAdres);
		
		textField_adres = new JTextField();
		GridBagConstraints gbc_textField_adres = new GridBagConstraints();
		gbc_textField_adres.insets = new Insets(0, 0, 5, 0);
		gbc_textField_adres.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_adres.gridx = 1;
		gbc_textField_adres.gridy = 9;
		add(textField_adres, gbc_textField_adres);
		textField_adres.setColumns(10);
		
		JLabel lblMiasto = new JLabel("miasto: ");
		GridBagConstraints gbc_lblMiasto = new GridBagConstraints();
		gbc_lblMiasto.anchor = GridBagConstraints.EAST;
		gbc_lblMiasto.insets = new Insets(0, 0, 5, 5);
		gbc_lblMiasto.gridx = 0;
		gbc_lblMiasto.gridy = 10;
		add(lblMiasto, gbc_lblMiasto);
		
		textField_miasto = new JTextField();
		GridBagConstraints gbc_textField_miasto = new GridBagConstraints();
		gbc_textField_miasto.insets = new Insets(0, 0, 5, 0);
		gbc_textField_miasto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_miasto.gridx = 1;
		gbc_textField_miasto.gridy = 10;
		add(textField_miasto, gbc_textField_miasto);
		textField_miasto.setColumns(10);
		
		JLabel lblKodPocztowy = new JLabel("kod pocztowy: ");
		GridBagConstraints gbc_lblKodPocztowy = new GridBagConstraints();
		gbc_lblKodPocztowy.anchor = GridBagConstraints.EAST;
		gbc_lblKodPocztowy.insets = new Insets(0, 0, 5, 5);
		gbc_lblKodPocztowy.gridx = 0;
		gbc_lblKodPocztowy.gridy = 11;
		add(lblKodPocztowy, gbc_lblKodPocztowy);
		
		textField_kod = new JTextField();
		GridBagConstraints gbc_textField_kod = new GridBagConstraints();
		gbc_textField_kod.insets = new Insets(0, 0, 5, 0);
		gbc_textField_kod.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_kod.gridx = 1;
		gbc_textField_kod.gridy = 11;
		add(textField_kod, gbc_textField_kod);
		textField_kod.setColumns(10);
		
		JButton btnZarejestrujSi = new JButton("Zarejestruj się!");
		btnZarejestrujSi.setAction(action);
		GridBagConstraints gbc_btnZarejestrujSi = new GridBagConstraints();
		gbc_btnZarejestrujSi.insets = new Insets(0, 0, 5, 0);
		gbc_btnZarejestrujSi.gridx = 1;
		gbc_btnZarejestrujSi.gridy = 12;
		add(btnZarejestrujSi, gbc_btnZarejestrujSi);
	}
	public static void createAndShowGUI() {
		JFrame frame = new JFrame("Oszczedny Konsument");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Register newContentPane = new Register();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Zarejestruj się!");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {

			if(try_to_register(textField_nazw.getText(), textField_imie.getText(),textField_wiek.getText(),
					textField_adres.getText(),textField_miasto.getText(),textField_kod.getText(),
					textField_nick.getText(),passwordField.getPassword(),
					passwordField_1.getPassword(), textField_e_m.getText())){
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						JOptionPane.showMessageDialog(Register.this, "Rejestracja przebiegła pomyślnie");
					}
				});
			}

			
		}
	}
	private boolean try_to_register(String nazwisko,String imie, String wiek, String adres, String miasto,
			String kod_pocztowy,String nick,char[] haslo, char[] potwHaslo, String email){
		try {
			Integer myWiek;
			if(!wiek.equals(""))
				myWiek = new Integer(wiek);
			else
				myWiek=null;
			if(nick.equals("") || Arrays.equals(haslo,"".toCharArray()) || email.equals("")){
				return false;
			}
			if(nazwisko.equals("")) nazwisko=null;
			if(imie.equals("")) imie=null;
			if(wiek.equals("")) myWiek=null;
			if(adres.equals("")) adres=null;
			if(miasto.equals("")) miasto=null;
			
			if(!kod_pocztowy.equals("") && !kod_pocztowy.matches("\\d\\d-\\d\\d\\d")){
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						JOptionPane.showMessageDialog(Register.this, "Zły format kodu pocztowego.");
					}
				});
				return false;
			}
			if(haslo.length<=5 || haslo.length>100|| !Arrays.equals(potwHaslo, haslo)){
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						JOptionPane.showMessageDialog(Register.this, "Złe hasło.");
					}
				});
				return false;
			}
			if(checkIfNickExists(nick)){
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						JOptionPane.showMessageDialog(Register.this, "Wybrany nick już istnieje.");
					}
				});
				return false;
			}
			if(DataBaseUpdate.insertKlient(nazwisko,
					imie,
					myWiek,
					adres,
					miasto,
					kod_pocztowy,
					nick,
					new Date(-1),
					new String(haslo),email)){	
					return true;
			}
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					JOptionPane.showMessageDialog(Register.this, "Rejestracja nie powiodła się. Spróbuj ponownie później.");
				}
			});
			return false;
		} catch (NumberFormatException e) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					JOptionPane.showMessageDialog(Register.this, "Niepoprawnie wypełnione pole \"wiek.\"");
				}
			});
			return false;
		}
		catch(Exception e){
			e.printStackTrace();
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					JOptionPane.showMessageDialog(Register.this, "Rejestracja nie powiodła się. Zły format danych,");
				}
			});
			return false;
		}
		
	}
	private boolean checkIfNickExists(String nick) {
		return DataBaseGet.checkIfNickExists(nick);
	}
}