import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.NumberFormatException;

/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{
    
    private double tax;
    private LinkedHashMap<String, Double> goods;
    
    public static void main(String[] args){
        
        Main app = new Main();
        
        app.tax = 0;
        app.goods = new LinkedHashMap<String, Double>();
        
        app.survey();
    }
    
    private void survey(){
        
        try(
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ){
            System.out.println("Please, enter tax rate:");
            // Make sure this is double, if not catch exception
            this.tax = getDouble(reader);
            
            String tmpName, answerYN;
            Double tmpPrice;
            
            while(true){
                System.out.println("Do you want to continue?(yes/no)");
                answerYN = reader.readLine();
                if(answerYN.equals("no")){
                    break;
                }else if(answerYN.equals("yes")){
                    System.out.println("Enter the name of the goods:");
                    tmpName = reader.readLine();
                    System.out.println("Enter the price for this goods:");
                    tmpPrice = getDouble(reader);
                    
                    this.goods.put(tmpName, tmpPrice);
                }
            }
            
            Set keys = this.goods.keySet();
            Iterator<String> iterator = keys.iterator();
            String currentName;
            double currentPrice, taxForCurrent;
            String taxForGoods, totalPrice;
            
            System.out.println("Name:\t\tPrice:\t\tTaxes:\t\tTotal:");
            
            while(iterator.hasNext()){
                currentName = iterator.next();
                currentPrice = this.goods.get(currentName);
                if(currentName.matches("(?i)^food$") || currentName.matches("(?i)^clothing$")){
                    taxForCurrent = 0;
                }else{
                    taxForCurrent = this.tax;
                }
                taxForGoods = Double.toString(currentPrice*taxForCurrent);
                totalPrice = Double.toString(currentPrice + currentPrice*taxForCurrent);
                System.out.println(currentName + "\t\t" + currentPrice + "\t\t" + 
                                   (taxForGoods.length() > 7 ? taxForGoods.substring(0,7) : taxForGoods) + "\t\t" + 
                                   (totalPrice.length() > 7 ? totalPrice.substring(0,7) : totalPrice));
            }
           
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    // Check to get Double value when  needed
    private Double getDouble(BufferedReader reader){
        double dbl;
        while(true){
            try{
                dbl = Double.parseDouble(reader.readLine());
                break;
            }catch(NumberFormatException e){
                System.out.println("Please, enter numerical value:");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return dbl;
    }
   
}
