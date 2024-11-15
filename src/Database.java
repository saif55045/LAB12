import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private Connection conn;
	public Database()
	{
		try{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb","root","");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public Student getstudentbyid(int id)
	{
		String query="Select * from student where id = ?";
		try
		{
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("major"),
                        rs.getTimestamp("timestamp")
                );
			}
			else
				return null;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
