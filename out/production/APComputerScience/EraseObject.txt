import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * provides methods that can be used to erase objects(connected groups of characters) in a 2D array
 * @author Myles Carpenter
 * date - April 16, 2021
 * gang gang deifeijfie domutes donuts
 */
public class EraseObject {

    private boolean[][] grid;

    /**
     * initializes grid and loads grid
     * @param path path of string to be loaded using load()
     */
    public EraseObject(String path){
        grid = new boolean[21][21];
        load(path);
    }

    /**
     * loads file of coordinates to the grid
     * @param path path to file that should be loaded
     */
    public void load(String path){
        try{
            Scanner in = new Scanner(new File(path));
            int size = in.nextInt();
            for(int i = 0; i < size; ++i){
                grid[in.nextInt()][in.nextInt()] = true;
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * prints a visual representation of the grid to the console
     */
    public void printGrid(){
        for(int row = 1; row < grid.length; ++row){
            for(int col = 1; col < grid[0].length; ++col){
                if(grid[row][col]) System.out.print("#");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * finds first object by searching from left to right of each column and going down the columns
     */
    public void erase(){
        boolean found = false;
        for(int row = 1; row < grid.length; ++row) {
            for (int col = 1; col < grid[0].length; ++col) {
                if(!found){
                    if(grid[row][col]){
                        traceGrid(row, col);
                        found = true;
                    }
                }

            }
        }
    }

    /**
     * deletes the object on the given coordinates
     * @param row row coordinate
     * @param col column coordinate
     */
    private void traceGrid(int row, int col) {
        if (!(row < 1 || row >= grid.length || col < 1 || col >= grid.length)) {
            if (grid[row][col]) {
                grid[row][col] = false;
                traceGrid(row + 1, col);
                traceGrid(row - 1, col);
                traceGrid(row, col - 1);
                traceGrid(row, col + 1);
            }
        }
    }
}

/*
/Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=54068:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/mylescarpenter/IdeaProjects/APComputerScience/out/production/APComputerScience Client


 #############
             #
             #
             #
             #
             #
             #
    #####
    #####
    #####
    ##  #
    #  ##
    #####

             ####
             #
          ####
          #









    #####
    #####
    #####
    ##  #
    #  ##
    #####

             ####
             #
          ####
          #
















             ####
             #
          ####
          #





















Process finished with exit code 0

 */