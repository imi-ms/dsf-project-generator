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
    public static boolean generatePomFile(DsfProjectDTO dsfProjectDTO)  {
        try {
            HashMap<String, Object> config = new HashMap<>();
            config.put("project", dsfProjectDTO);
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache mustache = mf.compile("pom.mustache");
            StringWriter writer = new StringWriter();
            mustache.execute(writer, config).flush();
            File pomFile = new File(dsfProjectDTO.getOutputPath() + File.separator + dsfProjectDTO.getProcessFolderName() +
                File.separator + "pom.xml");
            Files.write(pomFile.toPath(),
                writer.toString().getBytes());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
