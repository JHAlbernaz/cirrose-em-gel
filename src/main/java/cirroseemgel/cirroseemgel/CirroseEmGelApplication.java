package cirroseemgel.cirroseemgel;

import cirroseemgel.cirroseemgel.db.DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class CirroseEmGelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CirroseEmGelApplication.class, args);
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from usuario");
			while (rs.next()) {
				System.out.println(rs.getString("id") + ", " + rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeConnection();
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
