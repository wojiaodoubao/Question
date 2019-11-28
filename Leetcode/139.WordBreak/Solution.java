import java.util.*;
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict.size() == 0 || s.length() == 0) {
            return false;
        }
        
        HashSet<String> set = new HashSet<>();
        int max = -1;
        int min = s.length() + 1;
        for (int i=0;i<wordDict.size();i++) {
            set.add(wordDict.get(i));
            if (wordDict.get(i).length() > max) {
                max = wordDict.get(i).length();
            }
            if (wordDict.get(i).length() < min) {
                min = wordDict.get(i).length();
            }
        }
        boolean[] stats = new boolean[s.length()];
        for (int i=0;i<stats.length;i++) {
            if (i < max && set.contains(s.substring(0, i+1))) {
                stats[i] = true;
                System.out.println(stats[i]);
                continue;
            }
            boolean found = false;
            for (int j=min;j<=max && j<=i;j++) {
               if (stats[i-j] && set.contains(s.substring(i-j+1, i+1))) {
                    found = true;
                    break;
                }
            }
            stats[i] = found;
        }
        return stats[s.length() - 1];
    }}
