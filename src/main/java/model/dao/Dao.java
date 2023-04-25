package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Myynti;

public class Dao {
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement stmtPrep = null;
	private String sql;
	private String db = "Myynti.sqlite";

	private Connection yhdista() {
		Connection con = null;
		String path = System.getProperty("catalina.base");
		path = path.substring(0, path.indexOf(".metadata")).replace("\\", "/"); 
		String url = "jdbc:sqlite:" + path + db;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(url);
			System.out.println("Yhteys avattu.");
		} catch (Exception e) {
			System.out.println("Yhteyden avaus ep�onnistui.");
			e.printStackTrace();
		}
		return con;
	}
	private void sulje() {		
		if (stmtPrep != null) {
			try {
				stmtPrep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
				System.out.println("Yhteys suljettu.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<Myynti> getAllItems() {
		ArrayList<Myynti> myynnit = new ArrayList<Myynti>();
		sql = "SELECT * FROM asiakkaat ORDER BY asiakas_id ASC";
		try {
			con = yhdista();
			if (con != null) {
				stmtPrep = con.prepareStatement(sql);
				rs = stmtPrep.executeQuery();
				if (rs != null) { 
					while (rs.next()) {
						Myynti myynti = new Myynti();
						myynti.setAsiakas_id(rs.getInt(1));
						myynti.setEtunimi(rs.getString(2));
						myynti.setSukunimi(rs.getString(3));
						myynti.setPuhelin(rs.getString(4));
						myynti.setSposti(rs.getString(5));
						myynnit.add(myynti);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sulje();
		}
		return myynnit;
	}
	public ArrayList<Myynti> getAllItems (String searchStr) {
		ArrayList<Myynti> myynnit = new ArrayList<Myynti>();
		sql = "SELECT * FROM asiakkaat WHERE asiakas_id LIKE ? or etunimi LIKE ? OR sukunimi LIKE ? OR puhelin LIKE ? OR sposti LIKE ?";
		try {
			con = yhdista();
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setString(1,  "%" + searchStr + "%");
			stmtPrep.setString(2,  "%" + searchStr + "%");
			stmtPrep.setString(3,  "%" + searchStr + "%");
			stmtPrep.setString(4,  "%" + searchStr + "%");
			stmtPrep.setString(5,  "%" + searchStr + "%");
			rs = stmtPrep.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Myynti myynti = new Myynti();
					myynti.setAsiakas_id(rs.getInt(1));
					myynti.setEtunimi(rs.getString(2));
					myynti.setSukunimi(rs.getString(3));
					myynti.setPuhelin(rs.getString(4));
					myynti.setSposti(rs.getString(5));
					myynnit.add(myynti);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sulje();
		}
		return myynnit;
	}
	
	public boolean addItem(Myynti myynti) {
		boolean paluuArvo = true;
		sql = "INSERT INTO asiakkaat(etunimi, sukunimi, puhelin, sposti)VALUES(?,?,?,?)";
		try {
			con = yhdista();
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setString(1, myynti.getEtunimi());
			stmtPrep.setString(2, myynti.getSukunimi());
			stmtPrep.setString(3, myynti.getPuhelin());
			stmtPrep.setString(4, myynti.getSposti());
			stmtPrep.executeUpdate();		
		} catch (Exception e) {
			paluuArvo=false;
			e.printStackTrace();
		} finally {
			sulje();
		}
		return paluuArvo;
	}
	
	public boolean removeItem(int id) {
		boolean paluuArvo = true;
		sql = "DELETE FROM asiakkaat WHERE asiakas_id=?";
		try {
			con = yhdista();
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setInt(1, id);
			stmtPrep.executeUpdate();		
		} catch (Exception e) {
			paluuArvo=false;
			e.printStackTrace();
		} finally {
			sulje();
		}
		return paluuArvo;
	}
}
