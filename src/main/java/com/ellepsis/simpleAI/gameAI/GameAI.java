package com.ellepsis.simpleAI.gameAI;

import com.ellepsis.simpleAI.ai.ActivationFunction;
import com.ellepsis.simpleAI.ai.NeuralNetwork;
import com.ellepsis.simpleAI.ai.NeuralNetworkBuilder;
import com.ellepsis.simpleAI.gameLogic.GameControl;

/**
 * @author Ellepsis
 * @since 0.0.1
 */
public class GameAI {

    private static final int HIDDEN_LAYERS_COUNT = 2;
    private static final int OUTPUT_ACTIONS_COUNT = 4;
    private static final int HIDDEN_LAYER_NEURON_COUNT = 16;
    private static final int INPUT_LAYER_NEURON_COUNT = 16;

    private GameControl gameControl;
    private NeuralNetwork neuralNetwork;

    public GameAI(GameControl gameControl) {
        this.neuralNetwork = constructNetwork();
        this.gameControl = gameControl;
    }

    private NeuralNetwork constructNetwork() {
        NeuralNetworkBuilder builder = new NeuralNetworkBuilder();
        ActivationFunction activationFunction = new ActivationFunction();
        builder.createInputLayer(INPUT_LAYER_NEURON_COUNT)
                .createHiddenLayers(HIDDEN_LAYERS_COUNT, HIDDEN_LAYER_NEURON_COUNT, activationFunction)
                .createOutputLayer(OUTPUT_ACTIONS_COUNT, activationFunction);
        return builder.build();
    }

}
