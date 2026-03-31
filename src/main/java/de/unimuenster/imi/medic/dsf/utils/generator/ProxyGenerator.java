package de.unimuenster.imi.medic.dsf.utils.generator;

import de.unimuenster.imi.medic.dsf.shared.DsfOrganizationDTO;
import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import de.unimuenster.imi.medic.dsf.utils.generator.base.AbstractGenerator;
import java.util.HashMap;

public class ProxyGenerator extends AbstractGenerator {

    private final String proxyConfFile;
    private final String proxyFile;
    private final String proxyFolder;

    public ProxyGenerator() {
        super();
        this.proxyConfFile = "proxy/proxy.conf.mustache";
        this.proxyFile = "proxy/nginx.conf";
        this.proxyFolder = "src/main/resources";
    }

    @Override
    public boolean generate(DsfProjectDTO dsfProjectDTO) {
        try {
            String devSetupBaseFolder = dsfProjectDTO.getDevSetupBaseFolderName();
            for (DsfOrganizationDTO organization : dsfProjectDTO.getOrganizations()) {
                HashMap<String, Object> config = new HashMap<>();
                config.put("organization", organization);
                this.generateTemplate(config, this.proxyFolder + "/" + this.proxyConfFile,
                        devSetupBaseFolder + "/proxy/conf.d/" + organization.getNamespaceDash() + ".conf");
            }
            this.copyFile(this.proxyFolder, this.proxyFile, devSetupBaseFolder + "/" + this.proxyFile);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
