import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

public class Dict
{
    private LinkedList<DictObj> ll;
    
    public Dict(){
        ll = new LinkedList<DictObj>();
    }
    
    public void add(String key, String value){
        boolean contains = false;
        // find if key already exists
        for(int i = 0; i < ll.size(); i++){
            if(ll.get(i).getKey().equals(key)){
                contains = true;
                ll.get(i).add(value);
                break;
            }
        }
        
        if(!contains){
            DictObj tmp = new DictObj(key, value);
            ll.add(tmp);
        }
        
    }
    
    public ArrayList<String> find(String key){
        for(int i = 0; i < ll.size(); i++){
            if(ll.get(i).getKey().equals(key)){
                Iterator iterator = ll.get(i).getValues().iterator();
                ArrayList<String> tmp = new ArrayList<String>();
                while(iterator.hasNext()){
                    tmp.add((String)iterator.next());
                }
                return tmp;
            }
        }
        
        return new ArrayList<String>();
    }
}
