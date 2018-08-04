import java.util.*;
import java.io.*;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Iterator;

public class groceryStore{

   private Scanner groceryListReader;
   final List<String> groceriesArraylist = new ArrayList<String>();
   final List<String> sortedGroceriesArraylist = new ArrayList<String>();
   
   

   final List<String> importedGroceriesArraylist = new ArrayList<String>();
   final List<String> unimportedGroceriesArraylist = new ArrayList<String>();


   
   //this methods opens the groceryList file 
      /*
      public void openFile(){
         
         try{
            groceryListReader = new Scanner(new File("grocery_list.txt"));
         }
         catch (Exception e){
       		System.out.println("Could not find file");
       	}
         
      }
      
      
      //this method reads the contents from the groceryList file and puts them 
      //into a arraylist of strings

      /*
      public void readFile(){
      
         boolean whiteSpace = false;
      
             
         while(groceryListReader.hasNextLine()){
         
              String groceriesFromFile = groceryListReader.nextLine();
              //groceryListReader.useDelimiter("\\Z"); 
              //groceriesArraylist.add(groceriesFromFile);
              
              
              if (groceriesFromFile.contains("\n")){
                  whiteSpace = true;
                  System.out.println(whiteSpace);
              }
              else{
                  //System.out.println(groceriesFromFile);
                  groceriesArraylist.add(groceriesFromFile);
             
                  System.out.println(groceriesArraylist);


          
              }              
              
              
              
             //System.out.println(groceriesFromFile);
             
             
             //System.out.println(groceryListReader.nextLine());
             
             //groceriesArraylist.add(groceriesFromFile);
             
             //System.out.println(groceriesArraylist);
        
         }
                
      }
      */
      
      //this methods opens the groceryList file 
      //this method reads the contents from the groceryList file and puts them 
      //into a arraylist of strings

      public void readFile(){
            try{
               BufferedReader groceryListReader = new BufferedReader(new FileReader("grocery_list.txt"));
               String groceriesFromFile = null;
                           
               while((groceriesFromFile = groceryListReader.readLine()) != null){
                    
                        if(groceriesFromFile.contains("imported")){
                        
                           //System.out.println(groceriesFromFile);
                           importedGrocerySorter(groceriesFromFile);
                        
                        }
                        else{
                        
                           unimportedGrocerySorter(groceriesFromFile);
                           
                        
                        } 
                        
                        
                        //groceriesArraylist.add(groceriesFromFile);
                       //System.out.println(groceriesArraylist);
                    
                   
                     //System.out.println(groceryListReader);
                    //System.out.println(groceriesFromFile);
                    //System.out.println(groceriesArraylist);

                    //grocerySorter(groceriesArraylist);
               } 
               //groceriesArraylist.add(groceriesFromFile);
               //System.out.println(groceryListReader);
               //System.out.println(groceriesArraylist);


                                     
  
            }
            catch(Exception e){
            
               System.out.println("Could not find file");

            }

         }
         
         
         
         /*
         
         //public void grocerySorter(List <String> x){
         
         public void grocerySorter(String x){

            String keyWord = "1 imported";
            int importedPosition=0;
            Iterator<String> groceryIterator = x.iterator();
                     
            //for (int i =0; i<x.length; i++){
            
            while (groceryIterator.hasNext()) {           
               //if (x.equals(keyWord)){
               if (groceryIterator.next().contains(keyWord)){
                  System.out.println(groceryIterator.next() + "\nposition: "+importedPosition);
                  
                 }
                 importedPosition++;
            }
               
            
         
         }
         */
         
         public void importedGrocerySorter(String imported){
         
            
            importedGroceriesArraylist.add(imported);
            
            /*
            Pattern p = Pattern.compile("([0-9])");
            Matcher m = p.matcher(imported);
           
            if(m.find()){
               System.out.println("Hello "+m.find());
            }
            /*
            if(imported.matches(".*\\d+.*")){
               System.out.println(imported);
            }
            
            */
            //System.out.println(imported);
            //System.out.println("////////////////////\n"+importedGroceriesArraylist);


            //this scanner will return the 1st integer in the string(ie the amount of 
            try{
            
               //Scanner scanner = new Scanner(imported).useDelimiter("\\D+").nextInt();
            
                //while(scanner.hasNextInt())
                //this extracts the quantity(ie the 1st number on each string) from the grocery list
                  int res = new Scanner(imported).useDelimiter("\\D+").nextInt();
                  
                  
                 //System.out.println("Imported Quantity: " +res);
                 
                 double importedPrices = 0.0;
            
                  //this pattern extracts the doubles from the strings from the text files
                  Pattern p = Pattern.compile("\\d+\\.\\d+");
                  Matcher m = p.matcher(imported);
                        
            while(m.find()){
            
               importedPrices = Double.parseDouble(m.group(0));
               
               //System.out.println("Price: " +m.group(0));
               //System.out.println("Price: " + importedPrices);

            }

                 
                 
                 

            }
            catch(Exception e){
            
               System.out.println("Could not find number");
            }
         
         
         
         }
         
