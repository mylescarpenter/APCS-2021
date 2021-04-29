import java.util.ArrayList;
import java.util.Random;

/**
 * class Permutations - provides a method that returns and ArrayList holding random permutations
 *                      of the numbers on the interval [1, n] with n being specified by the user
 * @author mylescarpenter
 * date December 18, 2020
 * yellow dog
 */
public class Permutations {

    /**
     * <b>Summary</b>: provides method that returns an ArrayList< Integer> that holds
     *                 a random permutations of the numbers from 1 inclusive to n inclusive
     * @param n - the top bound of the interval that you would like to find the permutations of
     * @return - an ArrayList< Integer> that holds
     *           the random permutations
     */
    public static ArrayList<Integer> nextPermutation(int n){
        ArrayList<Integer> p = new ArrayList<>(n);
        Random random = new Random();
        ArrayList<Integer> nums = new ArrayList<>(n);
        int randIndex = 0;
        ArrayList<Integer> test = new ArrayList<>(n);

        for(int i = 1; i <= n; ++i){
            nums.add(i);
        }

        for(int i = n; i > 1; --i) {
            randIndex = random.nextInt(i);
            p.add(nums.get(randIndex));
            nums.remove(randIndex);
        }
        p.add(nums.get(0));

        return p;
    }
}

/*
/Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=63862:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/mylescarpenter/IdeaProjects/APComputerScience/out/production/APComputerScience Client
List 1: [1, 4, 2, 8, 6, 3, 9, 5, 7, 10]
List 2: [7, 1, 2, 6, 4, 8, 5, 10, 3, 9]
List 3: [1, 6, 7, 5, 10, 2, 3, 8, 4, 9]
List 4: [1, 5, 3, 4, 8, 7, 6, 2, 9, 10]
List 5: [2, 3, 7, 6, 9, 10, 5, 8, 1, 4]
List 6: [8, 2, 10, 4, 3, 5, 9, 6, 1, 7]
List 7: [4, 3, 5, 7, 9, 10, 6, 1, 8, 2]
List 8: [3, 4, 10, 1, 8, 6, 7, 9, 2, 5]
List 9: [10, 7, 9, 1, 8, 4, 5, 3, 2, 6]
List 10: [6, 5, 10, 7, 1, 8, 9, 2, 3, 4]

Process finished with exit code 0
 */
