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

    public Cell getTopLeftNeighbour() {
        return this.topLeftCell;
    }

    public void defineTopLeftNeighbour(Cell cell) {
        this.topLeftCell = cell;
    }

    public Cell getTopNeighbour() {
        return this.topCell;
    }

    public void defineTopNeighbour(Cell cell) {
        this.topCell = cell;
    }

    public Cell getTopRightNeighbour() {
        return this.topRightCell;
    }

    public void defineTopRightNeighbour(Cell cell) {
        this.topRightCell = cell;
    }

    public Cell getRightNeighbour() {
        return this.rightCell;
    }

    public void defineRightNeighbour(Cell cell) {
        this.rightCell = cell;
    }

    public Cell getBottomRightNeighbour() {
        return this.bottomRightCell;
    }

    public void defineBottomRightNeighbour(Cell cell) {
        this.bottomRightCell = cell;
    }

    public Cell getBottomNeighbour() {
        return this.bottomCell;
    }

    public void defineBottomNeighbour(Cell cell) {
        this.bottomCell = cell;
    }

    public Cell getBottomLeftNeighbour() {
        return this.bottomLeftCell;
    }

    public void defineBottomLeftNeighbour(Cell cell) {
        this.bottomLeftCell = cell;
    }

    public Cell getLeftNeighbour() {
        return this.leftCell;
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
            case UNDEAD:
                if(topLeftCell.getState()==CellState.UNDEAD)
                    cellsWithState++;
                if(topCell.getState()==CellState.UNDEAD)
                    cellsWithState++;
                if(topRightCell.getState()==CellState.UNDEAD)
                    cellsWithState++;
                if(rightCell.getState()==CellState.UNDEAD)
                    cellsWithState++;
                if(bottomRightCell.getState()==CellState.UNDEAD)
                    cellsWithState++;
                if(bottomCell.getState()==CellState.UNDEAD)
                    cellsWithState++;
                if(bottomLeftCell.getState()==CellState.UNDEAD)
                    cellsWithState++;
                if(leftCell.getState()==CellState.UNDEAD)
                    cellsWithState++;
            case IMMORTAL:
                if(topLeftCell.getState()==CellState.IMMORTAL)
                    cellsWithState++;
                if(topCell.getState()==CellState.IMMORTAL)
                    cellsWithState++;
                if(topRightCell.getState()==CellState.IMMORTAL)
                    cellsWithState++;
                if(rightCell.getState()==CellState.IMMORTAL)
                    cellsWithState++;
                if(bottomRightCell.getState()==CellState.IMMORTAL)
                    cellsWithState++;
                if(bottomCell.getState()==CellState.IMMORTAL)
                    cellsWithState++;
                if(bottomLeftCell.getState()==CellState.IMMORTAL)
                    cellsWithState++;
                if(leftCell.getState()==CellState.IMMORTAL)
                    cellsWithState++;
        }
        return cellsWithState;
    }
}
