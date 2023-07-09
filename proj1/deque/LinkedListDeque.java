package deque;

public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;


    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next = newNode;
        newNode.next.prev = newNode;
        size++;
    }

    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = newNode;
        newNode.prev.next = newNode;
        size++;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node curr = sentinel.next;
        while (curr != sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println("");
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node toRemove = sentinel.next;
        sentinel.next = toRemove.next;
        toRemove.next.prev = sentinel;
        size--;

        return toRemove.item;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node toRemove = sentinel.prev;
        toRemove.prev.next = sentinel;
        sentinel.prev = toRemove.prev;
        size--;

        return toRemove.item;
    }

    public T get(int index) {
        int ix = 0;
        Node curr = sentinel.next;
        while (curr != sentinel && ix < index) {
            ix++;
            curr = curr.next;
        }
        if (curr == sentinel) {
            return null;
        }
        return curr.item;

    }

    public T getRecursive(int index) {
        getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Node curr) {
        if (index == 0) {
            return curr.item;
        }
        getRecursiveHelper(index - 1, curr.next);
    }

    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }
}
