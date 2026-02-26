package de.unimuenster.imi.medic.dsf.utils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import java.io.File;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.HashMap;

public class DbGenerator {

    public static boolean generateDb(DsfProjectDTO dsfProjectDTO) {
        try {
            HashMap<String, Object> config = new HashMap<>();
            config.put("project", dsfProjectDTO);
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache mustache = mf.compile("db/init-db.mustache");
            StringWriter writer = new StringWriter();
            mustache.execute(writer, config).flush();
            File dockerComposeFile = new File(dsfProjectDTO.getOutputPath() + File.separator + "dev-setup" +
                    File.separator + "db" + File.separator + "init-db.sh");
            Files.write(dockerComposeFile.toPath(),
                    writer.toString().getBytes());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
