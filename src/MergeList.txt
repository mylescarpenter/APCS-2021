import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * provides methods that manipulate a LinkedList full of grocery store items
 * @date April 26, 2021
 * @author mylescarpenter
 * what does the fox say
 */
public class MergeList {
/** main LinkiedList for Item storage */
    private List list = new LinkedList<Item>();

    /**
     * intializes MergeList and calls all scripted events
     * @param path - path to file of Item id's and inv counts
     */
    public MergeList(String path){
        load(path);
        printList();
        System.out.println();
        mergeSort(list, 0, list.size()-1);
        printList();
        System.out.println();
        reverse();
        printList();
        System.out.println();
    }

    /**
     * loads LinkedList with file contents
     * @param path - path to file
     */
    public void load(String path){
        try{
            Scanner in = new Scanner(new File(path));
            while(in.hasNext()){
                list.add(0, new Item(in.nextInt(), in.nextInt()));
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * sorts LinkedList with mergesort algorithm
     * @param a - List to be sorted
     * @param first - first index of sort
     * @param last - last index of sort
     */

    public void mergeSort(List<Comparable> a, int first, int last) {
        Comparable temp;
        int mid;
        if (last - first == 1) {
            if (a.get(first).compareTo(a.get(last)) > 0) {

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
    }

    /**
     * merges to sublists, used for mergesort
     * @param a - main List
     * @param first - first index
     * @param mid - middle index
     * @param last - last index
     */
    private void merge(List <Comparable> a, int first, int mid, int last){
        //replace these lines with your code
        int j = 0, k = 0; //j index of a, k index of b
        List<Comparable> l1 = new LinkedList<>();
        for(int i = 0; i <= a.subList(first, mid + 1).size() - 1; ++i){
            l1.add(a.subList(first, mid + 1).get(i));
        }

        List<Comparable> l2 = new LinkedList<>();
        for(int i = 0; i <= a.subList(mid + 1, last + 1).size() - 1; ++i){
            l2.add(a.subList(mid + 1, last + 1).get(i));
        }

        for(int i = first; i <= last; ++i){
            if(j > l1.size() - 1){
                a.set(i, l2.get(k));
                ++k;

            }else if(k > l2.size() - 1){
                a.set(i, l1.get(j));
                ++j;

            }else if(l1.get(j).compareTo(l2.get(k)) <= 0){
                a.set(i, l1.get(j));
                ++j;

            }else if(l1.get(j).compareTo(l2.get(k)) >= 0){
                a.set(i, l2.get(k));
                ++k;

            }
        }


    }

    /**
     * reverses List
     */

    public void reverse(){
        List temp = new LinkedList<Item>();

        for (Object item:list) {
            temp.add(0, item);
        }

        list = temp;
    }

    /**
     * @return Item List of store
     */
    public List getList(){
        return list;
    }

    /**
     * print function for store list
     */
    public void printList(){
        for(int i = 0; i < list.size(); ++i){
            System.out.println(list.get(i));
        }

    }

}

/*
/Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=64185:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/mylescarpenter/IdeaProjects/APComputerScience/out/production/APComputerScience Client
Id: 12328 Inv: 63
Id: 7282 Inv: 73
Id: 8303 Inv: 90
Id: 15320 Inv: 82
Id: 15814 Inv: 60
Id: 15917 Inv: 51
Id: 12705 Inv: 14
Id: 13066 Inv: 8
Id: 206 Inv: 31
Id: 18061 Inv: 3
Id: 14088 Inv: 92
Id: 18871 Inv: 69
Id: 184 Inv: 14
Id: 17911 Inv: 96
Id: 19967 Inv: 45
Id: 18465 Inv: 27
Id: 18410 Inv: 56
Id: 2370 Inv: 65
Id: 18618 Inv: 64
Id: 196 Inv: 60

Id: 184 Inv: 14
Id: 196 Inv: 60
Id: 206 Inv: 31
Id: 2370 Inv: 65
Id: 7282 Inv: 73
Id: 8303 Inv: 90
Id: 12328 Inv: 63
Id: 12705 Inv: 14
Id: 13066 Inv: 8
Id: 14088 Inv: 92
Id: 15320 Inv: 82
Id: 15814 Inv: 60
Id: 15917 Inv: 51
Id: 17911 Inv: 96
Id: 18061 Inv: 3
Id: 18410 Inv: 56
Id: 18465 Inv: 27
Id: 18618 Inv: 64
Id: 18871 Inv: 69
Id: 19967 Inv: 45

Id: 19967 Inv: 45
Id: 18871 Inv: 69
Id: 18618 Inv: 64
Id: 18465 Inv: 27
Id: 18410 Inv: 56
Id: 18061 Inv: 3
Id: 17911 Inv: 96
Id: 15917 Inv: 51
Id: 15814 Inv: 60
Id: 15320 Inv: 82
Id: 14088 Inv: 92
Id: 13066 Inv: 8
Id: 12705 Inv: 14
Id: 12328 Inv: 63
Id: 8303 Inv: 90
Id: 7282 Inv: 73
Id: 2370 Inv: 65
Id: 206 Inv: 31
Id: 196 Inv: 60
Id: 184 Inv: 14


Process finished with exit code 0

 */