         public void unimportedGrocerySorter(String unimported){
         
             unimportedGroceriesArraylist.add(unimported);
             //System.out.println(unimported);
             //System.out.println("////////////////////\n"+unimportedGroceriesArraylist);
             
             /*
             Scanner scanner = new Scanner(unimported);

             
             //this scanner will return the 1st integer in the string(ie the amount of 
            try{
            
               while(scanner.hasNextDouble()){
                 //double res = scanner.useDelimiter("\\D+").nextDouble();
                 //System.out.println(res);
                  System.out.println(scanner.nextDouble());
                 }
                 
                 
             
            }
            catch(Exception e){
            
               System.out.println("Could not find number");
            }

            */
            
            //this extracts the quantity(ie the 1st number on each string) from the grocery list
            int res = new Scanner(unimported).useDelimiter("\\D+").nextInt();
            //System.out.println("Unimported Quantity: " +res);

            
            
            
            double unimportedPrices = 0.0f;
            double totalUP =0.0;
            
           //this pattern extracts the doubles from the strings from the text files
            Pattern pricePattern = Pattern.compile("\\d+\\.\\d+");
            Matcher priceMatcher = pricePattern.matcher(unimported);
            
            //Pattern x = Pattern.parsePattern(unimported.substring(0,1));
            //String x = unimported.substring(0,1);
            //System.out.println("hey: "+x);

            //this replaces all occurrences of "at #.##" and replace them with ":"+z
            //String replaceString= x.replaceAll("at "unimportedPrices+,":"+z);

                        
             //this converts the double into string and formats it to have 2 decimal points 
             //String z = String.format ("%.2f", unimportedPrices);
            
            List <Double> totalUnimportedPrices = new ArrayList<Double>();
            Iterator<Double> groceryIterator = totalUnimportedPrices.iterator();

                        
            while(priceMatcher.find()){
            
               
            
               unimportedPrices = Double.parseDouble(priceMatcher.group(0));
               totalUnimportedPrices.add(unimportedPrices);
               
                              
             
               
               //System.out.println("Price: " +m.group(0));
               //System.out.println("Unimported Price: " +Math.ceil((unimportedPrices)));

               //System.out.printf("Unimported Price: %.2f %n" +unimportedPrices * .10 );
               
               //System.out.println("Unimported Price: " +totalUnimportedPrices );
               
               //this converts the double into string and formats it to have 2 decimal points 
               //String z = String.format ("%.2f", unimportedPrices);
               String z = String.format ("%.2f", unimportedPrices*3);
               System.out.println(z);


               
                //this replaces all occurrences of "at #.##" and replace them with ":"+ variable
                String replaceString= unimported.replaceAll("at "+unimportedPrices,":"+z);
                
                //System.out.println(replaceString);


               //totalUP = unimportedPrices +unimportedPrices;

              
               //totalUP = totalUP+totalUnimportedPrices;
               System.out.println("Unimported Total: " +totalUP);

               

            }
            
            /*
            while (groceryIterator.hasNext()){
               
               
                  totalUP = totalUP+groceryIterator.next();
                  System.out.println("Unimported Total: " +totalUP);

               }

            */
            
            
            
            /*
            //this pattern extracts the unimported item name from the strings from the text files
            Pattern itemNamePattern = Pattern.compile(res+"(.*?)"+"at");
            Matcher itemNameMatcher = pricePattern.matcher(unimported);
            */

            
            /*          
              while(itemNameMatcher.find()){
               System.out.println("Item name: "+itemNameMatcher.group(0));
            
            }

            */
         
         }



         
         
         
      
      public void closeFile(){
           groceryListReader.close();

      }
      
      
      public static void main(String [] args){
      
         groceryStore object = new groceryStore();
         
         
         //object.openFile();
         object.readFile();
         //object.closeFile();


       
      
      }



}
