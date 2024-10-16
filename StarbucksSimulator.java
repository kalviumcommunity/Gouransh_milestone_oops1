import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Inventory {
    protected String name;
    protected double price;
    public static int totalCoffeesOrdered = 0;
    public static int totalSnacksOrdered = 0;

    public Inventory(String var1, double var2) {
       this.name = var1;
       this.price = var2;
      }
      
      public static void displayTotalOrders() {
          System.out.println("Total Coffees ordered: " + totalCoffeesOrdered);
          System.out.println("Total Snacks ordered: " + totalSnacksOrdered);
      }
    public String getName() {
       return this.name;
    }
 
    public double getPrice() {
       return this.price;
    }
 }
 // Source code is decompiled from a .class file using FernFlower decompiler.
class Coffee extends Inventory {
    public Coffee(String var1, double var2) {
       super(var1, var2);
      //  totalCoffeesOrdered++;
    }
 }
// Source code is decompiled from a .class file using FernFlower decompiler.
class Snack extends Inventory {
    public Snack(String var1, double var2) {
       super(var1, var2);
      //  totalSnacksOrdered++;
    }
 }
// Source code is decompiled from a .class file using FernFlower decompiler.
class User {
    private String name;
    private Cart cart;
 
    public User(String var1) {
       this.name = var1;
       this.cart = new Cart();
    }
 
    public String getName() {
       return this.name;
    }
 
    public Cart getCart() {
       return this.cart;
    }
 }

class Cart {
   private List<Inventory> items = new ArrayList();

   public Cart() {
   }

   public void addItem(Inventory var1) {
      this.items.add(var1);
   }

   public void displayCart() {
      System.out.println("Cart Items:");
      Iterator var1 = this.items.iterator();

      while(var1.hasNext()) {
         Inventory var2 = (Inventory)var1.next();
         PrintStream var10000 = System.out;
         String var10001 = var2.getName();
         var10000.println("- " + var10001 + ": $" + var2.getPrice());
      }

      Inventory.displayTotalOrders();

   }

   public double getTotalPrice() {
      double var1 = 0.0;

      Inventory var4;
      for(Iterator var3 = this.items.iterator(); var3.hasNext(); var1 += var4.getPrice()) {
         var4 = (Inventory)var3.next();
      }

      return var1;
   }
}

public class StarbucksSimulator {
   public StarbucksSimulator() {
   }

   public static void main(String[] var0) {
      Scanner var1 = new Scanner(System.in);
      System.out.print("Enter your name: ");
      String var2 = var1.nextLine();
      User var3 =  new User(var2);
      Coffee[] var4 = new Coffee[]{ new Coffee("Espresso", 2.5), new Coffee("Latte", 3.5), new Coffee("Cappuccino", 3.0), new Coffee("balck_Coffee", 2.75)};
      Snack[] var5 = new Snack[]{ new Snack("Croissant", 2.0), new Snack("Muffin", 1.75)};

      String var9;
      for(boolean var6 = false; !var6; var6 = var9.equals("yes")) {
         System.out.println("Do you want Coffee or Snacks? (Type 'coffee' or 'snacks')");
         String var7 = var1.nextLine().toLowerCase();
         int var8;
         if (var7.equals("coffee")) {
            Inventory.totalCoffeesOrdered++;
            System.out.println("Coffee Menu:");

            for(var8 = 0; var8 < var4.length; ++var8) {
               System.out.println(var8 + 1 + ". " + var4[var8].getName() + " - $" + var4[var8].getPrice());
            }

            System.out.print("Enter the S.number of the coffee you want: ");
            var8 = var1.nextInt() - 1;
            var1.nextLine();
            var3.getCart().addItem(var4[var8]);
         } else if (var7.equals("snacks")) {
            Inventory.totalSnacksOrdered++;
            System.out.println("Snack Menu:");

            for(var8 = 0; var8 < var5.length; ++var8) {
               System.out.println(var8 + 1 + ". " + var5[var8].getName() + " - $" + var5[var8].getPrice());
            }

            System.out.print("Enter the number of the snack you want: ");
            var8 = var1.nextInt() - 1;
            var1.nextLine();
            var3.getCart().addItem(var5[var8]);
         } else{
            System.out.println("Invalid input. Please try again.");  
         }



         System.out.print("Do you want to checkout? (yes or no): ");
         var9 = var1.nextLine().toLowerCase();
      }


      System.out.println("Order for " + var3.getName() + ":");
      var3.getCart().displayCart();
      System.out.println("Total price (without taxes): $" + var3.getCart().getTotalPrice());
      var1.close();

   }
}
   
 