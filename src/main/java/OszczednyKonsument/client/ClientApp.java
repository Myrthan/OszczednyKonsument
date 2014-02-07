package OszczednyKonsument.client;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import OszczednyKonsument.DataBaseModel.SerachResult;
import OszczednyKonsument.DataBaseModel.Sklep;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class ClientApp extends JPanel implements ActionListener {
	private boolean DEBUG = false;
	private List<Produkt> selectProdukty;
	private LinkedList<Object[]> data = new LinkedList<Object[]>();
	private LinkedList<Object[]> dataKoszyk = new LinkedList<Object[]>();
	private SerachList model;
	private Koszyk modelKoszyk;
	private int currentChoosed = -1;
	private int currentChoosed2 = -1;
	private static JFrame frame;
	public DataOutputStream out;
	public DataInputStream in;

	private ClientApp(DataInputStream in, DataOutputStream out) {
		super(new GridLayout());
		this.in = in;
		this.out = out;
		try {
			out.writeUTF("SELECTPRODUKTY");
			out.flush();

			int size = in.readInt();
			if (size == 0)
				data = null;
			else {
				int size2 = in.readInt();
				for (int i = 0; i < size; i++) {
					Object[] w = new Object[size2];
					for (int j = 0; j < size2; j++)
						w[j] = in.readUTF();
					data.add(w);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * selectProdukty = DataBaseGet.selectProdukty();
		 * 
		 * for (int i = 0; i < selectProdukty.size(); i++) {
		 * data.add(selectProdukty.get(i).toObject()); }
		 */
		model = new SerachList(data);
		JTable table = new JTable(model);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		table.setPreferredScrollableViewportSize(new Dimension(
				screenSize.width / 6, screenSize.height / 2));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		table.getSelectionModel().addListSelectionListener(new RowListener());

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JButton b1 = new JButton("Dodaj do koszyka");
		b1.setVerticalTextPosition(AbstractButton.BOTTOM);
		b1.setHorizontalTextPosition(AbstractButton.LEFT);
		b1.setMnemonic(KeyEvent.VK_M);
		b1.addActionListener(this);

		JLabel label = new JLabel("Menu", JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalTextPosition(JLabel.CENTER);
		// add(label);
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

		JButton b5 = new JButton("Opinie");
		b5.setAction(action);
		b5.setVerticalTextPosition(AbstractButton.VERTICAL);
		b5.setHorizontalTextPosition(AbstractButton.CENTER);
		b5.setMnemonic(KeyEvent.VK_M);
		b5.addActionListener(this);

		JButton b6 = new JButton("Recenzje");
		b6.setVerticalTextPosition(AbstractButton.VERTICAL);
		b6.setHorizontalTextPosition(AbstractButton.CENTER);
		b6.setMnemonic(KeyEvent.VK_M);
		b6.addActionListener(this);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel firstPanel = new JPanel();
		firstPanel.setLayout(new GridLayout(4, 4));
		firstPanel.setMaximumSize(new Dimension(400, 400));
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		b4.setAlignmentX(Component.CENTER_ALIGNMENT);
		b5.setAlignmentX(Component.CENTER_ALIGNMENT);
		b6.setAlignmentX(Component.CENTER_ALIGNMENT);
		firstPanel.add(b1);
		firstPanel.add(b2);
		firstPanel.add(b3);
		firstPanel.add(b4);
		firstPanel.add(b5);
		firstPanel.add(b6);
		panel.setOpaque(false);
		panel.add(firstPanel);
		add(panel);
		modelKoszyk = new Koszyk(dataKoszyk);
		JTable koszyk = new JTable(modelKoszyk);
		koszyk.setFillsViewportHeight(true);
		koszyk.setAutoCreateRowSorter(true);
		koszyk.getSelectionModel().addListSelectionListener(new RowListener2());
		add(koszyk);

	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Dodaj do koszyka")) {
			if (currentChoosed < 0)
				return;
			dataKoszyk.add(data.get(currentChoosed));
			modelKoszyk.fireTableDataChanged();
		} else if (command.equals("Wyszukaj")) {
			Set<Integer> w = new HashSet<Integer>();
			if (dataKoszyk.size() == 0)
				return;
			for (int i = 0; i < dataKoszyk.size(); i++)
				w.add((Integer) dataKoszyk.get(i)[0]);
			try {
				out.writeUTF("SERACHQUERY");
				out.flush();
				out.writeInt(w.size());
				out.flush();
				for (Integer r : w) {
					out.writeInt(r);
					out.flush();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<SerachResult> serachRes = null;
			// DataBaseGet.serachQuery(w);

			/*
			 * JFrame frame2 = new JFrame("Oszczedny Konsument");
			 * frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 * frame2.getContentPane().setLayout( new
			 * BoxLayout(frame2.getContentPane(), BoxLayout.Y_AXIS));
			 * 
			 * ShowResult newContentPane = new ShowResult(in,out);
			 * 
			 * frame2.pack(); frame2.setVisible(true);
			 */
			if (serachRes.size() > 0)
				JOptionPane.showMessageDialog(frame, new String(
						"TWOJ SKLEP MA ID: " + serachRes.get(0).id_sklep
								+ " KOSZT ZAKUPOW:"
								+ serachRes.get(0).resultSum));
			else
				JOptionPane
						.showMessageDialog(
								frame,
								new String(
										"W zadnym ze sklepow nie ma wszystkich produktow z listy."));
		} else if (command.equals("Wyczysc liste zakupow")) {
			dataKoszyk.clear();
			modelKoszyk.fireTableDataChanged();
		} else if (command.equals("Usun")) {
			if (currentChoosed2 < 0)
				return;
			dataKoszyk.remove(currentChoosed2);
			modelKoszyk.fireTableDataChanged();
		} else if (command.equals("Opinie")) {
			if (currentChoosed2 < 0)
				return;
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
			if (dataModel.size() == 0)
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

	public static void createAndShowGUI(DataInputStream in, DataOutputStream out) {
		frame = new JFrame("Oszczedny Konsument");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		ClientApp newContentPane = new ClientApp(in, out);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
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
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Opinie");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					if(currentChoosed!=-1)
						ProductPanel.createAndShowGUI(selectProdukty.get(currentChoosed).id_produkt, in, out);
				}
			});
		}
	}
}
