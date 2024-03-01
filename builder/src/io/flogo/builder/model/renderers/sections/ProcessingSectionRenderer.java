package io.flogo.builder.model.renderers.sections;

import io.flogo.model.Section;
import io.flogo.builder.model.renderers.SectionRenderer;
import io.flogo.builder.model.architecture.LayerView;
import io.flogo.builder.model.architecture.Output;
import io.flogo.builder.model.architecture.SectionView;
import io.flogo.builder.model.architecture.layers.ActivationLayerView;
import io.flogo.builder.model.architecture.layers.ProcessingLayerView;
import io.intino.magritte.framework.Layer;

import java.lang.reflect.Method;

import static io.flogo.builder.model.architecture.layers.ActivationLayerView.ActivationLayersViewPackage;
import static io.flogo.builder.model.architecture.layers.ProcessingLayerView.ProcessingLayersViewPackage;

public abstract class ProcessingSectionRenderer implements SectionRenderer {
    private Output previousOutput = null;
    protected LayerRenderer layerRenderer = new LayerRenderer();

    @Override
    public SectionRenderer init(Output output) {
        if (output != null) previousOutput = output;
        return this;
    }

    @Override
    public abstract SectionView render(Section section);

    @Override
    public Output sectionOutput() {
        return previousOutput;
    }

    public class LayerRenderer {

        protected LayerView process(Layer layer) {
            try {
                if (!isActivationLayer(layer)) return processingLayerViewFrom(layer);
                return activationLayerViewFrom(layer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public ProcessingLayerView processingLayerViewFrom(Layer layer) throws Exception {
            ProcessingLayerView layerView = (ProcessingLayerView) getMethodToCreateViewForProcessing(layer)
                    .invoke(null, layer, previousOutput);
            previousOutput = layerView.getLayerOutput();
            return layerView;
        }

        public ActivationLayerView activationLayerViewFrom(Layer layer) throws Exception {
            return (ActivationLayerView) getMethodToCreateViewForActivation(layer).invoke(null, layer);
        }

        private Method getMethodToCreateViewForActivation(Layer layer) throws Exception {
            return classFrom(layer).getDeclaredMethod("from", Layer.class);
        }

        private static Method getMethodToCreateViewForProcessing(Layer layer) throws Exception {
            return classFrom(layer).getDeclaredMethod("from", Layer.class, Output.class);
        }

        private static Class<? extends LayerView> classFrom(Layer layer) throws ClassNotFoundException {
            return (Class<? extends LayerView>) Class.forName(className(layer));
        }

        private static String className(Layer layer) {
            return packageName(layer) + getLayerName(layer) + "LayerView";
        }

        private static String packageName(Layer layer) {
            return isActivationLayer(layer) ? ActivationLayersViewPackage : ProcessingLayersViewPackage;
        }

        private static String getLayerName(Layer layer) {
            return layer.getClass().toString().split("\\$")[2];
        }

        private static boolean isActivationLayer(Layer layer) {
            return layer.core$().conceptList().get(1).toString().split("\\$")[2].startsWith("ActivationLayer");
        }

    }
}
