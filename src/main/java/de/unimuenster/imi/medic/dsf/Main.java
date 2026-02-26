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
        DsfOrganizationDTO dic = new DsfOrganizationDTO.Builder()
                .name(InputChecker.checkIfValidOrganizationName("dic"))
                .role(DsfOrganizationRole.DIC)
                .fhirIp("172.20.0.67")
                .fhirProxyPassIp("172.20.0.66")
                .fhirPort(5000)
                .fhirFrontendSubnet("172.20.0.64/28")
                .bpeIp("172.20.0.115")
                .bpeProxyPassIp("172.20.0.114")
                .bpePort(5003)
                .bpeFrontendSubnet("172.20.0.112/28")
                .build();
        DsfOrganizationDTO hrp = new DsfOrganizationDTO.Builder()
                .name(InputChecker.checkIfValidOrganizationName("hrp"))
                .role(DsfOrganizationRole.HRP)
                .fhirIp("172.20.0.83")
                .fhirProxyPassIp("172.20.0.82")
                .fhirPort(5001)
                .fhirFrontendSubnet("172.20.0.80/28")
                .bpeIp("172.20.0.131")
                .bpeProxyPassIp("172.20.0.130")
                .bpePort(5004)
                .bpeFrontendSubnet("172.20.0.128/28")
                .build();
        DsfOrganizationDTO cos = new DsfOrganizationDTO.Builder()
                .name(InputChecker.checkIfValidOrganizationName("cos"))
                .role(DsfOrganizationRole.COS)
                .fhirIp("172.20.0.99")
                .fhirProxyPassIp("172.20.0.98")
                .fhirPort(5002)
                .fhirFrontendSubnet("172.20.0.96/28")
                .bpeIp("172.20.0.147")
                .bpeProxyPassIp("172.20.0.146")
                .bpePort(5005)
                .bpeFrontendSubnet("172.20.0.144/28")
                .build();
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
    }
}
