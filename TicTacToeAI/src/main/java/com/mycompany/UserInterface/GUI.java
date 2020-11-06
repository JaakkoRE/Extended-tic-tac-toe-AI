/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.UserInterface;

/**
 *
 * @author Jaakko
 */
import com.mycompany.tictactoeai.BoardLogic.Board;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class GUI extends Application {

    public GridPane boardUi;
    public Board board;
    public char[][] gameBoard;
    public Button[][] buttons;
    public GUI() {
        this.board = new Board(1,1,1);
        this.gameBoard = board.getGameBoard();
    }
    public void setBoard(Board board) {
        this.board = board;
        this.gameBoard = board.getGameBoard();
    }
    public void interFaceStart() {  
        launch(GUI.class);
    }
    @Override
    public void start(Stage window) throws Exception {
        VBox startingScreen = new VBox();
        TextField xLength = new TextField();
        xLength.setPromptText("Board width");
        TextField yLength = new TextField();
        yLength.setPromptText("Board height");
        TextField vclField = new TextField();
        vclField.setPromptText("Victory condition length");
        Button start = new Button("Start");
        startingScreen.getChildren().addAll(xLength,yLength,vclField,start);
        int hei = 5;
        start.setOnAction((event) -> {
            int x =  Integer.parseInt(xLength.getText());
            int y =  Integer.parseInt(yLength.getText());
            int vcl =  Integer.parseInt(vclField.getText());
            this.board = new Board(x,y,vcl);
            this.gameBoard = board.getGameBoard();
            GridPane gridpane = boardSetUp();
            Scene view = new Scene(gridpane, 450, 300);
            window.setScene(view);
            boardUi = gridpane;
        });
        
        Scene view = new Scene(startingScreen, 450, 300);
        window.setScene(view);
        window.show();
    }
    
    public GridPane boardSetUp() {
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(7,7,7,7));
        gridpane.setVgap(4);
        gridpane.setHgap(4);
        
        Button[][] buttonlist = new Button[gameBoard.length][gameBoard[0].length];
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard[0].length; j++){
                buttonlist[i][j] = new Button(gameBoard[i][j]+"");
                gridpane.add(buttonlist[i][j],i,j);
                Button hei = new Button();
                buttonlist[i][j].setOnAction((event) -> {
                });
            }
        }

        return gridpane;
    }

}
