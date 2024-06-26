package io.flogo.builder.model.architecture.layers.processing;

import io.flogo.builder.model.architecture.LayerView;
import io.flogo.builder.model.architecture.OutputView;
import io.flogo.builder.model.architecture.layers.ProcessingLayerView;
import io.flogo.builder.model.architecture.layers.VLayerView;
import io.flogo.builder.model.architecture.layers.output.OneDimensionOutputView;
import io.flogo.builder.model.laboratory.MaterializationView;
import io.flogo.model.Laboratory;
import io.intino.magritte.framework.Layer;

public final class LinearLayerView implements ProcessingLayerView {
    public final OutputView previousLayerOutput;
    public final OutputView thisLayerOutput;

    public LinearLayerView(OutputView previousLayerOutput, OutputView thisLayerOutput) {
        this.previousLayerOutput = previousLayerOutput;
        this.thisLayerOutput = thisLayerOutput;
    }

    public static ProcessingLayerView from(Layer layer, OutputView previousOutput) {
        return new LinearLayerView(previousOutput, new OneDimensionOutputView(getX(layer)));
    }

    private static int getX(Layer layer) {
        try {
            return (int) layer.getClass().getMethod("output").invoke(layer).getClass()
                    .getMethod("x").invoke(layer.getClass().getMethod("output").invoke(layer));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static LayerView createFromSubstitute(LayerView previous, MaterializationView materializationView) {
        return new LinearLayerView(previous instanceof VLayerView vLayerView ? vLayerView.previousLayerOutput : previous.getOutputView(), new OneDimensionOutputView(((Laboratory.Experiment.Materialization.Linear) materializationView.layer).output().x()));
    }

    @Override
    public LayerView from(OutputView previous) {
        return new LinearLayerView(previous == null ? previousLayerOutput: previous, thisLayerOutput);
    }

    @Override
    public OutputView getOutputView() {
        return thisLayerOutput;
    }
}
