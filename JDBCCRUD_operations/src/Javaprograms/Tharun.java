package Javaprograms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class JDBCCRUD
{
    Scanner sc = new Scanner(System.in);
    static Connection conn = null;

    public JDBCCRUD() throws SQLException 
    {
        if (conn == null)
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/anudeep", "root", "Melophile@526");
        }
    }

    public void insertData() throws SQLException 
    {
        if (conn == null)
        {
            throw new SQLException("Connection is null.");
        }
        System.out.println("You Entered a Insertion Option, please do Operations::");
        System.out.println("================================================");

        String sql = "insert into students (id,name,marks,attendance)values(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        int rowsEffected;
        while(true)
        {
        System.out.println("enter id:");
        pstmt.setInt(1, sc.nextInt());
        System.out.println("enter name:");
        pstmt.setString(2, sc.next());
        System.out.println("enter marks:");
        pstmt.setInt(3, sc.nextInt());
        System.out.println("enter Attendance:");
        pstmt.setInt(4, sc.nextInt());
        
        rowsEffected=pstmt.executeUpdate();
        System.out.println("records are inserted..");
        System.out.println("if you want to add more records...enter [yes/no]");
        String choice=sc.next();
        if(choice.equalsIgnoreCase("no"))
        {
        	break;
        }
        
        if (rowsEffected > 0) 
        {
            System.out.println("records  are inserted sucessfully");
        } else
        {
            System.out.println("records  are not  inserted sucessfully");
        }
        
        
        }
        
        
        
        
        
    }

    public void selectData() throws SQLException 
    {
    	System.out.println("You Entered Select Operation, please do Operation::");
    	System.out.println("=============================================");
        if (conn == null)
        {
            throw new SQLException("Connection is null.");
        }

        String sql2 = "select * from students";
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql2);
        System.out.println("ID\tNAME\tMARKS\tATTENDENCE");
        while (res.next()) 
        {
            System.out.print(res.getInt("id")+" \t");
            System.out.print(res.getString("name")+" \t");
            System.out.print(res.getInt("marks")+" \t");
            System.out.print(res.getInt("attendance")+" \t");
            System.out.println();
           
           
        }
    }

    public void updateData() throws SQLException {
        if (conn == null) {
            throw new SQLException("Connection is null.");
        }

        String sql3 = "update students set name = ? where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql3);

        System.out.println("Enter name please::");
        String name = sc.next();

        System.out.println("Enter ID please::");
        int id = sc.nextInt();

        pstmt.setString(1, name);
        pstmt.setInt(2, id);

        int rowsEffected = pstmt.executeUpdate();

        if (rowsEffected > 0) {
            System.out.println("Data is updated successfully.");
        } else {
            System.out.println("Data is not updated successfully.");
        }
    }
    public void deleteData() throws SQLException
    {
    	System.out.println("You entered delete Operation, Please do Operation:");
    	System.out.println("=============================================");
        if (conn == null) 
        {
            throw new SQLException("Connection is null.");
        }
        
   
        String sql4 = "delete from students where id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql4);
        int rowsEffected;
        while(true)
        {
        System.out.println("enter id please::");
        int id = sc.nextInt();
       
        pstmt.setInt(1, id);
        rowsEffected = pstmt.executeUpdate();
        System.out.println("records are deletd..");
        System.out.println("if you want to delete more records...enter [yes/no]");
        String choice=sc.next();
        if(choice.equalsIgnoreCase("no"))
        {
        	break;
        }
        
        if (rowsEffected > 0)
        {
            System.out.println("data is deleted from row sucessfullly");
        } else 
        {
            System.out.println("data is not deleted from row sucessfullly");
        }
        }
    }
}

public class Tharun 
{
 public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException
 {
     Connection conn = null;
     JDBCCRUD c = new JDBCCRUD();

     try 
     {
         Class.forName("com.mysql.cj.jdbc.Driver");
         String url = "jdbc:mysql://localhost:3306/anudeep";
         String username = "root";
         String password = "Melophile@526";
         conn = DriverManager.getConnection(url, username, password);

         int option;

         System.out.println("Please Enter Your Option::");
         System.out.println("============================");
         System.out.println("1. Insert");
         System.out.println("2. Select");
         System.out.println("3. Update");
         System.out.println("4. Delete");
         System.out.println("============================");
         option = c.sc.nextInt();

         switch (option) 
         {
             case 1:
                 c.insertData();
                 break;

             case 2:
                 c.selectData();
                 break;

             case 3:
                 c.updateData();
                 break;

             case 4:
                 c.deleteData();
                 break;

             default:
                 System.out.println("Invalid option.");
          }
     } 
     catch(SQLException se)
     {
    	 se.printStackTrace();
     }
     catch(Exception e)
     {
    	 e.printStackTrace();
     }
     finally
     {
         if (conn != null) 
         {
             conn.close();
         }
     }
 }
}
/*outPut::
 * Please Enter Your Option::
============================
1. Insert
2. Select
3. Update
4. Delete
============================
1
You Entered a Insertion Option, please do Operations::
================================================
enter id:
1
enter name:
Tharun
enter marks:
80
enter Attendance:
80
records are inserted..
if you want to add more records...enter [yes/no]
yes
records  are inserted sucessfully
enter id:
2
enter name:
Saini
enter marks:
90
enter Attendance:
95
records are inserted..
if you want to add more records...enter [yes/no]
yes
records  are inserted sucessfully
enter id:
3
enter name:
Rajashekar
enter marks:
95
enter Attendance:
98
records are inserted..
if you want to add more records...enter [yes/no]
yes
records  are inserted sucessfully
enter id:
4
enter name:
priyanka
enter marks:
90
enter Attendance:
96
records are inserted..
if you want to add more records...enter [yes/no]
yes
records  are inserted sucessfully
enter id:
5
enter name:
Manaswini
enter marks:
90
enter Attendance:
90
records are inserted..
if you want to add more records...enter [yes/no]
yes
records  are inserted sucessfully
enter id:
6
enter name:
hygjk
enter marks:
89
enter Attendance:
90
records are inserted..
if you want to add more records...enter [yes/no]
no

Please Enter Your Option::
============================
1. Insert
2. Select
3. Update
4. Delete
============================
2
You Entered Select Operation, please do Operation::
=============================================
ID	NAME	MARKS	ATTENDENCE
1 	Tharun 	80 	80 	
2 	Saini 	90 	95 	
3 	Raju 	95 	98 	
4 	Priya 	90 	96 
5   Manu	89  88
6 	hygjk 	89 	90 	    

Please Enter Your Option::
============================
1. Insert
2. Select
3. Update
4. Delete
============================
3
Enter name please::
Nani
Enter ID please::
6
Data is updated successfully.   

Please Enter Your Option::
============================
1. Insert
2. Select
3. Update
4. Delete
============================
4
You entered delete Operation, Please do Operation:
=============================================
enter id please::
5  */

