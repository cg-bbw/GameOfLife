package ch.bbw.cg;

import ch.bbw.cg.gamelogic.CellBoard;
import ch.bbw.cg.gamelogic.GameSettings;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainApp extends Application {


    private Canvas canvas;
    private GraphicsContext gc;
    private CellBoard board;

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
        int width = GameSettings.PADDING * 2 + GameSettings.COLS * GameSettings.TILE_SIZE;
        int height = GameSettings.PADDING * 2 + GameSettings.ROWS * GameSettings.TILE_SIZE;

        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();

        // Background color and size
        gc.setFill(GameSettings.GRID_COLOR);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void play(Stage stage) {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(2000), e -> {
                board.calculateNextGeneration(gc);
            })
        );
        timeline.setCycleCount(GameSettings.GENERATIONS);
        timeline.play();

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);

        stage.setTitle("Game of Life");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


