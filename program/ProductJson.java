import java.util.*;
import java.lang.*;
import java.text.*;

public class ProductJson {
   public static void main(String args[]) {

      Scanner scanner = new Scanner(System.in);
      scanner.useLocale(Locale.ENGLISH);

      System.out.println("Enter the product number: ");
      String num = scanner.nextLine();
      //9 is the minimum char count for num
      //11 is the max char count for num
      String brandName = "";

      // GET AND ASSIGN BRAND NAME
      if(num.substring(0,2).toUpperCase().equals("SM")){
        brandName = "ShoreMaster®";
      }
      else if(num.substring(0,2).toUpperCase().equals("BK")){
        if(num.substring(0,3).toUpperCase().equals("BKT")){
          brandName = "Beach King Tower";
        }
        else {
          brandName = "Beach King";
        }
      }
      else if(num.substring(0,2).toUpperCase().equals("DK")){
        brandName = "DAKA";
      }
      else if(num.substring(0,2).toUpperCase().equals("DR")){
        brandName = "Dock'Rite";
      }
      else if(num.substring(0,2).toUpperCase().equals("DL")){
        brandName = "DuraLift";
      }
      else if(num.substring(0,2).toUpperCase().equals("FL")){
        brandName = "FLOE";
      }
      else if(num.substring(0,2).toUpperCase().equals("HM")){
        brandName = "Harbor Master";
      }
      else if(num.substring(0,2).toUpperCase().equals("HW")){
        if(num.substring(0,3).toUpperCase().equals("HWA")){
          brandName = "Hewitt Flat Front";
        }
        else {
          brandName = "Hewitt Delux Front";
        }
      }
      else if(num.substring(0,2).toUpperCase().equals("SS")){
        brandName = "ShoreStation® Aluminum";
      }
      else if(num.substring(0,2).toUpperCase().equals("MS")){
        brandName = "ShoreStation® Steel";
      }
      else if(num.substring(0,2).toUpperCase().equals("LS")){
        brandName = "Lake Shore Products";
      }
      else if(num.substring(0,2).toUpperCase().equals("MX")){
        if(num.substring(0,3).toUpperCase().equals("MXT")){
          brandName = "Max Docks Tower";
        }
        else {
          brandName = "Max Docks";
        }
      }
      else if(num.substring(0,2).toUpperCase().equals("NM")){
        brandName = "Newmans";
      }
      else if(num.substring(0,2).toUpperCase().equals("CL")){
        if(num.substring(0,3).toUpperCase().equals("CLT")){
          brandName = "Nucraft/Craftlander High Top Canopy";
        }
        else if(num.substring(0,4).toUpperCase().equals("CLTZ")){
          brandName = "Nucraft/Craftlander High Top Zip Tower";
        }
        else {
          brandName = "Nucraft/Craftlander";
        }
      }
      else if(num.substring(0,2).toUpperCase().equals("PP")){
        brandName = "Pier Pleasure";
      }
      else if(num.substring(0,2).toUpperCase().equals("PD")){
        brandName = "Porta-Dock";
      }
      else if(num.substring(0,2).toUpperCase().equals("RL")){
        brandName = "RidgeLine";
      }
      else if(num.substring(0,2).toUpperCase().equals("VB")){
        brandName = "Vibo";
      }
      else {
        System.out.println("Brand not recognized, try again.");
        System.exit(1);
      }

      // GET AND ASSIGN CANOPY WIDTH
      // This will always be the last 3 chars in num
      String widthS = "";
      int width = 0;
      if (num.length() == 9) {
        widthS = num.substring(6,9);
        width = Integer.parseInt(widthS);
      } else if (num.length() == 10) {
        widthS = num.substring(7,10);
        width = Integer.parseInt(widthS);
      } else if (num.length() == 11) {
        widthS = num.substring(8,11);
        width = Integer.parseInt(widthS);
      }

      // GET AND ASSIGN CANOPY LENGTH
      // This will always be the first 4 NUMBERS in num
      String length1 = "";
      String length2 = "";
      int lengthFt = 0;
      int lengthIn = 0;
      if (num.length() == 9) {
        if (num.substring(5,6).equals("0") && num.substring(4,5).equals("0")){
          length1 = num.substring(2,4);
          lengthFt = Integer.parseInt(length1);
        } else {
          length1 = num.substring(2,4);
          length2 = num.substring(4,6);
          // Convert x'x" into x.x'
          lengthFt = Integer.parseInt(length1);
          lengthIn = Integer.parseInt(length2);
        }
      } else if (num.length() == 10) {
        if (num.substring(5,6).equals("0") && num.substring(6,7).equals("0")){
          length1 = num.substring(3,5);
          lengthFt = Integer.parseInt(length1);
        } else {
          length1 = num.substring(3,5);
          length2 = num.substring(5,7);
          lengthFt = Integer.parseInt(length1);
          lengthIn = Integer.parseInt(length2);
        }
      } else if (num.length() == 11) {
        if (num.substring(6,7).equals("0") && num.substring(7,8).equals("0")){
          length1 = num.substring(4,6);
          lengthFt = Integer.parseInt(length1);
        } else {
          length1 = num.substring(4,6);
          length2 = num.substring(6,8);
          lengthFt = Integer.parseInt(length1);
          lengthIn = Integer.parseInt(length2);
        }
      }

      // The markup to be added to every canopy
      double markup = 175;
      // The paypal charge to be added to each canopy
      double percent = 0.03;

      DecimalFormat df = new DecimalFormat("#.##");

      // GET THE SHELTER RITE PRICE
      System.out.println("Enter the Shelter-Rite® price: ");
      double shelter = scanner.nextDouble();
      // Add the markup price and increse by 3%
      shelter = ((shelter + markup) * percent) + (shelter + markup);
      // Stop the price at two decimal points
      shelter = Double.parseDouble(df.format(shelter));

      // GET THE HARBOR TIME PRICE
      System.out.println("Enter the Harbor-Time™ price: ");
      double harbor = scanner.nextDouble();
      // Add the markup price and increse by 3%
      harbor = ((harbor + markup) * percent) + (harbor + markup);
      // Stop the price at two decimal points
      harbor = Double.parseDouble(df.format(harbor));

      System.out.println("Product Number: " + num);
      System.out.println("Brand: " + brandName);
      System.out.println("Width: " + width + "in");
      System.out.println("Length: " + lengthFt + "ft" + lengthIn + "in");
      System.out.println("Shelter Price: " + shelter);
      System.out.println("Harbor Price: " + harbor);
    }
    
}
