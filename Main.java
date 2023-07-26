import java.util.*;

public class Main<State, Action> {

    private HashMap<State, HashMap<Action, Double>> qTable;
    private static double alpha = 0.3;
    private static double gamma = 0.9;


    public Main() {

        this.qTable = new HashMap<State, HashMap<Action, Double>>();

    }

    private double calculateQValues(double prevQValue, double reward, State state) {

        HashMap<Action, Double> actionsInThisState = this.qTable.get(state);

        if (actionsInThisState == null) {

            throw new RuntimeException("Current state has not been intilized.");

        }

        Set<Action> actions = actionsInThisState.keySet();

        double maxQValue = Double.NEGATIVE_INFINITY;
        Action maxQValueAction;

        for (Action action : actions) {

            Double actionQValue = actionsInThisState.get(action);

            if (actionQValue == null) {

                actionsInThisState.replace(action, 0.0);
                actionQValue = 0.0;

            }

            if (actionQValue > maxQValue) {

                maxQValue = actionQValue;
                maxQValueAction = action;

            }

        }

        double maxFutureReward = 0.0;

        return maxFutureReward;

    }

    public void updateQValues(State state, Action action, double reward) {

        HashMap<Action, Double> actionQValues = this.qTable.get(state);

        if (actionQValues == null) {

            this.qTable.put(state, new HashMap<Action, Double>());

        }

    }

    public static void main(String[] args) {
        


    }

}