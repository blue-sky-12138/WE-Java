public class CustomInteger {
    private int val;

    CustomInteger(){
        this.val = 0;
    }

    CustomInteger(int val){
        this.val = val;
    }

    public int getVal() {
        return this.val;
    }

    public void autoIncrease(){
        this.val++;
    }
}
