package io.flogo.builder.model.laboratory.optimizers;

import io.flogo.builder.model.laboratory.OptimizerView;
import io.flogo.model.Adadelta;
import io.flogo.model.Optimizer;

public class AdadeltaView implements OptimizerView {
    public final double learningRate;
    public final double eps;
    public final double rho;
    public final double weightDecay;

    public AdadeltaView(double learningRate, double eps, double rho, double weightDecay) {
        this.learningRate = learningRate;
        this.eps = eps;
        this.rho = rho;
        this.weightDecay = weightDecay;
    }


    public static OptimizerView from(Optimizer optimizer) {
        return new AdadeltaView(((Adadelta) optimizer).lr(),
                ((Adadelta) optimizer).eps(),
                ((Adadelta) optimizer).rho(),
                ((Adadelta) optimizer).weightDecay());
    }
}
