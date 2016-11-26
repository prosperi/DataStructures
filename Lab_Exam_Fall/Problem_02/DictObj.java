import java.util.TreeSet;

public class DictObj
{
    private String key;
    private TreeSet<String> values;
    
    public DictObj(String key, String value){
        this.key = key;
        this.values =  new TreeSet(new StringComparator());
        values.add(value);
    }
    
    public String getKey(){
        return key;
    }
    
    public TreeSet<String> getValues(){
        return values;
    }
    
    public void add(String value){
        values.add(value);
    }
}
