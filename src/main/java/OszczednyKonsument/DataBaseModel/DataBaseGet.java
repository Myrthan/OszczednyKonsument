package OszczednyKonsument.DataBaseModel;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import OszczednyKonsument.DataBase.Database;

public class DataBaseGet {
	/**
	 * @param query
	 * @param rg
	 * @param rf
	 * @return returns list of objects returned by query
	 */
	public static <T> List<T> select(String query, ReadGetter<T> rg,
			ReadFilter<T> rf) {
		List<T> container = new LinkedList<T>();
		try {
			ResultSet result = Database.getConnection().prepareStatement(query)
					.executeQuery();
			rg.takeResultSet(result);
			T ref;
			while (result.next()) {
				ref = rg.read();
				if (rf == null || rf.accept(ref))
					container.add(ref);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		Database.closeConnection();
		return container;
	}

	public static List<Produkt> selectProdukty() {
		return select("select * from produkty", new ReadGetter<Produkt>() {
			@Override
			public Produkt read() {
				try {
					return new Produkt(rs.getInt("id_produkt"), rs
							.getString("nazwa"), rs.getString("producent"), rs
							.getString("typ"));
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}, null);
	}

	public static List<Opinia> selectOpinie(Integer id_produkt) {
		return select("select nick,komentarz,ocena,data from opinie o "
				+ "join klienci k on o.klient=k.id_klient where o.produkt="
				+ id_produkt.toString() + ";", new ReadGetter<Opinia>() {
			@Override
			public Opinia read() {
				try {
					return new Opinia(rs.getString("nick"), rs
							.getString("komentarz"), rs.getInt("ocena"),rs.getTimestamp("data"));
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}, null);
	}

	public static List<Opinia> selectOpinie(String nazwa_prod) {
		return select("select nick,komentarz,ocena,data from opinie o "
				+ "join klienci k on o.klient=k.id_klient join produkty p on "
				+ "o.produkt=p.id_produkt and p.nazwa='" + nazwa_prod + "';",
				new ReadGetter<Opinia>() {
					@Override
					public Opinia read() {
						try {
							return new Opinia(rs.getString("nick"), rs
									.getString("komentarz"), rs.getInt("ocena"), rs.getTimestamp("data"));
						} catch (SQLException e) {
							e.printStackTrace();
							return null;
						}
					}
				}, null);
	}

	public static List<Recenzja> selectRecenzje(Integer id_prod) {
		return select(
				"select recenzja, autor from recenzje r where r.id_produkt="
						+ id_prod.toString() + ";", new ReadGetter<Recenzja>() {
					@Override
					public Recenzja read() {
						try {
							return new Recenzja(rs.getString("recenzja"), rs
									.getString("autor"));
						} catch (SQLException e) {
							e.printStackTrace();
							return null;
						}
					}
				}, null);
	}

	public static List<Recenzja> selectRecenzje(String nazwa_prod) {
		return select(
				"select recenzja, autor from recenzje r join produkty p on r.id_produkt=p.id_produkt "
						+ "and p.nazwa='" + nazwa_prod + "';",
				new ReadGetter<Recenzja>() {
					@Override
					public Recenzja read() {
						try {
							return new Recenzja(rs.getString("recenzja"), rs
									.getString("autor"));
						} catch (SQLException e) {
							e.printStackTrace();
							return null;
						}
					}
				}, null);
	}

	/*public static List<Recenzja> selectProduktyDoSprzedazy(Integer id_prod) {
		return select(
				"select id_pds,cena,id_produ from recenzje r join produkty p on r.id_produkt=p.id_produkt "
						+ "and p.nazwa='" + nazwa_prod + "';",
				new ReadGetter<Recenzja>() {
					@Override
					public Recenzja read() {
						try {
							return new Recenzja(rs.getString("recenzja"), rs
									.getString("autor"));
						} catch (SQLException e) {
							e.printStackTrace();
							return null;
						}
					}
				}, null);
	}*/

	private static class KlientAuth {
		Integer id_klient;
		String login;
		byte[] passwd;

		public KlientAuth(Integer id,String login, byte[] passwd) {
			this.id_klient=id;
			this.login = login;
			this.passwd = passwd;
		}
	}

	private static byte[] toBytes(char[] chars) {
		CharBuffer charBuffer = CharBuffer.wrap(chars);
		ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
		byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
				byteBuffer.position(), byteBuffer.limit());
		Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
		Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
		return bytes;
	}

	public static Integer checkPassword(String login, char[] passwd) {
		List<KlientAuth> myList = select(
				"select id_klient,nick, hasło from klienci where nick='" + login + "';",
				new ReadGetter() {
					@Override
					public KlientAuth read() {
						try {
							return new KlientAuth(rs.getInt("id_klient"),rs.getString("nick"), rs
									.getBytes("hasło"));
						} catch (SQLException e) {
							e.printStackTrace();
							return null;
						}
					}
				}, null);
		if (myList.size() > 0
				&& Arrays.equals(toBytes(passwd), myList.get(0).passwd))
			return myList.get(0).id_klient;
		return -1;
	}

	public static boolean checkIfNickExists(String nick) {
		List<Boolean> ans = select("select '" + nick
				+ "' in (select nick from klienci) as x;",
				new ReadGetter<Boolean>() {

					@Override
					public Boolean read() {
						// TODO Auto-generated method stub
						try {
							return rs.getBoolean("x");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return true;
					}
				}, null);
		if (ans.size() != 0) {
			return ans.get(0);
		}
		return false;
	}
}
