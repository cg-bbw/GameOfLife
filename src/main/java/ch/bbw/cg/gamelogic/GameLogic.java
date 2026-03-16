package ch.bbw.cg.gamelogic;

import java.util.Arrays;
import java.util.Random;

public class GameLogic {
    public static void calculateCellsNextGeneration(Cell cell) {
        //TODO implement special rules here
        int livingNeighbours = cell.amountOfNeighboursWithState(CellState.ALIVE);
        int deadNeighbours = cell.amountOfNeighboursWithState(CellState.DEAD);
        int undeadNeighbours = cell.amountOfNeighboursWithState(CellState.UNDEAD);
//        int infectedNeighbours = cell.amountOfNeighboursWithState(CellState.INFECTED);
//        int pregnantNeighbours = cell.amountOfNeighboursWithState(CellState.PREGNANT);
//        int protectedNeighbours = cell.amountOfNeighboursWithState(CellState.PROTECTED);
        int immortalNeighbours = cell.amountOfNeighboursWithState(CellState.IMMORTAL);

        // inside each of these rules for a specific cell state,
        // the priority is: latter over former.
        if(cell.getState() == CellState.ALIVE) {
            applyRulesForLivingCell(cell, livingNeighbours, deadNeighbours, undeadNeighbours, immortalNeighbours);
        } else if(cell.getState() == CellState.DEAD) {
            applyRulesForDeadCell(cell, livingNeighbours, deadNeighbours, undeadNeighbours, immortalNeighbours);
        } else if(cell.getState() == CellState.UNDEAD) {
            applyRulesForUndeadCell(cell, livingNeighbours, deadNeighbours, undeadNeighbours, immortalNeighbours);
        } else if(cell.getState() == CellState.IMMORTAL) {
            applyRulesForImmortalCell(cell, livingNeighbours, deadNeighbours, undeadNeighbours, immortalNeighbours);
        }

        //no rule has applied, therefore, the next state stays the same.
        if(cell.getCalculatedNextState() == null) {
            cell.setCalculatedNextState(cell.getState());
        }
    }

    private static void applyRulesForLivingCell(Cell cell, int livingNeighbours, int deadNeighbours, int undeadNeighbours, int immortalNeighbours) {
        //FIXME don't like it, that the rules are hardcoded here, only for comparison with array.
        int aliveRulesForLivingCells = 0;
        int deadRulesForLivingCells = 1;
        int undeadRulesForLivingCells = 2;
        int immortalRulesForLivingCells = 3;

        // Living cells die after some generations depending on their age and a random factor.
        if (cell.getGeneration() > new Random().nextInt(GameSettings.MAX_ALIVE_AGE) +
                (GameSettings.MAX_ALIVE_AGE-cell.getGeneration())) {
            cell.setCalculatedNextState(CellState.DEAD);
            return;
        }

        //
        if(Arrays.stream(GameSettings.activeRules).anyMatch(n -> aliveRulesForLivingCells == n)) {
            lessThanXNeighboursOfState(cell, 2, livingNeighbours, CellState.DEAD);
            moreThanXNeighboursOfState(cell, 4, livingNeighbours, CellState.DEAD);
        }
        if(Arrays.stream(GameSettings.activeRules).anyMatch(n -> deadRulesForLivingCells == n)) {
            // TODO implement dead rules for living cells
        }
        if (Arrays.stream(GameSettings.activeRules).anyMatch(n -> immortalRulesForLivingCells == n)) {
            moreThanXNeighboursOfState(cell, 7, livingNeighbours, CellState.IMMORTAL);
        }
    }

    private static void applyRulesForDeadCell(Cell cell, int livingNeighbours, int deadNeighbours, int undeadNeighbours, int immortalNeighbours) {
        int aliveRulesForDeadCells = 4;
        int deadRulesForDeadCells = 5;
        int undeadRulesForDeadCells = 6;
        int immortalRulesForDeadCells = 7;

        // Enough living cells around dead cells deliver enough nutrition to turn them into undead cells.
        if (Arrays.stream(GameSettings.activeRules).anyMatch(n -> undeadRulesForDeadCells == n)) {
            betweenXAndYNeighboursOfState(cell, 2, 4, livingNeighbours, CellState.UNDEAD);
        }

        // Specific arrangement of living cells around a dead cell will turn it into a living cell.
        if (Arrays.stream(GameSettings.activeRules).anyMatch(n -> aliveRulesForDeadCells == n)) {
            allEdgeNeighboursOfState(cell, CellState.ALIVE, CellState.ALIVE);
            allCornerNeighboursOfState(cell, CellState.ALIVE, CellState.ALIVE);
        }
    }

