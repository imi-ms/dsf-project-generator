package utils.generator;

import shared.DsfProjectDTO;
import utils.generator.base.AbstractGenerator;
import java.util.HashMap;


public class DbGenerator extends AbstractGenerator {
    private final String dbFile;
    private final String dbFolder;

    public DbGenerator() {
        super();
        this.dbFile = "db/init-db.sh.mustache";
        this.dbFolder = "src/main/resources";
    }

    @Override
    public boolean generate(DsfProjectDTO dsfProjectDTO) {
        try {
            HashMap<String, Object> config = new HashMap<>();
            config.put("project", dsfProjectDTO);
            this.generateTemplate(config, this.dbFolder, this.dbFile, dsfProjectDTO.getDevSetupBaseFolderName());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
