package OszczednyKonsument.client;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import OszczednyKonsument.DataBase.Database;
import OszczednyKonsument.DataBaseModel.DataBaseGet;
import OszczednyKonsument.DataBaseModel.Produkt;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ClientApp extends JPanel implements ActionListener {
	private boolean DEBUG = false;
	private List<Produkt> selectProdukty;
	private LinkedList<Object[]> data = new LinkedList<Object[]>();
	private LinkedList<Object[]> dataKoszyk = new LinkedList<Object[]>();
	private SerachList model;
	private Koszyk modelKoszyk;
	private int currentChoosed = -1;
	private int currentChoosed2 = -1;

	public ClientApp() {
		super(new GridLayout(1, 0));

		// TEST dla produktow
		selectProdukty = DataBaseGet.selectProdukty();

		for (int i = 0; i < selectProdukty.size(); i++) {
			data.add(selectProdukty.get(i).toObject());
		}
		model = new SerachList(data);
		JTable table = new JTable(model);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		table.setPreferredScrollableViewportSize(new Dimension(
				screenSize.width, screenSize.height / 2));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		table.getSelectionModel().addListSelectionListener(new RowListener());

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JButton b1 = new JButton("Dodaj produkt do koszyka");
		b1.setVerticalTextPosition(AbstractButton.BOTTOM);
		b1.setHorizontalTextPosition(AbstractButton.LEFT);
		b1.setMnemonic(KeyEvent.VK_M);
		b1.addActionListener(this);
		JLabel label = new JLabel("Image and Text", JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalTextPosition(JLabel.CENTER);

		JButton b2 = new JButton("Wyszukaj");
		b2.setVerticalTextPosition(AbstractButton.BOTTOM);
		b2.setHorizontalTextPosition(AbstractButton.LEFT);
		b2.setMnemonic(KeyEvent.VK_M);
		b2.addActionListener(this);

		JButton b3 = new JButton("Wyczysc liste zakupow");
		b3.setVerticalTextPosition(AbstractButton.VERTICAL);
		b3.setHorizontalTextPosition(AbstractButton.CENTER);
		b3.setMnemonic(KeyEvent.VK_M);
		b3.addActionListener(this);

		JButton b4 = new JButton("Usun");
		b4.setVerticalTextPosition(AbstractButton.VERTICAL);
		b4.setHorizontalTextPosition(AbstractButton.CENTER);
		b4.setMnemonic(KeyEvent.VK_M);
		b4.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		panel.setOpaque(false);
		add(panel);
		modelKoszyk = new Koszyk(dataKoszyk);
		JTable koszyk = new JTable(modelKoszyk);
		add(koszyk);
		koszyk.setFillsViewportHeight(true);
		koszyk.setAutoCreateRowSorter(true);
		koszyk.getSelectionModel().addListSelectionListener(new RowListener2());

	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Dodaj produkt do koszyka")) {
			if (currentChoosed < 0)
				return;
			dataKoszyk.add(data.get(currentChoosed));
			modelKoszyk.fireTableDataChanged();
		} else if (command.equals("Wyszukaj")) {
			
		} else if (command.equals("Wyczysc liste zakupow")) {
			dataKoszyk.clear();
			modelKoszyk.fireTableDataChanged();
		} else if (command.equals("Usun")) {
			dataKoszyk.remove(currentChoosed2);
			modelKoszyk.fireTableDataChanged();
		}
	}

	private class RowListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			ListSelectionModel lsm = (ListSelectionModel) event.getSource();
			currentChoosed = lsm.getMinSelectionIndex();
			if (event.getValueIsAdjusting()) {
				return;
			}
		}
	}
	private class RowListener2 implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			ListSelectionModel lsm = (ListSelectionModel) event.getSource();
			currentChoosed2 = lsm.getMinSelectionIndex();
			if (event.getValueIsAdjusting()) {
				return;
			}
		}
	}
	class SerachList extends AbstractTableModel {

		private String[] columnNames = { "ID", "Nazwa", "Producent", "Typ" };
		// Wywolujemy bezposrednio, normalnie posrednio przez serwer ?
		private LinkedList<Object[]> data;

		public SerachList(LinkedList<Object[]> data) {
			this.data = data;
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			if (data == null)
				return 0;
			return data.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			if (data == null)
				return 0;
			return data.get(row)[col];
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public void setModel(LinkedList<Object[]> collect) {
			this.data = collect;
		}

		private void printDebugData() {
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++) {
				System.out.print("    row " + i + ":");
				for (int j = 0; j < numCols; j++) {
					System.out.print("  " + data.get(i)[j]);
				}
				System.out.println();
			}
			System.out.println("--------------------------");
		}
	}

	class Koszyk extends AbstractTableModel {

		private String[] columnNames = { "ID", "Nazwa", "Producent", "Typ" };
		// Wywolujemy bezposrednio, normalnie posrednio przez serwer ?
		private LinkedList<Object[]> dataModel;

		public Koszyk(LinkedList<Object[]> data) {
			this.dataModel = data;
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			if (dataModel == null)
				return 0;
			return dataModel.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			if(dataModel.size() == 0)
					return 0;
			return dataModel.get(row)[col];
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public void setModel(LinkedList<Object[]> collect) {
			this.dataModel = collect;
		}

		private void printDebugData() {
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++) {
				System.out.print("    row " + i + ":");
				for (int j = 0; j < numCols; j++) {
					System.out.print("  " + dataModel.get(i)[j]);
				}
				System.out.println();
			}
			System.out.println("--------------------------");
		}
	}

	public static void createAndShowGUI() {
		JFrame frame = new JFrame("Oszczedny Konsument");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ClientApp newContentPane = new ClientApp();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}