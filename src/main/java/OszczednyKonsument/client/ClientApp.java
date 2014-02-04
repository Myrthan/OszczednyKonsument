package OszczednyKonsument.client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import OszczednyKonsument.DataBase.Database;
import OszczednyKonsument.DataBaseModel.DataBaseGet;
import OszczednyKonsument.DataBaseModel.Produkt;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

public class ClientApp extends JPanel {
	private boolean DEBUG = false;
	private List<Produkt> selectProdukty;
	private Object[][] data;
	public ClientApp() {
		super(new GridLayout(1, 0));
		
		//TEST dla produktow
		selectProdukty = DataBaseGet.selectProdukty();
		data = new Object[selectProdukty.size()][];
		for(int i = 0; i < selectProdukty.size(); i++ ){
			data[i] = selectProdukty.get(i).toObject();
		}
		JTable table = new JTable(new MyTableModel(data));
		
		
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);

		JScrollPane scrollPane = new JScrollPane(table);

		add(scrollPane);
	}

	class MyTableModel extends AbstractTableModel {
		private String[] columnNames = { "ID", "Nazwa", "Producent", "Typ" };
		// Wywolujemy bezposrednio, normalnie posrednio przez serwer ?
		private Object[][] data;

		public MyTableModel(Object[][] data) {
			this.data = data;
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {
			if (col < 2) {
				return false;
			} else {
				return true;
			}
		}

		public void setValueAt(Object value, int row, int col) {
			if (DEBUG) {
				System.out.println("Setting value at " + row + "," + col
						+ " to " + value + " (an instance of "
						+ value.getClass() + ")");
			}

			data[row][col] = value;

			if (DEBUG) {
				System.out.println("New value of data:");
				printDebugData();
			}
		}

		private void printDebugData() {
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++) {
				System.out.print("    row " + i + ":");
				for (int j = 0; j < numCols; j++) {
					System.out.print("  " + data[i][j]);
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