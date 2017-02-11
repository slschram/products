import java.util.*;
import java.lang.*;
import java.text.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.google.gson.Gson;

/**
* @author Sara Farkas
*/

public class ProductJson {
   public static void main(String args[]) {
    ArrayList<Object> productList = new ArrayList<Object>();
    Object[] data = new Object[] {10};
    int counter = 0;
    String num = "";

    while (true){
      Scanner scanner = new Scanner(System.in);
      scanner.useLocale(Locale.ENGLISH);

      System.out.println("Enter the product number: ");
      num = scanner.nextLine();
      // If the user types 'end', the program will end
      if (num.equals("end")){
        break;
      }

      if (num.length() < 2) {
        continue;
      }

      String two = num.substring(0,2).toUpperCase();
      String three = num.substring(0,3).toUpperCase();
      String four = num.substring(0,4).toUpperCase();
      boolean validBrand = false;

      // Make sure the first two characters are valid letters
      if (two.equals("SM") || two.equals("DK") || two.equals("DR")
      || two.equals("DL") || two.equals("FL") || two.equals("HM") || two.equals("SS")
      || two.equals("MS") || two.equals("LS") || two.equals("NM")
      || two.equals("CL") || two.equals("PP") || two.equals("PD") || two.equals("RL") || two.equals("VB")) {
        validBrand = true;
      }
      // Check for valid 3 letters
      else if (two.equals("BK") || two.equals("HW") || two.equals("MX") || two.equals("CL")) {
        if (Character.isDigit(num.charAt(2))) {
          validBrand = true;
        }
        else if (three.equals("BKT") || three.equals("HWA") || three.equals("MXT") || three.equals("CLT")) {
          if (Character.isDigit(num.charAt(3))) {
            validBrand = true;
          }
        }
      }
      // If the first two or three letters aren't valid, validBrand is false
      else {validBrand = false;}

      // Input validation
      while (
        validBrand == false ||
        // Make sure num is at least 9 character but no more than 11
        num.length() < 9 ||
        num.length() > 11 ||
        // Make sure there are at least 2 letters
        Character.isDigit(num.charAt(0)) ||
        Character.isDigit(num.charAt(1)) ||
        // Make sure the last 7 characters are digits
        // TODO refactor the below chunk of code
        !Character.isDigit(num.charAt(num.length()-1)) ||
        !Character.isDigit(num.charAt(num.length()-2)) ||
        !Character.isDigit(num.charAt(num.length()-3)) ||
        !Character.isDigit(num.charAt(num.length()-4)) ||
        !Character.isDigit(num.charAt(num.length()-5)) ||
        !Character.isDigit(num.charAt(num.length()-6)) ||
        !Character.isDigit(num.charAt(num.length()-7))
        ) {

          System.out.println("\nPlease re-enter the product number: ");
          num = scanner.nextLine();

          if (num.equals("end")){
            break;
          }

          two = num.substring(0,2).toUpperCase();
          three = num.substring(0,3).toUpperCase();
          four = num.substring(0,4).toUpperCase();

          // Reset validBrand & make sure the first two characters are valid letters
          if (two.equals("SM") || two.equals("DK") || two.equals("DR")
          || two.equals("DL") || two.equals("FL") || two.equals("HM") || two.equals("SS")
          || two.equals("MS") || two.equals("LS") || two.equals("NM")
          || two.equals("CL") || two.equals("PP") || two.equals("PD") || two.equals("RL") || two.equals("VB")) {
            validBrand = true;
          }
          // Check for valid 3 letters
          else if (two.equals("BK") || two.equals("HW") || two.equals("MX") || two.equals("CL")) {
            if (Character.isDigit(num.charAt(2))) {
              validBrand = true;
            }
            else if (three.equals("BKT") || three.equals("HWA") || three.equals("MXT") || three.equals("CLT")) {
              if (Character.isDigit(num.charAt(3))) {
                validBrand = true;
              }
            }
          }
          // If the first two or three letters aren't valid, validBrand is false
          else {validBrand = false;}

      } // end while loop

          String brandName = getBrand(num, scanner);
          int width = getWidth(num);

          int lengthResult[] = getLength(num);
          int lengthFt = lengthResult[0];
          int lengthIn = lengthResult[1];

          double priceResult[] = calculatePrice();
          double shelter = priceResult[0];
          double harbor = priceResult[1];

          // TODO save products to an array at the end of each input
          data = new Object[] {num, brandName, width, lengthFt, lengthIn, shelter, harbor};
          productList.add(data);
          data = new Object[] {10};
      }

      createJSON(productList);
    }

