import java.util.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Iterator;

public class salesTaxesJava{

   
   boolean isTaxable = true;
   boolean isImported = false;
   BufferedReader groceryListReader;
   
   final List<String> groceriesArraylist = new ArrayList<String>();
   final List<String> itemNameArraylist = new ArrayList<String>();

   final Iterator<String> groceryIterator = groceriesArraylist.iterator();
   final List <Double> extractedPricesList = new ArrayList<Double>();
   final List <Integer> groceryQuantity = new ArrayList<Integer>();
   final List<String> untaxableGroceriesArraylist = new ArrayList<String>();
   final List <Double> groceryPricesListWithTaxApplied = new ArrayList<Double>();

      
      /**
         Opens/reads the groceryList text file string by string
         Reads the contents into a arrayList of strings after 
         each string is tested for the keyword "imported" and the corresponding boolean is updated accordingly
         Sends the text file string to multiple functions for further extractions and calculations
         into a arraylist of strings and passes them to other methods 
         to have the price,quantity and item taxablity tested
         
         The name argument is a specifier that is relative to the argument.  
         @throws IOException  If an input or output exception occurred
         @param none
         @return none      
      */
      public void scanItems(){
      
            try{
                  groceryListReader = new BufferedReader(new FileReader("grocery_list.txt"));
                  String groceriesFromFile = null;
                              
                  while((groceriesFromFile = groceryListReader.readLine()) != null){
                  
                        //groceriesArraylist.add(groceriesFromFile);
                        //System.out.println(groceriesArraylist);     
                                                                      
                          if(groceriesFromFile.contains("imported")){
                           
                              
                              isImported = true;
                              groceriesArraylist.add(groceriesFromFile);
                                                     
                           }
                           else{
                           
                              isImported = false;
                             groceriesArraylist.add(groceriesFromFile);
                              
                           }  
                           
                          itemNameExtract(groceriesFromFile);
                          untaxableItems(groceriesFromFile); 
                          priceExtract(groceriesFromFile);
                  } 
                          

   
            }
            catch(Exception e){
                System.out.println(e);
                System.out.println("Could not find file");

            }

         }

         
         /**
            Returns the item's name pattern found in the contents of the text file
            
            @param groceryList strings passed to the function from text file
            @return  arraylist of strings that match patterns 
             
         */
         public List<String> itemNameExtract (String groceryList){
         
         
            Pattern itemNamePattern = Pattern.compile("" + "(.*?)" + " at");
            Matcher itemNameMatcher = itemNamePattern.matcher(groceryList);
                     
              while(itemNameMatcher.find()){
              
                  System.out.println(itemNameMatcher.group(0));
                  itemNameArraylist.add(itemNameMatcher.group(0));
               }

                System.out.println(itemNameArraylist);
                return itemNameArraylist;
         }
         
       
         
         /**
            Extracts double patterns found in string object
            @param groceryList strings passed to the function from text file
            @return  none 

         */
         public void priceExtract(String groceryList){
         
            double groceryPrices = 0.0;
            
            //this pattern extracts the doubles from the strings from the text files
            Pattern pricePattern = Pattern.compile("\\d+\\.\\d+");
            Matcher priceMatcher = pricePattern.matcher(groceryList);
            
            while(priceMatcher.find()){
          
               groceryPrices = Double.parseDouble(priceMatcher.group(0));
               extractedPricesList.add(groceryPrices);
             
            }
            salesTaxApplied(extractedPricesList);
                        
          }
         
         
         
         
         /**
            Tests taxablity of grocery list items by checking for specfic defined string keywords
            @param groceryList strings passed to the function from text file
            @return none

         */
         public void untaxableItems (String groceryList) {                        
             String chocolate = "chocolate";
             String book = "book";
             String pills = "pills";
             
             
             Pattern chocolatePattern = Pattern.compile(chocolate,Pattern.CASE_INSENSITIVE);
             Pattern bookPattern = Pattern.compile(book,Pattern.CASE_INSENSITIVE);
             Pattern medicinePattern = Pattern.compile(pills,Pattern.CASE_INSENSITIVE);
             
             
             for(String string :groceriesArraylist){
              if(chocolatePattern.matcher(string).find()) {
                 isTaxable = false;
                 
                 untaxableGroceriesArraylist.add(string);
                   
                                              
             } else if (bookPattern.matcher(string).find()) {

                  untaxableGroceriesArraylist.add(string);
                

                  isTaxable = false;
                  


             } else if (medicinePattern.matcher(string).find()) {
             
                   untaxableGroceriesArraylist.add(string);
                   isTaxable = false;
              
                  

             } else {
             
             
                 isTaxable = true;
                
             }
                         
         }
       }

         
         
