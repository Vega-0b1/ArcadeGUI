import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.control.ScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.io.IOException;
import java.awt.Desktop;

public class ArcadeGUI extends Application {
  TilePane pane = new TilePane();

  @Override
  public void start(Stage primaryStage){
    setGraphic();

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);
    scrollPane.setContent(pane);

    Scene scene = new Scene(scrollPane);
    scene.getStylesheets().add("css/main.css");
    primaryStage.setTitle("ArcadeGUI");
    primaryStage.setFullScreen(true);
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  public void setGraphic(){
    for(Global.i = 0; Global.i < fileCount();Global.i++){
      String btnName = Global.gameFiles[Global.i].getName();
      Button btn = new Button(btnName);

      File[] gameArt = new File("gameArt").listFiles();

      Image image = new Image("gameArt/" + gameArt[Global.i].getName());
      ImageView imageView = new ImageView(image);
      imageView.setFitWidth(350);
      imageView.setFitHeight(500);
      ServeHandle handle = new ServeHandle();

      btn.setGraphic(imageView);
      btn.setOnAction(handle);
      pane.getChildren().add(btn);
    }
  }

  public int fileCount(){
    int files = new File("gameLaunchers").listFiles().length;
    return files;
  }
}

class ServeHandle implements EventHandler<ActionEvent> {
  public void handle(ActionEvent e) {
    String BtnName = ((Button)e.getSource()).getText();
    try{

      Desktop desktop = null;
      if(Desktop.isDesktopSupported()) {
        desktop = Desktop.getDesktop();
      }

      desktop.open(new File("gameLaunchers/" + BtnName));
    }
    catch(Exception b){
      System.out.println("Directory not found");
    }
  }
}


class Global{
    public static int i;
    public static File[] gameFiles = new File("gameLaunchers").listFiles();
}
