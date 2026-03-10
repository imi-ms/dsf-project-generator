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
import java.util.UUID;

public class KeycloakGenerator {

    public static boolean generateKeycloakImport(DsfProjectDTO dsfProjectDTO) {
        try {
            for (DsfOrganizationDTO organization : dsfProjectDTO.getOrganizations()) {
                HashMap<String, Object> config = new HashMap<>();
                config.put("project", dsfProjectDTO);
                config.put("organization", organization);
                MustacheFactory mf = new DefaultMustacheFactory();
                Mustache mustache = mf.compile("keycloak/keycloak.mustache");
                StringWriter writer = new StringWriter();
                mustache.execute(writer, config).flush();
                File keycloakOrganizationFile = new File(dsfProjectDTO.getOutputPath() + File.separator +
                        "dev-setup/keycloak/import/"  + organization.getNamespaceDash() + ".json");
                Files.write(keycloakOrganizationFile.toPath(),
                        writer.toString().getBytes());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }



        return true;
    }
}
