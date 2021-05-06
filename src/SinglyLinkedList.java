public class SinglyLinkedList {

    private ListNode first;
    private ListNode last;

    public SinglyLinkedList() {
        first = null;
        last = null;
    }

    public SinglyLinkedList(ListNode node){
        addLast(node);
    }
    public SinglyLinkedList(Object value){
        addLast(new ListNode(value, null));
    }

    public void addLast(Object value){
        if(last == null) {
            last = new ListNode(value, null);
            first = last;
        }else{
            last.setNext(new ListNode(value, null));
            last = last.getNext();
        }
    }

    /**
     * returns integer value that represents the number of elements in list
     * @return size of linked list
     */
    public int getSize() {
        ListNode temp;
        int count = 0;
        if (first != null) {
            count = 1;
            temp = first;
            while (temp.getNext() != null) {
                temp = temp.getNext();
                ++count;
            }
        }
        return count;
    }

    /**
     * prints value of each element in a single line
     */
    public void printList(){
        ListNode temp = first;
        if(temp != null){

            while (temp.getNext() != null) {
                System.out.print(temp.getValue() + " ");
                temp = temp.getNext();
            }
            System.out.println(temp.getValue());
        }
    }
    /**
     * getter method for first reference
     * @return first ListNode
     */
    public ListNode getFirst() {
        return first;
    }

    /**
     * getter method for last reference
     * @return last ListNode
     */
    public ListNode getLast() {
        return last;
    }

}
/*
/Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=64188:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/mylescarpenter/IdeaProjects/APComputerScience/out/production/APComputerScience Client
First element: 1
Last element: 20
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
Size: 20

Process finished with exit code 0

 */