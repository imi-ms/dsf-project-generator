package de.unimuenster.imi.medic.dsf.utils.generator;

import java.util.HashMap;
import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import de.unimuenster.imi.medic.dsf.utils.generator.base.AbstractGenerator;

public class PomGenerator extends AbstractGenerator {

    private final String mainPom;
    private final String processPom;
    private final String pomFolder;

    public PomGenerator() {
        super();
        this.mainPom = "main-pom.xml.mustache";
        this.processPom = "pom.xml.mustache";
        this.pomFolder = "src/main/resources/build";
    }

    @Override
    public boolean generate(DsfProjectDTO dsfProjectDTO) {
        try {
            HashMap<String, Object> config = new HashMap<>();
            config.put("project", dsfProjectDTO);
            this.generateTemplate(config,
                    this.pomFolder + "/" + this.mainPom,
                    dsfProjectDTO.getOutputPath() + "/pom.xml");
            this.generateTemplate(config,
                    this.pomFolder + "/" + this.processPom,
                    dsfProjectDTO.getProcessBaseFolderName() + "/pom.xml");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

