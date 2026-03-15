package ch.bbw.cg;

import ch.bbw.cg.gamelogic.CellBoard;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainApp extends Application {

    public static final int COLS = 20;
    public static final int ROWS = 20;

    private static final int GENERATIONS = 25; // For unlimited animation loop use Timeline.INDEFINITE

    private Canvas canvas;
    private GraphicsContext gc;
    private CellBoard board;

    public static final int TILE_SIZE = 20; // pixels per tile
    public static final int PADDING = 20; // outer padding

    public static final Color GRID_COLOR = Color.LIGHTGREY; // --> alternative .web("#909090")

    @Override
    public void start(Stage stage) {
        // Define the initial state for each cell
        initializeCanvas();

        board = new CellBoard();
        board.initializeCellBoard(gc);
        board.defineAllCellsNeighbours();

        // Animation: Updates after a specific duration in milliseconds.
        play(stage);
    }

    private void initializeCanvas() {
        // A simple canvas to prove drawing works
        int width = PADDING * 2 + COLS * TILE_SIZE;
        int height = PADDING * 2 + ROWS * TILE_SIZE;

        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();

        // Background color and size
        gc.setFill(GRID_COLOR);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void play(Stage stage) {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(2000), e -> {
                board.calculateNextGeneration(gc);
            })
        );
        timeline.setCycleCount(GENERATIONS);
        timeline.play();

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);

        stage.setTitle("Game of Life");
        stage.setScene(scene);
        stage.show();
    }

//    public void drawBoard() {
//        for (int row = 0; row < ROWS; row++) {
//            for (int column = 0; column < COLS; column++) {
//                double x = PADDING + column * TILE_SIZE;
//                double y = PADDING + row * TILE_SIZE;
//
//                gc.setFill(alive[row][column] ? aliveColor : deadColor);
//                gc.fillRect(x, y, TILE_SIZE, TILE_SIZE);
//
//                gc.setStroke(gridLine);
//                gc.strokeRect(x, y, TILE_SIZE, TILE_SIZE);
//            }
//        }
//    }
//
//    private void applyRules() {
//        for (int row = 0; row < ROWS; row++) {
//            for (int column = 0; column < COLS; column++) {
//                if(alive[row][column]) {
//                    applyLivingCellRule(row, column, "4NeighboursRule");
//                    applyLivingCellRule(row, column, "lessThan2NeighboursRule");
//                } else {
//                    applyDeadCellRule(row, column, "3NeighboursRule");
//                }
//            }
//        }
//    }
//
//    private void applyLivingCellRule(int row, int column, String rule) {
//        int rowBefore;
//        int rowAfter;
//        int columnBefore;
//        int columnAfter;
//        if (row == 0) {
//            rowBefore = ROWS - 1;
//        } else {
//            rowBefore = row - 1;
//        }
//        if (column == 0) {
//            columnBefore = COLS - 1;
//        } else {
//            columnBefore = column - 1;
//        }
//        if (row == ROWS - 1) {
//            rowAfter = 0;
//        } else {
//            rowAfter = row + 1;
//        }
//        if (column == COLS - 1) {
//            columnAfter = 0;
//        } else {
//            columnAfter = column + 1;
//        }
//        switch (rule) {
//            case "4NeighboursRule":
//                if (alive[rowBefore][column] && alive[rowAfter][column] && alive[row][columnBefore] && alive[row][columnAfter])
//                    alive[row][column] = false; // death by overpopulation
//                break;
//            case "lessThan2NeighboursRule":
//                if ((alive[rowBefore][column] && alive[rowAfter][column]) ||
//                    (alive[row][columnBefore] && alive[row][columnAfter]) ||
//                    (alive[rowBefore][column] && alive[row][columnBefore]) ||
//                    (alive[rowAfter][column] && alive[row][columnAfter])) {}// cell keeps living
//                else
//                    alive[row][column] = false;
//                break;
//        }
//    }
//
//    private void applyDeadCellRule(int row, int column, String rule) {
//        int rowBefore;
//        int rowAfter;
//        int columnBefore;
//        int columnAfter;
//        if (row == 0) {
//            rowBefore = ROWS - 1;
//        } else {
//            rowBefore = row - 1;
//        }
//        if (column == 0) {
//            columnBefore = COLS - 1;
//        } else {
//            columnBefore = column - 1;
//        }
//        if (row == ROWS - 1) {
//            rowAfter = 0;
//        } else {
//            rowAfter = row + 1;
//        }
//        if (column == COLS - 1) {
//            columnAfter = 0;
//        } else {
//            columnAfter = column + 1;
//        }
//        switch (rule) {
//            case "3NeighboursRule":
//                if ((alive[rowBefore][column] && alive[rowAfter][column] && alive[row][columnBefore]) ||
//                        (alive[row][columnBefore] && alive[row][columnAfter] && alive[rowBefore][column]) ||
//                        (alive[rowBefore][column] && alive[rowAfter][column] && alive[row][columnAfter]) ||
//                        (alive[row][columnBefore] && alive[row][columnAfter] && alive[rowAfter][column]))
//                    alive[row][column] = true;
//                break;
//        }
//    }

    public static void main(String[] args) {
        launch(args);
    }
}


