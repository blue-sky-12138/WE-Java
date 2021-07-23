import java.util.Queue;
import java.util.Stack;

public class Calculator {
    private Stack<Object> suffixStack;
    private Stack<String> operateStack;

    //计算操作接口
    private interface Operate {
        double getResult(double num1, double num2);
    }

    //加法
    private static class addOperate implements Operate {
        @Override
        public double getResult(double num1, double num2) {
            return num1 + num2;
        }
    }

    //减法
    private static class subOperate implements Operate {
        @Override
        public double getResult(double num1, double num2) {
            return num1 - num2;
        }
    }

    //乘法
    private static class mutOperate implements Operate {
        @Override
        public double getResult(double num1, double num2) {
            return num1 * num2;
        }
    }

    //除法
    private static class divOperate implements Operate {
        @Override
        public double getResult(double num1, double num2) {
            return num1 / num2;
        }
    }

    //取模
    private static class modOperate implements Operate{
        @Override
        public double getResult(double num1, double num2){
            return (int)num1 % (int)num2;
        }
    }

    //计算工厂接口
    private interface CalculateFactory {
        Operate createOperate();
    }

    //加法工厂
    private static class addFactory implements CalculateFactory{
        @Override
        public Operate createOperate() {
            return new addOperate();
        }
    }

    //减法工厂
    private static class subFactory implements CalculateFactory{
        @Override
        public Operate createOperate(){
            return new subOperate();
        }
    }

    //乘法工厂
    private static class mutFactory implements CalculateFactory{
        @Override
        public Operate createOperate(){
            return new mutOperate();
        }
    }

    //除法工厂
    private static class divFactory implements CalculateFactory{
        @Override
        public Operate createOperate(){
            return new divOperate();
        }
    }

    //取模工厂
    private static class modFactory implements CalculateFactory{
        @Override
        public Operate createOperate(){
            return new modOperate();
        }
    }

    public Calculator(){
        this.suffixStack = new Stack<>();
        this.operateStack = new Stack<>();
    }

    //分析输入的数据，使其中缀表达式变成后缀表达式压入后缀栈
    public void analyseRaw(String s) throws IllegalArgumentException{
        if (s.equals("")) {
            throw new IllegalArgumentException();
        }

        String raw = s.replace(" ",""), nextOperate;
        int lastIndex = 0, sLength = raw.length();
        //判断上一个对象是数据
        boolean lastIsDigit = false;

        for (int i = 0; i < sLength; i++) {
            char temp = raw.charAt(i);
            //判断是否是数据
            if ((temp >= '0' && temp <= '9') || temp == '.'){
                lastIsDigit = true;
                //如果最后一个字符是数据，直接压栈
                if (i >= sLength - 1){
                    suffixStack.push(Double.valueOf(raw.substring(lastIndex)));
                    break;
                }else {
                    continue;
                }
            }

            //判断是否是操作符或小括号
            if ("+-*/%()".contains(String.valueOf(temp))){
                //如果最后一个字符是操作符且不是右括号，丢弃
                if (i >= sLength - 1 && !raw.substring(i).equals(")")){
                    break;
                }

                if (lastIsDigit){
                    suffixStack.push(Double.valueOf(raw.substring(lastIndex,i)));
                    lastIsDigit = false;
                }
                lastIndex = i + 1;

                nextOperate = raw.substring(i,i+1);
                if (nextOperate.equals("+") || nextOperate.equals("-")){
                    for (;!operateStack.isEmpty() && !operateStack.peek().equals("(");){
                        suffixStack.push(operateStack.pop());
                    }
                    operateStack.push(nextOperate);
                }else if (nextOperate.equals("*") || nextOperate.equals("/") || nextOperate.equals("%")){
                    operateStack.push(nextOperate);
                }else if (nextOperate.equals("(")) {
                    operateStack.push(nextOperate);
                }else if (nextOperate.equals(")")){
                    for (String lastOperate = operateStack.pop();!lastOperate.equals("(");lastOperate = operateStack.pop()){
                        //如果栈空了还没遇到右括号，则输入数据有问题
                        if (operateStack.isEmpty()){
                            throw new IllegalArgumentException();
                        }
                        suffixStack.push(lastOperate);
                    }
                }

                continue;
            }

            //如果包含非法字符
            throw new IllegalArgumentException();
        }

        //将操作符栈中剩余运算符压入后缀栈
        for (;!operateStack.isEmpty();){
            suffixStack.push(operateStack.pop());
        }
    }

    public void printlnSuffixStack(){
        for (;!suffixStack.isEmpty();) {
            Object ob = suffixStack.pop();
            System.out.println(ob.getClass().getName()+":"+ob.toString());
        }
    }

    public double getResult() throws IllegalArgumentException{
        Stack<Object> reverse = new Stack<>();
        double num1, num2;

        //倒置栈
        for (;!suffixStack.isEmpty();){
            reverse.push(suffixStack.pop());
        }

        for (;!reverse.isEmpty();){
            Object object = reverse.pop();
            if (object instanceof Double){
                suffixStack.push(object);
                continue;
            }

            Operate operate;
            String s = (String) object;
            operate = switch (s) {
                case "*" -> new mutFactory().createOperate();
                case "/" -> new divFactory().createOperate();
                case "+" -> new addFactory().createOperate();
                case "-" -> new subFactory().createOperate();
                case "%" -> new modFactory().createOperate();
                default -> throw new IllegalArgumentException();
            };


            if (suffixStack.isEmpty()){
                throw new IllegalArgumentException();
            }else {
                num2 = (Double) suffixStack.pop();
            }
            if (suffixStack.isEmpty()){
                throw new IllegalArgumentException();
            }else {
                num1 = (Double) suffixStack.pop();
            }

            suffixStack.push(operate.getResult(num1,num2));
        }

        var result = (Double) suffixStack.pop();    //计算结束后，栈里只有一个最终结果
        if (!suffixStack.isEmpty()) {       //如果计算结束栈不为空，则表示有操作符缺失
            throw new IllegalArgumentException();
        }

        return result;
    }

    public void reNew(){
        this.suffixStack = new Stack<>();
        this.operateStack = new Stack<>();
    }
}
