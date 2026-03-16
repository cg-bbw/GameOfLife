package ch.bbw.cg.gamelogic;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class CellBoard {

    public Cell[][] cells;

    private final Random rng = new Random();

    public CellBoard() {
        cells = new Cell[GameSettings.ROWS][GameSettings.COLS];
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void initializeCellBoard(GraphicsContext gc) {
        for (int row = 0; row < GameSettings.ROWS; row++) {
            for (int column = 0; column < GameSettings.COLS; column++) {
                cells[row][column] = new Cell(row, column);
                setInitialCellStateRandomly(cells[row][column], gc);
            }
        }
    }

    // TODO: implement a mode, where the user can define manually the initial state of the cells.
    //  Give him an interface with color description, introduction how to set the state and an apply button.
    private void setInitialCellStateRandomly(Cell cell, GraphicsContext gc) {
        // Set a cell to alive if the random number is less than INITIAL_CHANCE_TO_LIVE
        if(rng.nextInt(100)<GameSettings.INITIAL_CHANCE_TO_LIVE) {
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
        double x = GameSettings.PADDING + cell.getYPosition() * GameSettings.TILE_SIZE;
        double y = GameSettings.PADDING + cell.getXPosition() * GameSettings.TILE_SIZE;

        gc.setFill(cell.getColor(cell.getState()));
        gc.fillRect(x, y, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);

        gc.setStroke(GameSettings.GRID_COLOR);
        gc.strokeRect(x, y, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);
    }

    public void defineAllCellsNeighbours() {
        int rowBefore;
        int rowAfter;
        int columnBefore;
        int columnAfter;
        for (int row = 0; row < GameSettings.ROWS; row++) {
            for (int column = 0; column < GameSettings.COLS; column++) {
                if (row == 0) {
                    rowBefore = GameSettings.ROWS - 1;
                } else {
                    rowBefore = row - 1;
                }
                if (column == 0) {
                    columnBefore = GameSettings.COLS - 1;
                } else {
                    columnBefore = column - 1;
                }
                if (row == GameSettings.ROWS - 1) {
                    rowAfter = 0;
                } else {
                    rowAfter = row + 1;
                }
                if (column == GameSettings.COLS - 1) {
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

        // iterate over each cell to draw it and then change the next state to
        // the current state and the current state to the previous state.
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
