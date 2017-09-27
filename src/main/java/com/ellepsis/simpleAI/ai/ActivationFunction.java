package com.ellepsis.simpleAI.ai;

/**
 * @author Ellepsis
 * @since 0.0.1
 */
public class ActivationFunction {
    public float calculate(NeuronConnection[] connections) {
        float result = 0;
        for (NeuronConnection connection : connections) {
            result += connection.weight * connection.sourceNeuron.getValue();
        }
        return result / connections.length;
    }
}
