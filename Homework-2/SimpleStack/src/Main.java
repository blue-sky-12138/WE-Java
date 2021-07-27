public class Main {
    public static void main(String[] args){
        var x = new SimpleStack();
        x.push(1);
        x.push(2);
        x.push(3);
        System.out.println(x.peek());
        System.out.println(x.pop());
        System.out.println(x.search(1));
    }
}
