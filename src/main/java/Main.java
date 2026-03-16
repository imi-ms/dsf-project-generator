import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import shared.DsfOrganizationDTO;
import shared.DsfOrganizationRole;
import shared.DsfProjectDTO;
import shared.DsfVersion;
import utils.generator.ProjectGenerator;
import utils.generator.*;
import utils.generator.base.AbstractGenerator;
import utils.helper.InputCheckHelper;
import utils.helper.NetworkSelectionHelper;

public class Main {

    public static void main(String[] args) throws IOException {
        NetworkSelectionHelper networkSelectionHelper = new NetworkSelectionHelper();
        DsfOrganizationDTO dic = new DsfOrganizationDTO.Builder().name("dic")
            .role(DsfOrganizationRole.DIC).ipConfig(networkSelectionHelper.getAndRemoveValidIp()).build();
        DsfOrganizationDTO hrp = new DsfOrganizationDTO.Builder().name("hrp")
            .role(DsfOrganizationRole.HRP).ipConfig(networkSelectionHelper.getAndRemoveValidIp()).build();
        DsfOrganizationDTO cos = new DsfOrganizationDTO.Builder().name("cos")
            .role(DsfOrganizationRole.COS).ipConfig(networkSelectionHelper.getAndRemoveValidIp()).build();
        List<DsfOrganizationDTO> organizations = new ArrayList<>();
        organizations.add(dic);
        organizations.add(hrp);
        organizations.add(cos);

        DsfProjectDTO dsfProjectDTO = new DsfProjectDTO(InputCheckHelper.checkIfValidProjectName("Test"),
                InputCheckHelper.checkIfDomainExists("imi.ms"),
                DsfVersion.V_2_0_1, organizations,
                "./output/");
        
        List<AbstractGenerator> generators = List.of(new FolderGenerator(), 
                new SecretsGenerator(), 
                new DockerGenerator(), 
                new DbGenerator(), 
                new KeycloakGenerator(), 
                new ProxyGenerator(), 
                new PomGenerator(), 
                new ProcessGenerator(), 
                new DevEnvGenerator(), 
                new AllowListGenerator());
        
        ProjectGenerator projectGenerator = new ProjectGenerator(generators);
        projectGenerator.generate(dsfProjectDTO);
    }
}
