import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Store - provides methods that allow you to track information about items in a store
 * @author - Myles Carpenter
 * date - March 20, 2020
 */
public class Store {

    ArrayList<Item> items = new ArrayList<>();
    Scanner in;

    /**
     * If a file path is specified in the constructor, the file contents will automatically loaded
     * into the array
     * @param path - String that holds a path to a file
     */
    public Store(String path){
        load(path);
    }

    /**
     * adds the contents of a file to items(ArrayList). File should hold integers alternating
     * between the id number and then the inventory number of different items
     * @param path
     */
    public void load(String path){
        try{
            in = new Scanner(new File(path));
            while(in.hasNextInt()){
                items.add(new Item(in.nextInt(), in.nextInt()));
            }
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }

    /**
     * Used in the sort. Swaps the list elements at indices a and b
     * @param list - list that should have the elements swapped
     * @param a - first index of swap target
     * @param b - second index of swap target
     */
    public void swap(ArrayList <Item> list, int a, int b) {
        //replace these lines with your code
        Item temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }

    /**
     * triggers the sort of items
     */
    public void sort(){
        sort(items, 0, items.size()-1);
    }

    /**
     * uses a quicksort to sort the items in items in non-descending order based on the Id number
     * @param list - list to be sorted
     * @param first - first index of sort
     * @param last - last index of sort
     */
    private void sort (ArrayList <Item> list, int first, int last){
        int g = first, h = last;

        int midIndex = (first + last) / 2;

        Item dividingValue = list.get(midIndex);
        do{
            while (list.get(g).compareTo(dividingValue) < 0){
                g++;

            }
            while (list.get(h).compareTo(dividingValue) > 0){
                h--;

            }
            if (g <= h){
                //swap(list[g], list[h]);

                swap(list,g,h);
                g++;
                h--;
            }
        }while (g < h);
        if (h > first) sort (list,first,h);
        if (g < last) sort (list,g,last);
    }

    /**
     * prints the contents of the Store in a user-friendly format
     */
   public void print(){
       int count = 1;
       System.out.printf("%s %8s %5s", "", "Id", "Inv");
       System.out.println();
       for (Item item: items) {
           System.out.printf("%d %8d %5d", count, item.getId(), item.getInv());
           System.out.println();

           if((count) % 10 == 0) System.out.println();
            ++count;
       }
   }

    /**
     * clears items
     */
    public void clear(){
        items.clear();
    }

    /**
     * @return toString of ArrayList items
     */
    public String toString(){
        return items.toString();
    }

    /**
     * test method for binary search method
     */
    public void testSearch(){
        int idToFind;
        int invReturn;
        int index;
        Scanner in = new Scanner(System.in);

        System.out.println("Testing search algorithm\n");
        do{
            System.out.println();
            System.out.print("Enter Id value to search for (-1 to quit) ---> ");
            idToFind = in.nextInt();
            //index = bsearch(new Item(idToFind, 0));
            //recursive version call
            index = bsearch (new Item(idToFind, 0), 0, items.size()-1);
            System.out.print("Id # " + idToFind);
            if (index == -1){
                System.out.println(" No such part in stock");
            }else{
                System.out.println(" Inventory = " + items.get(index).getInv());
            }
        } while (idToFind >= 0);
    }

    /**
     * Searches the specified ArrayList of Item Objects for the specified
     * id using a recursive binary search algorithm
     *
     * @param idToSearch Id value being search for
     * @param first Starting index of search range
     * @param last Ending index of search range
     * @return index of Item if found, -1 if not found
     */

    private int bsearch(Item idToSearch, int first, int last){
        int mid = (first + last) / 2;

        if (idToSearch.getId() == items.get(mid).getId()) return mid;
        else if (idToSearch.getId() < items.get(mid).getId()) return bsearch(idToSearch, first, mid);
        else if(idToSearch.getId() > items.get(mid).getId()) return bsearch(idToSearch, mid + 1, last);

        return -1;
    }

}
