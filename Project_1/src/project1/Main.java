package project1;

import java.util.*;
import com.mysql.jdbc.PreparedStatement;
import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;


public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		// TODO Auto-generated method stub
	ConnectionManager obj=new ConnectionManager();
		Connection con=obj.connection();
		Statement statement=con.createStatement();
		Statement statement1=con.createStatement();
		Statement statement2=con.createStatement();
		
		//Statement statement=con.createStatement();
		Scanner sc=new Scanner(System.in);
		String username;
		String pswd;
		int pro_id;
		String pro_name;
		int min_qty;
		int pr = 0 ;
		int qty = 0 ;
		int pro;
		String agentname;
		String ag_pswd;
		String ch;
		int p = 0;
		int tr = 0;
		boolean flag=false;
		do
		{
			System.out.println("1.Admin Login");
			System.out.println("2.Agent Login");
			System.out.println("3.Exit");
			int ip=sc.nextInt();
			try {
			switch(ip)
			{
			 case 1:
			 {
				System.out.println("Enter admin name");
				username=sc.next();
				System.out.println("Enter password");
				pswd=sc.next();
			
			    ResultSet rs=statement.executeQuery("select admin_name,admin_pass from admin_login");
				while(rs.next())
					{
						if(rs.getString(1).equals(username) && (rs.getString(2).equals(pswd)))
						{do {
							
							System.out.println("1.Add Product");
							System.out.println("2.Inventory Details");
							System.out.println("3.Logout");
							int ip1=sc.nextInt();
							switch(ip1)
							{
				
							case 1:
							{
								try {
									System.out.println("*****************Products Details*****************");
									System.out.println("----------------------------------------------------");
									System.out.println("Enter product id:");
									 pro_id=sc.nextInt();
									 System.out.println("Enter product name:");
									 pro_name=sc.next();
									 System.out.println("Enter minimum quantity:");
									min_qty=sc.nextInt();
									System.out.println("Enter price:");
								    pr=sc.nextInt();
								    System.out.println("Enter quantity available:");
								    qty=sc.nextInt();
								    int cost=pr*qty;
								    								
								    statement2.execute("insert into admin(product_id,product_name,min_sell_qty,price,qty_available,total_cost) values('"+pro_id+"','"+pro_name+"','"+min_qty+"','"+pr+"','"+qty+"','"+cost+"')");
							    System.out.println("Value inserted");  
								}
								catch (SQLException e2) 
							     {
							
							       e2.printStackTrace();
		                         }
								  
							}	
							
							 	
							case 2:
							{
								try {
									  
									ResultSet rs50=statement1.executeQuery("select product_id,product_name,qty_available,price,total_cost from admin");
									System.out.println("*****************Inventory Details*****************");
									System.out.println("---------------------------------------------------------------------");
									System.out.println("Product id\tProduct Name\tQuantity\tPrice\tTotal Cost");
									System.out.println("---------------------------------------------------------------------");
							        if(rs50.next()) 
									{
									do {
										System.out.println(rs50.getInt("product_id")+"\t\t"+rs50.getString("product_name")+"\t\t"+rs50.getInt("qty_available")+"\t\t"+rs50.getInt("price")+"\t\t"+rs50.getInt("total_cost"));
									}while(rs50.next());
									}
								else {
									System.out.println("No result found");
								}
								}
								catch (SQLException e2) 
								    {
							        	//System.out.println("hii");
								       e2.printStackTrace();
			                         }
								
							}
								break;
							case 3:
							{
								System.exit(0);
							}
		                     break;
							}
							System.out.println("do you want to continue?(y/n)");
						   String s=sc.next();
						   if(s.equalsIgnoreCase("y")) {
							   flag=true;
						   }
						   else
						   {
							   flag=false;
						   }
						}while(flag);
						}
						
					
			}
			 }
		break;
			 
	case 2:
	{
		System.out.println("Enter agent name");
		agentname=sc.next();
		System.out.println("Enter password");
		ag_pswd=sc.next();
		
			ResultSet rs2=statement.executeQuery("select agent_name,agent_pass from agent_login");
			if(rs2.next())
			{
				if(rs2.getString(1).equals(agentname) && (rs2.getString(2).equals(ag_pswd)))
				{
					do {
						
					System.out.println("1.Buy / Sell");
					System.out.println("2.Show History");
					System.out.println("3.Logout");
					int ip2=sc.nextInt();
					switch(ip2)
					{
					 case 1:
					 {
						 Buy b = new Buy();
						 b.add();
						 
					 }
					 break;
					 case 2:
					 {
						 ResultSet rs4=statement.executeQuery("select * from history");
						 if(rs4.next())
						 {
							 do
						      {
							 System.out.println("User Name\t"+"Product ID\t"+"product name\t"+"buy/sell\t"+"quantity\t"+"total cost");
							 System.out.println("------------------------------------------------------------");
							 System.out.println(rs4.getString("username")+"\t\t"+rs4.getString("product_id")+"\t\t"+rs4.getString("product_name")+"\t\t"+rs4.getString("transaction")+"\t\t"+rs4.getString("quantity")+"\t\t"+rs4.getString("totalcost"));
						      }while(rs4.next());
		 
						 }
					 }
					 break;
					 case 3:
					 {
						 System.exit(0);
						 System.out.println("Thank you for coming..");
					 }
					 break;
					}
					System.out.println("do you want to continue?(y/n)");
					   String s=sc.next();
					   if(s.equalsIgnoreCase("y")) {
						   flag=true;
					   }
					   else
					   {
						   flag=false;
					   }
					}while(flag);
				}	  
					
			}
	       }
			
			}//switch
			}catch (ClassNotFoundException |SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			 }
			 }while(true);
}}