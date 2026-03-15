package ch.bbw.cg.gamelogic;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class Cell {

    // Cell coordinates
    private int xPosition;
    private int yPosition;

    // Cell states
    private CellState state;
    private CellState previousState;
    private CellState calculatedNextState;

    // Cell colors
    // No need for the color attribute, because it is defined by the state
    private static final Map<CellState, Color> colors = Map.of(
            CellState.ALIVE, Color.LIGHTGREEN,
            CellState.DEAD, Color.BLACK,
            CellState.UNDEAD, Color.DARKGREY,
            CellState.INFECTED, Color.GREENYELLOW,
            CellState.PREGNANT, Color.FORESTGREEN,
            CellState.PROTECTED, Color.LIGHTBLUE);

    //TODO use generation as age and for a limited life span
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
        return colors.get(state);
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

    public void calculateNextState() {

    }
}
