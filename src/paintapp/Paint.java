/** 
 * @author Abdul Liban
 * 
 */
package paintapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Paint
{

   private SimpleStringProperty name;
   private SimpleStringProperty type;
   private SimpleStringProperty base;
   private SimpleIntegerProperty id;
   private SimpleStringProperty shade;
   private SimpleIntegerProperty stock;
   private SimpleIntegerProperty sqft;
   private SimpleDoubleProperty cost;
   private SimpleStringProperty brand;

   public ArrayList<Paint> paintList = new ArrayList<>();
   public ArrayList<Paint> searchResults = new ArrayList<>();

   /**
    * Default constructor.
    */
   public Paint ()
   {
      //this("hi", "same", "asf", 25, "sag", 25, 125, 125.5, "asf", "asga");
   }


   public Paint (int id, String name, String type, String base, String shade, int sqft, double cost, int stock, String brand)
   {
      this.name = new SimpleStringProperty(name);
      this.type = new SimpleStringProperty(type);
      this.base = new SimpleStringProperty(base);
      this.id = new SimpleIntegerProperty(id);
      this.shade = new SimpleStringProperty(shade);
      this.stock = new SimpleIntegerProperty(stock);
      this.sqft = new SimpleIntegerProperty(sqft);
      this.cost = new SimpleDoubleProperty(cost);
      this.brand = new SimpleStringProperty(brand);
   }

   public String getName ()
   {
      return name.get();
   }

   public void setName (String name)
   {
      this.name.set(name);
   }

   public StringProperty nameProperty ()
   {
      return name;
   }

   public String getType ()
   {
      return type.get();
   }

   public void setType (String type)
   {
      this.type.set(type);
   }

   public StringProperty typeProperty ()
   {
      return type;
   }

   public String getBase ()
   {
      return base.get();
   }

   public void setBase (String base)
   {
      this.base.set(base);
   }

   public StringProperty baseProperty ()
   {
      return base;
   }

   public int getId ()
   {
      return id.get();
   }

   public void setId (int id)
   {
      this.id.set(id);
   }

   public IntegerProperty idProperty ()
   {
      return id;
   }

   public String getShade ()
   {
      return shade.get();
   }

   public void setShade (String shade)
   {
      this.shade.set(shade);
   }

   public StringProperty shadeProperty ()
   {
      return shade;
   }

   public String getBrand ()
   {
      return brand.get();
   }

   public void setBrand (String brand)
   {
      this.brand.set(brand);
   }

   public StringProperty brandProperty ()
   {
      return brand;
   }

   public int getStock ()
   {
      return stock.get();
   }

   public void setStock (int stock)
   {
      this.stock.set(stock);
   }

   public IntegerProperty stockProperty ()
   {
      return stock;
   }

   public int getSqft ()
   {
      return sqft.get();
   }

   public void setSqft (int sqft)
   {
      this.sqft.set(sqft);
   }

   public IntegerProperty sqftProperty ()
   {
      return sqft;
   }

   public double getCost ()
   {
      return cost.get();
   }

   public void setCost (double cost)
   {
      this.cost.set(cost);
   }

   public DoubleProperty costProperty ()
   {
      return cost;
   }

   public ArrayList<Paint> getPaintList ()
   {
      return paintList;
   }

   public void setPaintList (ArrayList<Paint> paintList)
   {
      this.paintList = paintList;
   }

   public ArrayList<Paint> getSearchResult ()
   {
      return searchResults;
   }

   public void setSearchResult (ArrayList<Paint> searchResults)
   {
      this.searchResults = searchResults;
   }

   @Override
   public String toString ()
   {
      return getId() + "|" + getName() + "|" + getType() + "|" + getBase() + "|" + getShade() + "|" + getSqft() + "|" + getCost() + "|" + getStock() + "|" + getBrand();
   }

   public ArrayList<Paint> getPaint ()
   {

      try {
         File file = new File("src\\paintapp\\paint.txt");
         Scanner scan = new Scanner(file);

         while (scan.hasNextLine()) {
            String record = scan.nextLine();
            String[] recordData = record.split("\\|");
            int id = Integer.parseInt(recordData[0]);
            int sqft = Integer.parseInt(recordData[5]);
            Double price = Double.parseDouble(recordData[6]);
            int stock = Integer.parseInt(recordData[7]);
            //Strings can be parse as is whereas int,doubles need to be parse into a variable first before pass
            //it to the constructor
            paintList.add(new Paint(id, recordData[1], recordData[2], recordData[3], recordData[4], sqft, price, stock, recordData[8]));
         }
         scan.close();
      }
      catch (FileNotFoundException fileNotFound) {
         System.out.println("file does not exist");
      }
      catch (Exception ex) {
         System.out.println(ex.getMessage());
      }

      return paintList;
   }

   public boolean addPaint ()
   {

      try {
         FileWriter file = new FileWriter("src\\paintapp\\paint.txt");
         PrintWriter print = new PrintWriter(file);
         for (int i = 0; i < paintList.size(); i++) {
            print.println(paintList.get(i).toString());
         }
         print.close();
         return true;
      }
      catch (IOException ioe) {
         System.out.println("file could not be Edited");
      }

      return false;
   }

   public ArrayList<Paint> search (String paintName)
   {
      if (paintName != null) {
         for (int i = 0; i < this.getPaintList().size(); i++) {
            if (this.getPaintList().get(i).getName().toLowerCase().contains(paintName.toLowerCase())) {
               this.searchResults.add(this.getPaintList().get(i));
            }
         }
      }
      else {
         return this.paintList;
      }
      return searchResults;
   }

}
