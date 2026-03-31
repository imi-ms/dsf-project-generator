package de.unimuenster.imi.medic.dsf.utils.generator;

import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import de.unimuenster.imi.medic.dsf.utils.generator.base.AbstractGenerator;

import java.util.HashMap;


public class AllowListGenerator extends AbstractGenerator {

    private final String templateFile;
    private final String templateFolder;

    public AllowListGenerator() {
        super();
        this.templateFile = "templates/bundle.xml.mustache";
        this.templateFolder = "process/main";
    }

    @Override
    public boolean generate(DsfProjectDTO dsfProjectDTO) {
        try {
            HashMap<String, Object> config = new HashMap<>();
            config.put("project", dsfProjectDTO);
            this.generateTemplate(config,
                    this.templateFolder,
                    this.templateFile,
                    dsfProjectDTO.getProcessBaseFolderMainResourcesName());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
