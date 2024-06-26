package io.flogo.builder.model.architecture.layers.output;

import io.flogo.builder.model.architecture.OutputView;

public class UndeterminedOutputView implements OutputView {

    @Override
    public int dimensions() {
        return -1;
    }

    @Override
    public int[] asArray() {
        return new int[0];
    }
}
