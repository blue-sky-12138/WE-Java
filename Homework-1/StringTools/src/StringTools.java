import java.util.ArrayList;

public class StringTools {
    public boolean isDigit(String s){
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9'){
                return false;
            }
        }
        return true;
    }

    public boolean isUpper(String s){
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < 'A' || c > 'Z'){
                return false;
            }
        }
        return true;
    }

    public boolean isLower(String s){
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < 'a' || c > 'z'){
                return false;
            }
        }
        return true;
    }

    public boolean isAlpha(String s){
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))){
                return false;
            }
        }
        return true;
    }


    public String toUpper(String s){
        StringBuffer res = new StringBuffer();
        if (this.isAlpha(s)){
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isLowerCase(c)){
                    c = Character.toUpperCase(c);
                }
                res.append(c);
            }
        }
        return res.toString();
    }

    public String[] split(String s,char patter){
        return s.split(Character.toString(patter));
    }

    public int[] splitToDigitArray(String s){
        if (!this.isDigit(s)){
            return null;
        }

        char[] chs = s.toCharArray();
        int[] res = new int[chs.length];
        for (int i = 0; i < chs.length; i++) {
            res[i] = chs[i] - '0';
        }

        for (int i = 0; i < chs.length; i++) {
            for (int j = i + 1; j < chs.length; j++) {
                if (res[i] > res[j]){
                    int temp = res[i];
                    res[i] = res[j];
                    res[j] = temp;
                }
            }
        }

        return res;
    }

    public char[] splitToCharArray(String s){
        if (!isAlpha(s)){
            return null;
        }

        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            for (int j = i + 1; j < chs.length; j++) {
                if (chs[i] > chs[j]){
                    char temp = chs[i];
                    chs[i] = chs[j];
                    chs[j] = temp;
                }
            }
        }

        return chs;
    }
}
