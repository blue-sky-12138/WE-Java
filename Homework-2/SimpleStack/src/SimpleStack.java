public class SimpleStack {
    private ListNode head;
    private int size;

    private class ListNode{
        Object val;
        ListNode next;

        ListNode(){
            this.val = null;
            this.next = null;
        }

        ListNode(Object val){
            this.val = val;
            this.next = null;
        }
    }

    public int getSize() {
        return size;
    }

    SimpleStack(){
        this.head = null;
        this.size = 0;
    }

    SimpleStack(Object val){
        this.head = new ListNode(val);
        this.size = 1;
    }

    public boolean empty(){
        return this.size == 0;
    }

    public boolean push(Object val){
        if (this.size == 0){
            this.head = new ListNode(val);
        }else {
            var temp = head;
            for (int i = 0;i < this.size - 1;i++){
                temp = temp.next;
            }
            temp.next = new ListNode(val);
        }

        this.size++;
        return true;
    }

    public Object pop()throws StackOverflowError{
        if (this.size == 0){
            throw new StackOverflowError();
        }

        Object result;
        if (this.size ==1){
            result = head.val;
            head = null;
        }else {
            var temp = head;
            for (int i = 0;i < this.size - 2;i++){
                temp = temp.next;
            }
            result = temp.next.val;
            temp.next = null;
        }

        this.size--;
        return result;
    }

    public Object peek(){
        Object result;

        if (this.size ==1){
            result = head.val;
        }else {
            var temp = head;
            for (int i = 0;i < this.size - 1;i++){
                temp = temp.next;
            }
            result = temp.val;
        }

        return result;
    }

    public boolean search(Object val){
        var temp = head;
        if (val.equals(temp.val)){
            return true;
        }

        for (int i = 0;i < this.size - 1;i++){
            temp = temp.next;
            if (val.equals(temp.val)){
                return true;
            }
        }
        return false;
    }
}
