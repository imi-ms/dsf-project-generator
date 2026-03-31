package de.unimuenster.imi.medic.dsf.utils.generator;

import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import de.unimuenster.imi.medic.dsf.utils.generator.base.AbstractGenerator;
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
                System.out.println("Error while generating project." + generator.getClass().getSimpleName());
                File projectFolder = new File(dsfProjectDTO.getOutputPath());
                this.deleteDirectory(projectFolder);
                projectFolder.mkdirs();
                return false;
            }
        }
        return true;
    }

}