         /**
            Calculates sales tax added to prices based on status of isTaxable and isImported boolean
            @param groceryPric double patterns found in text file strings
            @return none 
         */
         
         public void salesTaxApplied(List <Double> groceryPrice){
            double salesTax=1.0;
            double totalSalesTax =0.0;
            double totalGroceryPriceAfterTax = 0.0;
            double groceryPriceAfterTax =0.0;
            
                 
            for (double index : groceryPrice ){
                if(isTaxable == true  && isImported == true) {
                  //System.out.println("isTaxable & isImported");
                  salesTax = .15;
                  
                  //this sums up the amount of sales tax thats applied to each item (ie multiplying the price by a %)
                  totalSalesTax = totalSalesTax + (index*salesTax);
                 
                  //this is the individual prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)                  
                  groceryPriceAfterTax += ((index+(index*salesTax)));
                  
 
                   break;



   
               }
               
               else if(isTaxable == false && isImported == true ){
               //System.out.println("is not Taxable & isImported");

                  salesTax = .05;
                  //this sums up the amount of sales tax thats applied to each item (ie multiplying the price by a %)
                  totalSalesTax = totalSalesTax + (index*salesTax);
                 
                  //this is the individual prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)                  
                  groceryPriceAfterTax += ((index+(index*salesTax)));
                  
 
                   break;


               }
               
               else if(isTaxable == true && isImported == false ){
                
                  salesTax = .10;
                  
                 //this sums up the amount of sales tax thats applied to each item (ie multiplying the price by a %)
                  totalSalesTax = totalSalesTax + (index*salesTax);
                 
                  //this is the individual prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)                  
                  groceryPriceAfterTax += ((index+(index*salesTax)));
                  
 
                   break;

               }
               
               else{
              

                  salesTax = 0;
                  //this sums up the amount of sales tax thats applied to each item (ie multiplying the price by a %)
                  totalSalesTax = totalSalesTax + (index*salesTax);
                                    
                  //this is the individual prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)                  
                  groceryPriceAfterTax += index;
                }
               groceryPricesListWithTaxApplied.add(groceryPriceAfterTax);
               totalSalesTax +=(index*salesTax);
               totalGroceryPriceAfterTax += groceryPriceAfterTax;
            }
                  printReceipt(totalSalesTax,totalGroceryPriceAfterTax);

           }
               
                           
  
         
         /**
            Prints to the screen the completed with item name and price w/ sales tax applied
            Prints total sales tax and total price after applied sales tax
            
            @param totalSalesTax sum of the sales taxes applied to item prices
            @param totalGroceryPriceAfterTax sum of the price with applied sales taxes to item prices
            @return none
                       
         */
         public void printReceipt(double totalSalesTax, double totalGroceryPriceAfterTax){
               
            
               for (double d: groceryPricesListWithTaxApplied){      
                  for (String string: itemNameArraylist ){
                        System.out.printf(string,"%s : %.2f\n", d);
                        continue;
                  }
                                 
               }

               System.out.printf("total sales taxes:  %.2f\n", totalSalesTax);
               System.out.printf("total prices after tax : %.2f\n", totalGroceryPriceAfterTax);               
         }
          
         /**
            Closes text file 
         
            @throws IOException If an input or output exception occurred
            @param none
            @return none
         */         
         public void closeFile(){
         
            try{
               groceryListReader.close();
            }
            catch(Exception e){
               System.out.println(e);
               System.out.println("Could not close file");

            
            }
                 
         }
         
      
       
       
      public static void main(String [] args){
      
         salesTaxesJava object = new salesTaxesJava();
         object.scanItems();
         object.closeFile();


       
      
      }



}
