package utils.generator;

import shared.DsfProjectDTO;
import utils.generator.base.AbstractGenerator;

import java.util.HashMap;


public class DockerGenerator extends AbstractGenerator {

    private final String dockerFile;
    private final String dockerFolder;

    public DockerGenerator() {
        super();
        this.dockerFile = "docker-compose.yml.mustache";
        this.dockerFolder = "src/main/resources";
    }

    public boolean generate(DsfProjectDTO dsfProjectDTO)  {
        try {
            HashMap<String, Object> config = new HashMap<>();
            config.put("project", dsfProjectDTO);
            this.generateTemplate(config, this.dockerFolder,
                    this.dockerFile,
                    dsfProjectDTO.getDevSetupBaseFolderName());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
