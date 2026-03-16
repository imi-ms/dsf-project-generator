package utils.generator;

import shared.DsfProjectDTO;
import utils.generator.base.AbstractGenerator;
import java.io.File;
import java.util.List;


public class ProjectGenerator extends AbstractGenerator {

    private final List<AbstractGenerator> generators;

    public ProjectGenerator(List<AbstractGenerator> generators)  {
        super();
        this.generators = generators;
    }

    @Override
    public boolean generate(DsfProjectDTO dsfProjectDTO) {
        for (AbstractGenerator generator : this.generators) {
            if (!generator.generate(dsfProjectDTO)) {
                this.deleteDirectory(new File(dsfProjectDTO.getOutputPath()));
                return false;
            }
        }
        return true;
    }

}
