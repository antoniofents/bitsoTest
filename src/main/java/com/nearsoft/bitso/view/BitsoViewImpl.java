package com.nearsoft.bitso.view;

import com.nearsoft.bitso.client.DiffOrdersWebSocketClient;
import com.nearsoft.bitso.model.Order;
import com.nearsoft.bitso.model.Trade;
import com.nearsoft.bitso.controller.BitsoController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class BitsoViewImpl extends Application implements BitsoView {



    BitsoController bitsoController;

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.init();
        bitsoController= new BitsoController(this);
        initView(primaryStage);
    }

    private void initView(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Send Image!");
        btn.setPrefSize(400, 27);
        btn.setOnAction(event-> { System.out.println("start button");});


        GridPane grid = new GridPane();
        grid.setHgap(2);
        grid.setVgap(2);
        grid.setPadding(new Insets(0, 10, 0, 10));

        // Category in column 2, row 1
        Text category = new Text("Sales:");
        category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(category, 16, 15);

        // Title in column 3, row 1
        Text chartTitle = new Text("Current Year");
        chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(chartTitle, 2, 0);

        // Subtitle in columns 2-3, row 2
        Text chartSubtitle = new Text("Goods and Services");
        grid.add(chartSubtitle, 1, 1, 2, 1);

        grid.add(btn, 0, 0, 1, 2);
        // Left label in column 1 (bottom), row 3
        Text goodsPercent = new Text("Goods\n80%");

        GridPane.setValignment(goodsPercent, VPos.BOTTOM);
        grid.add(goodsPercent, 0, 2);


        ScrollPane sp = new ScrollPane();
        sp.setFitToWidth(true);
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        sp.setContent(vbox);
        for(int i=0 ; i<10 ; i++){
            Text test = new Text("element " +i );
            vbox.getChildren().add(test);
        }

        grid.add(sp,15, 15);


      /*  // Chart in columns 2-3, row 3
        ImageView imageChart = new ImageView(
                new Image(LayoutSample.class.getResourceAsStream("graphics/piechart.png")));
        grid.add(imageChart, 1, 2, 2, 1);
*/
        // Right label in column 4 (top), row 3
        Text servicesPercent = new Text("Services\n20%");
        GridPane.setValignment(servicesPercent, VPos.TOP);
        grid.add(servicesPercent, 3, 2);


        Scene scene = new Scene(grid, 400, 427);

        primaryStage.setTitle("Image push!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @Override
    public void init() {
        System.setProperty("http.agent", "Chrome");
    }

    @Override
    public  void launchApp(){
        new BitsoViewImpl().launch(null);
    }

    @Override
    public void cleanAndRestart() {

    }


    @Override
    public void updateTrades(List<Trade> trades) {

    }

    @Override
    public void updateBids(List<Order> trades) {

    }

    @Override
    public void updateAsks(List<Order> trades) {

    }


}
