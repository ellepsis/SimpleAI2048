package com.ellepsis.simpleAI.ai.neurons;

import com.ellepsis.simpleAI.ai.ActivationFunction;
import com.ellepsis.simpleAI.ai.NeuronConnection;

/**
 * @author Ellepsis
 * @since 0.0.1
 */
public class LayerNeuron implements Neuron {
    private float value;
    private NeuronConnection[] neuronConnections;
    private ActivationFunction activationFunction;

    public LayerNeuron(ActivationFunction activationFunction, NeuronConnection[] neuronConnections) {
        this.activationFunction = activationFunction;
        this.neuronConnections = neuronConnections;
    }

    public LayerNeuron(ActivationFunction activationFunction, int connectionsCount) {
        this.activationFunction = activationFunction;
        this.neuronConnections = new NeuronConnection[connectionsCount];
    }

    private float calculateValue() {
        this.value = activationFunction.calculate(neuronConnections);
        return this.value;
    }

    @Override
    public float getValue() {
        return value;
    }
}
