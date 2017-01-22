import java.util.*;
import java.lang.*;
import java.text.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
* @author Sara Farkas
*/

// To compile: javac -cp  /Users/user/Desktop/products/program/json-simple-1.1.1.jar ProductJson.java
// To run: java -cp .:json-simple-1.1.1.jar ProductJson
public class ProductJson {
   public static void main(String args[]) {
      Scanner scanner = new Scanner(System.in);
      scanner.useLocale(Locale.ENGLISH);

      System.out.println("Enter the product number: ");
      String num = scanner.nextLine();
      //9 is the minimum char count for num
      //11 is the max char count for num
      String brandName = getBrand(num);
      int width = getWidth(num);

      int lengthResult[] = getLength(num);
      int lengthFt = lengthResult[0];
      int lengthIn = lengthResult[1];

      double priceResult[] = calculatePrice();
      double shelter = priceResult[0];
      double harbor = priceResult[1];

      createJSON(num, brandName, width, lengthFt, lengthIn, shelter, harbor);

      System.out.println("Product Number: " + num);
      System.out.println("Brand: " + brandName);
      System.out.println("Width: " + width + "in");
      System.out.println("Length: " + lengthFt + "ft" + lengthIn + "in");
      System.out.println("Shelter Price: " + shelter);
      System.out.println("Harbor Price: " + harbor);

    }

    // GET AND ASSIGN BRAND NAME
    public static String getBrand(String num){
      String brandName = "";
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

      return brandName;
    }

    // GET AND ASSIGN CANOPY WIDTH
    public static int getWidth(String num){
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

      return width;
    }

    // GET AND ASSIGN CANOPY LENGTH
    public static int[] getLength(String num){
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

      return new int[] {lengthFt, lengthIn};
    }

    public static double[] calculatePrice(){
      // The markup to be added to every canopy
      double markup = 175;
      // The paypal charge to be added to each canopy
      double percent = 0.03;
      DecimalFormat df = new DecimalFormat("#.##");
      double shelter = 0;
      double harbor = 0;

      // GET THE SHELTER RITE PRICE
      Scanner scanner = new Scanner(System.in);
      scanner.useLocale(Locale.ENGLISH);

      System.out.println("Enter the Shelter-Rite® price: ");
      shelter = scanner.nextDouble();
      // Add the markup price and increse by 3%
      shelter = ((shelter + markup) * percent) + (shelter + markup);
      // Stop the price at two decimal points
      shelter = Double.parseDouble(df.format(shelter));

      // GET THE HARBOR TIME PRICE
      System.out.println("Enter the Harbor-Time™ price: ");
      harbor = scanner.nextDouble();
      // Add the markup price and increse by 3%
      harbor = ((harbor + markup) * percent) + (harbor + markup);
      // Stop the price at two decimal points
      harbor = Double.parseDouble(df.format(harbor));

      return new double[] {shelter, harbor};
    }

    // WRITE DATA AS JSON OBJECT TO FILE
    public static void createJSON(
    String num, String brandName,
    int width, int lengthFt, int lengthIn,
    double shelter, double harbor){
      JSONObject obj = new JSONObject();
  		obj.put("Name", "liftcanopies.com");
  		obj.put("Year", "2016");

  		JSONArray products = new JSONArray();
      JSONObject obj1 = new JSONObject();
      JSONObject obj2 = new JSONObject();

  		obj2.put("id", num);
      obj2.put("brand", brandName);
      obj2.put("width", width);
      obj2.put("lengthFt", lengthFt);
      obj2.put("lengthIn", lengthIn);
      obj2.put("shelterPrice", shelter);
      obj2.put("harborPrice", harbor);

      obj1.put("product", obj2);

      products.add(obj1);
  		obj.put("Product List", products);

  		try {
        FileWriter file = new FileWriter("/Users/user/Desktop/products/products.json"); {
    			file.write(obj.toJSONString());
    			System.out.println("Successfully Copied JSON Object to File...");
    			System.out.println("\nJSON Object: " + obj);
          file.close();
        }
  		} catch (IOException e) {
        e.printStackTrace();
      }
    }


}
