package io.flogo.builder.model.architecture.layers.processing;

import io.flogo.builder.model.architecture.LayerView;
import io.flogo.builder.model.architecture.OutputView;
import io.flogo.builder.model.architecture.layers.VLayerView;
import io.flogo.builder.model.architecture.layers.output.OneDimensionOutputView;
import io.flogo.builder.model.laboratory.SubstituteView;
import io.flogo.model.RecurrentSection;
import io.intino.magritte.framework.Layer;

public class GRULayerView extends RecurrentLayerView {
    public GRULayerView(OutputView previousLayerOutput, OneDimensionOutputView thisLayerOutput, int numLayers, OutputType outputType, boolean bidirectional) {
        super(previousLayerOutput, thisLayerOutput, numLayers, outputType, bidirectional);
    }

    public static GRULayerView from(Layer layer, OutputView outputView) {
        RecurrentSection.Block.GRU gru = (RecurrentSection.Block.GRU) layer;
        return new GRULayerView(outputView, output(gru), gru.stackedRecurrentSections(), outputType(gru), gru.bidirectional());
    }

    private static OutputType outputType(RecurrentSection.Block.GRU gru) {
        return OutputType.valueOf(gru.outputType().toString());
    }

    private static OneDimensionOutputView output(RecurrentSection.Block.GRU gru) {
        return new OneDimensionOutputView(gru.output().x());
    }

    @Override
    public OutputView getOutputView() {
        return thisLayerOutput;
    }

    @Override
    public LayerView from(VLayerView vLayerView, SubstituteView substituteViews) {
        return null;
    }

    @Override
    public LayerView from(LayerView previous) {
        return null;
    }
}
