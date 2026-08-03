package de.unimuenster.imi.medic.dsf.shared;

import java.util.List;
import java.util.UUID;

public class DsfProjectDTO {
    private final String projectName;
    private final String projectOrganization;
    private final String dsfVersion; //TODO: Move to Orga
    private List<DsfOrganizationDTO> organizations;
    private String outputPath;
    private String parentOrganizationId;

    public DsfProjectDTO(String projectName,
                         String projectOrganization,
                         DsfVersion version,
                         List<DsfOrganizationDTO> organizations,
                         String outputPath) {
        this.projectName = projectName.toLowerCase();
        this.projectOrganization = projectOrganization;
        this.dsfVersion = version.getVersion();
        this.organizations = organizations;
        this.outputPath = outputPath;
        this.parentOrganizationId = UUID.randomUUID().toString();
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getProjectNameDash() {
        return this.projectName.replaceAll("_", "-");
    }

    public String getProjectOrganization() {
        return this.projectOrganization;
    }

    public List<DsfOrganizationDTO> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<DsfOrganizationDTO> organizations) {
        this.organizations = organizations;
    }

    public void addOrganization(DsfOrganizationDTO organization) {
        this.organizations.add(organization);
    }

    public String getDsfVersion() {
        return dsfVersion;
    }

    public String getOutputPath() {
        return outputPath + "/" + this.getProjectNameDash();
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getDomain() {
        return this.projectOrganization.substring(0, projectOrganization.lastIndexOf("."))
                .replaceAll("-","_");
    }

    public String getDomainName() {
        return this.projectOrganization.substring(this.projectOrganization.lastIndexOf(".") + 1);
    }

    public String getProcessFolderName() {
        if (this.getProjectName().contains("-process")) return this.getProjectName();
        return this.getProjectName() + "-process";
    }

    public String getProcessBaseFolderName() {
        return this.getOutputPath() + "/" + this.getProcessFolderName();
    }

    public String getDevSetupBaseFolderName() {
        return this.getOutputPath() + "/dev-setup";
    }

    public String getProcessBaseFolderTemplateName() {
        return this.getProcessBaseFolderName() + "/templates";
    }

    public String getProcessBaseFolderMainName() {
        return this.getProcessBaseFolderName() + "/src/main";
    }

    public String getProcessBaseFolderMainResourcesName() {
        return this.getProcessBaseFolderMainName() + "/resources";
    }

    public String getProcessBaseFolderTestName() {
        return this.getProcessBaseFolderName() + "/test";
    }

    public String getProcessBaseFolderTestResourcesName() {
        return this.getProcessBaseFolderTestName() + "/resources";
    }

    public String getProcessPackageName() {
        return this.getDomainName().replace("-", "_") + "." + this.getDomain() + "." +
                "process" + "." + this.getProjectName().replace("-process", "")
                .replace("-", "_");
    }

    public String getProcessPackageNameFolder() {
        return this.getProcessPackageName().replace(".", "/").replace("-", "_");
    }

    public String getProcessOrganizationName() {
        return this.getDomain() + "." + this.getDomainName();
    }

    public String getProcessOrganizationNameConcat() {
        return this.getDomain() + this.getDomainName();
    }

    public String getProcessNameOrganization() {
        return this.getDomainName() + "." + this.getDomain();
    }

    public String getParentOrganizationId() {
        return this.parentOrganizationId;
    }
}
