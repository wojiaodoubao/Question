/**
 * 2016-9-8
 * 这题是没出完吧
 */
public class Main{
    public boolean isSubsequence(String s, String t) {
    	int i =0;
    	for(int j=0;i<s.length()&&j<t.length()&&t.length()-j>=s.length()-i;j++){
    		if(s.charAt(i)==t.charAt(j))
    			i++;
    	}   
    	return i==s.length();
    }	
	public static void main(String args[]){
		String s = "axc";
		String t = "ahbgdc";
		System.out.println(new Main().isSubsequence(s,t));
	}
}