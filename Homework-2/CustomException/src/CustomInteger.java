public class CustomInteger {
    private int val;
    public static int min = 1<<31;
    public static int max = 0x7fffffff;

    CustomInteger(){}
    CustomInteger(int val){this.val = val;}

    public void add(int mount)throws CustomException{
        if (val > max - mount){
            throw new CustomException();
        }
        this.val += mount;
    }

    public void sub(int mount)throws CustomException{
        if (val < min - mount){
            throw new CustomException();
        }
        this.val -= mount;
    }

    public int getVal() {
        return val;
    }
}
