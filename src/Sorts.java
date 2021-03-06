import java.util.*;

/**
 *  Description of the Class
 *
 * @author     Your Name Here
 * @created    Month Day, Year
 */
public class Sorts{
    private long steps;



    public Sorts(){
        steps = 0;
    }

    /**
     *  Description of the Method
     *
     * @param  list  reference to an array of integers to be sorted
     */
    public void bubbleSort(ArrayList <Comparable> list){
        steps = 0;
        for (int outer = 0; outer < list.size() - 1; outer++){
            for (int inner = 0; inner < list.size()-outer-1; inner++){
                steps += 3;//count one compare and 2 gets
                if (list.get(inner).compareTo(list.get(inner + 1)) > 0){
                    steps += 4;//count 2 gets and 2 sets
                    Comparable temp = list.get(inner);
                    list.set(inner,list.get(inner + 1));
                    list.set(inner + 1,temp);
                }
            }
        }
        System.out.println();
        System.out.println("Bubble Sort");
        System.out.println();
    }

    /**
     *  Description of the Method
     *
     * @param  list  reference to an array of integers to be sorted
     */
    public void selectionSort(ArrayList <Comparable> list){
        int min;
        steps = 0;


        for (int outer = 0; outer < list.size() - 1; outer++){
            min = outer;
            for (int inner = outer + 1; inner < list.size(); inner++){
                steps += 3;
                if (list.get(inner).compareTo(list.get(min)) > 0) {
                    min = inner; // a new smallest item is found
                }
            }
            //swap list[outer] & list[min]
            steps += 4;
            Comparable temp = list.get(outer);
            list.set(outer, list.get(min));
            list.set(min, temp);
        }
        System.out.println();
        System.out.println("Selection Sort");
        System.out.println();
    }

    /**
     *  Description of the Method
     *
     * @param  list  reference to an array of integers to be sorted
     */
    public void insertionSort(ArrayList <Comparable> list){
        //replace these lines with your code
        for (int outer = 1; outer < list.size(); outer++){
            int position = outer;
            Comparable key = list.get(position);
            steps += 2;

            // Shift larger values to the right
            while (position > 0 && list.get(position - 1).compareTo(key) > 0){
                steps += 2;
                list.set(position, list.get(position - 1));
                position--;
            }
            steps += 1;
            list.set(position, key);
        }

        System.out.println();
        System.out.println("Insertion Sort");
        System.out.println();
    }


    /**
     *  Takes in entire vector, but will merge the following sections
     *  together:  Left sublist from a[first]..a[mid], right sublist from
     *  a[mid+1]..a[last].  Precondition:  each sublist is already in
     *  ascending order
     *
     * @param  a      reference to an array of integers to be sorted
     * @param  first  starting index of range of values to be sorted
     * @param  mid    midpoint index of range of values to be sorted
     * @param  last   last index of range of values to be sorted
     */
    private void merge(ArrayList <Comparable> a, int first, int mid, int last){
        //replace these lines with your code
        int j = 0, k = 0; //j index of a, k index of b
        ArrayList<Comparable> l1 = new ArrayList<>();
        for(int i = 0; i <= a.subList(first, mid + 1).size() - 1; ++i){
            l1.add(a.subList(first, mid + 1).get(i));
        }

        ArrayList<Comparable> l2 = new ArrayList<>();
        for(int i = 0; i <= a.subList(mid + 1, last + 1).size() - 1; ++i){
            l2.add(a.subList(mid + 1, last + 1).get(i));
        }

        for(int i = first; i <= last; ++i){
            if(j > l1.size() - 1){
                a.set(i, l2.get(k));
                ++k;
                steps+=2;
            }else if(k > l2.size() - 1){
                a.set(i, l1.get(j));
                ++j;
                steps+=2;
            }else if(l1.get(j).compareTo(l2.get(k)) <= 0){
                a.set(i, l1.get(j));
                ++j;
                steps+=5;
            }else if(l1.get(j).compareTo(l2.get(k)) >= 0){
                a.set(i, l2.get(k));
                ++k;
                steps+=5;
            }
        }
        System.out.println();
        System.out.println("Merge");
        System.out.println();

    }

    /**
     *  Recursive mergesort of an array of integers
     *
     * @param  a      reference to an array of integers to be sorted
     * @param  first  starting index of range of values to be sorted
     * @param  last   ending index of range of values to be sorted
     */
    public void mergeSort(ArrayList <Comparable> a, int first, int last){
        Comparable temp;
        int mid;
        if(last - first == 1){
            steps+=3;
            if(a.get(first).compareTo(a.get(last)) > 0){
                steps += 4;
                temp = a.get(first);
                a.set(first, a.get(last));
                a.set(last, temp);
            }
        }else if(last - first > 1) {
            mid = (first + last) / 2;
            mergeSort(a, first, mid);
            mergeSort(a, mid + 1, last);
            merge(a, first, mid, last);
        }


        System.out.println();
        System.out.println("Merge Sort");
        System.out.println();
    }

    public void quickSort (ArrayList <Comparable> list, int first, int last){
        int g = first, h = last;

        int midIndex = (first + last) / 2;
        ++steps;
        Comparable dividingValue = list.get(midIndex);
        do{
            while (list.get(g).compareTo(dividingValue) < 0){
                g++;
                ++steps;
            }
            while (list.get(h).compareTo(dividingValue) > 0){
                h--;
                ++steps;
            }
            if (g <= h){
                //swap(list[g], list[h]);
                steps += 4;
                swap(list,g,h);
                g++;
                h--;
            }
        }while (g < h);
        if (h > first) quickSort (list,first,h);
        if (g < last) quickSort (list,g,last);
    }


    /**
     *  Accessor method to return the current value of steps
     *
     */
    public long getStepCount(){
        return steps;
    }

    /**
     *  Modifier method to set or reset the step count. Usually called
     *  prior to invocation of a sort method.
     *
     * @param  stepCount   value assigned to steps
     */
    public void setStepCount(long stepCount){
        steps = stepCount;
    }

    /**
     *  Interchanges two elements in an ArrayList
     *
     * @param  list  reference to an array of integers
     * @param  a     index of integer to be swapped
     * @param  b     index of integer to be swapped
     */
    public void swap(ArrayList <Comparable> list, int a, int b){
        //replace these lines with your code
        Comparable temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);

        System.out.println();
        System.out.println("Swap");
        System.out.println();
    }


}