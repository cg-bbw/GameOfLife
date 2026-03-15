package ch.bbw.cg.gamelogic;

import ch.bbw.cg.MainApp;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class CellBoard {

    public Cell[][] cells;

    private static final int INITIAL_CHANCE_TO_LIVE = 40; // Choose a value between 0 and 100.
    private final Random rng = new Random();

    public CellBoard() {
        cells = new Cell[MainApp.ROWS][MainApp.COLS];
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void initializeCellBoard(GraphicsContext gc) {
        for (int row = 0; row < MainApp.ROWS; row++) {
            for (int column = 0; column < MainApp.COLS; column++) {
                cells[row][column] = new Cell(row, column);
                setInitialCellStateRandomly(cells[row][column], gc);
            }
        }
    }

    private void setInitialCellStateRandomly(Cell cell, GraphicsContext gc) {
        // Set a cell to alive if the random number is less than INITIAL_CHANCE_TO_LIVE
        if(rng.nextInt(100)<INITIAL_CHANCE_TO_LIVE) {
            cell.setState(CellState.ALIVE);
        } else {
            cell.setState(CellState.DEAD);
        }
        //TODO setCellGender
        setCellParameters(cell, 0);
        drawCell(cell, gc);
    }

    private void setCellParameters(Cell cell, int generation) {
        cell.setGeneration(generation);
        //TODO set more parameters here
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
        for (int row = 0; row < MainApp.ROWS; row++) {
            for (int column = 0; column < MainApp.COLS; column++) {
                if (row == 0) {
                    rowBefore = MainApp.ROWS - 1;
                } else {
                    rowBefore = row - 1;
                }
                if (column == 0) {
                    columnBefore = MainApp.COLS - 1;
                } else {
                    columnBefore = column - 1;
                }
                if (row == MainApp.ROWS - 1) {
                    rowAfter = 0;
                } else {
                    rowAfter = row + 1;
                }
                if (column == MainApp.COLS - 1) {
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

    public void calculateNextGeneration(GraphicsContext gc) {
        // iterate over each cell to calculate its next state, without changing current state
        for(Cell[] row : cells) {
            for(Cell cell : row) {
                GameLogic.calculateCellsNextGeneration(cell);
            }
        }

        // iterate over each cell to draw it and then change next state to current state and
        // current state to previous state.
        for(Cell[] row : cells) {
            for(Cell cell : row) {
                cell.setState(cell.getCalculatedNextState());
                cell.setPreviousState(cell.getState());
                //reset calculatedNextState
                cell.setCalculatedNextState(null);
                if (cell.getState() == CellState.ALIVE) {
                    cell.setGeneration(cell.getGeneration() + 1);
                } else if (cell.getState() == CellState.DEAD) {
                    cell.setGeneration(0);
                }
                setCellParameters(cell, cell.getGeneration());
                drawCell(cell, gc);
            }
        }


    }
}
