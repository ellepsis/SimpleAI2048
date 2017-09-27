package com.ellepsis.simpleAI.ai.neurons;

/**
 * @author Ellepsis
 * @since 0.0.1
 */
public class InputNeuron implements Neuron {
    private float value;

    public InputNeuron(float value) {
        this.value = value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public float getValue() {

        return 0;
    }

}
