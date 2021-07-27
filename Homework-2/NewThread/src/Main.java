public class Main {
    public static void main(String[] args) throws InterruptedException {
        printNumberFirst.quickStart();
        System.out.println();

        Thread.sleep(3000);

        printNumberSecond.quickStart();
        System.out.println();

        Thread.sleep(3000);

        printNumberThird.quickStart();
        System.out.println();
    }
}
