package ch.bbw.cg.gamelogic;

public class Rule {
    private CellState ruleAppliedOnState;
    private CellState ruleOfState;

    public Rule(CellState ruleAppliedOnState, CellState ruleOfState) {
        this.ruleAppliedOnState = ruleAppliedOnState;
        this.ruleOfState = ruleOfState;
    }
}
