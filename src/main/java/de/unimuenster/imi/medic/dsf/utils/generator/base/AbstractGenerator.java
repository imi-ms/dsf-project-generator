package de.unimuenster.imi.medic.dsf.utils.generator.base;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import java.io.File;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.HashMap;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public abstract class AbstractGenerator {

    protected final MustacheFactory mf;

    public AbstractGenerator() {
        this.mf = new DefaultMustacheFactory();
    }

    public abstract boolean generate(DsfProjectDTO dsfProjectDTO);

    protected boolean generateTemplate(HashMap<String, Object> config,
                                       String source,
                                       String target) throws Exception  {
        try {
            Mustache mustache = this.mf.compile(source);
            StringWriter writer = new StringWriter();
            mustache.execute(writer, config).flush();
            File proxyOrganizationFile = new File(target);
            String normalized = writer.toString().replace("\r\n", "\n")
                    .replace("\r", "\n");
            Files.write(proxyOrganizationFile.toPath(), normalized.getBytes());
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    protected boolean generateTemplate(HashMap<String, Object> config,
                                       String origin,
                                       String source,
                                       String target) throws Exception  {
        try {
            this.generateTemplate(config,
                    this.generateSourceFolder(origin, source),
                    this.generateTargetFromMustache(source, target));
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    protected boolean copyFile(String origin, String source, String target) throws Exception {
        try {
            File sourceFile = new File(this.generateSourceFolder(origin, source));
            File targetFile = new File(target);
            Files.copy(sourceFile.toPath(), targetFile.toPath(), REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    protected String generateTargetFromMustache(String source, String target) {
        return target + "/" + source.replace(".mustache", "");
    }

    protected String generateSourceFolder(String origin, String source) {
        return origin + "/" + source;
    }

    /*
    Source: https://www.baeldung.com/java-delete-directory
     */
    protected boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}
