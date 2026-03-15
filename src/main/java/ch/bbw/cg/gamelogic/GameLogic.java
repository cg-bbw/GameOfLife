package ch.bbw.cg.gamelogic;

public class GameLogic {
    public static void calculateCellsNextGeneration(Cell cell) {
        //TODO implement special rules here
        int livingNeighbours = cell.amountOfNeighboursWithState(CellState.ALIVE);
        int deadNeighbours = cell.amountOfNeighboursWithState(CellState.DEAD);

        if(cell.getState() == CellState.ALIVE) {
            applyRulesForLivingCell(cell, livingNeighbours, deadNeighbours);
        } else if(cell.getState() == CellState.DEAD) {
            applyRulesForDeadCell(cell, livingNeighbours, deadNeighbours);
        }
        if(cell.getCalculatedNextState() == null) {
            cell.setCalculatedNextState(cell.getState());
        }
    }

    private static void applyRulesForLivingCell(Cell cell, int livingNeighbours, int deadNeighbours) {
        lessThanTwoLivingNeighbours(cell, livingNeighbours);
        twoOrThreeLivingNeighbours(cell, livingNeighbours);
        moreThanThreeLivingNeighbours(cell, livingNeighbours);
        //TODO more interesting rules for living cells
    }

    //Any live cell with fewer than two live neighbours dies, as if by underpopulation.
    private static void lessThanTwoLivingNeighbours(Cell cell, int livingNeighbours){
        if(livingNeighbours < 2) {
            cell.setCalculatedNextState(CellState.DEAD);
        }
    }

    //Any live cell with two or three live neighbours lives on to the next generation.
    private static void twoOrThreeLivingNeighbours(Cell cell, int livingNeighbours){
        if(livingNeighbours >= 2 && livingNeighbours <= 3) {
            cell.setCalculatedNextState(CellState.ALIVE);
        }
    }

    //Any live cell with more than three live neighbours dies, as if by overpopulation.
    private static void moreThanThreeLivingNeighbours(Cell cell, int livingNeighbours){
        if(livingNeighbours > 3) {
            cell.setCalculatedNextState(CellState.DEAD);
        }
    }

    private static void applyRulesForDeadCell(Cell cell, int livingNeighbours, int deadNeighbours) {
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
