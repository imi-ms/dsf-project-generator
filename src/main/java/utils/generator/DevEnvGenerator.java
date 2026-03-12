package utils.generator;

import shared.DsfProjectDTO;
import utils.generator.base.AbstractGenerator;

import java.util.HashMap;


public class DevEnvGenerator extends AbstractGenerator {

    private final String devSetupFile;
    private final String pluginPropertiesFile;
    private final String templateFolder;

    public DevEnvGenerator() {
        super();
        this.devSetupFile = "templates/dev-setup.env.mustache";
        this.pluginPropertiesFile = "plugin.properties";
        this.templateFolder = "src/main/resources/process/main";
    }

    @Override
    public boolean generate(DsfProjectDTO dsfProjectDTO) {
        try {
            HashMap<String, Object> config = new HashMap<>();
            config.put("project", dsfProjectDTO);
            this.generateTemplate(config,
                    this.templateFolder,
                    this.devSetupFile,
                    dsfProjectDTO.getProcessBaseFolderMainResourcesName());
            this.copyFile(this.templateFolder,
                    this.pluginPropertiesFile,
                    dsfProjectDTO.getProcessBaseFolderMainResourcesName() + "/" + this.pluginPropertiesFile);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
