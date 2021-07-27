public class Main {
    public static void main(String[] args) throws CustomException {
        CustomInteger x = new CustomInteger(1 << 30);
        try {
            x.add(1<<30);
        }
        catch (CustomException e){
            System.out.println("数据溢出！");
        }
    }
}
