package ch.bbw.cg.gamelogic;

import ch.bbw.cg.MainApp;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class CellBoard {
    private static final int COLS = 5;
    private static final int ROWS = 5;

    public Cell[][] cells;

    private static final int INITIAL_CHANCE_TO_LIVE = 100; // Choose a value between 0 and 100.
    private final Random rng = new Random();

    public CellBoard() {
        cells = new Cell[ROWS][COLS];
    }

    public void initializeCellBoard(GraphicsContext gc) {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLS; column++) {
                cells[row][column] = new Cell(row, column);
                setInitialCellStateRandomly(cells[row][column], gc);
            }
        }
    }

    private void setInitialCellStateRandomly(Cell cell, GraphicsContext gc) {
        if(rng.nextInt(100)<INITIAL_CHANCE_TO_LIVE) {
            cell.setState(CellState.ALIVE);
        } else {
            cell.setState(CellState.DEAD);
        }
        //TODO setCellGender
        setCellParameters(cell, gc, 0);
    }

    // Set a cell to alive if the random number is less than INITIAL_CHANCE_TO_LIVE
    private void setCellParameters(Cell cell, GraphicsContext gc, int generation) {
        cell.setGeneration(generation);
        drawCell(cell, gc);
    }

    private void drawCell (Cell cell, GraphicsContext gc) {
        double x = MainApp.PADDING + cell.getYPosition() * MainApp.TILE_SIZE;
        double y = MainApp.PADDING + cell.getXPosition() * MainApp.TILE_SIZE;

        gc.setFill(cell.getColor(cell.getState()));
        gc.fillRect(x, y, MainApp.TILE_SIZE, MainApp.TILE_SIZE);

        gc.setStroke(MainApp.GRID_COLOR);
        gc.strokeRect(x, y, MainApp.TILE_SIZE, MainApp.TILE_SIZE);
    }

    public void defineAllCellsNeighbours() {
        int rowBefore;
        int rowAfter;
        int columnBefore;
        int columnAfter;
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLS; column++) {
                if (row == 0) {
                    rowBefore = ROWS - 1;
                } else {
                    rowBefore = row - 1;
                }
                if (column == 0) {
                    columnBefore = COLS - 1;
                } else {
                    columnBefore = column - 1;
                }
                if (row == ROWS - 1) {
                    rowAfter = 0;
                } else {
                    rowAfter = row + 1;
                }
                if (column == COLS - 1) {
                    columnAfter = 0;
                } else {
                    columnAfter = column + 1;
                }
                cells[row][column].defineTopLeftNeighbour(cells[rowBefore][columnBefore]);
                cells[row][column].defineTopNeighbour(cells[rowBefore][column]);
                cells[row][column].defineTopRightNeighbour(cells[rowBefore][columnAfter]);
                cells[row][column].defineRightNeighbour(cells[row][columnAfter]);
                cells[row][column].defineBottomRightNeighbour(cells[rowAfter][columnAfter]);
                cells[row][column].defineBottomNeighbour(cells[rowAfter][column]);
                cells[row][column].defineBottomLeftNeighbour(cells[rowAfter][columnBefore]);
                cells[row][column].defineLeftNeighbour(cells[row][columnBefore]);
            }
        }
    }

    public void calculateNextGeneration() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLS; column++) {
                GameLogic.calculateNextGeneration(this);
                //cells[row][column].calculateNextState();
            }
        }
    }
}
