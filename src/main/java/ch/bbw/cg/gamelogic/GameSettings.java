package ch.bbw.cg.gamelogic;

import javafx.scene.paint.Color;

import java.util.Map;

public class GameSettings {
    public static final int COLS = 20;
    public static final int ROWS = 20;

    public static final int TILE_SIZE = 20; // pixels per tile
    public static final int PADDING = 20; // outer padding

    public static final int GENERATIONS = 25; // For unlimited animation loop use Timeline.INDEFINITE

    public static final Color GRID_COLOR = Color.LIGHTGREY; // --> alternative .web("#909090")

    // Cell colors
    // No need for the color attribute, because it is defined by the state
    public static final Map<CellState, Color> colors = Map.of(
            CellState.ALIVE, Color.LIGHTGREEN,
            CellState.DEAD, Color.GREY,
            CellState.UNDEAD, Color.DARKGREY,
            CellState.INFECTED, Color.GREENYELLOW,
            CellState.PREGNANT, Color.FORESTGREEN,
            CellState.PROTECTED, Color.LIGHTBLUE);
}
