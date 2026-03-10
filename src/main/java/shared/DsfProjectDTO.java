package shared;

import java.util.List;

public class DsfProjectDTO {
    private final String projectName;
    private final String projectOrganization;
    private final String dsfVersion; //TODO: Move to Orga
    private List<DsfOrganizationDTO> organizations;
    private String outputPath;

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
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getDomain() {
        return this.projectOrganization.substring(0, projectOrganization.lastIndexOf(".")).replaceAll("-","_");
    }

    public String getDomainName() {
        return this.projectOrganization.substring(projectOrganization.lastIndexOf(".") + 1,
                projectOrganization.length());
    }

    public String generateProcessFolderName() {
        if (this.getProjectName().contains("-process")) return this.getProjectName();
        return this.getProjectName() + "-process";
    }
}
