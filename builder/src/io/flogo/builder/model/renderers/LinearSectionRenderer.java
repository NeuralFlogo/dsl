package io.flogo.builder.model.renderers;

import io.flogo.blatt.model.LinearSection;
import io.flogo.blatt.model.Section;
import io.flogo.builder.model.views.SectionView;
import io.flogo.builder.model.views.blocks.processing.LinearBlockView;
import io.flogo.builder.model.views.sections.processing.LinearSectionView;

public class LinearSectionRenderer extends ProcessingSectionRenderer {
    @Override
    public SectionView render(Section section) {
        return new LinearSectionView(((LinearSection) section)
                .blockList()
                .stream()
                .map(this::processEach)
                .toList());
    }

    private LinearBlockView processEach(LinearSection.Block block) {
        return new LinearBlockView(block.layerList().stream().map(this::processEach).toList());
    }

}
