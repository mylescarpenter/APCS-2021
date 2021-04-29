import java.util.ArrayList;

/**
 * provides methods that create, hold, and allow manipulation of data about an item
 * @author Myles Carpenter
 * date - March 20, 2020
 */
public class Item/* extends Object */implements Comparable<Item>{
    private int myId;
    private int myInv;

    /**
     * instantiates an Item object and assigns it id and inventory values as specified in parameters
     * @param id - id number of item
     * @param inv - inventory count of item
     */
    public Item(int id, int inv){
        myId = id;
        myInv = inv;
    }
    /**
     * getter method for myId
     * @return value of id as an integer
     */
    public int getId(){return myId;}

    /**
     * getter method for myInv
     * @return value of inventory count as an integer
     */
    public int getInv(){return myInv;}

    /**
     * compares the ids of two Items
     * @param o - other Item
     * @return 1 if the first id has a greater id value, -1 if the first id has a lesser id value, and 0 if they are equivalent
     */
    public int compareTo(Item o) {
        return Integer.compare(myId, o.getId());
    }

    /**
     * tells if two Items have equivalent id numbers
     * @param other - Item to compare to
     * @return true if the two Item's id numbers are the same, else returns false
     */
    public boolean equals(Item other){
        if(Integer.compare(myId, other.getId()) == 0) return true;
        else return false;
    }

    /**
     * @return the id and inventory count of said Item
     */
    public String toString(){
        return "Id: " + myId + " Inv: " + myInv;
    }
}
