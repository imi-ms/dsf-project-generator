package utils;

import shared.DsfOrganizationDTO;
import shared.DsfProjectDTO;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ProxyGenerator {

    public static boolean generateProxy(DsfProjectDTO dsfProjectDTO) {
        try {
            File proxyConf = new File("./src/main/resources/proxy/proxy.conf");
            for (DsfOrganizationDTO organization : dsfProjectDTO.getOrganizations()) {
                String proxyStr = Files.readString(proxyConf.toPath());
                String organizationName = organization.getNamespace();
                String proxyOrganizationStr = proxyStr.replace("{ organizationName }", organizationName)
                        .replace("{ frontendFhirIp }", organization.getIpConfig().getFhirFrontendIp())
                        .replace("{ frontendBpeIp }", organization.getIpConfig().getBpeFrontendIp());
                File proxyOrganizationFile = new File(dsfProjectDTO.getOutputPath() + File.separator +
                        "dev-setup/proxy/conf.d/"  + organizationName + ".conf");
                Files.write(proxyOrganizationFile.toPath(),
                        Collections.singleton(proxyOrganizationStr),
                        StandardCharsets.UTF_8);
            }
            File nginxConf = new File("./src/main/resources/proxy/nginx.conf");
            File nginxConfFile = new File(dsfProjectDTO.getOutputPath() + File.separator +
                    "dev-setup/proxy/nginx.conf");
            Files.copy(nginxConf.toPath(), nginxConfFile.toPath(), REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
