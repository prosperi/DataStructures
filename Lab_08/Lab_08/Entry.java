
public class Entry{
    private String key;
    private Team value;
    
    public Entry(String key, Team value){
        this.key = key;
        this.value = value;
    }
    
    public String getKey(){
        return this.key;
    }
    
    public Team getValue(){
        return this.value;
    }
    
}
