/**
 * @author Abdul Liban
 */
package paintapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PaintApp extends Application
{

   @Override
   public void start (Stage stage) throws Exception
   {
      Parent root = FXMLLoader.load(getClass().getResource("PaintOverview.fxml"));

      Scene scene = new Scene(root);
      stage.setTitle("Paint");
      stage.setScene(scene);
      stage.show();
   }

   /**
    * @param args the command line arguments
    */
   public static void main (String[] args)
   {
      launch(args);
   }


}
