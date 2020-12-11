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
import com.mycompany.tictactoeai.AILogic.MinMaxAI;
import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// rough. very work in progress. Useless currently
public class GUI extends Application {

    private GameStatus status;
    public Board board;
    public char[][] gameBoard;
    public Button[][] buttons;
    public MinMaxAI ai;
    public Label GUIstatus;
    
    private Stage stag;
    private Scene scen;
    public GUI() {
        this.board = new Board(1,1,1);
        this.status = new GameStatus(board,'O');
        this.gameBoard = board.getGameBoard();
        this.ai = new MinMaxAI(this.status);
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void setBoard(Board board) {
        this.board = board;
        this.gameBoard = board.getGameBoard();
        this.status = new GameStatus(board,'O');
        this.ai = new MinMaxAI(this.status);

    }
    public void interFaceStart() {  
        launch(GUI.class);
    }
    @Override
    public void start(Stage window) throws Exception {
        VBox startingScreen = new VBox();
        GUIstatus = new Label("");
        TextField xLength = new TextField();
        xLength.setPromptText("Board width");
        TextField yLength = new TextField();
        yLength.setPromptText("Board height");
        TextField vclField = new TextField();
        vclField.setPromptText("Victory condition length");
        Button startVsAI = new Button("Start you vs AI");
        Button startAIVsAI = new Button("Start AI vs AI (Text user interface recommended)");
        Label guide = new Label("AI vs AI play works better with text user interface while\ngraphical user interface works better with player vs AI");
        Button stopExecution = new Button("Stop");
                
        Button userInterfaceText = new Button("Use text user interface");
        startingScreen.getChildren().addAll(GUIstatus,xLength, yLength, vclField, startVsAI, startAIVsAI,guide, userInterfaceText, stopExecution);
        startVsAI.setOnAction((event) -> {           
            try {
            int x =  Integer.parseInt(xLength.getText());
            int y =  Integer.parseInt(yLength.getText());
            int vcl =  Integer.parseInt(vclField.getText());
            
            this.board = new Board(x,y,vcl);
            this.status = new GameStatus(board,'O');
            this.gameBoard = board.getGameBoard();
            this.ai = new MinMaxAI(this.status);
            GridPane gridpane = boardSetUpVSAI();
            Scene view = new Scene(gridpane, 900, 600);
            window.setScene(view);
            } catch (NumberFormatException z) { 
                GUIstatus.setText("Values need to be numbers");
            }
        });
        
        startAIVsAI.setOnAction((event) -> {
            try {
            int x =  Integer.parseInt(xLength.getText());
            int y =  Integer.parseInt(yLength.getText());
            int vcl =  Integer.parseInt(vclField.getText());
            this.board = new Board(x,y,vcl);
            this.status = new GameStatus(board,'O');
            this.gameBoard = board.getGameBoard();
            this.ai = new MinMaxAI(this.status);
            VBox box = simpleBoardSetUpAIVSAI();
            Scene view = new Scene(box, 900, 600);
            window.setScene(view);
            } catch (NumberFormatException z) { 
                GUIstatus.setText("Values need to be numbers");
            }
            
        });
        
        userInterfaceText.setOnAction((event) -> {
            Scene view = new Scene(new GridPane(), 0, 0);
            window.setScene(view);
            window.hide();
            UI u2 = new UI();
            u2.UILogicStart();
        }); 
        
        stopExecution.setOnAction((event) ->{
            Platform.exit();
            System.exit(0);
        });
        Scene view = new Scene(startingScreen, 450, 600);
        this.scen = view;
        this.stag = window;
        window.setScene(view);
        window.show();
        
    }
    public VBox simpleBoardSetUpAIVSAI() {
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(7,7,7,7));
        gridpane.setVgap(4);
        gridpane.setHgap(4);
        
        Button[][] buttonlist = new Button[gameBoard.length][gameBoard[0].length];
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard[0].length; j++){
                Button gameButton = new Button("_");  
                buttonlist[i][j] = gameButton;
                gridpane.add(buttonlist[i][j],i,j);
            }
        }
        VBox box = new VBox();
        Button startButton = new Button("Start the game");
        Button backButton = new Button("Go back");
        startButton.setOnAction((event) -> {
            while(true) {
                GameStatus aiMove = ai.alphaBetaBoard(status);                        
                int whereMoveX;
                    int whereMoveY;
                    for (int ind = 0; ind < gameBoard.length; ind++){
                        for (int jnd = 0; jnd < gameBoard[0].length; jnd++){
                            if (aiMove.board.gameBoard[ind][jnd] != status.board.gameBoard[ind][jnd]) {
                                whereMoveX = ind;
                                whereMoveY = jnd;
                                this.status = aiMove.copyGameStatus();
                                buttonlist[whereMoveX][whereMoveY].setText(this.status.otherPlayer()+"");
                            }
                        }
                    }    
                    if (status.checkAll() != 0) {
                        if (status.checkAll() == 'n') {
                            GUIstatus.setText("end state: \n" + status.board.toString()+ "\n " + "draw \n");
                        } else {
                            GUIstatus.setText("end state: \n" + status.board.toString()+ "\n " + status.checkAll() + " won \n");
                        }
                        break;
                    }
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                box.setVisible(false);
//                box.setVisible(true);    
            }
        });
        backButton.setOnAction((event) -> {
            this.stag.setScene(this.scen);
            this.stag.show();
        });
        box.getChildren().add(gridpane);
        box.getChildren().add(startButton);
        box.getChildren().add(backButton);
        return box;
    }
    
    public GridPane boardSetUpVSAI() {
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(7,7,7,7));
        gridpane.setVgap(4);
        gridpane.setHgap(4);
        
        Button[][] buttonlist = new Button[gameBoard.length][gameBoard[0].length];
        this.status = ai.alphaBetaBoard(status);
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard[0].length; j++){
                Button gameButton = new Button(status.board.gameBoard[i][j]+"");
                buttonlist[i][j] = gameButton;
                int x = i;
                int y = j;
                gameButton.setOnAction((event) -> {
                    Board board = new Board(3,3,3);
                    char changeValStatus = status.setBoardValue(x, y);
                    if (changeValStatus != 'e') {
                        gameButton.setText(status.otherPlayer()+"");
                    
                        if (status.checkAll() != 0) {
                            if (status.checkAll() == 'n') {
                                GUIstatus.setText("end state: \n" + status.board.toString()+ "\n " + "draw \n");
                            } else {
                            GUIstatus.setText("end state: \n" + status.board.toString()+ "\n " + status.checkAll() + " won \n");
                            }
                            this.stag.setScene(this.scen);
                            this.stag.show();
                        }
                        
                        GameStatus aiMove = ai.alphaBetaBoard(status);                        
                        int whereMoveX;
                        int whereMoveY;
                        for (int ind = 0; ind < gameBoard.length; ind++){
                            for (int jnd = 0; jnd < gameBoard[0].length; jnd++){
                                if (aiMove.board.gameBoard[ind][jnd] != status.board.gameBoard[ind][jnd]) {
                                    whereMoveX = ind;
                                    whereMoveY = jnd;
                                    this.status = aiMove.copyGameStatus();
                                    buttonlist[whereMoveX][whereMoveY].setText(this.status.otherPlayer()+"");
                                }
                            }
                        }    
                        if (status.checkAll() != 0) {
                            if (status.checkAll() == 'n') {
                                GUIstatus.setText("end state: \n" + status.board.toString()+ "\n " + "draw \n");
                            } else {
                            GUIstatus.setText("end state: \n" + status.board.toString()+ "\n " + status.checkAll() + " won \n");
                            }
                            this.stag.setScene(this.scen);
                            this.stag.show();
                        }
                    }
                });
                gridpane.add(buttonlist[i][j],i,j);

            }
        }
        Button backButton = new Button("Go back");
        backButton.setOnAction((event) -> {
            this.stag.setScene(this.scen);
            this.stag.show();
        });
        gridpane.add(backButton,gameBoard[0].length,gameBoard.length);
        return gridpane;
    }

}
