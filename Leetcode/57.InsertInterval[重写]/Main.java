import java.util.*;
/**
 * 2016-3-13
 * 多情况的这种,好弱,各种打补丁才把用例都过了,估计程序还是不对,只不过把测试用例过了
 * 得重写!
 */
class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
}
public class Main{
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    	if(newInterval==null)
    		return intervals;
    	if(intervals==null)
    		return intervals;
        ArrayList<Interval> result = new ArrayList<Interval>();
        if(intervals.size()==0){
        	result.add(newInterval);
        	return result;
        }
        int left = 0;
        boolean left_Ready = false;
        if(newInterval.start<intervals.get(0).start){
        	left=newInterval.start;
        	left_Ready=true;
        }
        int right = 0;
        boolean right_Ready = false;
        int i=0;
        //打补丁
        if(left_Ready && newInterval.end<intervals.get(0).start){
            result.add(newInterval);
            result.addAll(intervals);
            return result;
        }//补丁结束
        for(i=0;i<intervals.size()-1;i++){      	
        	if(!left_Ready){
        		if(intervals.get(i).start<=newInterval.start && intervals.get(i).end>=newInterval.start){
        			left=intervals.get(i).start;
        			left_Ready=true;
        			i--;
        			continue;
        		}
        		else if(intervals.get(i).end<newInterval.start && newInterval.start<=intervals.get(i+1).start){
        			left=newInterval.start;
        			left_Ready=true;
        		}
        		result.add(intervals.get(i));
        	}
        	else if(!right_Ready){
        		if(intervals.get(i).start<=newInterval.end && intervals.get(i).end>=newInterval.end){
        			right=intervals.get(i).end;
        			right_Ready=true;
        		}
        		else if(intervals.get(i).end<=newInterval.end && newInterval.end<intervals.get(i+1).start){
        			right=newInterval.end;
        			right_Ready=true;
        		}
                else if(newInterval.end<intervals.get(i).start){//打补丁
                    right=newInterval.end;
                    right_Ready=true; 
                    result.add(new Interval(left,right));
                    result.add(intervals.get(i)); 
                    continue;                  
                }//补丁结束
        		if(left_Ready&&right_Ready){
        			result.add(new Interval(left,right));
        		}
        	}
        	else{//left_Ready&&right_Ready==true
        		result.add(intervals.get(i));
        	}
        }
        if(left_Ready&&right_Ready){
        	result.add(intervals.get(i));
        	return result;
        }
        else if(left_Ready && !right_Ready){
	        if(intervals.get(i).start<=newInterval.end && intervals.get(i).end>=newInterval.end){
	        	right=intervals.get(i).end;
	        }
	        else if(newInterval.end<intervals.get(i).start){
	        	right = newInterval.end;
	        	right_Ready=true;
	        }
	        else{
	        	right = newInterval.end;
	        }
			result.add(new Interval(left,right));
			if(left_Ready&&right_Ready)
				result.add(intervals.get(i));
			return result;
        }
        else{//!left_Ready&&!right_Ready==true
        	if(newInterval.start>=intervals.get(i).start&&newInterval.start<=intervals.get(i).end){
        		left=intervals.get(i).start;
        	}
        	else{
        		left=newInterval.start;
        		left_Ready = true;
        	}
        	if(newInterval.end>=intervals.get(i).start&&newInterval.end<=intervals.get(i).end){
        		right=intervals.get(i).end;
        	}
        	else{
        		right = newInterval.end;
        		right_Ready = true;
        	}
        	if(left_Ready&&right_Ready)
        		result.add(intervals.get(i));
			result.add(new Interval(left,right));
			return result;
        }
    }
	public static void main(String args[]){
		Main main = new Main();
		List<Interval> list = new ArrayList<Interval>();
		/*
		list.add(new Interval(1,2));
		list.add(new Interval(3,5));
		list.add(new Interval(6,7));
		list.add(new Interval(8,10));
		list.add(new Interval(12,16));
		list = main.insert(list,new Interval(4,9));
		*/
		/*
		list.add(new Interval(1,5));	
		list = main.insert(list,new Interval(6,8));	
		*/
        /*
		list.add(new Interval(1,5));	
		list = main.insert(list,new Interval(0,0));
        */
        /*
        list.add(new Interval(2,5));
        list.add(new Interval(6,7));
        list.add(new Interval(8,9));
        list = main.insert(list,new Interval(0,1)); 
        */ 
        list.add(new Interval(0,5));
        list.add(new Interval(9,12)); 
        list = main.insert(list,new Interval(7,16));       
		for(Interval i : list){
			System.out.println("["+i.start+","+i.end+"]");
		}
	}
}