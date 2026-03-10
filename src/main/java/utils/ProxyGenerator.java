package utils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import shared.DsfOrganizationDTO;
import shared.DsfProjectDTO;

import java.io.File;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ProxyGenerator {

    public static boolean generateProxy(DsfProjectDTO dsfProjectDTO) {
        try {
            for (DsfOrganizationDTO organization : dsfProjectDTO.getOrganizations()) {
                HashMap<String, Object> config = new HashMap<>();
                config.put("organization", organization);
                MustacheFactory mf = new DefaultMustacheFactory();
                Mustache mustache = mf.compile("proxy/proxy.mustache");
                StringWriter writer = new StringWriter();
                mustache.execute(writer, config).flush();
                File proxyOrganizationFile = new File(dsfProjectDTO.getOutputPath() + File.separator +
                        "dev-setup/proxy/conf.d/"  + organization.getNamespace() + ".conf");
                Files.write(proxyOrganizationFile.toPath(),
                        writer.toString().getBytes());
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