    private static void applyRulesForUndeadCell(Cell cell, int livingNeighbours, int deadNeighbours, int undeadNeighbours, int immortalNeighbours) {
        int aliveRulesForUndeadCells = 8;
        int deadRulesForUndeadCells = 9;
        int undeadRulesForUndeadCells = 10;
        int immortalRulesForUndeadCells = 11;

        // Undead cells die after some generations depending on their age and a random factor.
        if (cell.getGeneration() > new Random().nextInt(GameSettings.MAX_UNDEAD_AGE) +
                (GameSettings.MAX_UNDEAD_AGE-cell.getGeneration())) {
            cell.setCalculatedNextState(CellState.DEAD);
            return;
        }

        // Only dead cells around will kill an undead cell.
        if (Arrays.stream(GameSettings.activeRules).anyMatch(n -> deadRulesForUndeadCells == n)) {
            moreThanXNeighboursOfState(cell, 7, deadNeighbours, CellState.DEAD);
        }

        // Enough living cells around an undead cell bring it back to life.
        if (Arrays.stream(GameSettings.activeRules).anyMatch(n -> aliveRulesForUndeadCells == n)) {
            moreThanXNeighboursOfState(cell, 4, livingNeighbours, CellState.ALIVE);
        }

        // Immortals can bring undead cells back to life.
        if (Arrays.stream(GameSettings.activeRules).anyMatch(n -> immortalRulesForUndeadCells == n)) {
            moreThanXNeighboursOfState(cell, 1, immortalNeighbours, CellState.ALIVE);
        }
    }

    private static void applyRulesForImmortalCell(Cell cell, int livingNeighbours, int deadNeighbours, int undeadNeighbours, int immortalNeighbours) {
        int aliveRulesForImmortalCells = 12;
        int deadRulesForImmortalCells = 13;
        int undeadRulesForImmortalCells = 14;
        int immortalRulesForImmortalCells = 15;
        if (Arrays.stream(GameSettings.activeRules).anyMatch(n -> undeadRulesForImmortalCells == n)) {
            allEdgeNeighboursOfState(cell, CellState.UNDEAD, CellState.UNDEAD);
        }
    }

    // generic rule for cell with less than x neighbours of a specific state
    private static void lessThanXNeighboursOfState(Cell cell, int x, int neighboursOfState, CellState nextState){
        if(neighboursOfState < x) {
            cell.setCalculatedNextState(nextState);
        }
    }

    // generic rule for cell with more than x neighbours of a specific state
    private static void moreThanXNeighboursOfState(Cell cell, int x, int neighboursOfState, CellState nextState){
        if(neighboursOfState > x) {
            cell.setCalculatedNextState(nextState);
        }
    }

    //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
    private static void betweenXAndYNeighboursOfState(Cell cell, int x, int y, int neighboursOfState, CellState nextState) {
        if(neighboursOfState >= x && neighboursOfState <= y) {
            cell.setCalculatedNextState(nextState);
        }
    }

    private static void allEdgeNeighboursOfState(Cell cell, CellState neighboursOfState, CellState nextState) {
        if(cell.getTopNeighbour().getState()==neighboursOfState &&
                cell.getRightNeighbour().getState()==neighboursOfState &&
                cell.getBottomNeighbour().getState()==neighboursOfState &&
                cell.getLeftNeighbour().getState()==neighboursOfState) {
            cell.setCalculatedNextState(nextState);
        }
    }

    private static void allCornerNeighboursOfState(Cell cell, CellState neighboursOfState, CellState nextState) {
        if(cell.getTopLeftNeighbour().getState()==neighboursOfState &&
                cell.getTopRightNeighbour().getState()==neighboursOfState &&
                cell.getBottomRightNeighbour().getState()==neighboursOfState &&
                cell.getBottomLeftNeighbour().getState()==neighboursOfState) {
            cell.setCalculatedNextState(nextState);
        }
    }

}
