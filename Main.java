import java.util.*;

public class Main<State, Action> {

    private HashMap<State, HashMap<Action, Double>> qTable;
    private static double alpha = 0.3;
    private static double gamma = 0.9;


    public Main() {

        this.qTable = new HashMap<State, HashMap<Action, Double>>();

    }

    private double calculateQValues(double prevQValue, double reward, State oldState, Action actionTaken, State newState) {

        HashMap<Action, Double> actionsInNewState = this.qTable.get(newState);

        if (actionsInNewState == null) {

            throw new RuntimeException("Current state has not been intilized.");

        }

        Set<Action> actions = actionsInNewState.keySet();

        double maxFutureReward = Double.NEGATIVE_INFINITY;

        for (Action action : actions) {

            Double actionQValue = actionsInNewState.get(action);

            if (actionQValue == null) {

                actionsInNewState.put(action, 0.0);
                actionQValue = 0.0;

            }

            if (actionQValue > maxFutureReward) {

                maxFutureReward = actionQValue;

            }

        }

        HashMap<Action, Double> oldStateActions = this.qTable.get(oldState);

        Double oldQValue;

        if (oldStateActions == null) { 
            
            oldQValue = 0.0;

            this.qTable.put(oldState, new HashMap<Action, Double>());
        
        }

        else {

            Double unchekcedOldQValue = oldStateActions.get(actionTaken);

            if (unchekcedOldQValue == null) {

                oldQValue = 0.0;

            }

            else {

                oldQValue = unchekcedOldQValue;

            }

        }

        return oldQValue + alpha * (reward + gamma * maxFutureReward - oldQValue);

    }

    public void updateQValues(State oldState, Action actionTaken, State newState, double reward) {

        HashMap<Action, Double> actionQValues = this.qTable.get(oldState);

        if (actionQValues == null) {

            this.qTable.put(oldState, new HashMap<Action, Double>());

            actionQValues = this.qTable.get(oldState);

        }

        Double actionQValue = actionQValues.get(actionTaken);

        if (actionQValue == null) {

            actionQValue = 0.0;

        }

        this.qTable.get(oldState).replace(actionTaken, this.calculateQValues(actionQValue, reward, oldState, actionTaken, newState))

    }

    public static void main(String[] args) {
        


    }

}