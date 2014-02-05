package OszczednyKonsument.client;

import javax.swing.JFrame;

import java.awt.FlowLayout;

import javax.swing.JTextPane;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import OszczednyKonsument.DataBaseModel.DataBaseGet;

public class Logger extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;
	private final Action action = new SwingAction();
	public Logger() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNick = new JLabel("nick:");
		GridBagConstraints gbc_lblNick = new GridBagConstraints();
		gbc_lblNick.insets = new Insets(0, 0, 5, 5);
		gbc_lblNick.anchor = GridBagConstraints.EAST;
		gbc_lblNick.gridx = 0;
		gbc_lblNick.gridy = 0;
		getContentPane().add(lblNick, gbc_lblNick);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("password: ");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		getContentPane().add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 1;
		gbc_passwordField_1.gridy = 1;
		getContentPane().add(passwordField, gbc_passwordField_1);
		
		JButton btnLogIn = new JButton("Zaloguj się");
		btnLogIn.setAction(action);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkPassword(textField.getText(),passwordField.getPassword())){
					JOptionPane.showMessageDialog(Logger.this, "Login OK");
				}
				else{
					JOptionPane.showMessageDialog(Logger.this, "Zły login lub hasło");
				}
			}
		});
		btnLogIn.setText("Zaloguj się");
		GridBagConstraints gbc_btnLogIn = new GridBagConstraints();
		gbc_btnLogIn.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogIn.gridx = 1;
		gbc_btnLogIn.gridy = 2;
		getContentPane().add(btnLogIn, gbc_btnLogIn);
		
	}
	
	public static void createAndShowGUI() {
		JFrame frame = new Logger();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//ClientApp newContentPane = new ClientApp();
		//newContentPane.setOpaque(true); // content panes must be opaque
		//frame.setContentPane(newContentPane);

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
			//putValue(NAME, "SwingAction");
			//putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private boolean checkPassword(String login, char[] passwd){
		return DataBaseGet.checkPassword(login, passwd);
	}
}
