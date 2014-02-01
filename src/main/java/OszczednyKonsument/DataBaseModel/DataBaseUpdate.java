package OszczednyKonsument.DataBaseModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import OszczednyKonsument.DataBase.Database;

public class DataBaseUpdate {

	public boolean insertKlient(String imie, String nazwisko, int wiek,
			String adres, String miasto, String kod_pocztowy, String nick,
			Date data_rejestracji, String haslo, String email) {
		try {
			PreparedStatement prepStmt = Database.getConnection()
					.prepareStatement(
							"insert into czytelnicy values (NULL, ?, ?, ?);");
			prepStmt.setString(1, nazwisko);
			prepStmt.setString(2, imie);
			prepStmt.setInt(3, wiek);
			prepStmt.setString(4, adres);
			prepStmt.setString(5, miasto);
			prepStmt.setString(6, kod_pocztowy);
			prepStmt.setString(7, nick);
			prepStmt.setDate(8, data_rejestracji);
			prepStmt.setString(9, haslo);
			prepStmt.setString(10, email);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawianiu klienta");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
