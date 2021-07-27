public class List {
      public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
      }

    public ListNode reverseList(ListNode head) {
        if (head == null){
            return null;
        }

        var result = new ListNode(head.val);
        for(ListNode temp = result;head.next != null;){
            head = head.next;
            temp = new ListNode(head.val);
            temp.next = result;
            result = temp;
        }
        return result;
    }
}
