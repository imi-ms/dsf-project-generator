package de.unimuenster.imi.medic.dsf;

import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import de.unimuenster.imi.medic.dsf.utils.DbGenerator;
import de.unimuenster.imi.medic.dsf.utils.DockerGenerator;
import de.unimuenster.imi.medic.dsf.utils.FolderGenerator;
import de.unimuenster.imi.medic.dsf.utils.KeycloakGenerator;
import de.unimuenster.imi.medic.dsf.utils.PomGenerator;
import de.unimuenster.imi.medic.dsf.utils.ProcessGenerator;
import de.unimuenster.imi.medic.dsf.utils.ProxyGenerator;
import de.unimuenster.imi.medic.dsf.utils.SecretsGenerator;

public class ProjectGenerator {

    public static void generate(DsfProjectDTO projectDTO) {
        FolderGenerator gen = new FolderGenerator();
        gen.generateProjectFiles(projectDTO);
        SecretsGenerator.generateSecrets(projectDTO);
        DockerGenerator.generateDockerFile(projectDTO);
        DbGenerator.generateDb(projectDTO);
        KeycloakGenerator.generateKeycloakImport(projectDTO);
        ProxyGenerator.generateProxy(projectDTO);
        PomGenerator.generatePomFiles(projectDTO);
        ProcessGenerator.generateProcess(projectDTO);
    }

}
