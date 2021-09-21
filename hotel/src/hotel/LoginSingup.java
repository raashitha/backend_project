package hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class LoginSingup {
	//LoginSingup
	

public void login() throws Exception{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelpro","root","root");
		Statement st=con.createStatement();
		
		Scanner sc=new Scanner(System.in);
		System.out.println("PLEASE ENTER USERNAME : ");
		String username=sc.next();
		
		System.out.println("PLEASE ENTER PASSWORD : ");
		String password=sc.next();
		
		ResultSet rs=st.executeQuery("SELECT * FROM user WHERE username='"+ username +"' and Password='"+ password +"' ");
			if(rs.next())
			{
				System.out.println("WELCOME "+ username);
			}else 
			{
				System.out.println("INVALID USERNAME OR PASSWORD");
				System.out.println("KINDLY SING-UP FIRST");
				signup();
			}
	}
	public void signup() throws Exception{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelpro","root","root");
		Statement st=con.createStatement();
		
		Scanner sc=new Scanner(System.in);
		String data="INSERT INTO user(userName, password,Name, age, gender,city,govermentId ) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stmt=con.prepareStatement(data);
		int status=0;
		
		System.out.println("PLEASE ENTER A BELOW DETAIL !!!");
		System.out.println("PLEASE ENTER USERNAME :");
		String newUser=sc.next();
		System.out.println("PLEASE ENTER PASSWORD :");
		String newPass=sc.next();		
		System.out.println("please enter your Name :");
		String name=sc.next();		
		System.out.println("PLEASE ENTER AGE :");
		int age=sc.nextInt();		
		System.out.println("PLEASE ENTER GENDER :");
		String gender=sc.next();		
		System.out.println("PLEASE ENTER CITY:");
		String place=sc.next();		
		System.out.println("PLEASE ENTER GOVERMENT-ID :");
		String AadharNumbers=sc.next();
		   String strPattern = "^[1-9][0-9]{11}$";
		   
			if(AadharNumbers.matches(strPattern))
		       {
		    	   System.out.println("valid adhaar number");
		    	   
		    	      		  			       
		   		   
		       }
		       else {
		    	   System.out.println("-------invalid adharcard,please sign up again---------");
		    	   signup();
		       }
           
		
		   						
		stmt.setString(1, newUser);
		stmt.setString(2, newPass);
		stmt.setString(3, name);
		stmt.setInt(4, age);
		stmt.setString(5, gender);
		stmt.setString(6, place);
		stmt.setString(7, AadharNumbers);
		
		
		
		status=stmt.executeUpdate();
			if(status>0)
			{
				System.out.println("SING-UP SUCCESSFULLY!!");
			}
			else {
				System.out.println("PLEASE SING-UP AGAIN");
			}						
	}
}
