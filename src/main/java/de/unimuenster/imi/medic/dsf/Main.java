package de.unimuenster.imi.medic.dsf;

import de.unimuenster.imi.medic.dsf.shared.DsfOrganizationDTO;
import de.unimuenster.imi.medic.dsf.shared.DsfOrganizationRole;
import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import de.unimuenster.imi.medic.dsf.utils.DbGenerator;
import de.unimuenster.imi.medic.dsf.utils.FolderGenerator;
import de.unimuenster.imi.medic.dsf.utils.InputChecker;
import de.unimuenster.imi.medic.dsf.utils.KeycloakGenerator;
import de.unimuenster.imi.medic.dsf.utils.PomGenerator;
import de.unimuenster.imi.medic.dsf.utils.ProxyGenerator;
import de.unimuenster.imi.medic.dsf.utils.SecretsGenerator;
import de.unimuenster.imi.medic.dsf.utils.DockerGenerator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        NetworkHandler networkHandler = new NetworkHandler();
        DsfOrganizationDTO dic = new DsfOrganizationDTO.Builder().name("dic")
            .role(DsfOrganizationRole.DIC).ipConfig(networkHandler.getAndRemoveValidIp()).build();
        DsfOrganizationDTO hrp = new DsfOrganizationDTO.Builder().name("hrp")
            .role(DsfOrganizationRole.HRP).ipConfig(networkHandler.getAndRemoveValidIp()).build();
        DsfOrganizationDTO cos = new DsfOrganizationDTO.Builder().name("cos")
            .role(DsfOrganizationRole.COS).ipConfig(networkHandler.getAndRemoveValidIp()).build();
        List<DsfOrganizationDTO> organizations = new ArrayList<>();
        organizations.add(dic);
        organizations.add(hrp);
        organizations.add(cos);

        DsfProjectDTO dsfProjectDTO = new DsfProjectDTO(InputChecker.checkIfValidProjectName("Test"),
                InputChecker.checkIfDomainExists("imi.ms"), organizations,
                "./output/");


//        MustacheFactory mf = new DefaultMustacheFactory();
//        Mustache mustache = mf.compile("template.mustache");
//        mustache.execute(new PrintWriter(System.out), new Example()).flush();

        FolderGenerator gen = new FolderGenerator();
        gen.generateProjectFiles(dsfProjectDTO);
        SecretsGenerator.generateSecrets(dsfProjectDTO);
        DockerGenerator.generateDockerFile(dsfProjectDTO);
        DbGenerator.generateDb(dsfProjectDTO);
        KeycloakGenerator.generateKeycloakImport(dsfProjectDTO);
        ProxyGenerator.generateProxy(dsfProjectDTO);
        PomGenerator.generatePomFile(dsfProjectDTO);
        ProcessGenerator.generateProcess(dsfProjectDTO);
    }
}
