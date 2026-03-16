package ch.bbw.cg.gamelogic;

import javafx.scene.paint.Color;

public class Cell {

    // Cell coordinates
    private int xPosition;
    private int yPosition;

    // Cell states
    private CellState state;
    private CellState previousState;
    private CellState calculatedNextState;

    //TODO use generation as age and for a limited life span
    // generation in this case means age of the cell, because we are not
    // adding new cells to the board, as it would be the case in a real life simulation
    private int generation;

    // Neighbour cells
    private Cell topLeftCell;
    private Cell topCell;
    private Cell topRightCell;
    private Cell rightCell;
    private Cell bottomRightCell;
    private Cell bottomCell;
    private Cell bottomLeftCell;
    private Cell leftCell;

    //TODO implement gender for reproduction feature
    private enum CellGender {
        MALE, FEMALE, HERMAPHRODITE;
    }
    private CellGender gender;

    public Cell(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int x) {
        this.xPosition = x;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int y) {
        this.yPosition = y;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public CellState getPreviousState() {
        return previousState;
    }

    public void setPreviousState(CellState previousState) {
        this.previousState = previousState;
    }

    public CellState getCalculatedNextState() {
        return calculatedNextState;
    }

    public void setCalculatedNextState(CellState calculatedNextState) {
        this.calculatedNextState = calculatedNextState;
    }

    public Color getColor(CellState state) {
        return GameSettings.colors.get(state);
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public void defineTopLeftNeighbour(Cell cell) {
        this.topLeftCell = cell;
    }

    public void defineTopNeighbour(Cell cell) {
        this.topCell = cell;
    }

    public void defineTopRightNeighbour(Cell cell) {
        this.topRightCell = cell;
    }

    public void defineRightNeighbour(Cell cell) {
        this.rightCell = cell;
    }

    public void defineBottomRightNeighbour(Cell cell) {
        this.bottomRightCell = cell;
    }

    public void defineBottomNeighbour(Cell cell) {
        this.bottomCell = cell;
    }

    public void defineBottomLeftNeighbour(Cell cell) {
        this.bottomLeftCell = cell;
    }

    public void defineLeftNeighbour(Cell cell) {
        this.leftCell = cell;
    }

    public int amountOfNeighboursWithState(CellState state) {
        //FIXME probably better to use a map with state and int value for amount of neighbours
        // and call the method only once. But maybe it will be less readable
        int cellsWithState = 0;
        switch (state) {
            case ALIVE:
                if(topLeftCell.getState()==CellState.ALIVE)
                    cellsWithState++;
                if(topCell.getState()==CellState.ALIVE)
                    cellsWithState++;
                if(topRightCell.getState()==CellState.ALIVE)
                    cellsWithState++;
                if(rightCell.getState()==CellState.ALIVE)
                    cellsWithState++;
                if(bottomRightCell.getState()==CellState.ALIVE)
                    cellsWithState++;
                if(bottomCell.getState()==CellState.ALIVE)
                    cellsWithState++;
                if(bottomLeftCell.getState()==CellState.ALIVE)
                    cellsWithState++;
                if(leftCell.getState()==CellState.ALIVE)
                    cellsWithState++;
                break;
            case DEAD:
                if(topLeftCell.getState()==CellState.DEAD)
                    cellsWithState++;
                if(topCell.getState()==CellState.DEAD)
                    cellsWithState++;
                if(topRightCell.getState()==CellState.DEAD)
                    cellsWithState++;
                if(rightCell.getState()==CellState.DEAD)
                    cellsWithState++;
                if(bottomRightCell.getState()==CellState.DEAD)
                    cellsWithState++;
                if(bottomCell.getState()==CellState.DEAD)
                    cellsWithState++;
                if(bottomLeftCell.getState()==CellState.DEAD)
                    cellsWithState++;
                if(leftCell.getState()==CellState.DEAD)
                    cellsWithState++;
                break;
        }
        return cellsWithState;
    }
}
