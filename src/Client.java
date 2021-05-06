

public class Client {

        public static void main(String args[]){

                SinglyLinkedList numList = new SinglyLinkedList();

                for(int i = 1; i <= 20; ++i){
                        numList.addLast(i);
                }

                System.out.println("First element: " + numList.getFirst().getValue());
                System.out.println("Last element: " + numList.getLast().getValue());
                numList.printList();
                System.out.println("Size: " + numList.getSize());

        }
}
