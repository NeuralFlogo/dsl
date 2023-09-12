package io.flogo.builder.model.architecture_views.loss_functions;

import io.flogo.blatt.model.LossFunction;
import io.flogo.builder.model.architecture_views.LossFunctionView;

public class MSELossView implements LossFunctionView {
    public static LossFunctionView from(LossFunction lossFunction) {
        return new MSELossView();
    }
}
