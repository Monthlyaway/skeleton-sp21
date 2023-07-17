package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private Node sentinel;
    private int size;


    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next = newNode;
        newNode.next.prev = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = newNode;
        newNode.prev.next = newNode;
        size++;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node curr = sentinel.next;
        while (curr != sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println("");
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public T getFirst() {
        return sentinel.next.item;
    }

    @Override
    public T getLast() {
        return sentinel.prev.item;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Node curr) {
        if (index == 0) {
            return curr.item;
        }
        return getRecursiveHelper(index - 1, curr.next);
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
