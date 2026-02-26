package utils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import shared.DsfProjectDTO;

import java.io.File;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashMap;
import java.util.Set;

public class DbGenerator {

    public static boolean generateDb(DsfProjectDTO dsfProjectDTO) {
        try {
            HashMap<String, Object> config = new HashMap<>();
            config.put("project", dsfProjectDTO);
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache mustache = mf.compile("db/init-db.mustache");
            StringWriter writer = new StringWriter();
            mustache.execute(writer, config).flush();
            File dbFile = new File(dsfProjectDTO.getOutputPath() + File.separator + "dev-setup" +
                    File.separator + "db" + File.separator + "init-db.sh");

            Files.createFile(dbFile.toPath());
            String normalized = writer.toString().replace("\r\n", "\n")
                    .replace("\r", "\n");
            Files.writeString(dbFile.toPath(), normalized, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
            Set<PosixFilePermission> perms = Files.getPosixFilePermissions(dbFile.toPath());
            perms.add(PosixFilePermission.OWNER_EXECUTE);
            perms.add(PosixFilePermission.GROUP_EXECUTE);
            perms.add(PosixFilePermission.OTHERS_EXECUTE);
            Files.setPosixFilePermissions(dbFile.toPath(), perms);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
