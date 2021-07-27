import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        var result = new ArrayList<String>();
        var map = new HashMap<String,Integer>();

        var list = s1.split(" ");
        for (String word: list) {
            map.put(word,map.getOrDefault(word,0)+1);
        }
        list = s2.split(" ");
        for (String word: list){
            map.put(word,map.getOrDefault(word,0)+1);
        }

        var entry = map.entrySet();
        for (var temp:entry){
            if (temp.getValue() == 1){
                result.add(temp.getKey());
            }
        }

        return result.toArray(new String[result.size()]);
    }
}
