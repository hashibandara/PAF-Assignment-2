package model;
import java.sql.*;
public class User
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf", "root", "madhi99#");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertUser(String name, String contact, String email, String password)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into users(`id`,`name`,`contact`,`email`,`password`)"+ " values (?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, name);
 preparedStmt.setString(3, contact);
 preparedStmt.setString(4,email);
 preparedStmt.setString(5, password);
 // execute the statement
 preparedStmt.execute();
 con.close();
 
 String newUser = readUsers(); 
 output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}"; 
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
 System.err.println(e.getMessage());
 }
 return output;
 }
public String readUsers()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>User Name</th><th>Contact Number</th>" + "<th>Email</th>" + "<th>Password</th>" +"<th>Update</th><th>Remove</th></tr>";

 String query = "select * from users";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String id = Integer.toString(rs.getInt("id"));
 String name = rs.getString("name");
 String contact = rs.getString("contact");
 String email = rs.getString("email");
 String password = rs.getString("password");
 // Add into the html table
 output += "<tr><td>" + name + "</td>";
 output += "<td>" + contact + "</td>";
 output += "<td>" + email + "</td>";
 output += "<td>" + password + "</td>";
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' "
		 + "class='btnUpdate btn btn-secondary' data-itemid='" + id + "'></td>"
		 + "<td><input name='btnRemove' type='button' value='Remove' "
		 + "class='btnRemove btn btn-danger' data-itemid='" + id + "'></td></tr>"; 
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the user.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updateUser(String id, String name, String contact, String email, String password)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE users SET name=?,contact=?,email=?,password=?WHERE id=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, name);
preparedStmt.setString(2, contact);
preparedStmt.setString(3, email);
preparedStmt.setString(4, password);
preparedStmt.setInt(5, Integer.parseInt(id));
// execute the statement
preparedStmt.execute();
con.close();
String newUser = readUsers(); 
output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}"; 
}
catch (Exception e)
{
output =  "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}"; 
System.err.println(e.getMessage());
}
return output;
}
public String deleteUser(String id)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for deleting."; }
// create a prepared statement
String query = "delete from users where id=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, Integer.parseInt(id));
// execute the statement
preparedStmt.execute();
con.close();
String newUser = readUsers(); 
output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}"; 
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\": \"Error while deleting the User.\"}"; 

System.err.println(e.getMessage());
}
return output;
}
} 
