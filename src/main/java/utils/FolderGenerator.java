package utils;

import shared.DsfOrganizationDTO;
import shared.DsfProjectDTO;
import java.io.File;
import java.util.List;

public class FolderGenerator {

    public FolderGenerator() {}

    public boolean generateProjectFiles(DsfProjectDTO dsfProjectDTO) {
        return this.generateProjectFolders(dsfProjectDTO);
    }

    private boolean generateProjectFolders(DsfProjectDTO dsfProjectDTO) {
        try {
            String gitKeep = ".gitkeep";

            // 1.) Create project directory - if not exists
            File projectFolder = new File(dsfProjectDTO.getOutputPath());
            if (!projectFolder.mkdirs()) return false;

            // 2.) Create main directories
            // 2.1) Create browser-certs
            File browserCerts = this.createFolder(projectFolder, "browser-certs");
            this.addGitKeep(browserCerts);

            // 2.2) Create cert
            File cert = this.createFolder(projectFolder, "cert");
            this.addGitKeep(cert);

            // 2.3) Create dev-setup
            File devSetup = this.createFolder(projectFolder, "dev-setup");
            this.addGitKeep(devSetup);
            List<String> devFolders = List.of("db", "keycloak", "proxy", "secrets");
            for (String dev : devFolders) {
                File devFolder = this.createFolder(devSetup, dev);
                this.addGitKeep(devFolder);
            }

            // 2.4) Add organizations to browser-cert and dev-setup
            for (DsfOrganizationDTO organization : dsfProjectDTO.getOrganizations()) {
                // 2.4.1) Add to organization to browser-cert
                File browserCert = this.createFolder(browserCerts, organization.getName());
                this.addGitKeep(browserCert);

                // 2.4.2) Add organization to dev-setup
                File devOrganization = this.createFolder(devSetup, organization.getName());
                this.addGitKeep(devOrganization);

                // 2.4.2.1) Add DSF BPE server folder to organization dev setup
                File devOrganizationBpe = this.createFolder(devOrganization, "bpe");
                this.addGitKeep(devOrganizationBpe);
                List<String> bpeFolders = List.of("last_event", "log", "plugin", "process");
                for (String bpeFolder : bpeFolders) {
                    File devOrganizationBpeFolder = this.createFolder(devOrganizationBpe, bpeFolder);
                    this.addGitKeep(devOrganizationBpeFolder);
                }

                // 2.4.2.2) Add DSF FHIR server folder to organization dev setup
                File devOrganizationFhir = this.createFolder(devOrganization, "fhir");
                this.addGitKeep(devOrganizationFhir);
                List<String> fhirFolders = List.of("conf", "log");
                for (String fhirFolder : fhirFolders) {
                    File devOrganizationFhirFolder = this.createFolder(devOrganizationFhir, fhirFolder);
                    this.addGitKeep(devOrganizationFhirFolder);
                }
            }

            // 2.5) Create process folder
            return true;
        } catch (Exception e) {
            return  false;
        }
    }

    private File createFolder(File directory, String folder) throws Exception {
        File newDirectory = new File(directory + File.separator, folder);
        if (!newDirectory.mkdir()) throw new Exception("Folder could not be created.");
        return newDirectory;
    }

    private void addGitKeep(File directory) throws Exception {
        String GITKEEP = ".gitkeep";
        File directoryKeep = new File(directory + File.separator, GITKEEP);
        if (!directoryKeep.createNewFile()) throw new Exception("GitKeep could not be created.");
    }
}
