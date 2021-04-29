import java.io.File;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * class Compact - reads a file, stores the integers on the file in an array, and provides a method
 *                 that removes all zeros from the array
 * @author mylescarpenter
 * date - February 18, 2021
 */
public class Compact {
/** array holds the integers in the file */
    private int[] contents;
    /**
     * constructor accept a file path as a string, reads the file, and adds each integer
     * value to an array
     * @param path - path of file to be read
     */
    public Compact(String path){
        int n = 1;
        contents = new int[n];
        int[] tempArray;

        try{
            File file = new File(path);
            Scanner in = new Scanner(file);

            while(in.hasNext()){
                contents[n-1] = in.nextInt();
                tempArray = contents;
                contents = new int[n + 1];
                ++n;

                for (int i = 0; i < tempArray.length; ++i) {
                    contents[i] = tempArray[i];
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(contents));
    }

    /**
     * method removes all zeros from an array
     */
   public void removeZeros(){
        //traverse array
       int[] tempArray;

       for(int i = 0; i < contents.length; ++i) {
           //when you hit 0, replace all members at index i with member at i + 1
           if (contents[i] == 0) {
               for (int r = i; r < contents.length - 2; r++) {
                   contents[r] = contents[r + 1];
               }

               tempArray = contents;
               contents = new int[contents.length - 1];
               for (int b = 0; b < contents.length; ++b) {
                   contents[b] = tempArray[b];
               }
                --i;
           }
       }
   }

    /**
     * toString method of Compact class
     * @return - contents array as a string displayed in form [a, b, c]
     */
   public String toString(){
        return Arrays.toString(contents);
   }

}
