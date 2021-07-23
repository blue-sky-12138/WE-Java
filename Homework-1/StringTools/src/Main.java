import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        var tool = new StringTools();
        var raw = "acbdw,1269547,AASIDX,AIUydjs,12sjaA,3819247,ausSHSzio,IUFISsi";
        System.out.println(tool.isDigit("01652612356"));
        System.out.println(tool.toUpper("fdafdFDEFD"));
        System.out.println(Arrays.toString(tool.split(raw, ',')));
        System.out.println(Arrays.toString(tool.splitToDigitArray("456789132")));
        System.out.println(Arrays.toString(tool.splitToCharArray("fdasFGDAT")));
    }
}
