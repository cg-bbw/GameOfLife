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

    public static final int INITIAL_CHANCE_TO_LIVE = 90;
    public static final int REVIVE_CHANCE = 1;
    //TODO public static final int SURVIVING_CHANCE = 20;

    public static final int MAX_ALIVE_AGE = 10;
    public static final int MAX_UNDEAD_AGE = 5;

    // FIXME Not sure yet if the ruleNumber is actually needed.
    public enum activeRuleEnum {
        ALIVE_ALIVE(0, true),
        ALIVE_DEAD(1,true),
        ALIVE_UNDEAD(2,true),
        ALIVE_IMMORTAL(3,true),
        DEAD_ALIVE(4,true),
        DEAD_DEAD(5,true),
        DEAD_UNDEAD(6,true),
        DEAD_IMMORTAL(7,true),
        UNDEAD_ALIVE(8,true),
        UNDEAD_DEAD(9,true),
        UNDEAD_UNDEAD(10,true),
        UNDEAD_IMMORTAL(11,true),
        IMMORTAL_ALIVE(12,true),
        IMMORTAL_DEAD(13,true),
        IMMORTAL_UNDEAD(14,true),
        IMMORTAL_IMMORTAL(15,true);

        private final int ruleNumber;
        private final boolean active;

        activeRuleEnum(int ruleNumber, boolean active) {
            this.ruleNumber = ruleNumber;
            this.active = active;
        }

        public int getRuleNumber() {
            return ruleNumber;
        }

        public boolean isActive() {
            return active;
        }
    }
}
