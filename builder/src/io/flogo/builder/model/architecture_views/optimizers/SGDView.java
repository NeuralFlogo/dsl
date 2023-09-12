package io.flogo.builder.model.architecture_views.optimizers;

import io.flogo.blatt.model.Optimizer;
import io.flogo.blatt.model.SGD;
import io.flogo.builder.model.architecture_views.OptimizerView;

public class SGDView implements OptimizerView {
    public final double learningRate;
    public final double momentum;
    public final double momentumDecay;
    public final double weightDecay;

    public SGDView(double learningRate, double momentum, double momentumDecay, double weightDecay) {
        this.learningRate = learningRate;
        this.momentum = momentum;
        this.momentumDecay = momentumDecay;
        this.weightDecay = weightDecay;
    }

    public static OptimizerView from(Optimizer optimizer) {
        return new SGDView(((SGD) optimizer).lr(),
                           ((SGD) optimizer).momentum(),
                           ((SGD) optimizer).momentumDecay(),
                           ((SGD) optimizer).weightDecay());
    }
}
