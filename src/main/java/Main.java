import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import shared.DsfOrganizationDTO;
import shared.DsfOrganizationRole;
import shared.DsfProjectDTO;
import shared.DsfVersion;
import utils.FolderGenerator;
import utils.NetworkHandler;
import utils.InputChecker;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        NetworkHandler networkHandler = new NetworkHandler();
        DsfOrganizationDTO dic = new DsfOrganizationDTO.Builder().name("dic")
            .role(DsfOrganizationRole.DIC).generateValidSettings(networkHandler).build();
        DsfOrganizationDTO hrp = new DsfOrganizationDTO.Builder().name("hrp")
            .role(DsfOrganizationRole.HRP).generateValidSettings(networkHandler).build();
        DsfOrganizationDTO cos = new DsfOrganizationDTO.Builder().name("cos")
            .role(DsfOrganizationRole.COS).generateValidSettings(networkHandler).build();
        List<DsfOrganizationDTO> organizations = new ArrayList<>();
        organizations.add(dic);
        organizations.add(hrp);
        organizations.add(cos);

        DsfProjectDTO dsfProjectDTO = new DsfProjectDTO(InputChecker.checkIfValidProjectName("Test"),
                InputChecker.checkIfDomainExists("imi.ms"),
                DsfVersion.V_2_0_1, organizations,
                "./output/");


//        MustacheFactory mf = new DefaultMustacheFactory();
//        Mustache mustache = mf.compile("template.mustache");
//        mustache.execute(new PrintWriter(System.out), new Example()).flush();

        FolderGenerator gen = new FolderGenerator();
        gen.generateProjectFiles(dsfProjectDTO);

        ProcessGenerator.generateProcess(dsfProjectDTO);
        SecretsGenerator.generateSecrets(dsfProjectDTO);
        DockerGenerator.generateDockerFile(dsfProjectDTO);
        DbGenerator.generateDb(dsfProjectDTO);
        KeycloakGenerator.generateKeycloakImport(dsfProjectDTO);
        ProxyGenerator.generateProxy(dsfProjectDTO);
        PomGenerator.generatePomFile(dsfProjectDTO);
    }
}
