package utils.generator;

import shared.DsfOrganizationDTO;
import shared.DsfProjectDTO;
import utils.generator.base.AbstractGenerator;
import utils.helper.PasswordGeneratorHelper;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;


public class SecretsGenerator extends AbstractGenerator {

    private final List<String> secretFiles;
    private final String appClientCertPwdFile;
    private final String appDbLiquibasePwdFile;
    private final String secretsFolder;
    private final PasswordGeneratorHelper passwordGeneratorHelper;

    public SecretsGenerator() {
        super();
        this.secretFiles = List.of("db_%s_bpe_user.password",
                "db_%s_bpe_user_engine.password",
                "db_%s_fhir_user.password",
                "db_%s_fhir_user_permanent_delete.password");
        this.appClientCertPwdFile = "secrets/app_client_certificate_private_key.pem.password";
        this.appDbLiquibasePwdFile = "secrets/db_liquibase.password";
        this.secretsFolder = "src/main/resources";
        this.passwordGeneratorHelper = new PasswordGeneratorHelper();
    }

    public boolean generate(DsfProjectDTO dsfProjectDTO) {
        try {
            String secretsTarget = dsfProjectDTO.getDevSetupBaseFolderName() + "/";
            this.copyFile(this.secretsFolder, this.appClientCertPwdFile, secretsTarget + this.appClientCertPwdFile);
            this.copyFile(this.secretsFolder, this.appDbLiquibasePwdFile, secretsTarget + this.appDbLiquibasePwdFile);
            for (String secretFile : secretFiles) {
                for (DsfOrganizationDTO organization : dsfProjectDTO.getOrganizations()) {
                    String organizationsSecret = secretFile.formatted(organization.getNamespace());
                    File organizationsSecretsFile = new File(secretsTarget + "secrets/" + organizationsSecret);
                    Files.write(organizationsSecretsFile.toPath(),
                            Collections.singleton(this.passwordGeneratorHelper.generateSecret(32)),
                            StandardCharsets.UTF_8);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
