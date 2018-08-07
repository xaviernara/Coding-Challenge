import java.util.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Iterator;

public class groceryStore{

   private Scanner groceryListReader;
   final List<String> groceriesArraylist = new ArrayList<String>();
   final List<String> sortedGroceriesArraylist = new ArrayList<String>();
   
   Iterator<String> groceryIterator = groceriesArraylist.iterator();


   final List<String> importedGroceriesArraylist = new ArrayList<String>();
   final List<String> unimportedGroceriesArraylist = new ArrayList<String>();
   
   boolean isTaxable = true;
   boolean isImported = false;
   
      
      //this methods opens the groceryList file 
      //this method reads the contents from the groceryList file and puts them 
      //into a arraylist of strings

      public void scanItems(){
      
           List<String> groceriesArraylist = new ArrayList<String>();
           Iterator<String> groceryIterator = groceriesArraylist.iterator();


            try{
               BufferedReader groceryListReader = new BufferedReader(new FileReader("grocery_list.txt"));
               String groceriesFromFile = null;
                           
               while((groceriesFromFile = groceryListReader.readLine()) != null){
                                        
                    //groceriesArraylist.add(groceriesFromFile);
                    
                    //System.out.println("all items: "+groceriesArraylist);
                    
                    
                      //quantityExtract(groceriesFromFile);
                                            
                    
                    
                        if(groceriesFromFile.contains("imported")){
                        
                           //System.out.println(groceriesFromFile);
                           //importedGrocerySorter(groceriesFromFile);
                           isImported = true;
                           System.out.println("this item is imported:" + groceriesFromFile);

                           //quantityExtract(groceriesFromFile);
                        
                        }
                        else{
                        
                           isImported = false;
                           System.out.println("this item is unimported:" + groceriesFromFile);
                           //priceExtract(groceriesFromFile);


                        
                           //unimportedGrocerySorter(groceriesFromFile);
                           
                        
                        } 
                        
                        quantityExtract(groceriesFromFile);

                        priceExtract(groceriesFromFile);

                        untaxableItems(groceriesFromFile);
                        
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
         
         
         
         
         
         
         //this extracts the quantity(ie the 1st number on each string) from the grocery list
         //works
         public int quantityExtract(String groceryList){
         //public int quantityExtract(List <String> groceryList){

            ///Iterator<String> groceryIterator = groceryList.iterator();

            //try{
            
                       
               int groceryQuantity = new Scanner(groceryList).useDelimiter("\\D").nextInt();
               System.out.println("Quantity: " +groceryQuantity);
               
               //afterSaleTaxApplied(groceryQuantity);
               return groceryQuantity;      
            
            
                          //}
            
            //catch(Exception e){
            
              // System.out.println("Could not find quantity");
            //}       
         }
         
         
         
         
         //this method extracts the price from the strings from the text files
         //works
         public void priceExtract(String groceryList){
         
            double groceryPrices = 0.0;
           
            List <Double> extractedPricesList = new ArrayList<Double>();

         
            //this pattern extracts the doubles from the strings from the text files
            Pattern pricePattern = Pattern.compile("\\d+\\.\\d+");
            Matcher priceMatcher = pricePattern.matcher(groceryList);
            
            while(priceMatcher.find()){
            
               
            
               groceryPrices = Double.parseDouble(priceMatcher.group(0));
               extractedPricesList.add(groceryPrices);
               
                              
          
               //System.out.println("Price: " +priceMatcher.group(0));
               System.out.println("Price: " +extractedPricesList);

                
               salesTaxApplied(extractedPricesList);             
               

               
               

               //totalUP = unimportedPrices +unimportedPrices;

              
               //totalUP = totalUP+totalUnimportedPrices;
               //System.out.println("Unimported Total: " +totalUP);

               

            }
            
          }
         
         
         
         
         
         //this method determines if the grocery items on the grocery list are taxable or not 
         //by testing if they contain specfic keywords from the word bank (ie food,book,medicine)
         //works
         public void untaxableItems (String groceryList){
         
            List<String> untaxableGroceriesArraylist = new ArrayList<String>();
            Iterator<String> untaxableGroceryIterator = untaxableGroceriesArraylist.iterator();

            untaxableGroceriesArraylist.add("chocolate");
            untaxableGroceriesArraylist.add("book");
            untaxableGroceriesArraylist.add("pills");
            
            List<String> groceriesArraylist = new ArrayList<String>();
            Iterator<String> groceryIterator = untaxableGroceriesArraylist.iterator();

            
                        
             groceriesArraylist.add(groceryList);
             System.out.println("inside:" + groceriesArraylist);
              // while (untaxableGroceryIterator.hasNext()) {     
               //if (groceryList.contains(groceryIterator.next())){
               //if (groceryList.contains(untaxableGroceriesArraylist[0])||groceryList.contains(untaxableGroceriesArraylist[1])||groceryList.contains(untaxableGroceriesArraylist[2]))){
                 if(groceryList.contains("book")){
                     isTaxable = false;
                     //System.out.println("item is a book: " + groceryList);
                     
                 }
                 else if (groceryList.contains("pills")){
                     isTaxable = false;
                     //System.out.println("item is a medcine: " + groceryList);
   
                 
                 }
                 
                 else if (groceryList.contains("chocolate")){
                      isTaxable = false;
                      //System.out.println("item is a food: " + groceryList);

                 
                 }
               else{
                     isTaxable = true;
                     //isImported = false;
                    // System.out.println("item is taxable: " + groceryList);

              }
                 //importedPosition++;
            }
        
         

         
         
         
         //public void salesTaxApplied(double groceryPrice){
         public void salesTaxApplied(List <Double> groceryPrice){
         
            double salesTax=1.0;
            double totalSalesTax =0.0;
            double groceryPriceAfterTax = 0.0;
            double totalGroceryPriceAfterTax = 0.0;

            Iterator<Double> groceryPriceIterator = groceryPrice.iterator();
           
            while(groceryPriceIterator.hasNext()){
            
               if(isTaxable == true  && isImported == true) {
               
                  salesTax = .15;
                  
                  //this sums up the amount of sales tax thats applied to each item (ie multiplying the price by a %)
                  totalSalesTax = totalSalesTax + (groceryPriceIterator.next()*salesTax);
                  
                  //this is the individual prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)
                  //groceryPriceAfterTax = groceryPriceAfterTax +(groceryPriceIterator.next()+(groceryPriceIterator.next()*salesTax));
                  groceryPriceAfterTax = (groceryPriceIterator.next()+(groceryPriceIterator.next()*salesTax));
                  
 
                  //this sums up the amount of total prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)
                  totalGroceryPriceAfterTax = totalGroceryPriceAfterTax + groceryPriceAfterTax;
   
                                             
                   //this converts the double into string and formats it to have 2 decimal points 
                  String groceryPriceWithTaxApplied = String.format ("%.2f", groceryPriceAfterTax);
                  System.out.println("string prices multiplied:"+groceryPriceWithTaxApplied);
                  
                  System.out.println("total sales taxes:"+ totalSalesTax);
                  System.out.println("total price after tax :"+  totalGroceryPriceAfterTax);
   
   
               }
               
               else if(isTaxable == false && isImported == true ){
               
                  salesTax = .05;
                  //this sums up the amount of sales tax thats applied to each item (ie multiplying the price by a %)
                  totalSalesTax = totalSalesTax + (groceryPriceIterator.next()*salesTax);
                  
                  //this is the individual prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)
                  //groceryPriceAfterTax = groceryPriceAfterTax +(groceryPriceIterator.next()+(groceryPriceIterator.next()*salesTax));
                  groceryPriceAfterTax = (groceryPriceIterator.next()+(groceryPriceIterator.next()*salesTax));
                  

                  //this sums up the amount of total prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)
                  totalGroceryPriceAfterTax = totalGroceryPriceAfterTax + groceryPriceAfterTax;
   
                                             
                   //this converts the double into string and formats it to have 2 decimal points 
                  String groceryPriceWithTaxApplied = String.format ("%.2f", groceryPriceAfterTax);
                  System.out.println("string prices multiplied:"+groceryPriceWithTaxApplied);
                  
                  System.out.println("total sales taxes:"+ totalSalesTax);
                  System.out.println("total price after tax :"+  totalGroceryPriceAfterTax);
               
               }
               
               else if(isTaxable == true && isImported == false ){
               //else {

                  salesTax = .10;
                  
                 //this sums up the amount of sales tax thats applied to each item (ie multiplying the price by a %)
                  totalSalesTax = totalSalesTax + (groceryPriceIterator.next()*salesTax);
                  
                  //this is the individual prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)
                  //groceryPriceAfterTax = groceryPriceAfterTax +(groceryPriceIterator.next()+(groceryPriceIterator.next()*salesTax));
                  //groceryPriceAfterTax = (groceryPriceIterator.next()+(groceryPriceIterator.next()*salesTax));
                  

                  //this sums up the amount of total prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)
                  totalGroceryPriceAfterTax = totalGroceryPriceAfterTax + groceryPriceAfterTax;
   
                                             
                   //this converts the double into string and formats it to have 2 decimal points 
                  //String groceryPriceWithTaxApplied = String.format ("%.2f", groceryPriceAfterTax);
                  String groceryPriceWithTaxApplied = String.format ("%.2f", (groceryPriceIterator.next()+(groceryPriceIterator.next()*salesTax)));

                  System.out.println("string prices multiplied:"+groceryPriceWithTaxApplied);
                  
                  System.out.println("total sales taxes:"+ totalSalesTax);
                  System.out.println("total price after tax :"+  totalGroceryPriceAfterTax);
               }
               
               else {
               
                  
                 //this sums up the amount of sales tax thats applied to each item (ie multiplying the price by a %)
                  totalSalesTax = totalSalesTax + (groceryPriceIterator.next()*salesTax);
                  
                  //this is the individual prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)
                  //groceryPriceAfterTax = groceryPriceAfterTax +(groceryPriceIterator.next()+(groceryPriceIterator.next()*salesTax));
                  groceryPriceAfterTax = (groceryPriceIterator.next()+(groceryPriceIterator.next()*salesTax));
                  
                  //this sums up the amount of total prices after sales tax has been applied to each item (ie multiplying the price by a % and adding that to the orginal number)
                  totalGroceryPriceAfterTax = totalGroceryPriceAfterTax + groceryPriceAfterTax;
   
                                             
                   //this converts the double into string and formats it to have 2 decimal points 
                  String groceryPriceWithTaxApplied = String.format ("%.2f", groceryPriceAfterTax);
                  System.out.println("string prices multiplied:"+groceryPriceWithTaxApplied);
                  
                  System.out.println("total sales taxes:"+ totalSalesTax);
                  System.out.println("total price after tax :"+  totalGroceryPriceAfterTax);

               
               
               }

            
            
            }
               
                           
        }
         
         /*
         //this is will finish constructing the reciept after all the other calculations have been made
         public void afterSaleTaxApplied(String grocerylist, double groceryPrices ){
         
             //this converts the double into string and formats it to have 2 decimal points 
             //String z = String.format ("%.2f", unimportedPrices);

         
         
             //this replaces all occurrences of "at #.##" and replace them with ":"+ variable
                String replaceString = grocerylist.replaceAll("at "+groceryPrices,":"+z);
                
                //System.out.println("Replaced string: "+replaceString);

         
         
         }
         
                  
        /*
        
        
        
        
        
        
         
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


            /*
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
         
         
         
         
         /*
         public void unimportedGrocerySorter(String unimported){
         
             unimportedGroceriesArraylist.add(unimported);
             System.out.println("ALL UNINPORTED: "+unimported);
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
            
            /*
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
               
                              
          
               //System.out.println("Price: " +priceMatcher.group(0));
               System.out.println("Price: " +totalUnimportedPrices);

               
               
              
               //System.out.println("Unimported Price: " +totalUnimportedPrices );
               
               //this converts the double into string and formats it to have 2 decimal points 
               String z = String.format ("%.2f", unimportedPrices*2);  
               System.out.println("string prices multiplied:"+z);


               
                //this replaces all occurrences of "at #.##" and replace them with ":"+ variable
                String replaceString= unimported.replaceAll("at "+unimportedPrices,":"+z);
                
                System.out.println("Replaced string: "+replaceString);


               //totalUP = unimportedPrices +unimportedPrices;

              
               //totalUP = totalUP+totalUnimportedPrices;
               //System.out.println("Unimported Total: " +totalUP);

               

            }
            */
            
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

                     
         }
        */

         
         
         
      
         public void closeFile(){
              groceryListReader.close();
   
         }
         
      
       
       
      public static void main(String [] args){
      
         groceryStore object = new groceryStore();
         
         
         //object.openFile();
         object.scanItems();
         //object.closeFile();


       
      
      }



}
