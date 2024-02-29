package io.flogo.builder.model.evolution.optimizers;

import io.flogo.model.Optimizer;
import io.flogo.builder.model.evolution.OptimizerView;
import io.flogo.model.SGDWithNesterovMomentum;

public class SGDWithNesterovMomentumView implements OptimizerView {
    public final double learningRate;
    public final double momentum;
    public final double momentumDecay;
    public final double weightDecay;

    public SGDWithNesterovMomentumView(double learningRate, double momentum, double momentumDecay, double weightDecay) {
        this.learningRate = learningRate;
        this.momentum = momentum;
        this.momentumDecay = momentumDecay;
        this.weightDecay = weightDecay;
    }


    public static OptimizerView from(Optimizer optimizer) {
        return new SGDWithNesterovMomentumView(((SGDWithNesterovMomentum) optimizer).lr(),
                                           ((SGDWithNesterovMomentum) optimizer).momentum(),
                                           ((SGDWithNesterovMomentum) optimizer).momentumDecay(),
                                           ((SGDWithNesterovMomentum) optimizer).weightDecay());
    }
}
