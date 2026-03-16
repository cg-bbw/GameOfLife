package ch.bbw.cg.gamelogic;

public class GameLogic {
    public static void calculateCellsNextGeneration(Cell cell) {
        //TODO implement special rules here
        int livingNeighbours = cell.amountOfNeighboursWithState(CellState.ALIVE);
        int deadNeighbours = cell.amountOfNeighboursWithState(CellState.DEAD);
        int undeadNeighbours = cell.amountOfNeighboursWithState(CellState.UNDEAD);
        int infectedNeighbours = cell.amountOfNeighboursWithState(CellState.INFECTED);
        int pregnantNeighbours = cell.amountOfNeighboursWithState(CellState.PREGNANT);
        int protectedNeighbours = cell.amountOfNeighboursWithState(CellState.PROTECTED);
        int immortalNeighbours = cell.amountOfNeighboursWithState(CellState.IMMORTAL);


        //TODO implement Rule objects that can be switched on and off.
        if(cell.getState() == CellState.ALIVE) {
            applyRulesForLivingCell(cell, livingNeighbours, deadNeighbours, undeadNeighbours, immortalNeighbours);
        } else if(cell.getState() == CellState.DEAD) {
            applyRulesForDeadCell(cell, livingNeighbours, deadNeighbours, undeadNeighbours, immortalNeighbours);
        } else if(cell.getState() == CellState.UNDEAD) {
            applyRulesForUndeadCell(cell, livingNeighbours, deadNeighbours, undeadNeighbours, immortalNeighbours);
        } else if(cell.getState() == CellState.IMMORTAL) {
            applyRulesForImmortalCell(cell, livingNeighbours, deadNeighbours, undeadNeighbours, immortalNeighbours);
        }

        //no rule has applied, therefore the next state stays the same.
        if(cell.getCalculatedNextState() == null) {
            cell.setCalculatedNextState(cell.getState());
        }
    }

    private static void applyRulesForLivingCell(Cell cell, int livingNeighbours, int deadNeighbours, int undeadNeighbours, int immortalNeighbours) {
        if(GameSettings.appliedRules.get(Rule.RuleType.ALIVE_DEAD)) {
            lessThanTwoLivingNeighbours(cell, livingNeighbours);
//            twoOrThreeLivingNeighbours(cell, livingNeighbours);
            moreThanThreeLivingNeighbours(cell, livingNeighbours);
        }
        if(GameSettings.appliedRules.get(Rule.RuleType.ALIVE_IMMORTAL)) {
            onlyLivingNeighbours(cell, livingNeighbours);
        }
        //TODO more interesting rules for living cells
    }

    //Any live cell with fewer than two live neighbours dies, as if by underpopulation.
    private static void lessThanTwoLivingNeighbours(Cell cell, int livingNeighbours){
        if(livingNeighbours < 2) {
            cell.setCalculatedNextState(CellState.DEAD);
        }
    }

//    //Any live cell with two or three live neighbours lives on to the next generation.
//    private static void twoOrThreeLivingNeighbours(Cell cell, int livingNeighbours){
//        if(livingNeighbours >= 2 && livingNeighbours <= 3) {
//            cell.setCalculatedNextState(CellState.ALIVE);
//        }
//    }

    //Any live cell with more than three live neighbours dies, as if by overpopulation.
    private static void moreThanThreeLivingNeighbours(Cell cell, int livingNeighbours){
        if(livingNeighbours > 3) {
            cell.setCalculatedNextState(CellState.DEAD);
        }
    }

    private static void onlyLivingNeighbours(Cell cell, int livingNeighbours){
        if(livingNeighbours == 8) {
            cell.setCalculatedNextState(CellState.IMMORTAL);
        }
    }

    private static void applyRulesForDeadCell(Cell cell, int livingNeighbours, int deadNeighbours, int undeadNeighbours, int immortalNeighbours) {
        threeLivingNeighbours(cell, livingNeighbours);
        //TODO more interesting rules for dead cells
    }

    //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
    private static void threeLivingNeighbours(Cell cell, int livingNeighbours) {
        if(livingNeighbours == 3) {
            cell.setCalculatedNextState(CellState.ALIVE);
        }
    }

}
