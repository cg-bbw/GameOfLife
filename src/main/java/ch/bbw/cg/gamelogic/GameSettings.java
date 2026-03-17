package ch.bbw.cg.gamelogic;

import javafx.scene.paint.Color;

import java.util.Map;

public class GameSettings {
    public static final int COLS = 30;
    public static final int ROWS = 30;

    public static final int TILE_SIZE = 20; // pixels per tile
    public static final int PADDING = 20; // outer padding

    public static final int GENERATIONS = 100; // For unlimited animation loop use Timeline.INDEFINITE
    public static final int RENDER_SPEED = 1000; //value in milliseconds

    public static final Color GRID_COLOR = Color.LIGHTGREY; // --> alternative .web("#909090")

    // Cell colors
    // No need for the color attribute, because it is defined by the state
    public static final Map<CellState, Color> colors = Map.of(
            CellState.ALIVE, Color.LIGHTGREEN,
            CellState.DEAD, Color.GREY,
            CellState.UNDEAD, Color.PURPLE,
            CellState.INFECTED, Color.GREENYELLOW,
            CellState.PREGNANT, Color.FORESTGREEN,
            CellState.PROTECTED, Color.LIGHTBLUE,
            CellState.IMMORTAL, Color.WHITE);

    public static final int INITIAL_CHANCE_TO_LIVE = 20;
    public static final int REVIVE_CHANCE = 1;
    //TODO public static final int SURVIVING_CHANCE = 20;

    public static final int MAX_ALIVE_AGE = 10;
    public static final int MAX_UNDEAD_AGE = 5;

    //TODO implement a setting choice from the user. The numbers in the array should be added by choice.

    /**
     * Active rules: If the following numbers appear in the array,
     * the corresponding rules are active.
     *
     * Rules:
     * 0 = Alive-Alive
     * 1 = Alive-Dead
     * 2 = Alive-Undead
     * 3 = Alive-Immortal
     * 4 = Dead-Alive
     * 5 = Dead-Dead
     * 6 = Dead-Undead
     * 7 = Dead-Immortal
     * 8 = Undead-Alive
     * 9 = Undead-Dead
     * 10 = Undead-Undead
     * 11 = Undead-Immortal
     * 12 = Immortal-Alive
     * 13 = Immortal-Dead
     * 14 = Immortal-Undead
     * 15 = Immortal-Immortal
     */
    public static final int[] activeRules = {
            0, 1, 2, 3,
            4, 5, 6, 7,
            8, 9, 10, 11,
            12, 13, 14, 15
    };

    // active rules with only living and dead rules
//    public static final int[] activeRules = {
//            0, 1,
//            4, 5
//    };
}
