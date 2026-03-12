package utils;

import shared.DsfProjectDTO;
import utils.generator.base.AbstractGenerator;
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
                //this.deleteDirectory(new File(dsfProjectDTO.getOutputPath()));
                //return false;
                System.out.println("Generator failed" + generator.getClass().getSimpleName());
            }
        }
        return true;
    }

}
