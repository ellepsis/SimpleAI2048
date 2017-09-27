package com.ellepsis.simpleAI.ai;

import com.ellepsis.simpleAI.ai.neurons.LayerNeuron;
import com.ellepsis.simpleAI.ai.neurons.Neuron;

/**
 * @author Ellepsis
 * @since 0.0.1
 */
public class NetworkLayer {
    Neuron[] neurons;

    public NetworkLayer(Neuron[] neurons) {
        this.neurons = neurons;
    }

    public void calculateLayer(){
        for (Neuron neuron: neurons){
            neuron.getValue();
        }
    }

    public Neuron[] getNeurons() {
        return neurons;
    }
}
