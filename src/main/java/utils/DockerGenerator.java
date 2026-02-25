package utils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import shared.DsfProjectDTO;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;

public class DockerGenerator {

    public static boolean generateDockerFile(DsfProjectDTO dsfProjectDTO)  {
        try {
            HashMap<String, Object> config = new HashMap<>();
            config.put("project", dsfProjectDTO);
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache mustache = mf.compile("docker-compose.mustache");
            StringWriter writer = new StringWriter();
            mustache.execute(writer, config).flush();
            File dockerComposeFile = new File(dsfProjectDTO.getOutputPath() + File.separator + "dev-setup" +
                    File.separator + "docker-compose.yml");
            Files.write(dockerComposeFile.toPath(),
                    writer.toString().getBytes());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
