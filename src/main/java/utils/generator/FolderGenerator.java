package utils.generator;

import shared.DsfOrganizationDTO;
import shared.DsfProjectDTO;
import utils.generator.base.AbstractGenerator;

import java.io.File;
import java.util.List;

public class FolderGenerator extends AbstractGenerator {

    public FolderGenerator() {
        super();
    }

    public boolean generate(DsfProjectDTO dsfProjectDTO) {
        try {
            // 1.) Create project directory - if not exists, else delete
            File projectFolder = new File(dsfProjectDTO.getOutputPath());
            this.deleteDirectory(projectFolder);

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
            List<String> devFolders = List.of("db", "keycloak/import", "proxy/conf.d", "secrets");
            for (String dev : devFolders) {
                File devFolder = this.createFolders(devSetup, dev);
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
            File processFolder = this.createFolder(projectFolder,
                    dsfProjectDTO.getProcessFolderName());
            File processSrcFolder = this.createFolder(processFolder, "src");

            // 2.5.1) Create main folder
            File processSrcMainFolder = this.createFolder(processSrcFolder, "main");
            String packageFolder = "java" + File.separator + dsfProjectDTO.getDomainName() + File.separator +
                    dsfProjectDTO.getDomain() + File.separator + "process" + File.separator +
                    dsfProjectDTO.getProjectName().replace("-process", "");
            File processSrcJavaFolder = this.createFolders(processSrcMainFolder, packageFolder);
            List<String> processFolders = List.of("listener", "message", "service", "spring/config");
            for (String process : processFolders) {
                File processSubFolder = this.createFolders(processSrcJavaFolder, process);
                this.addGitKeep(processSubFolder);
            }

            // 2.5.2) Create resource folder
            File resourceMainFolder = this.createFolder(processSrcMainFolder, "resources");
            File resourceConfigFolder = this.createFolders(resourceMainFolder, "META-INF/services");
            File resourceBpeFolder = this.createFolder(resourceMainFolder, "bpe");
            File resourceFhirFolder = this.createFolder(resourceMainFolder, "fhir");
            List<String> fhirResourceFolders = List.of("ActivityDefinition", "CodeSystem", "Questionnaire",
                    "StructureDefinition", "Task", "ValueSet");
            for (String fhirResourceFolder : fhirResourceFolders) {
                this.createFolders(resourceFhirFolder, fhirResourceFolder);
            }
            File resourceTemplatesFolder = this.createFolder(resourceMainFolder, "templates");

            // 2.5.3) Create test folder
            File processTestFolder = this.createFolder(processFolder, "test");
            File processTestBpeJavaFolders = this.createFolders(processTestFolder, packageFolder +
                    File.separator + "bpe");
            File processTestFhirJavaFolders = this.createFolders(processTestFolder, packageFolder +
                    File.separator + "fhir/profile");
            File processTestResourcesFolder = this.createFolder(processTestFolder, "resources");
            return true;
        } catch (Exception e) {
            File projectFolder = new File(dsfProjectDTO.getOutputPath());
            this.deleteDirectory(projectFolder);
            return  false;
        }
    }

    private File createFolder(File directory, String folder) throws Exception {
        File newDirectory = new File(directory + File.separator, folder);
        if (!newDirectory.mkdir()) throw new Exception("Folder could not be created.");
        return newDirectory;
    }

    private File createFolders(File directory, String folders) throws Exception {
        File newDirectories = new File(directory + File.separator, folders);
        if (!newDirectories.mkdirs()) throw new Exception("Folders could not be created.");
        return newDirectories;
    }

    private void addGitKeep(File directory) throws Exception {
        String GITKEEP = ".gitkeep";
        File directoryKeep = new File(directory + File.separator, GITKEEP);
        if (!directoryKeep.createNewFile()) throw new Exception("GitKeep could not be created.");
    }
}
