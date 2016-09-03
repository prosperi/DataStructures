import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Write a description of class Main here.
 * 
 * @author Zura Mestiashvili 
 * @version 1.0.0
 */
public class Main{
    
    // Line that should be copied to ouput file
    String outputLine;
   
    public static void main(String args[]){
        Main app = new Main();
        
        app.run(args[0], args[1]);
    }
    
    private void run(String input, String output){
        try(
            Scanner scanner = new Scanner(new File(input));
        ){
            
            this.outputLine = "";
            
            while(scanner.hasNext()){
                getWords(scanner.nextLine());
            }
            
            // Output the line
            PrintWriter outputFile = new PrintWriter(output, "UTF-8");
            outputFile.write(this.outputLine);
            outputFile.close();
            System.out.println(this.outputLine);
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    private void getWords(String line){
        String[] wordsArr = line.split(" ");
        for(int i = Integer.parseInt(wordsArr[0])+1; i < wordsArr.length; i++){
            this.outputLine += wordsArr[i] + " "; 
        }
    }
    
}
