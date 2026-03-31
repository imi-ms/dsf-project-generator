package de.unimuenster.imi.medic.dsf.utils.generator;

import de.unimuenster.imi.medic.dsf.shared.DsfOrganizationDTO;
import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import de.unimuenster.imi.medic.dsf.utils.generator.base.AbstractGenerator;

import java.util.HashMap;


public class KeycloakGenerator extends AbstractGenerator {

    private final String keycloakFile;
    private final String keycloakFolder;

    public KeycloakGenerator() {
        super();
        this.keycloakFile = "keycloak/keycloak.json.mustache";
        this.keycloakFolder = "src/main/resources";
    }

    public boolean generate(DsfProjectDTO dsfProjectDTO) {
        try {
            for (DsfOrganizationDTO organization : dsfProjectDTO.getOrganizations()) {
                HashMap<String, Object> config = new HashMap<>();
                config.put("project", dsfProjectDTO);
                config.put("organization", organization);
                this.generateTemplate(config,
                        this.keycloakFolder + "/" + this.keycloakFile,
                       dsfProjectDTO.getDevSetupBaseFolderName() + "/keycloak/import/" +
                               organization.getNamespaceDash() + ".json");
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
