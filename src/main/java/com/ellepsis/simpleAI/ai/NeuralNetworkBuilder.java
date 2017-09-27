package com.ellepsis.simpleAI.ai;

import com.ellepsis.simpleAI.ai.neurons.InputNeuron;
import com.ellepsis.simpleAI.ai.neurons.LayerNeuron;
import com.ellepsis.simpleAI.ai.neurons.Neuron;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Ellepsis
 * @since 0.0.1
 */
public class NeuralNetworkBuilder {

    private NetworkLayer inputLayer;
    private ArrayList<NetworkLayer> hiddenLayers;
    private NetworkLayer outputLayer;

    public NeuralNetworkBuilder createInputLayer(int size) {
        Neuron[] neurons = new Neuron[size];
        for (int i = 0; i < size; i++) {
            neurons[i] = new InputNeuron(0);
        }
        this.inputLayer = new NetworkLayer(neurons);
        return this;
    }

    public NeuralNetworkBuilder createHiddenLayers(int count, int size, ActivationFunction activationFunction) {
        NetworkLayer previousLayer = getPreviousLayer();
        for (int i = 0; i < count; i++) {
            NetworkLayer networkLayer = createNetworkLayer(size, activationFunction, previousLayer);
            hiddenLayers.add(networkLayer);
            previousLayer = networkLayer;
        }
        return this;
    }

    public NeuralNetworkBuilder createHiddenLayer(int size, ActivationFunction activationFunction) {
        hiddenLayers.add(createNetworkLayer(size, activationFunction, getPreviousLayer()));
        return this;
    }

    public NeuralNetworkBuilder createOutputLayer(int outputSize, ActivationFunction activationFunction) {
        this.outputLayer = createNetworkLayer(outputSize, activationFunction, getPreviousLayer());
        return this;
    }

    public NeuralNetwork build() {
        if (inputLayer == null || outputLayer == null) {
            throw new RuntimeException("Can't construct a network without initial and output layer");
        }
        int hiddenLayersSize = hiddenLayers == null ? 0 : hiddenLayers.size();
        ArrayList<NetworkLayer> allLayers = new ArrayList<>(2 + hiddenLayersSize);
        allLayers.add(inputLayer);
        if (hiddenLayersSize != 0) {
            allLayers.addAll(hiddenLayers);
        }
        allLayers.add(outputLayer);
        return new NeuralNetwork(allLayers);
    }

    /**
     * The method returns the last level the constructing neural network
     *
     * @return last level of the constructing neural network
     */
    private NetworkLayer getPreviousLayer() {
        NetworkLayer previousLayer;
        if (hiddenLayers == null || hiddenLayers.size() == 0) { //if not hidden hiddenLayers is created yet, set previous to initial
            if (inputLayer == null) {
                throw new RuntimeException("The input layer is not created. Create the input layer first");
            } else {
                previousLayer = inputLayer;
            }
        } else { //if any hidden layer is created, set the previous layer to a previous hidden layer
            previousLayer = hiddenLayers.get(hiddenLayers.size() - 1);
        }
        return previousLayer;
    }

    private NetworkLayer createNetworkLayer(int size, ActivationFunction activationFunction,
                                            NetworkLayer previousLayer) {
        Neuron[] neurons = new Neuron[size];
        for (int i = 0; i < size; i++) {
            neurons[i] = new LayerNeuron(activationFunction, createFullConnection(previousLayer, getInitialWeight()));
        }
        return new NetworkLayer(neurons);
    }

    private NeuronConnection[] createFullConnection(NetworkLayer previousLayer, final float initialWeight) {
        return Arrays.stream(previousLayer.getNeurons())
                .map(o -> new NeuronConnection(o, initialWeight)).toArray(NeuronConnection[]::new);
    }

    private float getInitialWeight() {
        return 0;
    }
}
