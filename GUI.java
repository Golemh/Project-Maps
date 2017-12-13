import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUI extends Application {
    
    public void start(Stage primaryStage) throws IOException {
        final GraphBuilder graph = new GraphBuilder();
        graph.buildGraph();
        
    	Button btn = new Button();
        btn.setText("Show map");
       
         LinkGenerator linkgen = new LinkGenerator();
         linkgen.setApiKey("AIzaSyCxkwnaGiEcNqla43FKBPfNR3sW-ApIQ5E");
        
        ImageView icon = new ImageView("http://icons.iconarchive.com/icons/alienvalley/osx-dock-replacement/512/maps-icon.png");
        icon.setEffect(new DropShadow());
        icon.setFitHeight(100); 
        icon.setFitWidth(100);
        
        Label start = new Label("Starting point: ");
        TextField a = new TextField();
        Label end = new Label("Ending point: ");
        TextField b = new TextField();
        HBox panel = new HBox(20, start,a,end,b);
        panel.setPadding(new Insets(10));
        panel.setAlignment(Pos.CENTER);
        
        ImageView show= new ImageView();
        VBox root = new VBox(10); 
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.setPrefHeight(300);
        root.setPrefWidth(800);
        root.getChildren().addAll(icon,panel,btn,show);
        
        
        
        
        
        
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle(".root {\r\n" + 
        		"    -fx-base: rgb(50, 50, 50);\r\n" + 
        		"    -fx-background: rgb(50, 50, 50);\r\n" + 
        		"\r\n" + 
        		"    /* make controls (buttons, thumb, etc.) slightly lighter */\r\n" + 
        		"    -fx-color: derive(-fx-base, 10%);\r\n" + 
        		"\r\n" + 
        		"    /* text fields and table rows background */\r\n" + 
        		"    -fx-control-inner-background: rgb(20, 20, 20);\r\n" + 
        		"    /* version of -fx-control-inner-background for alternative rows */\r\n" + 
        		"    -fx-control-inner-background-alt: derive(-fx-control-inner-background, 2.5%);\r\n" + 
        		"\r\n" + 
        		"    /* text colors depending on background's brightness */\r\n" + 
        		"    -fx-light-text-color: rgb(220, 220, 220);\r\n" + 
        		"    -fx-mid-text-color: rgb(100, 100, 100);\r\n" + 
        		"    -fx-dark-text-color: rgb(20, 20, 20);\r\n" + 
        		"\r\n" + 
        		"    /* A bright blue for highlighting/accenting objects.  For example: selected\r\n" + 
        		"     * text; selected items in menus, lists, trees, and tables; progress bars */\r\n" + 
        		"    -fx-accent: rgb(0, 80, 100);\r\n" + 
        		"\r\n" + 
        		"    /* color of non-focused yet selected elements */\r\n" + 
        		"    -fx-selection-bar-non-focused: rgb(50, 50, 50);\r\n" + 
        		"}\r\n" + 
        		"\r\n" + 
        		"/* Fix derived prompt color for text fields */\r\n" + 
        		".text-input {\r\n" + 
        		"    -fx-prompt-text-fill: derive(-fx-control-inner-background, +50%);\r\n" + 
        		"}\r\n" + 
        		"\r\n" + 
        		"/* Keep prompt invisible when focused (above color fix overrides it) */\r\n" + 
        		".text-input:focused {\r\n" + 
        		"    -fx-prompt-text-fill: transparent;\r\n" + 
        		"}\r\n" + 
        		"\r\n" + 
        		"/* Fix scroll bar buttons arrows colors */\r\n" + 
        		".scroll-bar > .increment-button > .increment-arrow,\r\n" + 
        		".scroll-bar > .decrement-button > .decrement-arrow {\r\n" + 
        		"    -fx-background-color: -fx-mark-highlight-color, rgb(220, 220, 220);\r\n" + 
        		"}\r\n" + 
        		"\r\n" + 
        		".scroll-bar > .increment-button:hover > .increment-arrow,\r\n" + 
        		".scroll-bar > .decrement-button:hover > .decrement-arrow {\r\n" + 
        		"    -fx-background-color: -fx-mark-highlight-color, rgb(240, 240, 240);\r\n" + 
        		"}\r\n" + 
        		"\r\n" + 
        		".scroll-bar > .increment-button:pressed > .increment-arrow,\r\n" + 
        		".scroll-bar > .decrement-button:pressed > .decrement-arrow {\r\n" + 
        		"    -fx-background-color: -fx-mark-highlight-color, rgb(255, 255, 255);\r\n" + 
        		"}");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            Image map;
            ImageView MapView;
            @Override
            public void handle(ActionEvent event) {
             
             String startLoc = graph.network.findLatLong(a.getText());
             String endLoc = graph.network.findLatLong(b.getText());
             linkgen.setPath(startLoc, endLoc);
             String url = linkgen.generateLink();
             map = new Image(url);
             if (MapView == null)
                 MapView = ImageViewBuilder.create().image(map).build();
            
             else
                MapView.setImage(map);
             
             if (MapView.getScene() == null)
                root.getChildren().add(MapView);
             linkgen.clearParam();
                 
            }
        });
        
        Scene scene = new Scene(root, 700, 650);
        
        primaryStage.setTitle("Project Maps");
        primaryStage.setScene(scene);
        
        primaryStage.getIcons().add(icon.getImage());
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

        
        Scene scene = new Scene(root, 700, 650);
        
        primaryStage.setTitle("Project Maps");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
