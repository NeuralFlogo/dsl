package io.flogo.builder.operations;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class ArchitectureTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
			rule().condition((type("architecture"))).output(literal("from implementations.")).output(mark("library")).output(literal(".architecture.architecture import ")).output(mark("library", "FirstUppercase")).output(literal("Architecture as Architecture\n")).output(mark("import")).output(literal("\n\narchitecture = (Architecture(\"")).output(mark("name")).output(literal("\")\n                    ")).output(mark("component").multiple("\n")).output(literal(")")),
			rule().condition((type("import"))).output(mark("section", "import").multiple("\n")).output(literal("\n")).output(mark("block", "import").multiple("\n")).output(literal("\n")).output(mark("layer", "import").multiple("\n")),
			rule().condition((type("component")), (attribute("section"))).output(literal(".attach(")).output(mark("section")).output(literal(")")),
			rule().condition((type("component")), (attribute("layer"))).output(literal(".attach(")).output(mark("layer")).output(literal(")")),
			rule().condition((type("section")), (trigger("import"))).output(literal("from implementations.")).output(mark("library")).output(literal(".architecture.sections.")).output(mark("type", "Lowercase")).output(literal(" import ")).output(mark("library", "FirstUppercase")).output(mark("type")).output(literal("Section as ")).output(mark("type", "CamelCase")).output(literal("Section")),
			rule().condition((type("block")), (trigger("import")), (attribute("type"))).output(literal("from implementations.")).output(mark("library")).output(literal(".architecture.blocks.")).output(mark("type", "Lowercase")).output(literal(" import ")).output(mark("library", "FirstUppercase")).output(mark("type")).output(literal("Block as ")).output(mark("type", "CamelCase")).output(literal("Block")),
			rule().condition((type("block")), (trigger("import"))).output(literal("from implementations.")).output(mark("library")).output(literal(".architecture.block import ")).output(mark("library", "FirstUppercase")).output(literal("Block as Block")),
			rule().condition((type("layer")), (trigger("import")), (attribute("package")), (attribute("dimensionality"))).output(literal("from implementations.")).output(mark("library")).output(literal(".architecture.layers.")).output(mark("package")).output(literal(".")).output(mark("type", "Lowercase")).output(literal(" import ")).output(mark("library", "FirstUppercase")).output(mark("dimensionality")).output(literal("Dimensional")).output(mark("type")).output(literal("Layer as ")).output(mark("type")).output(literal("Layer")),
			rule().condition((type("layer")), (trigger("import")), (attribute("package")), (attribute("reduce"))).output(literal("from implementations.")).output(mark("library")).output(literal(".architecture.layers.")).output(mark("package")).output(literal(".")).output(mark("type", "Lowercase")).output(literal(" import ")).output(mark("library", "FirstUppercase")).output(mark("type")).output(literal("Layer as ")).output(mark("type")).output(literal("Layer\n")).output(mark("reduce", "import").multiple("")),
			rule().condition((type("layer")), (trigger("import")), (attribute("package"))).output(literal("from implementations.")).output(mark("library")).output(literal(".architecture.layers.")).output(mark("package")).output(literal(".")).output(mark("type", "Lowercase")).output(literal(" import ")).output(mark("library", "FirstUppercase")).output(mark("type")).output(literal("Layer as ")).output(mark("type")).output(literal("Layer")),
			rule().condition((type("layer")), (trigger("import")), (attribute("dimensionality"))).output(literal("from implementations.")).output(mark("library")).output(literal(".architecture.layers.")).output(mark("type", "Lowercase")).output(literal(" import ")).output(mark("library", "FirstUppercase")).output(mark("dimensionality")).output(literal("Dimensional")).output(mark("type")).output(literal("Layer as ")).output(mark("type")).output(literal("Layer")),
			rule().condition((type("layer")), (trigger("import"))).output(literal("from implementations.")).output(mark("library")).output(literal(".architecture.layers.")).output(mark("type", "Lowercase")).output(literal(" import ")).output(mark("library", "FirstUppercase")).output(mark("type")).output(literal("Layer as ")).output(mark("type")).output(literal("Layer")),
			rule().condition((type("reduce")), (trigger("import")), (attribute("linear"))),
			rule().condition((type("reduce")), (trigger("import")), (attribute("flatten"))),
			rule().condition((type("section"))).output(mark("type", "CamelCase")).output(literal("Section([\n            ")).output(mark("block").multiple(", \n")).output(literal("\n])")),
			rule().condition((type("block")), (attribute("type"))).output(mark("type", "CamelCase")).output(literal("Block([\n    ")).output(mark("layer").multiple(", \n")).output(literal("\n])")),
			rule().condition((type("block"))).output(literal("Block([\n    ")).output(mark("layer").multiple(", \n")).output(literal("\n])")),
			rule().condition((anyTypes("kernel","stride","padding"))).output(literal("(")).output(mark("dimension").multiple(", ")).output(literal(")")),
			rule().condition((allTypes("Linear","layer"))).output(mark("type")).output(literal("Layer(in_features=")).output(mark("in_features")).output(literal(", out_features=")).output(mark("out_features")).output(literal(", dimension=")).output(mark("dimension")).output(literal(", bias=")).output(mark("bias")).output(literal(")")),
			rule().condition((allTypes("Convolutional","layer"))).output(mark("type")).output(literal("Layer(in_channels=")).output(mark("in_channels")).output(literal(", out_channels=")).output(mark("out_channels")).output(literal(", kernel=")).output(mark("kernel")).output(literal(", stride=")).output(mark("stride")).output(literal(", padding=")).output(mark("padding")).output(literal(")")),
			rule().condition((anyTypes("MaxPool","AvgPool")), (type("layer"))).output(mark("type")).output(literal("Layer(kernel=")).output(mark("kernel")).output(literal(", stride=")).output(mark("stride")).output(literal(", padding=")).output(mark("padding")).output(literal(")")),
			rule().condition((anyTypes("LSTM","RNN","GRU")), (type("layer"))).output(mark("type")).output(literal("Layer(input_size=")).output(mark("input")).output(literal(", hidden_size=")).output(mark("hidden")).output(literal(", output_type=")).output(mark("type")).output(literal("Layer.OutputType.")).output(mark("output")).output(literal(", num_layer=")).output(mark("num_layers")).output(literal(", bidirectional=")).output(mark("bidirectional", "FirstUpperCase")).output(literal(", dropout=")).output(mark("dropout")).output(literal("),\n")).output(mark("reduce").multiple(", \n")),
			rule().condition((type("reduce")), (attribute("slicing"))).output(literal("SlicingLayer(start=")).output(mark("from")).output(literal(", end=")).output(mark("to")).output(literal(")")),
			rule().condition((type("reduce")), (attribute("linear"))).output(literal("LinearLayer(in_features=")).output(mark("in_features")).output(literal(", out_features=")).output(mark("out_features")).output(literal(", dimension=")).output(mark("dimension")).output(literal(", bias=")).output(mark("bias")).output(literal(")")),
			rule().condition((type("reduce")), (attribute("flatten"))).output(literal("FlattenLayer(from_dim=")).output(mark("from")).output(literal(", to_dim=")).output(mark("to")).output(literal(")")),
			rule().condition((allTypes("ELU","layer"))).output(mark("type")).output(literal("Layer(alpha=")).output(mark("alpha")).output(literal(")")),
			rule().condition((allTypes("LeakyReLU","layer"))).output(mark("type")).output(literal("Layer(negative_slope=")).output(mark("slope")).output(literal(")")),
			rule().condition((allTypes("Softmax","layer"))).output(mark("type")).output(literal("Layer(dimension=")).output(mark("dimension")).output(literal(")")),
			rule().condition((allTypes("BatchNormalization","layer"))).output(mark("type")).output(literal("Layer(num_features=")).output(mark("num_features")).output(literal(", eps=")).output(mark("eps")).output(literal(", momentum=")).output(mark("momentum")).output(literal(")")),
			rule().condition((allTypes("LayerNormalization","layer"))).output(mark("type")).output(literal("Layer(normalized_shape=")).output(mark("shape")).output(literal(", eps=")).output(mark("eps")).output(literal(")")),
			rule().condition((allTypes("Dropout","layer"))).output(mark("type")).output(literal("Layer(probability=")).output(mark("probability")).output(literal(")")),
			rule().condition((allTypes("Flatten","layer"))).output(mark("type")).output(literal("Layer(from_dim=")).output(mark("from_dim")).output(literal(", to_dim=")).output(mark("to_dim")).output(literal(")")),
			rule().condition((type("layer"))).output(mark("type")).output(literal("Layer()"))
		);
	}
}