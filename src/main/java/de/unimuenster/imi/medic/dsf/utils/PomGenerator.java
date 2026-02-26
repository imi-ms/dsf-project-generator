package de.unimuenster.imi.medic.dsf.utils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import java.io.File;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.HashMap;

public class PomGenerator {
    public static boolean generatePomFiles(DsfProjectDTO dsfProjectDTO)  {
     if (!generateMainPomFile(dsfProjectDTO)) return false;

     return generateProcessPomFile(dsfProjectDTO);
    }

    private static boolean generateMainPomFile(DsfProjectDTO dsfProjectDTO) {
        String templateName = "main-pom.mustache";
        String targetPath = dsfProjectDTO.getOutputPath() + File.separator  + "pom.xml";

        return generatePomFile(dsfProjectDTO, templateName, targetPath);
    }

    private static boolean generateProcessPomFile(DsfProjectDTO dsfProjectDTO) {
        String templateName = "pom.mustache";
        String targetPath = dsfProjectDTO.getOutputPath() + File.separator + dsfProjectDTO.generateProcessFolderName() +
            File.separator + "pom.xml";

        return generatePomFile(dsfProjectDTO, templateName, targetPath);
    }

    public static boolean generatePomFile(
            DsfProjectDTO dsfProjectDTO,
            String templateName,
            String targetPath
    ) {
        try {
            HashMap<String, Object> config = new HashMap<>();
            config.put("project", dsfProjectDTO);
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache mustache = mf.compile(templateName);
            StringWriter writer = new StringWriter();
            mustache.execute(writer, config).flush();
            File pomFile = new File(targetPath);
            Files.write(pomFile.toPath(),
                writer.toString().getBytes());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
