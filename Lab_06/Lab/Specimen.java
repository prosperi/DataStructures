
public class Specimen
{
    private String type;
    private String subgroup;
    private String name;
    private double age;
    private double birth;
    
    public Specimen(String type, String subgroup, String name, double age, double birth){
        this.type = type;
        this.subgroup = subgroup;
        this.name = name;
        this.age = age;
        this.birth = birth;
    }
    
    public String getType(){
        return type;
    }
    
    public String getSubgroup(){
        return subgroup;
    }
    
    public String getName(){
        return name;
    }
    
    public double getAge(){
        return age;
    }
    
    public double getBirth(){
        return birth;
    }
    
    public String toString(){
        return  type + " " + subgroup + " " + name + " " + age + " " + birth;
    }
    
}
