public class Main {
    public static void main(String[] args){
        DualList list = new DualList();
        list.add(1);
        list.add(5);
        list.addFirst(4);
        list.add(12);
        list.add(56);
        list.println();
        System.out.println(list.findByIndex(3));
        System.out.println(list.findByObject(4));
        list.deleteFirst();
        list.deleteLast();
        list.deleteByIndex(2);
        list.deleteByObject(1);
        list.println();
    }
}
