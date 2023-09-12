package io.flogo.builder.model.architecture_views.optimizers;

import io.flogo.blatt.model.Adagrad;
import io.flogo.blatt.model.Optimizer;
import io.flogo.builder.model.architecture_views.OptimizerView;

public class AdagradView implements OptimizerView {
    public final double learningRate;
    public final double learningRateDecay;
    public final double eps;
    public final double weightDecay;
    public final double initialAccumulator;

    public AdagradView(double learningRate, double learningRateDecay, double eps, double weightDecay, double initialAccumulator) {
        this.learningRate = learningRate;
        this.learningRateDecay = learningRateDecay;
        this.eps = eps;
        this.weightDecay = weightDecay;
        this.initialAccumulator = initialAccumulator;
    }


    public static OptimizerView from(Optimizer optimizer) {
        return new AdagradView(((Adagrad) optimizer).lr(),
                               ((Adagrad) optimizer).lrDecay(),
                               ((Adagrad) optimizer).eps(),
                               ((Adagrad) optimizer).weightDecay(),
                               ((Adagrad) optimizer).initialAccumulator());
    }
}
