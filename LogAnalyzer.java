/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
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
        reader = new LogfileReader();
    }
    
    public LogAnalyzer(String fileName){
        hourCounts = new int[24];
        reader = new LogfileReader(fileName);
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
    
    public int numberOfAssesses(){
        int total = 0;
        for(int hourCount : hourCounts) {
            total = total + hourCount;
        }
        return total;
    }
    
    public int busiestHour(){
        int highest = 0;
        int hour = 0;
        for(int i = 0; i < hourCounts.length; i++){
            if(hourCounts[i]>highest){
                highest = hourCounts[i];
                hour = i;
            }
        }
        return hour;
    }
    public int quietestHour(){
        int lowest = hourCounts[0];
        int hour = 0;
        for(int i = 0; i < hourCounts.length; i++){
            if(hourCounts[i]<lowest){
                lowest = hourCounts[i];
                hour = i;
            }
        }
        return hour;
    }
    public int busiestTwoHours(){
        int busiestTwo = 0;
        int busiestTwoCount = 0;
        for (int hour = 0; hour < hourCounts.length -1; hour++){
            int periodCount = hourCounts[hour] + hourCounts[hour+1];
            if(periodCount > busiestTwoCount){
                busiestTwo = hour;
                busiestTwoCount = periodCount;
            }
        }
        return busiestTwo;
    }
}
