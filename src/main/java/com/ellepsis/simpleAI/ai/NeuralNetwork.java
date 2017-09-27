package com.ellepsis.simpleAI.ai;

import com.ellepsis.simpleAI.ai.neurons.LayerNeuron;
import com.ellepsis.simpleAI.ai.neurons.Neuron;

import java.util.ArrayList;

/**
 * @author Ellepsis
 * @since 0.0.1
 */
public class NeuralNetwork {
    private ArrayList<NetworkLayer> layers;

    public NeuralNetwork(ArrayList<NetworkLayer> layers) {
        this.layers = layers;
    }

    public NetworkLayer getLayer(int index) {
        return layers.get(index);
    }
}
