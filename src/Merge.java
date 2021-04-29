import java.lang.reflect.Array;
import java.util.*;


/**
 *  A class to study merging.
 *
 *  @author Jason Quesenberry and Nancy Quesenberry (edits by Aaron Pavao)
 *  @created January 16, 2006
 */

public class Merge {
    Scanner console = new Scanner(System.in);
    ArrayList<Comparable> temp = new ArrayList<Comparable>();
    int steps = 0;

    public int getSteps() {
        return steps;
    }

    /**
     * <b>summary</b>: Merges
     *
     * @param a
     * @param b
     * @param c
     */
    public void merge(ArrayList<Comparable> a, ArrayList<Comparable> b, ArrayList<Comparable> c) {
        int j = 0, k = 0; //j index of a, k index of b

        for (int i = 0; i < a.size() + b.size(); ++i) {
            if (j > a.size() - 1) {
                c.add(b.get(k));
                ++k;
            } else if (k > b.size() - 1) {
                c.add(a.get(j));
                ++j;
            } else if (a.get(j).compareTo(b.get(k)) <= 0) {
                c.add(a.get(j));
                ++j;
            } else if (a.get(j).compareTo(b.get(k)) >= 0) {
                c.add(b.get(k));
                ++k;
            }
        }

        System.out.println();
        System.out.println("First array: " + a.toString());
        System.out.println("Size of first array: " + a.size());
        System.out.println("Second array: " + b.toString());
        System.out.println("Size of second array: " + b.size());
        System.out.println("Size of merged array: " + c.size());
        screenOutput(c);
    }

    /**
     * Initializes and returns temp with random integers in the range
     * 1 to largestInt
     *
     * @return an ArrayList of size specified by the user filled
     * with random numbers
     */
    public ArrayList<Comparable> fillArray() {
        System.out.print("How many numbers do you wish to generate? ");
        int numInts = console.nextInt();
        System.out.print("Largest integer to generate? ");
        int largestInt = console.nextInt();

        Random randGen = new Random();

        for (int loop = 0; loop < numInts; loop++) {
            Integer x = randGen.nextInt(largestInt) + 1;
            temp.add(x);
        }

        return temp;
    }

    /**
     * Initializes and returns temp with ordered integers in the range
     * 1 to largestInt
     *
     * @return an ArrayList of size specified by the user filled
     * with random numbers
     */
    public ArrayList<Comparable> fillOrderedArray() {
        fillArray();
        for (int outer = 1; outer < temp.size(); outer++) {
            int position = outer;
            Comparable key = temp.get(position);


            // Shift larger values to the right
            while (position > 0 && temp.get(position - 1).compareTo(key) > 0) {

                temp.set(position, temp.get(position - 1));
                position--;
            }

            temp.set(position, key);
        }

        return temp;
    }

    /**
     * Prints out the contents of the array in tabular form, 20 columns
     */
    public void screenOutput(ArrayList<Comparable> temp) {
        System.out.print(temp.toString());
    }


    public void merge(ArrayList<Comparable> a, int first, int mid, int last) {

        int j = 0, k = 0; //j index of a, k index of b
        ArrayList<Comparable> l1 = new ArrayList<>();
        for (int i = 0; i <= a.subList(first, mid + 1).size() - 1; ++i) {
            l1.add(a.subList(first, mid + 1).get(i));
        }

        ArrayList<Comparable> l2 = new ArrayList<>();
        for (int i = 0; i <= a.subList(mid + 1, last + 1).size() - 1; ++i) {
            l2.add(a.subList(mid + 1, last + 1).get(i));
        }

        for (int i = first; i <= last; ++i) {
            if (j > l1.size() - 1) {
                a.set(i, l2.get(k));
                ++k;
                steps += 2;
            } else if (k > l2.size() - 1) {
                a.set(i, l1.get(j));
                ++j;
                steps += 2;
            } else if (l1.get(j).compareTo(l2.get(k)) <= 0) {
                a.set(i, l1.get(j));
                ++j;
                steps += 5;
            } else if (l1.get(j).compareTo(l2.get(k)) >= 0) {
                a.set(i, l2.get(k));
                ++k;
                steps += 5;
            }
        }
    }

    // Recursively divides a list in half, over and over. When the
// sublist has one or two values, stop subdividing.
    public void mergeSort(ArrayList<Comparable> a) {
        int first = 0;
        int last = a.size() - 1;

        mergeSort(a, first, last);
    }

    public void mergeSort(ArrayList<Comparable> a, int first, int last) {
        Comparable temp;
        int mid;
        if (last - first == 1) {
            steps += 3;
            if (a.get(first).compareTo(a.get(last)) > 0) {
                steps += 4;
                temp = a.get(first);
                a.set(first, a.get(last));
                a.set(last, temp);
            }
        } else if (last - first > 1) {
            mid = (first + last) / 2;
            mergeSort(a, first, mid);
            mergeSort(a, mid + 1, last);
            merge(a, first, mid, last);
        }
        System.out.println(steps);
    }


    public void quickSort(ArrayList<Comparable> list, int first, int last) {
        int g = first, h = last;

        int midIndex = (first + last) / 2;
        ++steps;
        Comparable dividingValue = list.get(midIndex);
        do {
            while (list.get(g).compareTo(dividingValue) < 0) {
                g++;
                ++steps;
            }
            while (list.get(h).compareTo(dividingValue) > 0) {
                h--;
                ++steps;
            }
            if (g <= h) {
                //swap(list[g], list[h]);
                steps += 4;
                swap(list, g, h);
                g++;
                h--;
            }
        } while (g < h);
        if (h > first) quickSort(list, first, h);
        if (g < last) quickSort(list, g, last);
    }



    public void swap(ArrayList<Comparable> list, int a, int b) {
        //replace these lines with your code
        Comparable temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);

        System.out.println();
        System.out.println("Swap");
        System.out.println();
    }



}
    //merge(mergeSort(a, first, mid), mergeSort(a, mid+1, last));