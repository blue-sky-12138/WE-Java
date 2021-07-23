import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Calculator cal = new Calculator();
        String raw;
        Scanner in = new Scanner(System.in);
        for (;;){
            System.out.println("请输入您要计算的表达式：");
            raw = in.nextLine();
            try {
                cal.analyseRaw(raw);
                double result = cal.getResult();
                System.out.println("= "+result);
            }catch (IllegalArgumentException exception){
                cal.reNew();
                System.out.println("您输入的数据不合法！");
            }
        }
    }
}
