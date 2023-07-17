package deque;


public class ArrayDeque<T> implements Deque<T> {
    T[] array;
    int size;
    int front;
    int rear;

    public ArrayDeque() {
        this(8);
    }

    public ArrayDeque(int maxSize) {
        array = (T[]) new Object[maxSize + 1];
        size = 0;
        front = array.length / 2;
        rear = front - 1;
    }
    @Override
    public void addFirst(T item) {
        if (isFull()) {
            resize(size * 4);
        }
        if (front == 0) {
            front = array.length;
        }
        array[--front] = item;
        size++;
    }

    public boolean isFull() {
        return front + array.length - 2 == rear ||
                rear + 2 == front;
    }

    public void resize(int capacity) {
        if (size >= capacity) {
            System.out.println("Error: Can not resize ArrayDeque into a smaller capacity, possible data loss");
            return;
        }
        T[] newArray = (T[]) new Object[capacity + 1];
        if (front <= rear) {
            System.arraycopy(array, front, newArray, 0, rear - front + 1);
            int temp = front;
            front = 0;
            rear = front + rear - temp;
        } else {
            System.arraycopy(array, 0, newArray, 0, rear + 1);
            System.arraycopy(array, front, newArray, newArray.length - 1 - (array.length - 1 - front), array.length - front);
            front = newArray.length - 1 - (array.length - 1 - front);
        }
        array = newArray;

    }
    @Override
    public void addLast(T item) {
        if (isFull()) {
            resize(size * 4);
        }
        if (rear == array.length - 1) {
            rear = -1;
        }
        array[++rear] = item;
        size++;

    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("Deque is empty");
        }
        int index = front;
        while ((index % array.length) != rear) {
            System.out.print(array[index % array.length] + " ");
            index++;
        }
        System.out.println(array[rear]);
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if ((size < array.length / 4) && (size > 16)) {
            resize(array.length / 4);
        }
        T result = array[front++];
        if (front == array.length) {
            front = 0;
        }
        size--;
        return result;

    }
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if ((size < array.length / 4) && (size > 16)) {
            resize(array.length / 4);
        }
        T result = array[rear--];
        if (rear == -1) {
            rear = array.length - 1;
        }
        size--;
        return result;

    }
    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        return array[(front + index) % array.length];
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            return null;
        }
        return array[rear];
    }
    @Override
    public T getFirst() {
        if (isEmpty()) {
            return null;
        }
        return array[front];
    }
}