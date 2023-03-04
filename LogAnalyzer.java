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
    private int[] dailyCounts; //Counts the days.
    private int[] monthlyCounts; //Counts the months.
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
        dailyCounts = new int[29];
        monthlyCounts = new int[13];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
    }
    
    public LogAnalyzer(String filename)
    {
        // Create the reader to obtain the data.
        hourCounts = new int[24];
        dailyCounts = new int[29];
        monthlyCounts = new int[13];
        
        //Creates logfilereader stores string of filename.
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) 
        {
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
     * This part is not from me i had to look it up because i did not understand but i do understand what it does.
     * I'm prob gonna look over this project again during spring break.
     */
    public void analyzeDailyData()
    {
        while(reader.hasNext())
        {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dailyCounts[day]++;
        }
    }
    
    public void analyzeMonthlyData()
    {
        while(reader.hasNext())
        {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthlyCounts[month]++;
        }
    }
    
    public int quietestDay()
    {
        int quietest = 1;
        for(int i = 1; i < dailyCounts.length; i++)
        {
            if(dailyCounts[i] < dailyCounts[quietest])
                quietest = i;
        }
        return quietest;
    }
    
    public int busiestDay()
    {
        int busiest = 1;
        for(int i = 1; i < dailyCounts.length; i++)
        {
            System.out.println(dailyCounts[i]);
            if(dailyCounts[i] > dailyCounts[busiest])
                busiest = i;
        }
        return busiest;
    }
    
    public int avgAccessesPerMonth()
    {
        int numMonths = 0;
        int totalAccesses = 0;
        for(int i = 1; i <= 12; i++)
        {
            totalAccesses += monthlyCounts[i];
            if(monthlyCounts[i] > 0)
                numMonths++;
        }
        return totalAccesses/numMonths;
    }
    
    public void totalAccessesPerMonth()
    {
         System.out.println("Month: Count");
        for(int month = 1; month < monthlyCounts.length; month++)
        {
            System.out.println(month + ": " + monthlyCounts[month]);
        }
    }
     
    public int quietestMonth()
    {
        int quietest = 1;
        for(int i = 1; i <= 12; i++)
        {
            if(monthlyCounts[i] < monthlyCounts[quietest])
                quietest = i;
        }
        return quietest;
    }
    
    public int busiestMonth()
    {
        int busiest = 1;
        for(int i = 1; i <= 12; i++)
        {
            if(monthlyCounts[i] > monthlyCounts[busiest])
                busiest = i;
        }
        return busiest;
    }
}
