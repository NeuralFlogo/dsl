package io.flogo.builder.model.architecture.layers.activation;

import io.flogo.builder.model.architecture.LayerView;
import io.flogo.builder.model.architecture.OutputView;
import io.flogo.builder.model.architecture.layers.ActivationLayerView;
import io.flogo.builder.model.architecture.layers.VLayerView;
import io.flogo.builder.model.laboratory.MaterializationView;
import io.intino.magritte.framework.Layer;

public class LogSigmoidLayerView implements ActivationLayerView {
    public final OutputView outputView;

    public LogSigmoidLayerView(OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public LayerView from(OutputView previous) {
        return new LogSigmoidLayerView(previous == null ? this.outputView : previous);
    }

    public static ActivationLayerView createFromSubstitute(LayerView previous, MaterializationView materializationView) {
        return new LogSigmoidLayerView(previous instanceof VLayerView vLayerView ? vLayerView.previousLayerOutput : previous.getOutputView());
    }

    public static ActivationLayerView from(Layer layer, OutputView outputView) {
        return new LogSigmoidLayerView(outputView);
    }

    @Override
    public OutputView getOutputView() {
        return outputView;
    }
}