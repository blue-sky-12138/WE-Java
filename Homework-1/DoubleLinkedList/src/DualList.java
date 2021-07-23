import java.util.Objects;

public class DualList {
    private class Node{
        private Node previous;
        private Node next;
        private Object value;

        Node(Object value){
            this.value = value;
        }

        Node(){
            this.previous = null;
            this.next = null;
            this.value = null;
        }

        @Override
        public String toString() {
            return this.value.toString();
        }
    }

    private Node head;
    private int size;

    DualList(){
        this.head = null;
        size = 0;
    }

    DualList(Object value){
        this.head = new Node(value);
        size = 1;
    }

    public int getSize() {
        return size;
    }

    private boolean isEmpty(){
        return head == null;
    }

    public boolean add(Object value){
        return this.addLast(value);
    }

    public boolean addLast(Object value){
        if(isEmpty()){
            this.head = new Node(value);
            this.size++;
            return true;
        }else {
            Node last = head;
            for(;last.next != null;last = last.next){
            }
            return addAfter(last,value);
        }
    }

    public boolean addFirst(Object value){
        if(isEmpty()){
            this.head = new Node(value);
            this.size++;
            return true;
        }else {
            return addBefore(this.head,value);
        }
    }

    private boolean addBefore(Node node,Object value){
        var newNode = new Node(value);

        newNode.previous = node.previous;
        if(node.previous != null){
            node.previous.next = newNode;
        }
        newNode.next = node;
        node.previous = newNode;

        this.size++;
        this.head = newNode;
        return true;
    }

    private boolean addAfter(Node node,Object value){
        var newNode = new Node(value);

        newNode.next = node.next;
        if(node.next != null){
            node.next.previous = newNode;
        }
        node.next = newNode;
        newNode.previous = node;

        this.size++;
        return true;
    }

    public Object findByIndex(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException(index);
        }

        Node find = head;
        for (int i = 0;i < index;i++){
            find = find.next;
        }
        return find.value;
    }

    public int findByObject(Object value){
        Node find = head;
        for (int i = 0;i < size;i++){
            if(Objects.equals(find.value,value)){
                return i;
            }
            find = find.next;
        }
        return -1;
    }

    public boolean deleteFirst(){
        this.head = this.head.next;
        if (!this.isEmpty()){
            this.head.previous = null;
        }
        this.size--;
        return true;
    }

    public boolean deleteLast(){
        Node last = this.head;
        for (int i = 0; i < this.size - 1; i++) {
            last = last.next;
        }
        last.previous.next = null;
        this.size--;
        return true;
    }

    public boolean deleteByIndex(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException(index);
        }

        if (index == 0){
            return this.deleteFirst();
        }else if (index == this.size - 1){
            return this.deleteLast();
        }

        Node delete = this.head;
        for (int i = 0; i < index; i++) {
            delete = delete.next;
        }
        delete.previous.next = delete.next;
        delete.next.previous = delete.previous;
        this.size--;
        return true;
    }

    public boolean deleteByObject(Object value){
        int deleteIndex = this.findByObject(value);
        return this.deleteByIndex(deleteIndex);
    }

    public void println(){
        Node print = this.head;
        for (int i = 0; i < this.size; i++) {
            System.out.printf("%d:%s\n",i,print.toString());
            print = print.next;
        }
    }
}
