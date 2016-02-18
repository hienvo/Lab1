import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Timestamp;
import java.util.Date;
import model.todo;

public class mysqlconnector {
	
	public mysqlconnector() {
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("JDBC driver registered");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private Connection getConnection() {
		try {
			Connection conn = DriverManager
					.getConnection("jdbc:mysql://127.0.0.1/lab1?" + "user=root&password=054810407");

			System.out.println("Got Mysql database connection");
			return conn;
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return null;
	}
	
	public List<todo> getId() throws SQLException
	{
		List<todo> a = new ArrayList<todo>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try{
		con = getConnection();	
		stmt = con.prepareStatement("select * from user;");
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			todo b = new todo();
			b.setId(rs.getString("id"));
			b.setTodomsg(rs.getString("todomsg"));
			b.setTimestamp(rs.getString("time"));
			//System.out.println(rs.getString("id"));
			a.add(b);
		}
		return a;
		}
		
		catch(SQLException ex){ 
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		 finally {

				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException sqlEx) {
					} // ignore

					rs = null;
				}

				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException sqlEx) {
					} // ignore

					stmt = null;
				}
				if(con != null){
					try {
						con.close();
					} catch (SQLException sqlEx) {
					} // ignore

					con = null;
				}

			}
			return null;
		}
	
	public boolean insertUser(todo user) {
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		Connection con = null;
		List<todo> a = new ArrayList<todo>();
		String cmd;
		try {
			// Get the connection to the database
			con = getConnection();

			// Execute the query
			stmt = con.prepareStatement("select * from user;");
			rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				todo b = new todo();
				b.setId(rs.getString("id"));
				b.setTodomsg(rs.getString("todomsg"));
				b.setTimestamp(rs.getString("time"));
				//System.out.println(rs.getString("id"));				
				a.add(b);
				
			}
			//String s = user.getId();
			//System.out.println(a);
			//System.out.println(user);
			  
			for (todo b : a){
				
				//if (b1.getId().contains(user.getId()))
				if (b.getId().contains(user.getId()))
				{
					//System.out.println("user in list");
					cmd = "UPDATE user SET todomsg=?, time=? WHERE id=?;";
					stmt1 = con.prepareStatement(cmd);
					stmt1.setString(1, user.getTodomsg());
					stmt1.setString(2, user.getTimestamp());
					stmt1.setString(3, user.getId());
					
					//System.out.println(cmd);
					break;
				}
				
				else
					
				{
					//System.out.println("user not in list");
					cmd = "insert into user values(?,?,?)";
					stmt1 = con.prepareStatement(cmd);
					stmt1.setString(1, user.getId());
					stmt1.setString(2, user.getTodomsg());
					stmt1.setString(3, user.getTimestamp());
					//System.out.println(cmd);
				}
			}
				
			return stmt1.executeUpdate() > 0;

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException sqlEx) {
				} // ignore

				con = null;
			}

		}
		return false;
	}
	
	public boolean DeleteUser(todo user) {
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		Connection con = null;
		List<todo> a = new ArrayList<todo>();
		String cmd;
		try {
			// Get the connection to the database
			con = getConnection();

			// Execute the query
			stmt = con.prepareStatement("select * from user;");
			//stmt.setString(1, user.getId());
			rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				todo b = new todo();
				b.setId(rs.getString("id"));
				b.setTodomsg(rs.getString("todomsg"));
				b.setTimestamp(rs.getString("time"));
				//System.out.println(rs.getString("id"));				
				a.add(b);
				
			}
			
			//String s = user.getId();
			//System.out.println(a);
			//System.out.println(user);
			
			
			for (todo b : a)
			{
				
				//if b1.getId().contains(user.getId()))
				if (b.getId().contains(user.getId()))
				{
					cmd = "DELETE FROM user WHERE id = ?;";
					stmt1 = con.prepareStatement(cmd);
					stmt1.setString(1, user.getId());
							
				}
				else 
				{
//					System.out.println("User ID doesn't exist in DB");
					return false;
				}
				return stmt1.executeUpdate() > 0;
			
			//return stmt1.execute();
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException sqlEx) {
				} // ignore

				con = null;
			}

		}
		return false;
	}
	
	

//	public boolean replicate(todo user) {
//		PreparedStatement stmt = null;
//		PreparedStatement stmt1 = null;
//		ResultSet rs = null;
//		Connection con = null;
//		List<todo> a = new ArrayList<todo>();
//		String cmd;
//		try {
//			// Get the connection to the database
//			con = getConnection();
//
//			// Execute the query
//			stmt = con.prepareStatement("select * from user;");
//			rs = stmt.executeQuery();
//			
//			
//			while (rs.next()) {
//				todo b = new todo();
//				b.setId(rs.getString("id"));
//				b.setTodomsg(rs.getString("todomsg"));
//				b.setTimestamp(rs.getString("time"));
//				//System.out.println(rs.getString("id"));				
//				a.add(b);
//				
//			}
//			
////			cmd ="CREATE SCHEMA ?";
////			stmt1 = con.prepareStatement(cmd);
//			
//			
//									
//		} catch (SQLException ex) {
//			// handle any errors
//			System.out.println("SQLException: " + ex.getMessage());
//			System.out.println("SQLState: " + ex.getSQLState());
//			System.out.println("VendorError: " + ex.getErrorCode());
//		} finally {
//			// it is a good idea to release
//			// resources in a finally{} block
//			// in reverse-order of their creation
//			// if they are no-longer needed
//
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException sqlEx) {
//				} // ignore
//
//				rs = null;
//			}
//
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException sqlEx) {
//				} // ignore
//
//				stmt = null;
//			}
//			if(con != null){
//				try {
//					con.close();
//				} catch (SQLException sqlEx) {
//				} // ignore
//
//				con = null;
//			}
//
//		}
//		return false;
//	}
	
//	private Connection getConnection2() {
//		try {
//			Connection conn = DriverManager
//					.getConnection("jdbc:mysql://127.0.0.1/new_schema?" + "user=root&password=054810407");
//
//			System.out.println("Got Mysql database connection");
//			return conn;
//		} catch (SQLException ex) {
//			// handle any errors
//			System.out.println("SQLException: " + ex.getMessage());
//			System.out.println("SQLState: " + ex.getSQLState());
//			System.out.println("VendorError: " + ex.getErrorCode());
//		}
//		return null;
//	}

	
		public void createTable(String name) throws SQLException {
		    String sqlCreate = "CREATE TABLE IF NOT EXISTS " + name
		            + "  (id int(11), todomsg varchar(100), time varchar(45));";

		    PreparedStatement stmt = null;
		    Connection con = null;
		    con = getConnection();
		    stmt = con.prepareStatement(sqlCreate);		    
		}
	}
	

