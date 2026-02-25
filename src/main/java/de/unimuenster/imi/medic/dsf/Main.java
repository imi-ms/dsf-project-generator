package de.unimuenster.imi.medic.dsf;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import de.unimuenster.imi.medic.dsf.shared.DsfOrganizationDTO;
import de.unimuenster.imi.medic.dsf.shared.DsfOrganizationRole;
import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import de.unimuenster.imi.medic.dsf.shared.DsfVersion;
import de.unimuenster.imi.medic.dsf.utils.FolderGenerator;
import de.unimuenster.imi.medic.dsf.utils.InputChecker;
import de.unimuenster.imi.medic.dsf.utils.NetworkHandler;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                InputChecker.checkIfDomainExists("imi.ms"), organizations,
                "./output/");


//        MustacheFactory mf = new DefaultMustacheFactory();
//        Mustache mustache = mf.compile("template.mustache");
//        mustache.execute(new PrintWriter(System.out), new Example()).flush();

        HashMap<String, Object> config = new HashMap<>();
        config.put("project", dsfProjectDTO);

        Writer writer = new OutputStreamWriter(System.out);
        MustacheFactory mf = new DefaultMustacheFactory();
        //Mustache mustache = mf.compile(new StringReader("{{project.projectName}}! {{#project.organizations}}{{name}},{{/project.organizations}}"), "example");
        Mustache mustache = mf.compile("docker-compose.mustache");
        mustache.execute(writer, config);
        writer.flush();

        FolderGenerator gen = new FolderGenerator();
        gen.generateProjectFiles(dsfProjectDTO);
    }
}
