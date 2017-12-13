public class GUI extends Application {
    
    public void start(Stage primaryStage) throws IOException {
        final GraphBuilder graph = new GraphBuilder();
        graph.buildGraph();
        Button btn = new Button();
        
        LinkGenerator linkgen = new LinkGenerator();
        linkgen.setApiKey("AIzaSyCxkwnaGiEcNqla43FKBPfNR3sW-ApIQ5E");
        Label start = new Label("Starting point: ");
        TextField a = new TextField();
        Label end = new Label("Ending point: ");
        TextField b = new TextField();
        ImageView show;
        btn.setText("Show map");
        
        
        GridPane root = new GridPane();
        root.add(btn,350 , 60);
        root.add(start, 150, 30);
        root.add(end, 450, 30);
        root.add(a, 350, 30);
        root.add(b, 650, 30);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
             Image map;
             ImageView MapView;
             String startLoc = graph.network.findLatLong(a.getText());
             String endLoc = graph.network.findLatLong(b.getText());
             linkgen.setPath(startLoc, endLoc);
             String url = new String(linkgen.generateLink());
             map = new Image(url);
             MapView = ImageViewBuilder.create().image(map).build();
             root.getChildren().add(MapView);
             linkgen.clearParam();
            }
        });
        
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
