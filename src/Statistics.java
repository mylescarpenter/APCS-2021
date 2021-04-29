import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * class Statistics - provides methods that calculate various statistics about a list of integers on a file
 * @author mylescarpenter
 * date - December 20, 2020
 * satistics
 */
public class Statistics {

    /** nums holds the integer values on the file*/
    private int[] nums;

    /** creates new Statistics object and specifies the file used in statistic processes */
    public Statistics(String path){
        int n = 1;
        nums = new int[n];
        int[] tempArray;
        try{
            File f = new File(path);
            Scanner in = new Scanner(f);
            while(in.hasNext()){
                nums[n - 1] = in.nextInt();
                tempArray = nums;
                nums = new int[n + 1];
                ++n;

                for (int i = 0; i < tempArray.length; ++i) {
                    nums[i] = tempArray[i];
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * <b>Summary</b>: prints the average of the numbers of the file
     */
    public void printAverage(){
        System.out.printf("%.2f", getAverage(), "\n");
        System.out.println();
    }

    /**
     * <b>Summary</b>: returns the average of the data set(sum/number of numbers)
     * @return - average
     */
    public double getAverage(){
        long sum = 0;
        double avg;

        for (Integer num : nums) sum += num;

        return (double)sum / (nums.length - 1);
    }

    /**
     * <b>Summary</b>: prints the standard deviation of the data set
     */
    public void printStandardDeviation(){
        double sum = 0;
        double dev;

        for (Integer num : nums){
            sum += Math.pow((num - getAverage()), 2);
        }

        dev = Math.pow(sum / (nums.length - 1), .5);
        System.out.printf("%.2f", dev);
        System.out.println();
    }

    /**
     * <b>Summary</b>: finds the mode(most frequent number(s)) of the data set
     * @return - an String displaying the mode(s) of the data set
     */
    /*
    public ArrayList<Integer> getMode(){
        ArrayList<Integer> counts = new ArrayList<>(nums.size());
        for(Integer integer : nums)
        {
            counts.add(0);
        }
        int highestCount = 0;
        int mode = 0;
        ArrayList<Integer> modes = new ArrayList<>();

        for(Integer num : nums){ //finds count of each number index 0 = number 1
            counts.set(num - 1, counts.get(num - 1) + 1);
        }


        for(Integer count : counts){//finds the highest count present in counts list
            if(count > counts.get(mode)){
                highestCount = count;
            }
        }

        while(counts.contains(highestCount)){//adds mode(s) to list and returns the list
            modes.add(counts.indexOf(highestCount) + 1);
            counts.remove(counts.indexOf(highestCount));
        }

        return modes;

    }
*/
    public String getMode(){
        int[] counts = new int[101];
        int highestCount = 0;
        int num = 0;
        int[] modes = new int[0];

        for(int i = 0; i < nums.length; ++i){ //finds count of each number index 0 = number 1
            num = nums[i];

            counts[num] = counts[num] + 1;
        }
        /*System.out.println(Arrays.toString(counts));
        System.out.println(counts.length);*/

        for(Integer count : counts){//finds the highest count present in counts list
            if(count > highestCount){
                highestCount = count;
            }
        }

        int[] tempArray;

        for(int i = 0; i < counts.length; ++i){
            if(counts[i] == highestCount){
// add i to modes array
                tempArray = modes;
                modes = new int[modes.length + 1];
                for(int q = 0; q < tempArray.length; ++q){
                    modes[q] = tempArray[q];
                }
                modes[modes.length - 1] = i;
            }
        }

        return Arrays.toString(modes);

    }



   /* /Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=59799:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/mylescarpenter/IdeaProjects/APComputerScience/out/production/APComputerScience Client
    Average: 49.92
    Mode(s): [10, 48]
    Standard deviation: 29.48

    Process finished with exit code 0*/

}
