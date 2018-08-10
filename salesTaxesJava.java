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
   final Iterator<String> groceryIterator = groceriesArraylist.iterator();
   final List <Double> extractedPricesList = new ArrayList<Double>();
   final List <Integer> groceryQuantity = new ArrayList<Integer>();
   final List<String> untaxableGroceriesArraylist = new ArrayList<String>();
   final List <Double> groceryPricesListWithTaxApplied = new ArrayList<Double>();

      
      //this methods opens the groceryList file 
      //this method reads the contents from the groceryList file and puts them 
      //into a arraylist of strings and passes them to other methods 
      //to have the price,quantity and item taxablity tested 
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
                           //quantityExtract(groceriesArraylist);

                           //quantityExtract(groceriesFromFile);
   
                           untaxableItems(groceriesFromFile); 
                           priceExtract(groceriesFromFile);
   
                                      
                  } 
                  //System.out.println(groceriesArraylist); 

   
            }
            catch(Exception e){
                System.out.println(e);
                System.out.println("Could not find file");

            }

         }
         
         
         
         
         
         
         //this extracts the quantity(ie the 1st number on each string) from the grocery list
         //public int quantityExtract(String groceryList){
         //public List <Integer> quantityExtract(List <String> groceryList){
         public List <Integer> quantityExtract(String groceryList){

         
            int groceryQuantityInt =0;
            //String groceryQuantityString = groceryList.substring(0,1);
             
             //this pattern extracts the doubles from the strings from the text files
            Pattern quantityPattern = Pattern.compile("\\D+");
            //Pattern quantityPattern = Pattern.compile("1");
            Matcher quantityMatcher = quantityPattern.matcher(groceryList);


            //List <Integer> groceryQuantity = new ArrayList<Integer>();
            //while(groceryIterator.hasNext()){
            
            while(quantityMatcher.find()){
            //while(groceryList != null){
          
               //int groceryQuantityInt = new Scanner(groceryIterator.next()).useDelimiter("\\D").nextInt();
               groceryQuantityInt = Integer.parseInt(quantityMatcher.group(0));
               //groceryQuantityInt = Integer.parseInt(groceryQuantityString);
               
               groceryQuantity.add(groceryQuantityInt);
               //System.out.println("Quantity: " +groceryQuantity);

            //}
            }
            System.out.println("Quantity size: " +groceryQuantity.size());

            System.out.println("Quantity: " +groceryQuantity);

                 //int groceryQuantity = new Scanner(groceryList).useDelimiter("\\D").nextInt();

                //System.out.println("Quantity: " +groceryQuantity);
               
               //afterSaleTaxApplied(groceryQuantity);
               return groceryQuantity;      
                    
         }
         
         
         
         
         //this method extracts the price from the strings from the text files
         public void priceExtract(String groceryList){
         
            double groceryPrices = 0.0;
           
            //List <Double> extractedPricesList = new ArrayList<Double>();

         
            //this pattern extracts the doubles from the strings from the text files
            Pattern pricePattern = Pattern.compile("\\d+\\.\\d+");
            Matcher priceMatcher = pricePattern.matcher(groceryList);
            
            while(priceMatcher.find()){
            
               
            
               groceryPrices = Double.parseDouble(priceMatcher.group(0));
               extractedPricesList.add(groceryPrices);
               
                              
          
               //System.out.println("Price: " +priceMatcher.group(0));
               //System.out.println("Price: " +extractedPricesList);

               
                
               salesTaxApplied(extractedPricesList);
            }
            //System.out.println("Price size: " +extractedPricesList.size());
            //System.out.println("Price: " +extractedPricesList);
            
          }
         
         
         
         
         
         //this method determines if the grocery items on the grocery list are taxable or not 
         //by testing if they contain specfic keywords from the word bank (ie food,book,medicine)
         public void untaxableItems (String groceryList) {


             //List<String> untaxableGroceriesArraylist = new ArrayList<String>();
             //Iterator<String> untaxableGroceryIterator = untaxableGroceriesArraylist.iterator();

             
                          
             String chocolate = "chocolate";
             String book = "book";
             String pills = "pills";
             //String imported = "imported";
             
             Pattern chocolatePattern = Pattern.compile(chocolate,Pattern.CASE_INSENSITIVE);
             Pattern bookPattern = Pattern.compile(book,Pattern.CASE_INSENSITIVE);
             Pattern medicinePattern = Pattern.compile(pills,Pattern.CASE_INSENSITIVE);
             //List<String> groceriesArraylist = new ArrayList<String>();
             //Iterator<String> groceryIterator = untaxableGroceriesArraylist.iterator();


             
             for(String string :groceriesArraylist){
              if(chocolatePattern.matcher(string).find()) {
                 isTaxable = false;
                 
                 untaxableGroceriesArraylist.add(string);
                 //System.out.println("inside 1: ");
                 //continue;   
                                              
             } else if (bookPattern.matcher(string).find()) {

                  untaxableGroceriesArraylist.add(string);
                  //System.out.println("inside 2: ");

                  isTaxable = false;
                  //continue;


             } else if (medicinePattern.matcher(string).find()) {
             
                   untaxableGroceriesArraylist.add(string);
                   //System.out.println("inside 3: ");
                  

                  isTaxable = false;
              
                  //continue;

             } else {
             
             
                 isTaxable = true;
                 //System.out.println("inside 4: ");
                 //continue;
             }
                         
         }
        
         //System.out.println("untaxable items: "+ untaxableGroceriesArraylist);

       }

         
         
         
         //public void salesTaxApplied(double groceryPrice){
         public void salesTaxApplied(List <Double> groceryPrice){
            double salesTax=1.0;
            double totalSalesTax =0.0;
            double totalGroceryPriceAfterTax = 0.0;
            double groceryPriceAfterTax =0.0;
            
            //Iterator<Double> groceryPriceIterator = groceryPrice.iterator();

            //List <Double> groceryPriceAfterTax = new ArrayList <Double>();
            
            //Iterator<Double> GPATaxIterator = groceryPriceAfterTax.iterator();
            
           //System.out.println("The groceryPriceList has " + groceryPrice.size() + " elements");
           
            //while(groceryPriceIterator.hasNext()){
            for (double index : groceryPrice ){
                if(isTaxable == true  && isImported == true) {
                  //System.out.println("isTaxable & isImported");
                  salesTax = .15;
                  
                  //this sums up the amount of sales tax thats applied to each item (ie multiplying the price by a %)
                  //totalSalesTax = totalSalesTax + (index*salesTax);
                  
                  //this is the individual prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)                  
                  groceryPriceAfterTax = ((index+(index*salesTax)));
                  //groceryPricesListWithTaxApplied.add(groceryPriceAfterTax);
                  
 
                  //this sums up the amount of total prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)
                  //totalGroceryPriceAfterTax = totalGroceryPriceAfterTax + groceryPriceAfterTax;
                                                
                   //this converts the double into string and formats it to have 2 decimal points 
                  //System.out.printf("groceryPriceAfterTax: %.2f\n", groceryPriceAfterTax); 
                  /*                
                  System.out.printf("total sales taxes:  %.2f\n", totalSalesTax);
                  System.out.printf("total prices after tax : %.2f\n",  totalGroceryPriceAfterTax);
                  */
   
               }
               
               else if(isTaxable == false && isImported == true ){
               //System.out.println("is not Taxable & isImported");

                  salesTax = .05;
                   //this sums up the amount of sales tax thats applied to each item (ie multiplying the price by a %)
                  //totalSalesTax = totalSalesTax + (index*salesTax);
                  
                  //this is the individual prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)                  
                  groceryPriceAfterTax = ((index+(index*salesTax)));
                  //groceryPricesListWithTaxApplied.add(groceryPriceAfterTax);
 
                  //this sums up the amount of total prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)
                  //totalGroceryPriceAfterTax = totalGroceryPriceAfterTax + groceryPriceAfterTax;
                                                
                   //this converts the double into string and formats it to have 2 decimal points 
                  //System.out.printf("groceryPriceAfterTax: %.2f\n", groceryPriceAfterTax);                 
                   /*                
                  System.out.printf("total sales taxes:  %.2f\n", totalSalesTax);
                  System.out.printf("total prices after tax : %.2f\n",  totalGroceryPriceAfterTax);
                  */
               }
               
               else if(isTaxable == true && isImported == false ){
                 //System.out.println("isTaxable & is not Imported");

                  salesTax = .10;
                  
                 //this sums up the amount of sales tax thats applied to each item (ie multiplying the price by a %)
                  //totalSalesTax = totalSalesTax + (index*salesTax);
                  
                  //this is the individual prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)                  
                  groceryPriceAfterTax = ((index+(index*salesTax)));
                  //groceryPricesListWithTaxApplied.add(groceryPriceAfterTax);
 
                  //this sums up the amount of total prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)
                  //totalGroceryPriceAfterTax = totalGroceryPriceAfterTax + groceryPriceAfterTax;
                                                
                   //this converts the double into string and formats it to have 2 decimal points 
                  //System.out.printf("groceryPriceAfterTax: %.2f\n", groceryPriceAfterTax);                 
                  /*                
                  System.out.printf("total sales taxes:  %.2f\n", totalSalesTax);
                  System.out.printf("total prices after tax : %.2f\n",  totalGroceryPriceAfterTax);
                  */
               }
               
               else {
               //System.out.println("is not Taxable & is not Imported");

                  salesTax = 0;
                  //this sums up the amount of sales tax thats applied to each item (ie multiplying the price by a %)
                  //totalSalesTax = totalSalesTax + (index*salesTax);
                  
                  //this is the individual prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)                  
                  groceryPriceAfterTax = index;
                  //groceryPricesListWithTaxApplied.add(groceryPriceAfterTax);
 
                  //this sums up the amount of total prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)
                  //totalGroceryPriceAfterTax = totalGroceryPriceAfterTax + groceryPriceAfterTax;
                                                
                   //this converts the double into string and formats it to have 2 decimal points 
                  //System.out.printf("groceryPriceAfterTax: %.2f\n", groceryPriceAfterTax);                 
                   /*                
                  System.out.printf("total sales taxes:  %.2f\n", totalSalesTax);
                  System.out.printf("total prices after tax : %.2f\n",  totalGroceryPriceAfterTax);
                  */
               }
               groceryPricesListWithTaxApplied.add(groceryPriceAfterTax);
               totalSalesTax = totalSalesTax + (index*salesTax);
               totalGroceryPriceAfterTax = totalGroceryPriceAfterTax + groceryPriceAfterTax;
            }
                  System.out.println("groceryPriceAfterTax arraylist: "+ groceryPricesListWithTaxApplied); 
                  System.out.printf("total sales taxes:  %.2f\n", totalSalesTax);
                  System.out.printf("total prices after tax : %.2f\n", totalGroceryPriceAfterTax);

           }
               
                           
  
         
         
         //this is will finish constructing the reciept after all the other calculations have been made
         public void afterSaleTaxApplied(String grocerylist, List <Double>  groceryPricesListWithTaxApplied){
               
               //Iterator<String> untaxableGroceryIterator = untaxableGroceriesArraylist.iterator();
               
               for (String string: groceriesArraylist ){
               
               
               }
                      
         
         
                //this replaces all occurrences of "at #.##" and replace them with ":"+ variable
                //String replaceString = grocerylist.replaceAll("at "+groceryPrices,":"+z);
                
                //System.out.println("Replaced string: "+replaceString);

         
         
         }
              
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
         
         
         //object.openFile();
         object.scanItems();
         object.closeFile();


       
      
      }



}
