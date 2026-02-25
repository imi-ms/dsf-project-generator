package de.unimuenster.imi.medic.dsf.utils;

import shared.DsfOrganizationDTO;
import shared.DsfProjectDTO;

import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class SecretsGenerator {

    public static boolean generateSecrets(DsfProjectDTO dsfProjectDTO) {
        List<String> secretFiles = List.of("db_%s_bpe_user.password", "db_%s_bpe_user_engine.password",
                "db_%s_fhir_user.password", "db_%s_fhir_user_permanent_delete.password");
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        File secretsFolder = new File(dsfProjectDTO.getOutputPath() + File.separator + "dev-setup" +
                File.separator + "secrets");

        if (secretsFolder.exists()) {
            try {
                File appClientCertPwd = new File("./src/main/resources/secrets/app_client_certificate_private_key.pem.password");
                File appClientCertSecrets = new File(secretsFolder + File.separator + "app_client_certificate_private_key.pem.password");
                Files.copy(appClientCertPwd.toPath(), appClientCertSecrets.toPath(), REPLACE_EXISTING);
                File appDbLiquibase = new File("./src/main/resources/secrets/db_liquibase.password");
                File appDbLiquibaseSecrets = new File(secretsFolder + File.separator + "db_liquibase.password");
                Files.copy(appDbLiquibase.toPath(), appDbLiquibaseSecrets.toPath(), REPLACE_EXISTING);
                for (String secretFile: secretFiles) {
                    for (DsfOrganizationDTO organization : dsfProjectDTO.getOrganizations()) {
                        String organizationsSecret = secretFile.formatted(organization.getNamespace());
                        File organizationsSecretsFile = new File(secretsFolder + File.separator +
                                organizationsSecret);
                        Files.write(organizationsSecretsFile.toPath(),
                                Collections.singleton(passwordGenerator.generateSecret(32)),
                                StandardCharsets.UTF_8);
                    }
                }
            } catch (IOException e) {
                System.out.println("Could not copy file");
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }
}
