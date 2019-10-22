package project1;
import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;

public class Buy {
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
	Scanner sc=new Scanner(System.in);
	public void add() throws ClassNotFoundException, SQLException, NumberFormatException, IOException
	{
		// TODO Auto-generated method stub
		
		
		ConnectionManager obj=new ConnectionManager();
		Connection con=obj.connection();
		Statement statement=con.createStatement();
		
		int q1 = 0;
		int min = 0;
		int t;
		int quant = 0;
		int tot;
		int newq;
		int ncst;
		String pn = null;
		System.out.println("Enter product id");
		  pro = sc.nextInt();
			 ResultSet rs3=statement.executeQuery("select * from admin where product_id='"+pro+"'");
		  if(rs3.next())
		  {
			  p=rs3.getInt("price");
			   pn=rs3.getString("product_name");
			   q1=rs3.getInt("qty_available");
			   min=rs3.getInt("min_sell_qty");
			  t=rs3.getInt("total_cost");
			  System.out.println("Product name:  "+pn);
			  System.out.println("Price:\t"+p);
			  System.out.println("Cost:\t"+t);
		  
		      System.out.println("Enter your option buy or sell?");
		      ch=sc.next();
		      if(ch.equalsIgnoreCase("buy"))
		      {
				  System.out.println("Enter quantity:");
				   quant=sc.nextInt();
	                if(min>quant)
              		{
            	  		System.out.println("need minimum sell quantity");
	                    if(quant>q1)
			              {
			        	  System.out.println("quantity is not available");
			              }
              		} 
		           else
                   {
        	         tot=p*quant;
        	         statement.execute("insert into history values("+pro+",'"+pn+"','"+ch+"',"+quant+","+tot+")");
        	         newq=q1-quant;
        	         ncst=newq*p;
        	         statement.executeUpdate("update admin set qty_available="+newq+" where product_id='"+pro+"'");
        	         statement.executeUpdate("update admin set total_cost="+ncst+" where product_id='"+pro+"'");
                  }
		      }
		  if(ch.equalsIgnoreCase("sell"))
		  {
			  System.out.println("Enter quantity:");
			   quant=sc.nextInt();
    
        	   tot=p*quant;
        	   newq=q1+quant;
        	   ncst=newq*p;
        	  System.out.println("New quantity is: "+newq);
        	  statement.execute("insert into history values("+pro+",'"+pn+"','"+ch+"',"+quant+","+tot+")");
        	  statement.executeUpdate("update admin set qty_available="+newq+" where product_id='"+pro+"'");
        	  statement.executeUpdate("update admin set total_cost="+ncst+" where product_id='"+pro+"'");  
		  }
		  }
	}

}
