package database;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
  * Autogenerated by Lisptorq 0.1.4 
*/
public class QueryStatisticsResultPeer extends BaseQueryStatisticsResultPeer
{
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static Scroller<QueryStatisticsResult> weeklyStatistics(int limit)
    {
	return statisticsDaysAgo(7,limit);
    }    
    
    public static Scroller<QueryStatisticsResult> monthlyStatistics(int limit)
    {
	Calendar c = Calendar.getInstance();
	int month = c.get(Calendar.MONTH);
	if(month<=0)
	    c.roll(Calendar.YEAR, false);
	c.roll(Calendar.MONTH, -1);
	return statistics(c.getTime(),limit);
    }
    
    public static Scroller<QueryStatisticsResult> yearlyStatistics(int limit)
    {
	Calendar c = Calendar.getInstance();
	c.roll(Calendar.YEAR, -1);
//	System.out.println(c.getTime());
	return statistics(c.getTime(),limit);
    }
    
    public static Scroller<QueryStatisticsResult> statisticsDaysAgo(int days,int limit)
    {
	Calendar c = Calendar.getInstance();
	int dayOfYear = c.get(Calendar.DAY_OF_YEAR);
	if(dayOfYear<=days)
	    c.roll(Calendar.YEAR,false);
	c.roll(Calendar.DAY_OF_YEAR, -days);
	return statistics(c.getTime(),limit);
    }
    
    private static Scroller<QueryStatisticsResult> statistics(java.util.Date date2,int limit)
    {
	String date =DATE_FORMAT.format(date2);
	try
	{
	    String sql = "SELECT keyword,count(time) as frequency from QueryStatistics where time>\'"+date+"\' group by keyword order  by frequency desc limit "+limit+";";
	    return QueryStatisticsResultPeer
		    .doSelect(sql);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	return null;
    }

    public static void main(String[] args)
    {
	Scroller<QueryStatisticsResult> result = QueryStatisticsResultPeer
		.yearlyStatistics(10);
	while (result.hasNext())
	{
	    QueryStatisticsResult r =(QueryStatisticsResult)result.next();
	    System.out.println(r.getKeyword()+":"+r.getFrequency());
	}
    }
    
}