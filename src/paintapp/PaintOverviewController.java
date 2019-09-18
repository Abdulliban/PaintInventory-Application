/**
 * @author Abdul Liban
 */
package paintapp;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PaintOverviewController
{
   @FXML
   private TableView<Paint> paintTable;
   @FXML
   private TableColumn<Paint, String> nameColumn;
   @FXML
   private TableColumn<Paint, String> typeColumn;
   @FXML
   private TableColumn<Paint, String> baseColumn;
   @FXML
   private TableColumn<Paint, Integer> idColumn;
   @FXML
   private TableColumn<Paint, String> shadeColumn;
   @FXML
   private TableColumn<Paint, Integer> stockColumn;
   @FXML
   private TableColumn<Paint, Integer> sqftColumn;
   @FXML
   private TableColumn<Paint, Double> costColumn;
   @FXML
   private TableColumn<Paint, String> brandColumn;
   @FXML
   private TextField search;
   @FXML
   private TextField paintName;
   @FXML
   private ComboBox<String> paintType;
   @FXML
   private ComboBox<String> paintBase;
   @FXML
   private TextField paintShade;
   @FXML
   private TextField paintSqft;
   @FXML
   private TextField paintCost;
   @FXML
   private TextField paintBrand;
   @FXML
   private TextField paintStock;
   @FXML
   private ImageView logo;
   @FXML
   private Button searchButton;
   @FXML
   private Button addButton;
   @FXML
   private Button deleteButton;
   @FXML
   private Button edit;
   @FXML
   private Rectangle rec;


   @FXML
   private Label systemMsg;

   private ArrayList<Paint> paints = new ArrayList<>();
   private int id = 30;

   Paint objPaint = new Paint();





   @FXML
   public void initialize ()
   {
      // Initialize the Paint table with the two columns.
      nameColumn.setCellValueFactory(new PropertyValueFactory<Paint, String>("name"));
      typeColumn.setCellValueFactory(new PropertyValueFactory<Paint, String>("type"));
      baseColumn.setCellValueFactory(new PropertyValueFactory<Paint, String>("base"));
      idColumn.setCellValueFactory(new PropertyValueFactory<Paint, Integer>("id"));
      shadeColumn.setCellValueFactory(new PropertyValueFactory<Paint, String>("shade"));
      stockColumn.setCellValueFactory(new PropertyValueFactory<Paint, Integer>("stock"));
      sqftColumn.setCellValueFactory(new PropertyValueFactory<Paint, Integer>("sqft"));
      costColumn.setCellValueFactory(new PropertyValueFactory<Paint, Double>("cost"));
      brandColumn.setCellValueFactory(new PropertyValueFactory<Paint, String>("brand"));




      //adding to combobox type
      List<String> list = new ArrayList<String>();
      list.add("Water");
      list.add("Oil");
      ObservableList<String> obList = FXCollections.observableList(list);
      paintType.getItems().clear();
      paintType.setItems(obList);
      //adding to combox base
      List<String> list2 = new ArrayList<String>();
      list2.add("Gloss");
      list2.add("Matte");
      ObservableList<String> obList2 = FXCollections.observableList(list2);
      paintBase.getItems().clear();
      paintBase.setItems(obList2);

      objPaint.getPaintList().clear();
      paintTable.setItems(FXCollections.observableArrayList(objPaint.getPaint()));
      systemMsg.setTextFill(Color.GREEN);

      rec.setFill(Color.web("#ffce50"));
   }

   @FXML
   private void searchButton (ActionEvent event)
   {
      // systemMsg.setText("");
      String paintName = "";

      if (!search.getText().equals("")) {
         paintName = search.getText();
      }
      else {
         paintName = null;
      }

      paintTable.getItems().clear();

      paintTable.setItems(FXCollections.observableArrayList(objPaint.search(paintName)));
      search.clear();

   }

   @FXML
   private void deleteButton (ActionEvent event)
   {
      //systemMsg.setText("");
      ObservableList<Paint> allPaint, removePaint;
      Paint selectedPaint;
      allPaint = paintTable.getItems();
      selectedPaint = paintTable.getSelectionModel().getSelectedItem();
      removePaint = paintTable.getSelectionModel().getSelectedItems();

      removePaint.forEach(allPaint::remove);

      for (int x = 0; x < objPaint.getPaintList().size(); x++) {
         if (objPaint.getPaintList().get(x).getName() == selectedPaint.getName()) {
            objPaint.paintList.remove(x);
         }
         systemMsg.setText("Successfully removed.");
      }
      objPaint.addPaint();
   }

   @FXML
   private void addButton (ActionEvent event)
   {
      //systemMsg.setText("");
      for (int i = 0; i < paints.size(); i++) {
         System.out.println(paints.get(i).toString());
      }

      if (!paintName.getText().equals("")
              && !paintType.getValue().equals("")
              && !paintBase.getValue().equals("")
              && !paintShade.getText().equals("")
              && !paintSqft.getText().equals("")
              && !paintCost.getText().equals("")
              && !paintBrand.getText().equals("")
              && !paintStock.getText().equals("")
              && paintShade.getText().length() <= 6) {

         objPaint = new Paint();

         paints = objPaint.getPaint();

         paints.add(new Paint(generateId(),
                              paintName.getText(),
                              paintType.getValue(),
                              paintBase.getValue(),
                              paintShade.getText(),
                              Integer.parseInt(paintSqft.getText()),
                              Double.parseDouble(paintCost.getText()),
                              Integer.parseInt(paintStock.getText()),
                              paintBrand.getText()
         ));

         objPaint.setPaintList(paints);
         objPaint.addPaint();
         systemMsg.setTextFill(Color.GREEN);
         systemMsg.setText("Successfully added.");
         objPaint.paintList.clear();
         paintTable.setItems(FXCollections.observableArrayList(objPaint.getPaint()));


      }
      else {
         systemMsg.setTextFill(Color.RED);
         systemMsg.setText("*Please fill in all fields*\n *Shade is 6 Characters Long*");
      }
      paintName.clear();
      paintType.setValue(null);
      paintBase.setValue(null);
      paintShade.clear();
      paintSqft.clear();
      paintCost.clear();
      paintBrand.clear();
      paintStock.clear();
   }

   public int generateId ()
   {

      this.id++;
      return id;
   }

   @FXML
   public void clickItem (MouseEvent event)
   {
      rec.setFill(Color.web(paintTable.getSelectionModel().getSelectedItem().getShade()));
   }

   @FXML
   private void btnEditActionHandler (ActionEvent event)
   {
      // systemMsg.setText("");
      Paint selectedPaint;
      selectedPaint = paintTable.getSelectionModel().getSelectedItem();

      if (!paintName.getText().equals("")
              || !paintType.getValue().equals("")
              || !paintBase.getValue().equals("")
              || !paintShade.getText().equals("")
              || !paintSqft.getText().equals("")
              || !paintCost.getText().equals("")
              || !paintBrand.getText().equals("")
              || !paintStock.getText().equals("")) {

         for (int y = 0; y < objPaint.paintList.size(); y++) {
            if (objPaint.getPaintList().get(y).getName() == selectedPaint.getName()) {
               objPaint.paintList.get(y).setName(paintName.getText());
               objPaint.paintList.get(y).setType(paintType.getValue());
               objPaint.paintList.get(y).setSqft(Integer.parseInt(paintSqft.getText()));
               objPaint.paintList.get(y).setCost(Double.parseDouble(paintCost.getText()));
               objPaint.paintList.get(y).setBase(paintBase.getValue());
               objPaint.paintList.get(y).setShade(paintShade.getText());
               objPaint.paintList.get(y).setBrand(paintBrand.getText());
               objPaint.paintList.get(y).setStock(Integer.parseInt(paintStock.getText()));

            }
         }

         systemMsg.setText("You have edited " + selectedPaint.getId());

         paintName.clear();
         paintType.setValue(null);
         paintSqft.clear();
         paintCost.clear();
         paintBase.setValue(null);
         paintShade.clear();
         paintBrand.clear();
         paintStock.clear();

         objPaint.addPaint();
         objPaint.paintList.clear();
         paintTable.setItems(FXCollections.observableArrayList(objPaint.getPaint()));
      }
   }


}
