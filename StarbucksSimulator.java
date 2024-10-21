import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

abstract class Inventory {
    protected String itemName;
    protected double itemPrice;
    public static int totalCoffeesOrdered = 0;
    public static int totalSnacksOrdered = 0;

    public Inventory(String name, double price) {
       this.itemName = name;
       this.itemPrice = price;
    }

    public void setItemName(String name) {
       this.itemName = name;
    }

    public void setItemPrice(double price) {
       this.itemPrice = price;
    }

    public void showDetails() {
       System.out.println("Item: " + itemName + ", Price: $" + itemPrice);
    }

    // Abstract method to force subclasses to implement
    abstract String getCategory();

    public static void displayTotalOrders() {
        System.out.println("Total Coffees ordered: " + totalCoffeesOrdered);
        System.out.println("Total Snacks ordered: " + totalSnacksOrdered);
    }

    public String getItemName() {
       return this.itemName;
    }

    public double getItemPrice() {
       return this.itemPrice;
    }
}

interface Orderable {
   void placeOrder();
}

class Coffee extends Inventory implements Orderable {

   public Coffee(String name, double price) {
       super(name, price);
   }

   String getCategory() {
       return "Coffee";
   }

   public void placeOrder() {
       totalCoffeesOrdered++; // Increment the static counter
       System.out.println("Order placed for coffee: " + itemName);
   }
}

class Snack extends Inventory implements Orderable {

   public Snack(String name, double price) {
       super(name, price);
   }

   String getCategory() {
       return "Snack";
   }

   public void placeOrder() {
       totalSnacksOrdered++; // Increment the static counter
       System.out.println("Order placed for snack: " + itemName);
   }
}

class User {
    private String userName;
    private Cart userCart;

    public User(String name) {
       this.userName = name;
       this.userCart = new Cart();
    }

    public void setUserName(String name) {
       this.userName = name;
    }

    public String getUserName() {
       return this.userName;
    }

    public Cart getUserCart() {
       return this.userCart;
    }
}

class Cart {
   private List<Inventory> cartItems = new ArrayList<>();

   public Cart() {}

   public void addItem(Inventory item) {
      this.cartItems.add(item);
   }

   public void displayCartItems() {
      System.out.println("Cart Items:");
      for (Inventory item : cartItems) {
         System.out.println("- " + item.getItemName() + ": $" + item.getItemPrice());
      }
      Inventory.displayTotalOrders();
   }

   public double getTotalCartPrice() {
      double total = 0.0;
      for (Inventory item : cartItems) {
         total += item.getItemPrice();
      }
      return total;
   }
}

public class StarbucksSimulator {
   public StarbucksSimulator() {}

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter your name: ");
      String customerName = scanner.nextLine();
      User user = new User(customerName);

      Coffee[] availableCoffees = new Coffee[]{
         new Coffee("Espresso", 2.5),
         new Coffee("Latte", 3.5),
         new Coffee("Cappuccino", 3.0),
         new Coffee("Black Coffee", 2.75)
      };

      Snack[] availableSnacks = new Snack[]{
         new Snack("Croissant", 2.0),
         new Snack("Muffin", 1.75)
      };

      String checkoutChoice;
      boolean finishedOrdering = false;
      while (!finishedOrdering) {
         System.out.println("Do you want Coffee or Snacks? (Type 'coffee' or 'snacks')");
         String choice = scanner.nextLine().toLowerCase();

         int itemNumber;
         if (choice.equals("coffee")) {
            Inventory.totalCoffeesOrdered++;
            System.out.println("Coffee Menu:");
            for (int i = 0; i < availableCoffees.length; ++i) {
               System.out.println((i + 1) + ". " + availableCoffees[i].getItemName() + " - $" + availableCoffees[i].getItemPrice());
            }

            System.out.print("Enter the S.number of the coffee you want: ");
            itemNumber = scanner.nextInt() - 1;
            scanner.nextLine();  // Clear the newline character
            user.getUserCart().addItem(availableCoffees[itemNumber]);
         } else if (choice.equals("snacks")) {
            Inventory.totalSnacksOrdered++;
            System.out.println("Snack Menu:");
            for (int i = 0; i < availableSnacks.length; ++i) {
               System.out.println((i + 1) + ". " + availableSnacks[i].getItemName() + " - $" + availableSnacks[i].getItemPrice());
            }

            System.out.print("Enter the S.number of the snack you want: ");
            itemNumber = scanner.nextInt() - 1;
            scanner.nextLine();
            user.getUserCart().addItem(availableSnacks[itemNumber]);
         } else {
            System.out.println("Invalid input. Please try again.");
         }

         System.out.print("Do you want to checkout? (yes or no): ");
         checkoutChoice = scanner.nextLine().toLowerCase();
         finishedOrdering = checkoutChoice.equals("yes");
      }

      System.out.println("Order for " + user.getUserName() + ":");
      user.getUserCart().displayCartItems();
      System.out.println("Total price (without taxes): $" + user.getUserCart().getTotalCartPrice());
      scanner.close();
   }
}
