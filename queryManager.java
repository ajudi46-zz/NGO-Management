import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class queryManager {

	private static queryManager sQueryManager;
	private Connection mySQLConnection;
	private PreparedStatement checkUser,addEvent,selectEvents,selectEventCount,addUser,selectUsers,addSponsor;
	private queryManager() throws SQLException
	{
		mySQLConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ce_1", "root", "hahalolxd");
		PreparedStatement createpwdTable = mySQLConnection.prepareStatement("CREATE TABLE users (username varchar(50) primary key,password varchar(50))");
		PreparedStatement insertAdmin = mySQLConnection.prepareStatement("INSERT INTO users values('admin','admin')");
		PreparedStatement insertStaff = mySQLConnection.prepareStatement("INSERT INTO users values('james','noice')");
		PreparedStatement pwdTableCheck = mySQLConnection.prepareStatement("SELECT *  FROM information_schema.tables WHERE table_schema = 'ce_1'   AND table_name = 'users' LIMIT 1") ;
		PreparedStatement eventTableCheck = mySQLConnection.prepareStatement("SELECT *  FROM information_schema.tables WHERE table_schema = 'ce_1'   AND table_name = 'events' LIMIT 1") ;
		PreparedStatement sponsorTableCheck = mySQLConnection.prepareStatement("SELECT *  FROM information_schema.tables WHERE table_schema = 'ce_1'   AND table_name = 'sponsors' LIMIT 1") ;
		PreparedStatement createEventTable = mySQLConnection.prepareStatement("CREATE TABLE events (event_id int PRIMARY KEY NOT NULL AUTO_INCREMENT,name varchar(50),eDate Date,location varchar(50),rvol int,totalParticipants int DEFAULT 0)");
		PreparedStatement createSponsorTable = mySQLConnection.prepareStatement("CREATE TABLE sponsors (name varchar(50),amount int,event_id int references events(event_id))");
		if(pwdTableCheck.executeQuery().isBeforeFirst() == false)
		{
			createpwdTable.executeUpdate();
			insertAdmin.executeUpdate();
			insertStaff.executeUpdate();
		}
		
		if(eventTableCheck.executeQuery().isBeforeFirst() == false)
		{
			createEventTable.executeUpdate();
		}
		
		if(sponsorTableCheck.executeQuery().isBeforeFirst() == false)
		{
			createSponsorTable.executeUpdate();
		}
		
		checkUser = mySQLConnection.prepareStatement("SELECT * FROM users WHERE username=? and password=?");
		addUser = mySQLConnection.prepareStatement("INSERT INTO users values(?,?)");
		addEvent = mySQLConnection.prepareStatement("INSERT INTO events(name,eDate,location,rvol) values(?,?,?,?)");
		selectEvents = mySQLConnection.prepareStatement("SELECT * FROM events where name like ?");
		selectEventCount = mySQLConnection.prepareStatement("SELECT COUNT(*) FROM events where name like ?");
		selectUsers = mySQLConnection.prepareStatement("SELECT * FROM users where username=?");
		addSponsor = mySQLConnection.prepareStatement("INSERT INTO sponsors values(?,?,?)");
		
	}
	
	
	public static queryManager getQueryManager() throws SQLException
	{
		if(sQueryManager == null)
		{
			sQueryManager = new queryManager();
		}
		return sQueryManager;
	}
	
	public boolean isUser(String user,String pwd) throws SQLException
	{
		checkUser.setString(1, user);
		checkUser.setString(2, pwd);
		return checkUser.executeQuery().isBeforeFirst();
	}
	
	@SuppressWarnings("deprecation")
	public void createEvent(String name,String day,String month,String year,String Location,String requiredVolunteers) throws NumberFormatException, SQLException
	{
		addEvent.setString(1, name);
		addEvent.setDate(2, new Date(Integer.parseInt(year)-1900,Integer.parseInt(month)-1,Integer.parseInt(day)));
		addEvent.setString(3, Location);
		addEvent.setInt(4, Integer.parseInt(requiredVolunteers));
		addEvent.executeUpdate();
	}
	
	public void createUser(String username,String password) throws SQLException
	{
		addUser.setString(1, username);
		addUser.setString(2, password);
		addUser.executeUpdate();
	}
	public void createSponsor(String name,String amount,String event_id) throws SQLException
	{
		addSponsor.setString(1, name);
		addSponsor.setInt(2, Integer.parseInt(amount));
		addSponsor.setInt(3, Integer.parseInt(event_id));
		addSponsor.executeUpdate();
	}

	public int getEventCount(String searched) throws SQLException
	{
		selectEventCount.setString(1, "%" + searched + "%");
		ResultSet rs = selectEventCount.executeQuery();
		if(rs.isBeforeFirst() == false)
		{
			return 0;
		}
		else
		{
			rs.next();
			return rs.getInt(1);
		}
	}
	public ResultSet getEvents(String searched) throws SQLException {
		// TODO Auto-generated method stub
		selectEvents.setString(1, "%" + searched + "%");
		return selectEvents.executeQuery();
	}
	
	public String getUser(String username) throws SQLException
	{
		selectUsers.setString(1, username);
		ResultSet rs = selectUsers.executeQuery();
		if(rs.isBeforeFirst()==false)
		{
			return "";
		}
		else
		{
			rs.next();
			return rs.getString(1);
		}
		
	}
}
