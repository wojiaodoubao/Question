import java.util.*;

public class Main{
    class Node{
        char c;
        Node next;
        public Node(char c){
            this.c = c;
        }
    }
    public boolean isValid(String s) {
        Node stack = new Node('a');
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('||s.charAt(i)=='['||s.charAt(i)=='{'){
                Node node = new Node(s.charAt(i));
                node.next=stack.next;
                stack.next=node;
            }
            else{
                if(stack.next==null){
                    return false;
                }
                Node node = stack.next;
                stack.next = node.next;
                if(!match(node.c,s.charAt(i))){
                    return false;
                }
            }
        }
        if(stack.next==null)
            return true;
        else
            return false;
    }  
    private boolean match(char c1,char c2){
        if(c1=='('&&c2==')')
            return true;
        if(c1=='['&&c2==']')
            return true;
        if(c1=='{'&&c2=='}')
            return true;
        return false;                    
    }  
    public static void main(String args[]){
        System.out.println(new Main().isValid("()[]"));
    }
}