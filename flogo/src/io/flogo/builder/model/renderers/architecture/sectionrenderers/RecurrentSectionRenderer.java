package io.flogo.builder.model.renderers.architecture.sectionrenderers;

import io.flogo.builder.model.architecture.BlockView;
import io.flogo.builder.model.architecture.LayerView;
import io.flogo.builder.model.architecture.OutputView;
import io.flogo.builder.model.architecture.blocks.SimpleBlockView;
import io.flogo.builder.model.architecture.sections.processing.RecurrentSectionView;
import io.flogo.builder.model.renderers.architecture.SectionRenderer;
import io.flogo.model.RecurrentSection;
import io.flogo.model.RecurrentSection.Block;
import io.flogo.model.Section;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecurrentSectionRenderer extends SectionRenderer<RecurrentSectionView> {

    @Override
    public RecurrentSectionView render(Section section, OutputView input) {
        return renderSection(cast(section), input);
    }

    public RecurrentSectionView renderSection(RecurrentSection section, OutputView input) {
        return new RecurrentSectionView(blockViews(section.blockList().iterator(), input, new ArrayList<>()), input);
    }

    private List<BlockView> blockViews(Iterator<Block> blockIterator, OutputView input, ArrayList<BlockView> simpleBlockViews) {
        if (!blockIterator.hasNext()) return simpleBlockViews;
        simpleBlockViews.add(block(blockIterator.next().layerList().iterator(), input, new ArrayList<>()));
        return blockViews(blockIterator, simpleBlockViews.getLast().output(), simpleBlockViews);
    }

    private BlockView block(Iterator<Block.Layer> layersIterator, OutputView input, List<LayerView> layerViews) {
        if (!layersIterator.hasNext()) return new SimpleBlockView(layerViews);
        layerViews.add(render(layersIterator.next(), input));
        return block(layersIterator, layerViews.getLast().getOutputView(), layerViews);
    }

    private RecurrentSection cast(Section section) {
        return (RecurrentSection) section;
    }
}
