import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * class Life - provides methods that simulate generations of a very simple bacteria culture
 * @author Myles Carpenter
 * date - April 12, 2020
 * finally done
 */
public class Life {
/** stores environment data */
    private boolean[][] environment;
    private Scanner in;
/** initializes Life with empty environment */
    public Life(){
         environment = new boolean[22][22];
    }
/** initializes Life and loads with contents of specified file */
    public Life(String path){
        environment = new boolean[22][22];
        load(path);
    }

    /**
     * loads environment with contents of specified file
     * @param path - path of file to load
     */
    public void load(String path) {
        int population;
        int[] location = new int[2];

        try {
            in = new Scanner(new File(path));
            population = in.nextInt();
            while (in.hasNext()) {

                for (int i = 0; i < population; ++i) {
                    location[0] = in.nextInt();
                    location[1] = in.nextInt();

                    environment[location[0]][location[1]] = true;
                }


            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * loads arrToLoad with contents of arrData
     * @param arrToLoad - array to load
     * @param arrData - array to load arrToLoad with
     */
    public void load(boolean[][] arrToLoad, boolean[][] arrData){
        for(int col = 1; col <= 20; ++col){
            for(int row = 1; row <= 20; ++row){
                arrToLoad[row][col] = arrData[row][col];
            }
        }
    }

    /**
     * simulates a single generation of bacteria life
     */
    public void simulate(){
        boolean[][] temp = new boolean[22][22];
        int neighbors;

        load(temp, environment);

        for(int col = 1; col <= 20; ++col){
            for(int row = 1; row <= 20; ++row){
                neighbors = 0;
                for(int colShift = -1; colShift <= 1; ++colShift){
                    for(int rowShift = -1; rowShift <= 1; ++rowShift){
                        if(!(rowShift == 0 && colShift == 0))
                        neighbors += toInt(temp[row + rowShift][col + colShift]);
                    }
                }
                if(neighbors == 3){
                    environment[row][col] = true;
                }else if(neighbors != 2){
                    environment[row][col] = false;
                }
            }
        }


    }

    /**
     * returns 1 if true, 0 if false
     * @param b - boolean to be converted
     * @return - 1 if true, 0 if false
     */
    private int toInt(boolean b){
        if(b) return 1;
        else return 0;
    }

    /**
     * returns total population of environment
     * @return - total population of environment
     */
    public int getPopulation(){
        int population = 0;
        for(int col = 1; col <= 20; ++col){
            for(int row = 1; row <= 20; ++row){
                population += toInt(environment[row][col]);
            }
        }
        return population;
    }

    /**
     * returns population of specified axis
     * @param axis - "row" if row "col" if column
     * @param index - index of axis to be counted
     * @return population of specified axis
     */
    public int getPopulation(String axis, int index){
        int population = 0;
        if(axis.equals("col")){
            for(int i = 1; i <= 20; ++i){
                population += toInt(environment[i][index]);
            }
        }else if(axis.equals("row")){
            for(int i = 1; i <= 20; ++i){
                population += toInt(environment[index][i]);
            }
        }else{
            System.out.println("invalid axis");
            --population;
        }
        return population;
    }

    public boolean isOccupied(int row, int col){
        return environment[row][col];
    }

    /**
     * prints contents of environment
     */
    public void print(){
        System.out.print(" ");
        for(int i = 1; i <= 20; ++i) System.out.printf("%3d", (i) /*% 10*/);
        System.out.println();
        for(int row = 1; row <= 20; ++row){
            System.out.printf("%1d", row);
            for(int col = 1; col <= 20; ++col){
                if(environment[row][col]) System.out.printf("%3s","*");
                else System.out.printf("%3s", " ");
            }
            System.out.println();
        }
    }

}

/*
/Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=63434:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/mylescarpenter/IdeaProjects/APComputerScience/out/production/APComputerScience Client
Generation: 0
   1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20
1        *           *  *        *  *              *
2  *           *        *
3                    *                 *        *           *
4  *           *                             *     *  *
5  *                       *           *     *  *
6  *     *     *        *                 *           *
7                                   *        *  *        *
8              *                 *        *  *
9                    *              *  *     *        *     *
10                    *     *              *  *
11           *                    *                          *
12                    *  *     *  *                    *
13  *     *                             *
14  *  *                 *  *                 *  *
15                       *     *        *  *        *     *
16  *     *           *     *     *  *              *  *
17        *  *                                            *
18        *              *           *     *                 *
19  *              *  *              *  *  *  *
20           *  *     *     *        *              *  *
row 10 population: 6
col 10 population: 4
total population: 99
Generation: 1
   1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20
1                    *  *
2                 *     *           *
3                 *                             *  *
4                                            *     *
5  *        *                                *  *     *
6     *                                *  *        *
7           *                          *        *
8                                *                       *
9                 *     *           *  *        *
10                       *           *  *  *  *           *
11                    *     *     *
12                             *  *  *
13  *                 *        *
14  *  *                 *  *           *     *  *
15  *                 *        *  *  *  *  *  *     *
16     *  *  *           *  *  *  *  *  *           *     *
17        *  *           *        *  *  *              *  *
18     *  *  *        *              *     *  *
19           *  *  *  *           *  *     *  *
20              *     *  *           *     *
row 10 population: 5
col 10 population: 1
total population: 77
Generation: 2
   1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20
1                    *  *
2                 *     *
3                    *                          *  *
4                                            *        *
5                                            *        *
6        *                             *  *        *
7                                   *  *  *
8                                      *
9                    *           *           *
10                       *  *     *        *  *
11                       *  *              *
12                       *  *        *
13  *  *                 *     *     *
14  *  *              *  *  *           *     *  *
15  *                 *                       *     *  *
16     *     *        *  *                       *  *     *
17              *     *  *                             *  *
18     *              *  *                    *
19                                *  *
20           *  *     *  *        *  *     *  *
row 10 population: 4
col 10 population: 3
total population: 72
Generation: 3
   1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20
1                    *  *
2                 *     *
3                    *                          *  *
4                                            *        *
5                                            *  *  *  *
6                                   *        *
7                                   *
8                                      *
9                       *     *     *        *
10                    *     *              *  *
11                    *                 *  *  *
12                    *        *  *     *
13  *  *                       *  *  *  *
14                    *     *              *  *  *  *
15  *     *        *        *              *  *        *
16                                               *        *
17        *                 *                    *  *  *  *
18                 *  *  *
19                 *              *  *  *  *  *
20                                *  *  *
row 10 population: 3
col 10 population: 2
total population: 72
Generation: 4
   1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20
1                    *  *
2                 *     *
3                    *                          *  *
4                                            *        *
5                                         *  *     *  *
6                                            *     *
7                                   *  *
8                                *  *  *
9                       *  *           *     *
10                    *     *                    *
11                 *  *        *     *  *     *
12                             *
13                       *  *           *     *  *
14  *                    *  *     *  *           *  *
15                       *                 *           *
16     *                                         *        *
17                    *  *                       *  *  *  *
18                 *  *  *           *  *  *  *  *  *  *
19                 *              *        *
20                                *
row 10 population: 8
col 10 population: 5
total population: 88

Process finished with exit code 0

 */