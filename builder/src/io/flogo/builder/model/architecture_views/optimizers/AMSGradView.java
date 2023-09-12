package io.flogo.builder.model.architecture_views.optimizers;

import io.flogo.blatt.model.AMSGrad;
import io.flogo.blatt.model.Optimizer;
import io.flogo.builder.model.architecture_views.OptimizerView;

public class AMSGradView implements OptimizerView {
    public final double learningRate;
    public final double beta0;
    public final double beta1;
    public final double weightDecay;

    public AMSGradView(double learningRate, double beta0, double beta1, double weightDecay) {
        this.learningRate = learningRate;
        this.beta0 = beta0;
        this.beta1 = beta1;
        this.weightDecay = weightDecay;
    }

    public static OptimizerView from(Optimizer optimizer) {
        return new AdamView(((AMSGrad) optimizer).lr(),
                            ((AMSGrad) optimizer).beta0(),
                            ((AMSGrad) optimizer).beta1(),
                            ((AMSGrad) optimizer).weightDecay());
    }
}
