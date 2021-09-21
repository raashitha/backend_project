package hotel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;


public class Admin {
	
	public void userupdate() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelpro","root","root");
		Statement st=con.createStatement();
		
		Scanner sc=new Scanner(System.in);	

		String data3="update user set password=?,city=?  where userName=?";
		PreparedStatement stmt1=con.prepareStatement(data3);
		int status3=0;
		System.out.println("ENTER USERNAME TO UPDATE:");
		String r_name=sc.next();
		
		System.out.println("ENTER PASSWORD TO UPDATE:");
		String u_pass=sc.next();			
		System.out.println("ENTER CITY TO UPDATE:");
		String u_city=sc.next();
		
		   	   
		   	stmt1.setString(1, u_pass);
			stmt1.setString(2, u_city);
			stmt1.setString(3, r_name);
							
			
			status3=stmt1.executeUpdate();
			if(status3>0)
			{
				System.out.println("UPDATE USER DATA SUCCESFULLY!!");
			}
			else {
				System.out.println("PLEASE,UPDATE AGIN");
			}
			
		   }
		   
		
		
	
     public void reservupdate() throws Exception {
    	 
    	 Class.forName("com.mysql.cj.jdbc.Driver");
 		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelpro","root","root");
 		Statement st=con.createStatement();
 		
 		Scanner sc=new Scanner(System.in);
 		
    	 System.out.println("YOU CAN UPDATE RESERVATION DATA");
			String data4="UPDATE reservation set   startdate=?,enddate=?,numOfDays=?  WHERE roomId=? ";
			PreparedStatement stmt1_1=con.prepareStatement(data4);
			int status4=0;
			System.out.println("ENTER ROOM ID TO UPDATE:");
			int r_id=sc.nextInt();
			
			
			System.out.println("ENTER CHECK-IN  TO UPDATE:");
			String startdate=sc.next();
			System.out.println("ENTER CHECK-OUT TO UPDATE:");
			String enddate=sc.next();
			
		   LocalDate date1= LocalDate.parse(startdate);
		   LocalDate date2= LocalDate.parse(enddate);
		   int numOfDays = (int) ChronoUnit.DAYS.between(date1,date2);							  
		   System.out.println("total no .of days "+numOfDays);
		   
		   
		 
		   stmt1_1.setString(1, startdate);
		   stmt1_1.setString(2, enddate);							 
		   stmt1_1.setInt(3, numOfDays);
		   stmt1_1.setInt(4,r_id);
						  
		   status4=stmt1_1.executeUpdate(); 
						  
			   if(status4>0) 
				 {
					System.out.println(" RESERVATION IS UPDATED!!");
							  
				 } 					
				else
				  { 
						 System.out.println("NOT UPDATED!");
				  }
			
		}
		
     
	public void adminprocess() throws Exception{
        
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelpro","root","root");
		Statement st=con.createStatement();
		
		Scanner sc=new Scanner(System.in);		
		System.out.println("please enter number based on option:");
		
		System.out.println("ENTER 1, TO VIEW DATA ");
		System.out.println("ENTER 2, TO INSERT DATA ");
		System.out.println("ENTER 3, TO UPDATE DATA");
		System.out.println("ENTER 4, TO DELETE DATA ");
		System.out.println("ENTER 5, TO  EXIT ");
		int enter1=sc.nextInt();
		
		switch(enter1)
		{   
		case 1:
			System.out.println("ENTER 1, TO VIEW RECORD");
			int a_view=sc.nextInt();
		   if(a_view ==1)
		   {
			   
			  
			   ResultSet rs_v=st.executeQuery(" select u.userName,u.password,u.Name,u.age,u.gender,u.city,u.govermentId,r.roomId, r.startDate, r.endDate,r.numOfDays,r.totalCost from user as u inner join reservation as r on u.username=r.username");			      
					while(rs_v.next())
					{
						 System.out.println(rs_v.getString(1)+" "+rs_v.getString(2)+" "+rs_v.getString(3)+" "+rs_v.getInt(4)+" "+rs_v.getString(5)+" "+rs_v.getString(6)+" "+rs_v.getString(7)+" "+rs_v.getInt(8)+" "+rs_v.getString(9)+" "+rs_v.getString(10)+" "+rs_v.getString(11)+" "+rs_v.getInt(12) );
					} 
		   }
		  else {
				System.out.println("ENTER 1 TO  VIEW DATA ");
			}
		
		   adminprocess();
		   break;
		
		case 2:
			    
				System.out.println("ENTER 1, TO INSERT USER DATA OR ENTER 2, TO INSERT RESERVAION DATA");
				int a_insert=sc.nextInt();
				
				if(a_insert ==1)
				{
					LoginSingup ls=new LoginSingup();
					ls.signup();
				}else if(a_insert ==2)
				{
					User u=new User();
					u.book();
				}
				else {
					System.out.println("ENTER 1 OR 2  INSERT DATA ");
				}
				
				adminprocess();
				
		break;
		
		case 3:
		 
			System.out.println("ENTER 1, TO UPDATE USER DATA OR ENTER 2, TO UPDATE RESERVAION DATA");
			int enter2=sc.nextInt();
			
			if(enter2 ==1)
			{
				userupdate();
				
			}
			else if(enter2==2)
			{
				reservupdate();
			}
			else {
				System.out.println("ENTER 1 TO  UPDATE DATA ");
			}
			    adminprocess();					
		break; 
				
		
		case 4:
			System.out.println("ENTER 1, TO DELETE RECORD");
			int a_del=sc.nextInt();
			if(a_del ==1)
			{
				String data4_1="DELETE FROM  user  WHERE userName=?";
				PreparedStatement stmt2=con.prepareStatement(data4_1);
				int status4_1=0;
				
				System.out.println("PLEASE,ENTER USERNAME TO DELETE :");
			    String username=sc.next();						    
			    stmt2.setString(1,username);
				
				status4_1=stmt2.executeUpdate();							
					 if(status4_1>0)
					 {
					    System.out.println("RECORD DELETED"); 
					 } 
					 else {
						System.out.println("RECORD NOT DELETED"); 
						  }
			}			
			else {
				System.out.println("ENTER 1 TO DELETE DATA ");
			}
			
			adminprocess();
			break;
			
		case 5:
			System.out.println("THANK YOU!!");
			break;
		}	   		              
	}
}
