package com.ellepsis.simpleAI.ai;

import com.ellepsis.simpleAI.ai.neurons.LayerNeuron;
import com.ellepsis.simpleAI.ai.neurons.Neuron;

/**
 * @author Ellepsis
 * @since 0.0.1
 */
public class NeuronConnection {
    Neuron sourceNeuron;
    float weight;

    public NeuronConnection(Neuron sourceNeuron, float weight) {
        this.sourceNeuron = sourceNeuron;
        this.weight = weight;
    }
}
