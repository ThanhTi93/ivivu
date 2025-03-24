package connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class BDworkben {
	private static String DB_URL = "jdbc:mysql://localhost:3306/ivivu";
	private static String USER_NAME = "root";
	private static String PASSWORD = "149162536";
	private static Connection conn = null;

	public static void main(String[] args) {
		try {
			getConnect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Member");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			}
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static Connection getConnection(String dbURL, String userName, String password) {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, userName, password);
			System.out.println("connect successfully!");
		} catch (Exception ex) {
			System.out.println("connect failure!");
			ex.printStackTrace();
		}
		return conn;
	}// mo connection 

	public static Connection getConnect() {
		try {
			if (Objects.isNull(conn) || conn.isClosed()) {
				return getConnection(DB_URL, USER_NAME, PASSWORD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}