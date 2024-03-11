/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
        public int numberOfAccesses()
    {
        int total = 0;
        for (int position = 0; position<24; position++)
        {
            total = total + hourCounts[position];
        }
        return total;
    }
    
    public int busiestHour()
    {
        int busyHour = 0;
        for (int position = 1; position<24; position++)
        {
            if (hourCounts[position] > hourCounts[busyHour])
            {
                busyHour = position;
            }
        }
        return busyHour;
    }
    
    public int quietestHour()
    {
        int quietHour = 0;
        for (int position = 1; position<24; position++)
        {
            if (hourCounts[position] < hourCounts[quietHour])
            {
                quietHour = position;
            }
        }
        return quietHour;
    }
    
    public int busiestTwoHour()
    {
        int busyTwoHour = 0;
        int totalbusyTwoHour = hourCounts[0] + hourCounts[1];
        for (int position = 1; position<23; position++)
        {
            if (hourCounts[position] + hourCounts [position + 1] > totalbusyTwoHour)
            {
                busyTwoHour = position;
                totalbusyTwoHour = hourCounts[position] + hourCounts [position + 1];
            }
        }
        if (hourCounts[23] + hourCounts[0] > totalbusyTwoHour)
        {
            busyTwoHour = 23;
        }
        return busyTwoHour;
    }
}
