package io.flogo.builder.model.architecture.blocks.processing.recurrentBlocks;

import io.flogo.builder.model.architecture.blocks.processing.RecurrentBlockView;
import io.flogo.builder.model.architecture.layers.output.OneDimensionOutput;

public class RNN extends RecurrentBlockView {
    public RNN(OneDimensionOutput inputSize, OneDimensionOutput hiddenSize, int numLayers, String outputElement, boolean bidirectional) {
        super(inputSize, hiddenSize, numLayers, outputElement, bidirectional);
    }
}
