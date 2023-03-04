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
    
    public LogAnalyzer(String filename)
    {
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
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
    
     public int numberofAccesses()
    {
        int total = 0;
        //Add the value in each element of hourCounts
        //to total.
        for(int hour = 0; hour < hourCounts.length; hour++) 
        {
            total = total + hourCounts[hour];
        }
           return total;
    }
    
    public int busiestHour()
     {
        int numOfAccessesAtBusiest = 0;
        int busiest = 0;
        int index = 0;
        
        while (index < hourCounts.length -1)
        {
            if (numOfAccessesAtBusiest < hourCounts[index])
            {
                busiest = index;
                numOfAccessesAtBusiest = hourCounts[index];
                index++;
            }
            else
            {
                index++;
            }
        }
        return busiest;
    }
    
    public int quietestHour()
    {
        int numOfAccessesAtQuietest = hourCounts[0];
        int quietest = 0;
        int index = 0;
        
        while (index < hourCounts.length -1)
        {
            if (numOfAccessesAtQuietest > hourCounts[index])
            {
                quietest = index;
                numOfAccessesAtQuietest = hourCounts[index];
                index++;
            }
            else
            {
                index++;
            }
        }
        return quietest;
    }
    
    public int twoHourBusiest()
    {
            int numOfAccessesAtBusiest = 0;
            int index = 0;
            int busiest = 0;
                        
            while(index < hourCounts.length - 1)
            {
                if (numOfAccessesAtBusiest < hourCounts[index] + hourCounts[(index + 1)%24])
                {
                    busiest = index;
                    numOfAccessesAtBusiest = hourCounts[index] + hourCounts[index + 1];
                    index++;
                }
                else
                {
                    index++;
                }
            }
            return busiest;
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
