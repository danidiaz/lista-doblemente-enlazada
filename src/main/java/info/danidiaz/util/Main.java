package info.danidiaz.util;

public class Main {

    public static void main(String[] args) {

        DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
        l.addFirst(0);
        l.addFirst(1);
        l.addFirst(2);
        System.out.println(l);
        System.out.println(l.size());
        l = new DoublyLinkedList<>();
        l.addLast(0);
        l.addLast(1);
        l.addLast(2);
        System.out.println(l);
        System.out.println(l.size());
    }

}
