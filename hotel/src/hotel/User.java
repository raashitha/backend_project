package hotel;
import java.sql.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class User {
		
	public void book() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelpro","root","root");
		Statement st=con.createStatement();
		
		 System.out.println("FOR BOOKING:");							 							 
		 String data1="INSERT INTO reservation(roomId,userName,startDate,endDate,numOfDays)VALUES(?,?,?,?,?)";
		 PreparedStatement stmt=con.prepareStatement(data1);
		 int status1=0;
		 
		 Scanner sc=new Scanner(System.in);
		 System.out.println("PLEASE ENTER ROOMID: ");
		 int id=sc.nextInt();
	     System.out.println("PLEASE ENTER USERNAME: ");
		 String name1=sc.next();																						 
		 System.out.println("PLEASE ENTER CHECK-IN");
		 String startdate=sc.next();
		 System.out.println("PLEASE ENTER CHECK-OUT");
		 String enddate=sc.next();
		  
		 LocalDate date1= LocalDate.parse(startdate);
		 LocalDate date2= LocalDate.parse(enddate);
		 int numOfDays = (int) ChronoUnit.DAYS.between(date1,date2);							  
		 System.out.println("Total noOfDays "+numOfDays);
		 
					       
		stmt.setInt(1, id);
	    stmt.setString(2, name1);
	    stmt.setString(3, startdate);
		stmt.setString(4, enddate);							 
	    stmt.setInt(5, numOfDays);
	    
			   
	    status1=stmt.executeUpdate(); 
					  
		   if(status1>0) 
			 {
				System.out.println("ROOM IS RESERVED!!");
						  
			 } 					
			else
			  { 
					 System.out.println("NOT RESERVED,TRY AGAIN");
			  }
	}
	
	public void reservation() throws Exception{
		User h1=new User();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelpro","root","root");
		Statement st=con.createStatement();
		Scanner sc=new Scanner(System.in);
		

		System.out.println("PLEASE ENTER NUMBER:");
		System.out.println("ENTER 1, FOR ROOM DETAIL");
		System.out.println("ENTER 2, FOR ROOM RESERVATION");				
		System.out.println("enter 3,BILLING");
		System.out.println("ENTER 4, FOR EXIT");
		int res=sc.nextInt();
		
		switch(res)
		{
		case 1:
																					
			ResultSet rs=st.executeQuery("SELECT * FROM room ");
			System.out.println("THE ROOMS ARE : ");
			System.out.println("id cost typeOfRoom");
							      
				while(rs.next())
				{
					 System.out.println(rs.getInt(1)+" "+rs.getDouble(2)+" "+rs.getString(3) );
				}
				
		reservation();
		
		break;
		
	   case 2:
		   
		   book();
		   reservation();
		   
	   
	   
	   break;	
	   
	   case 3:
		   
		  
		  ResultSet rs1=st.executeQuery(" SELECT r.roomid,rs.numOfDays*r.costPerNight AS totalCost FROM reservation as rs INNER JOIN room as r ON r.roomid = rs.roomid ");
			System.out.println("id amount");	  
		   while(rs1.next())
			{
				 System.out.println(rs1.getInt(1)+" "+rs1.getInt(2));
			
			}
						
		   reservation();
	    break;
	    
        case 4:
		   
		   System.out.println("THANK YOU");
		   
	    break;
	    
	    
		}   
	}
	
	public static void main(String[] args) throws Exception{
		
		User h=new User();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelpro","root","root");
		Statement st=con.createStatement();
		
		try {
			Scanner sc=new Scanner(System.in);
			System.out.println("WELCOME");
			System.out.println("ENTER 1,IF YOU ARE CUSTOMER OR ENTER 2, YOU ARE ADMIN ");
			int i=sc.nextInt();
			
			if(i==1)
			{
				System.out.println("THANK YOU FOR CHOOSING OUR SERVICE!!");
				System.out.println("PLEASE ENTER 1 TO LOGIN OR ENTER 2, TO SING-UP");
				int enter=sc.nextInt();
				
					switch(enter)
					{
					case 1:
						LoginSingup ls=new LoginSingup();
						ls.login();
						break;
						
					case 2:
						
						LoginSingup ls1=new LoginSingup();
						ls1.signup();	
																
						break;						
					}
					h.reservation();					
} 
else if(i ==2)
	{     				
				System.out.println("WELCOME ADMIN");
				System.out.println("PLEASE ENTER 1, FOR LOG-IN ");
				int enter=sc.nextInt();				
						switch(enter)
						{
						  case 1:
							
							System.out.println("PLEASE ENTER USERNAME: ");
							String username=sc.next();
							
							System.out.println("PLEASE ENTER PASSWORD : ");
							String password=sc.next();
							
							ResultSet rs=st.executeQuery("SELECT * FROM Adminsec WHERE username='"+ username +"' and Password='"+ password +"' ");
								if(rs.next())
								{
									System.out.println("WELCOME  ADMIN");
									Admin a=new Admin();
									a.adminprocess();
								}else 
								{
									System.out.println("INVALID USERNAME OR PASSWORD,PLEASE CHECK AGAIN");
									
								}
							break;
                        }					
	
			}
			else {
				System.out.println("PLEASE ENTER CORRECT NUMBER");
			}
		}		
	catch(Exception e)
	{
	 System.out.println(e);
	}
		
}
}

