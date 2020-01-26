package basic.queue.using_linkedlist;

/**
 * @author Narendra Jha, njha.sde@gmail.com
 *
 * Queue using LinkedList
 */
class Queue {
    
    // Template for node in LinkedList
    class Node {
        private int data;
        private Node link;
        
        // Constructor
        public Node(int data) {
            this.data = data;
            this.link = null;
        }
    }
    
    private Node front;
    private Node rear;
    
    public Queue() {
        front = rear = null;
    }
    
    public boolean isEmpty() {
        return (front == null && rear == null);
    }
    
    public void enqueue(int value) {
        Node node = new Node(value);
        if (isEmpty()) {
            // when queue is empty
            front = rear = node;
            return;
        }
        rear.link = node;
        rear = node;
    }
    
    public int dequeue() {
        int value;
        if (isEmpty()) {
            // when queue is empty
            throw new IllegalStateException("Queue is empty");
        }
        else if (front == rear) {
            // when queue has only one element
            // in that case, dequeue operation
            // should make queue empty
            value = front.data;
            front = rear = null;
        }
        else {
            value = front.data;
            front = front.link;
        }
        return value;
    }
    
    public int front() {
        if (isEmpty()) {
            // when queue is empty
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node pntr = front;
        while (pntr != null) {
            result.append(pntr.data).append(", ");
            pntr = pntr.link;
        }
        if (result.indexOf(",") != -1)
            result.delete(result.lastIndexOf(","), result.length());
        result.append("]");
        return result.toString();
    }
}

public class QueueTest {

    public static void main(String[] args) {
        Queue q = new Queue();
        q.enqueue(2);q.enqueue(4);q.enqueue(6);q.enqueue(8);
        System.out.println(q); // [2, 4, 6, 8]
        System.out.println(q.dequeue()); // 2
        System.out.println(q); // [4, 6, 8]
        System.out.println(q.front()); // 4
        System.out.println(q); // [4, 6, 8]
    }

}
