package workshop1;

import java.io.Console;
import java.util.*;

public class App 
{
    public static void main( String[] args )
    {
        //initialise all items required for the shopping cart;
        List<String> cart = new LinkedList<>(); 
        int delIndex; 
        String input; 
        boolean stop = false; 
        Console cons = System.console(); 

        System.out.println( "Welcome to your shopping cart! ");

        while(!stop) {
            //when using console, we can use a prompt in the readline function 
            input = cons.readLine("> ");

            System.out.printf("READ: %s\n", input);
            //once we get the input form the user, we have to split up the terms
            String[] terms = input.split(" "); 
            String cmd = terms[0]; 

            switch(cmd) {
                case "add": 
                //since users may add more than 1 items, we have to convert the ',' to ' ' and split the terms up
                String itemsStr = terms[1]; 
                //replace the , with ' '; 
                String itemsReplaced = itemsStr.replace(",", " "); 
                //once replaced we can proceed to split them 
                String[] items = itemsReplaced.split(" ");
                
                //proceed with the for loop to check if specified item already exists
                for(int i=0; i<items.length; i++) {
                    boolean found = false; 
                    for (int j=0; j<cart.size(); j++) {
                        // all codes runs through here first, before running through the next if block; 
                        // if item = cart, found will be changed to true; if not it will remain as false; 
                        if(items[i].toUpperCase().equals(cart.get(j).toUpperCase())) {
                            found = true; 
                            System.out.println("The item is already in your cart! ");
                            break;
                        }
                    } 
                    // if item = cart, found = true, thus skip this block of codes
                    // if item != cart, found != true (!found); hence it will run through the if block below
                    if(!found) {
                        cart.add(items[i]); 
                        System.out.printf("Added %s to cart\n", items[i]);
                    }
                }
                break; 

                case "list": 
                //use a if and for function to print each item within the cart 
                if(cart.size()>0) {
                    for (int i=0; i<cart.size(); i++) {
                        System.out.printf("%d. %s\n", (i+1), cart.get(i));
                        } 
                    } else { 
                        System.out.println("Your cart is empty! ");
                    } break; 

                case "del": 
                // e.g. 0 (meaning user didnt input anything) or 1 (del but missing the index)
                if(terms.length <2) {
                    System.out.println("Please provide an index to delete. ");
                } else { 
                    try {
                        // since cmd is terms[0], the index to be deleted is terms[1]
                        // we use -1 because the count on the backend starts from 0 vs user side which starts from 1
                        delIndex = Integer.parseInt(terms[1]) - 1; 
                        // for checking purposes
                        System.out.println(delIndex);
                        if (delIndex >= 0 && delIndex < cart.size()) {
                            System.out.printf("Deleted %s from the cart.\n", cart.get(delIndex));
                            cart.remove(delIndex); 

                        } else {
                            showNoSuchItemToDel(); 
                        }

                    } catch (Exception e) {
                        showNoSuchItemToDel(); 
                    }
                }
                break;
                
                case "end":
                    stop = true; 
                    break;

                default:
                    System.out.println("Invalid command. ");
            } 
        }

        System.out.println("Thank you for shopping with us! ");
    }

    private static void showNoSuchItemToDel() {
        System.out.println("No such item to delete. ");
    }
}
