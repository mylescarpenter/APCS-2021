import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TourDeKnight {
    /** initializes horizontal vector for knight movement
     *   move = {0, 1, 2, 3, 4, 5, 6, 7, 8}
     *   horizontal = {0, 1, 2, 2, 1, -1, -2, -2, -1}
     *   vertical = {0, -2, -1, 1, 2, 2, 1, -1, -2};
     * */
    private final int[] horizontal = {0, 1, 2, 2, 1, -1, -2, -2, -1};
    /** initializes vertical vector for knight movement
     *   move = {0, 1, 2, 3, 4, 5, 6, 7, 8}
     *   horizontal = {0, 1, 2, 2, 1, -1, -2, -2, -1}
     *   vertical = {0, -2, -1, 1, 2, 2, 1, -1, -2};
     * */
    private final int[] vertical = {0, -2, -1, 1, 2, 2, 1, -1, -2};
    /** holds board data such as piece location */
    private int[][] board;
    /** holds accessibility for each space on the board */
    private int[][] accessibility;

    /**
     * initializes board and accessibility
     */
    public TourDeKnight(){
        board = new int[9][9];
        for(int row = 0; row < 9; ++row){
            for(int col = 0; col < 9; ++col) board[row][col] = 0;
        }
        initAccessibility("/Users/mylescarpenter/IdeaProjects/APComputerScience/src/access.txt");
    }

    /**
     * runs through a knights tour, maximizing the number of squares visited. Prints board to console upon completion
     */
    public void runRandom(){
        int[] move = {1, 2, 3, 4, 5, 6, 7, 8};
        board[1][1] = 1;
        int row = 1;
        int col = 1;
        int moveCount = 1;
        boolean moveFound;


        while(hasValidMove(row, col)){
            ++moveCount;
            shuffle(move);
            moveFound = false;

            for(int i = 0; i < move.length; ++i){
                    if (isValid(row, col, move[i]) && !moveFound) {
                        row += vertical[move[i]];
                        col += horizontal[move[i]];
                        board[row][col] = moveCount;
                        moveFound = true;
                    }
            }
        }

        printBoard(moveCount);

    }

    /**
     * runs an algorithm that completes a knight's tour using our new accessibility strategy
     */
    public void runAlgorithm(){
        board[1][1] = 1;
        int row = 1;
        int col = 1;
        int moveCount = 1;
        int bestMove;

        updateAccessibility(row, col);

        while(hasValidMove(row, col)){
            ++moveCount;
            bestMove = lowestAccessibility(row, col);
            row += vertical[bestMove];
            col += horizontal[bestMove];
            board[row][col] = moveCount;
            updateAccessibility(row, col);


        }

        printBoard(moveCount);
    }

    /**
     * decrements the accessibility value on square's of each of knight's valid moves from current location
     * @param row knight's current row location
     * @param col knight's current column location
     */
    private void updateAccessibility(int row, int col) {

        for(int i = 1; i < horizontal.length; ++i){
            if(isValid(row, col, i)) accessibility[row + vertical[i]][col + horizontal[i]] -= 1;
        }

    }

    /**
     *
     * @param row row value of knight's location
     * @param col column value of knight's location
     * @return move number that moves to square with lowest accessibility
     */
    public int lowestAccessibility(int row, int col){
        int lowestAcc = 8;
        int bestMove = 0;

        for(int i = 1; i < horizontal.length; ++i){
            if(isValid(row, col, i) && accessibility[row + vertical[i]][col + horizontal[i]] < lowestAcc){
                lowestAcc = accessibility[row + vertical[i]][col + horizontal[i]];
                bestMove = i;
            }

        }
        return bestMove;
    }
    /**
     * checks if a knights move has any valid move(square has not been visited and knight won't fall off the board)
     * @param row - current vertical location of knight
     * @param col - current horizontal location of knight
     * @return - true if has valid move else false
     */
    private boolean hasValidMove(int row, int col){
        for(int i = 0; i < horizontal.length; ++i){
            if(isValid(row, col, i)) return true;
        }
        return false;
    }

    /**
     * checks if a specific move is valid from the knights current location
     * @param row - current row of knight
     * @param col - current column of knight
     * @param moveNumber - The move number that should be checked(for more information check instance variables
     *                     horizontal and vertical)
     * @return - true if move is valid, else false
     */
    private boolean isValid(int row, int col, int moveNumber){
        if(row + vertical[moveNumber] < board.length && col + horizontal[moveNumber] < board.length
        && row + vertical[moveNumber] > 0 && col + horizontal[moveNumber] > 0
        && board[row + vertical[moveNumber]][col + horizontal[moveNumber]] == 0) return true;
        else return false;
    }

    /**
     * randomizes the order of a specified array
     * @param arr - array to be shuffled
     */
    private void shuffle(int[] arr){
        Random random = new Random();

        for(int i = 0; i < arr.length; ++i){
            int temp = arr[i];
            int randIndex = random.nextInt(arr.length);

            arr[i] = arr[randIndex];

            arr[randIndex] = temp;
        }
    }

    /**
     * prints specified array
     * @param arr - 2-dimensional int array to be printed
     */
    public void print(int[][] arr){
        System.out.printf("%3s", " ");
        for(int i = 1; i <= 8; ++i) System.out.printf("%3d", i);
        System.out.println();
        for(int row = 1; row <= 8; ++row){
            System.out.printf("%3d", row);
            for(int col = 1; col <= 8; ++col){
                System.out.printf("%3d", arr[row][col]);
            }
            System.out.println();
        }
    }

    /**
     * prints board and adds text below
     * @param moveCount - Total number of squares visited
     */
    private void printBoard(int moveCount){
        print(board);
        System.out.println("\n" + moveCount + " squares were visited");
    }

    /**
     * prints accessibility array
     */
    public void printAccess(){
        print(accessibility);
    }

    /**
     * initializes accessibility using a given file
     * @param path - file path
     */
    public void initAccessibility(String path){
        accessibility = new int[9][9];
        try{
            Scanner in = new Scanner(new File(path));

            while(in.hasNext()){
                for(int row = 1; row <= 8; ++row){
                    for(int col = 1; col<=8; ++col){
                        accessibility[row][col] = in.nextInt();
                    }
                }

            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

}


/*
/Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=51634:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/mylescarpenter/IdeaProjects/APComputerScience/out/production/APComputerScience Client
     1  2  3  4  5  6  7  8
  1  1 16 27 22  3 18 47 56
  2 26 23  2 17 46 57  4 19
  3 15 28 25 62 21 48 55 58
  4 24 35 30 45 60 63 20  5
  5 29 14 61 34 49 44 59 54
  6 36 31 38 41 64 53  6  9
  7 13 40 33 50 11  8 43 52
  8 32 37 12 39 42 51 10  7

64 squares were visited
Process finished with exit code 0

 */