    // GET AND ASSIGN BRAND NAME
    public static String getBrand(String num, Scanner scanner){
      String brandName = "";
      // Get the first two characters of the input
      String firstTwo = num.substring(0,2).toUpperCase();
      // Determine if the third character is a number
      // Matches SM, BK, DK, DR, DL, FL, HM, HW, SS, MS, LS, MX, NM, CL, PP, PD, RL, VB
      boolean char3 = Character.isDigit(num.charAt(2));
      String firstThree = num.substring(0,3).toUpperCase();
      // Determine if the fourth character is a number
      // Matches BKT, HWA, MXT, CLT
      boolean char4 = Character.isDigit(num.charAt(3));
      String firstFour = num.substring(0,4).toUpperCase();
      // Determine if the fifth character is a number
      // Matches CLTZ

      if(firstTwo.equals("SM") && char3 == true){
        brandName = "ShoreMaster®";
      }
      else if(firstTwo.equals("BK")){
        if(num.substring(0,3).toUpperCase().equals("BKT")){
          brandName = "Beach King Tower";
        }
        else {
          brandName = "Beach King";
        }
      }
      else if(firstTwo.equals("DK")){
        brandName = "DAKA";
      }
      else if(firstTwo.equals("DR")){
        brandName = "Dock'Rite";
      }
      else if(firstTwo.equals("DL")){
        brandName = "DuraLift";
      }
      else if(firstTwo.equals("FL")){
        brandName = "FLOE";
      }
      else if(firstTwo.equals("HM")){
        brandName = "Harbor Master";
      }
      else if(firstTwo.equals("HW")){
        if(num.substring(0,3).toUpperCase().equals("HWA")){
          brandName = "Hewitt Flat Front";
        }
        else {
          brandName = "Hewitt Deluxe Front";
        }
      }
      else if(firstTwo.equals("SS")){
        brandName = "ShoreStation® Aluminum";
      }
      else if(firstTwo.equals("MS")){
        brandName = "ShoreStation® Steel";
      }
      else if(firstTwo.equals("LS")){
        brandName = "Lake Shore Products";
      }
      else if(firstTwo.equals("MX")){
        if(num.substring(0,3).toUpperCase().equals("MXT")){
          brandName = "Max Docks Tower";
        }
        else {
          brandName = "Max Docks";
        }
      }
      else if(firstTwo.equals("NM")){
        brandName = "Newmans";
      }
      else if(firstTwo.equals("CL")){
        if(num.substring(0,3).toUpperCase().equals("CLT")){
          brandName = "Nucraft Craftlander High Top Canopy";
        }
        else if(num.substring(0,4).toUpperCase().equals("CLTZ")){
          brandName = "Nucraft Craftlander High Top Zip Tower";
        }
        else {
          brandName = "Nucraft Craftlander";
        }
      }
      else if(firstTwo.equals("PP")){
        brandName = "Pier Pleasure";
      }
      else if(firstTwo.equals("PD")){
        brandName = "Porta-Dock";
      }
      else if(firstTwo.equals("RL")){
        brandName = "RidgeLine";
      }
      else if(firstTwo.equals("VB")){
        brandName = "Vibo";
      }
      else {
        // Input validation
        while (
          // Make sure num is at least 9 character but no more than 11
          num.length() < 9 ||
          num.length() > 10 ||
          // Make sure at least the first two characters are letters
          Character.isDigit(num.charAt(0)) ||
          Character.isDigit(num.charAt(1)) ||
          // Make sure the last 7 characters are digits
          // TODO refactor the below chunk of code
          !Character.isDigit(num.charAt(num.length()-1)) ||
          !Character.isDigit(num.charAt(num.length()-2)) ||
          !Character.isDigit(num.charAt(num.length()-3)) ||
          !Character.isDigit(num.charAt(num.length()-4)) ||
          !Character.isDigit(num.charAt(num.length()-5)) ||
          !Character.isDigit(num.charAt(num.length()-6)) ||
          !Character.isDigit(num.charAt(num.length()-7)) ||
          !num.substring(0,4).toUpperCase().equals("CLTZ")
          ) {

            System.out.println("Brand not recognized, try again.");
            String newNum = scanner.nextLine();
            brandName = getBrand(newNum, scanner);

        } // end while loop
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

      // System.out.println("Enter the Shelter-Rite® price: ");
      // shelter = scanner.nextDouble();

      while (true) {
          System.out.println("Enter the Shelter-Rite® price:");
          try {
              shelter = Double.parseDouble(scanner.next());
              break; // will only get to here if input was a double
          } catch (NumberFormatException ignore) {
              System.out.println("Invalid input");
          }
      }

      // Add the markup price and increse by 3%
      shelter = ((shelter + markup) * percent) + (shelter + markup);
      // Stop the price at two decimal points
      shelter = Double.parseDouble(df.format(shelter));

      // GET THE HARBOR TIME PRICE
      while (true) {
          System.out.println("Enter the Harbor-Time™ price:");
          try {
              harbor = Double.parseDouble(scanner.next());
              break; // will only get to here if input was a double
          } catch (NumberFormatException ignore) {
              System.out.println("Invalid input");
          }
      }

      // Add the markup price and increse by 3%
      harbor = ((harbor + markup) * percent) + (harbor + markup);
      // Stop the price at two decimal points
      harbor = Double.parseDouble(df.format(harbor));

      return new double[] {shelter, harbor};
    }

    // WRITE DATA AS JSON OBJECT TO FILE
    public static void createJSON(ArrayList<Object> productList){
       // public static void createJSON(int count, String num, String brandName,
       // int width, int lengthFt, int lengthIn,double shelter, double harbor){

      // JSONObject obj = new JSONObject();
     // 	obj.put(productList);
     String json = new Gson().toJson(productList);

  		try {
        FileWriter file = new FileWriter("/Users/user/Desktop/products/products.json", true); {
    			file.write(json);
    			System.out.println("Successfully Copied JSON Object to File...");
    			System.out.println("\nJSON Object: " + json);
          file.close();
        }
  		} catch (IOException e) {
        e.printStackTrace();
      }
    }

}
