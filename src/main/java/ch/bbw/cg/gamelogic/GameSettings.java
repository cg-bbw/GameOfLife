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
            CellState.PROTECTED, Color.LIGHTBLUE,
            CellState.IMMORTAL, Color.DARKBLUE);

    public static final int INITIAL_CHANCE_TO_LIVE = 40;

    // TODO public static final int MAX_CELL_AGE = 100;

    /**
     * Active rules: First int stands for rule, second int stands for activeness
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
     *
     * Activeness:
     * 0 = inactive
     * 1 = active
     */
    public static final int[][] activeRules = {
            {0,1}, {1,1}, {2,1}, };

    public static Map<Rule.RuleType, Boolean> appliedRules = Map.of(
            Rule.RuleType.ALIVE_DEAD, true,
            Rule.RuleType.ALIVE_UNDEAD, true,
            Rule.RuleType.ALIVE_INFECTED, false,
            Rule.RuleType.ALIVE_PREGNANT, false,
            Rule.RuleType.ALIVE_PROTECTED, false,
            Rule.RuleType.ALIVE_IMMORTAL, true,
            Rule.RuleType.DEAD_ALIVE, true,
            Rule.RuleType.DEAD_UNDEAD, true,
            Rule.RuleType.DEAD_INFECTED, false,
            Rule.RuleType.DEAD_PREGNANT, false,
            Rule.RuleType.DEAD_PROTECTED, false,
            Rule.RuleType.DEAD_IMMORTAL, true,
            Rule.RuleType.UNDEAD_ALIVE, true,
            Rule.RuleType.UNDEAD_DEAD, true,
            Rule.RuleType.UNDEAD_INFECTED, false,
            Rule.RuleType.UNDEAD_PREGNANT, false,
            Rule.RuleType.UNDEAD_PROTECTED, false,
            Rule.RuleType.UNDEAD_IMMORTAL, true,
            Rule.RuleType.INFECTED_ALIVE, false,
            Rule.RuleType.INFECTED_DEAD, false,
            Rule.RuleType.INFECTED_UNDEAD, false,
            Rule.RuleType.INFECTED_PREGNANT, false,
            Rule.RuleType.INFECTED_PROTECTED, false,
            Rule.RuleType.INFECTED_IMMORTAL, false,
            Rule.RuleType.PREGNANT_ALIVE, false,
            Rule.RuleType.PREGNANT_DEAD, false,
            Rule.RuleType.PREGNANT_UNDEAD, false,
            Rule.RuleType.PREGNANT_INFECTED, false,
            Rule.RuleType.PREGNANT_PROTECTED, false,
            Rule.RuleType.PREGNANT_IMMORTAL, false,
            Rule.RuleType.PROTECTED_ALIVE, false,
            Rule.RuleType.PROTECTED_DEAD, false,
            Rule.RuleType.PROTECTED_UNDEAD, false,
            Rule.RuleType.PROTECTED_INFECTED, false,
            Rule.RuleType.PROTECTED_PREGNANT, false,
            Rule.RuleType.PROTECTED_IMMORTAL, false,
            Rule.RuleType.IMMORTAL_ALIVE, true,
            Rule.RuleType.IMMORTAL_DEAD, true,
            Rule.RuleType.IMMORTAL_UNDEAD, true,
            Rule.RuleType.IMMORTAL_INFECTED, false,
            Rule.RuleType.IMMORTAL_PREGNANT, false,
            Rule.RuleType.IMMORTAL_PROTECTED, false
    );
}
