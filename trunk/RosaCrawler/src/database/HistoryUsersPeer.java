package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 * Autogenerated by Lisptorq 0.1.4
 */
public class HistoryUsersPeer extends BaseHistoryUsersPeer
{
    public static void getCountList(long[] al)
    {
	try
	{
	    Calendar c = Calendar.getInstance();
//	    SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
//	    c.setTime(bartDateFormat.parse("20090112"));
	    int date = c.get(Calendar.YEAR) * 10000 + (c.get(Calendar.MONTH) + 1) * 100
		    + c.get(Calendar.DATE);
	    Statement statement;
	    Connection connection = Database.getConnection();
	    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
		    ResultSet.CONCUR_UPDATABLE);
	    ResultSet results;
	    String sql = "select * from ftpsearch.HistoryUsers where id>1 and id<6 or id=" + date;
	    results = statement.executeQuery(sql);
	    al[4] = -1;
	    while (results.next())
	    {
		if (results.getString(1).equals("2"))
		    al[0] = Integer.parseInt(results.getString(2)) + 1;
		if (results.getString(1).equals("3"))
		    al[1] = Integer.parseInt(results.getString(2)) + 1;
		if (results.getString(1).equals("4"))
		    al[2] = Integer.parseInt(results.getString(2)) + 1;
		if (results.getString(1).equals("5"))
		    al[3] = Integer.parseInt(results.getString(2)) + 1;
		if (results.getString(1).equals(Integer.toString(date)))
		    al[4] = Integer.parseInt(results.getString(2)) + 1;
	    }
	    if (al[4] == -1)
	    {
		sql = "insert into ftpsearch.HistoryUsers(id,count) values(" + date + ",0)";
		al[4] = 1;
		statement.execute(sql);
		sql = " id=" + date;
		if (c.get(Calendar.DATE) == 1)
		{
		    if (c.get(Calendar.MONTH) == 0)
		    {
			sql += " or id=3";
			al[1] = 1;
		    }
		    sql += " or id=4 ";
		    al[2] = 1;
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 1)
		{
		    sql += " or id=5 ";
		    al[3] = 1;
		}
		sql = "update ftpsearch.HistoryUsers set count=0 where " + sql;
		statement.execute(sql);
	    }
	    sql = "update ftpsearch.HistoryUsers set count=count+1 where id>1 and id<6 or id="
		    + date;
	    statement.execute(sql);
	    Database.release(connection);
	}
	catch (Exception e1)
	{
	    e1.printStackTrace();
	}
    }

    public static void synchronization()
    {
	Statement statement;
	Connection connection;
	try
	{
	    connection = Database.getConnection();
	    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
		    ResultSet.CONCUR_UPDATABLE);
	    String sql = "SELECT count(*) FROM ftpsearch.querystatistics";
	    ResultSet results;
	    results = statement.executeQuery(sql);
	    int count = 0;
	    while (results.next())
	    {
		count = Integer.parseInt(results.getString(1));
	    }
	    sql = "update ftpsearch.HistoryUsers set count=" + count + " where id=2 ";
	    statement.execute(sql);

	    sql = "SELECT count(*) FROM ftpsearch.querystatistics where time < '2009-04-09' and time>'2009-01-01'";
	    results = statement.executeQuery(sql);
	    while (results.next())
	    {
		count = Integer.parseInt(results.getString(1));
	    }
	    sql = "update ftpsearch.HistoryUsers set count=" + count + " where id=3 ";
	    statement.execute(sql);

	    sql = "SELECT count(*) FROM ftpsearch.querystatistics where time < '2009-04-09' and time>'2009-04-01'";
	    results = statement.executeQuery(sql);
	    while (results.next())
	    {
		count = Integer.parseInt(results.getString(1));
	    }
	    sql = "update ftpsearch.HistoryUsers set count=" + count + " where id=4 ";
	    statement.execute(sql);

	    sql = "SELECT count(*) FROM ftpsearch.querystatistics where time < '2009-04-09' and time>'2009-04-05'";
	    results = statement.executeQuery(sql);
	    while (results.next())
	    {
		count = Integer.parseInt(results.getString(1));
	    }
	    sql = "update ftpsearch.HistoryUsers set count=" + count + " where id=5 ";
	    statement.execute(sql);

	    sql = "SELECT count(*) FROM ftpsearch.querystatistics where time < '2009-04-09' and time>'2009-04-08'";
	    results = statement.executeQuery(sql);
	    while (results.next())
	    {
		count = Integer.parseInt(results.getString(1));
	    }
	    sql = "update ftpsearch.HistoryUsers set count=" + count + " where id=20090408";
	    statement.execute(sql);

	    Database.release(connection);
	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public static void main(String[] args)
    {
	HistoryUsersPeer.synchronization();
	// //HistoryUsersPeer.addCountListNewDay();
	// Calendar c=Calendar.getInstance();
	// System.out.println(c.get(Calendar.YEAR));
	// System.out.println(c.get(Calendar.MONTH));
	// System.out.println(c.get(Calendar.DATE));
	// System.out.println(c.get(Calendar.DAY_OF_WEEK));   
	// System.out.println(c.get(Calendar.YEAR)*10000+(c.get(Calendar.MONTH)+1)*100+c.get(Calendar.DATE));

//	long[] al ={ -1, -1, -1, -1, -1 };
//	HistoryUsersPeer.getCountList(al);
//	System.out.println(al[0]);
//	System.out.println(al[1]);
//	System.out.println(al[2]);
//	System.out.println(al[3]);
//	System.out.println(al[4]);
    }
}