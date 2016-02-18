import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.todo;

public class mysqlclient{
public mysqlconnector connector;
//public mysqlconnector connector2;

	public mysqlclient(){
		connector = new mysqlconnector();
	}
	
	public void runQueries() throws SQLException{

		List<todo> a = connector.getId();
		//boolean success;
		for(todo b : a){
			System.out.println(b.getId() +" " +b.getTodomsg());
			//success = connector2.insertUser(b);
		}
		
		 
	}
	
	public void runQueries_msg() throws SQLException{

		List<todo> a = connector.getId();
		for(todo b : a){
			System.out.println("msg : "+ b.getTodomsg());
		}
	}
	
	public void CreateTable(String name) throws SQLException{
		//connector.createTable("user2");
		connector.createTable(name);	
		
			System.out.println("Executed mysql command! Check your database!");
		
		
	}
	
	
	public void insertUser(todo user) {
		boolean success = connector.insertUser(user);
		if(!success){
			System.err.println("Insert failed");
		}else{
			System.out.println("Insert success!");
		}
	}
	
	public void DeleteUser(todo user) {
		boolean success = connector.DeleteUser(user);
		if(!success){
			System.err.println("Delete failed or user ID may not in DB");
		}else{
			System.out.println("Delete success!");
		}
	}
	
	
			
	
	public static void main (String[] arg) throws SQLException
	{
		
		
        

//		POST [id] [todo message]  Insert user and message
		
//		String timeStamp = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime());
//		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter the id: ");
//		String UserID = sc.next();
//		System.out.println("Enter the message: ");
//		sc.nextLine();
//		String todomsg = sc.nextLine();
//		
//		mysqlclient temp = new mysqlclient();
//		todo user = new todo();
//		user.setId(UserID);
//		user.setTimestamp(timeStamp);
//		user.setTodomsg(todomsg);
//		temp.insertUser(user);
//		
//	
	
		
		
// DELETE [id]	
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter the id to delete: ");
//		String UserID = sc.next();
//		
//		mysqlclient temp = new mysqlclient();
//		todo user = new todo();
//		user.setId(UserID);
//		temp.DeleteUser(user);
		
	
//		GET Display ID and message		
//		mysqlclient temp2 = new mysqlclient();
//		temp2.runQueries();

		
//		GET [i] Display message only		
//		mysqlclient temp3 = new mysqlclient();
//		temp3.runQueries_msg();
		
		
		
//REPLICATE[URI]
//		I'm not quiet understand how to do it. I tried a lot and coded something but It doesnt seem right.
		
		
		
		
// Create a new table if it doesn't exist			
		mysqlclient temp3 = new mysqlclient();
		temp3.CreateTable("user");
	
	}
		}

