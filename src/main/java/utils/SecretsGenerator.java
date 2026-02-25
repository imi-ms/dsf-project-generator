package utils;

import shared.DsfProjectDTO;

import java.io.File;
import java.io.IOException;

public class SecretsGenerator {

    public static boolean generateSecrets(DsfProjectDTO dsfProjectDTO) {
        File secretsFolder = new File(dsfProjectDTO.getOutputPath() + File.separator + "dev-setup" +
                File.separator + "secrets");
        if (secretsFolder.exists()) {
            File appClientCertPwd = new File("./secrets/app_client_certificate_private_key.pem.password");
            /*
            try {
                FileUtils.copyFile(original, copied);
            } catch (IOException e) {
                e.printStackTrace();
            }
             */
        }
        return false;
    }
}
