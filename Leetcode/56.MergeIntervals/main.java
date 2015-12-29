import java.util.*;
/**
 * 2016-1-21
 * 好久没写，今天开始恢复，快排写得有点蠢，话说我以前有个漂亮的写法有效避免死循环，时间久了给忘记了，蛋疼。
 */
public class main{
	public main(){
		Interval[] i = new Interval[2];
		i[0] = new Interval(1,4);
		i[1] = new Interval(0,4);
		//i[2] = new Interval(15,18);
		//i[3] = new Interval(2,6);
		List<Interval> result = new ArrayList<Interval>();
		for(Interval x:i)
			result.add(x);
		result = merge(result);
		for(Interval x:result)
			System.out.println(x.start+" "+x.end);		
	}
	class Interval{
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }		
	}
	public List<Interval> merge(List<Interval> intervals) {
		if(intervals==null || intervals.size()<=0)
			return intervals;
		quick_sort(intervals,0,intervals.size()-1);
		List<Interval> result = new ArrayList<Interval>();
		int start=intervals.get(0).start;
		int end=intervals.get(0).end;
        for(int i=1;i<intervals.size();i++){
        	if(intervals.get(i).start<=end && intervals.get(i).end>end)
        		end = intervals.get(i).end;
        	else if(intervals.get(i).start>end){
        		result.add(new Interval(start,end));
        		start = intervals.get(i).start;
        		end = intervals.get(i).end;
        	}
        }
        result.add(new Interval(start,end));
        return result;
    }
    public void quick_sort(List<Interval> intervals,int P,int Q){
    	//sort according to interval.start
    	if(P>=Q)
    		return;
    	Interval index = new Interval(intervals.get(P).start,intervals.get(P).end);
    	int p = P;
    	int q = Q;
    	while(p<q){
    		while(p<q && intervals.get(p).start<=index.start)
    			p++;
    		while(p<q && intervals.get(q).start>index.start)
    			q--;
    		if(p>=q)break;
    		exchange(intervals,p,q);
    	}
    	if(intervals.get(p).start<index.start){
    		exchange(intervals,P,p);
    		quick_sort(intervals,P,p-1);
    		quick_sort(intervals,p+1,Q);
    	}
    	else{
    		quick_sort(intervals,P,p-1);
    		quick_sort(intervals,p,Q);
    	}
    }
    public void exchange(List<Interval> intervals,int p,int q){
    	/*
    	Interval tmp = (Interval)intervals.get(p).clone();
    	intervals.set(p,intervals.get(q));
    	intervals.set(q,tmp);
    	*/
    	int start = intervals.get(p).start;
    	int end = intervals.get(p).end;
    	intervals.get(p).start = intervals.get(q).start;
    	intervals.get(p).end = intervals.get(q).end;
    	intervals.get(q).start = start;
    	intervals.get(q).end = end;
    }
	public static void main(String args[]){
		main m = new main();
	}
}