package de.unimuenster.imi.medic.dsf.shared;

import java.util.List;

public class DsfProjectDTO {

    private final String projectName;
    private final String projectOrganization;
    private List<DsfOrganizationDTO> organizations;
    private String outputPath;

    public DsfProjectDTO(String projectName, String projectOrganization,
        List<DsfOrganizationDTO> organizations, String outputPath) {
        this.projectName = projectName;
        this.projectOrganization = projectOrganization;
        this.organizations = organizations;
        this.outputPath = outputPath;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectOrganization() {
        return projectOrganization;
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

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getDomain() {
        return this.projectOrganization.substring(0, projectOrganization.lastIndexOf("."))
            .replaceAll("-", "_");
    }

    public String getDomainName() {
        return this.projectOrganization.substring(projectOrganization.lastIndexOf(".") + 1
        );
    }

    public String getProcessFolderName() {
        if (projectName.contains("-process")) return projectName;
        return projectName + "-process";
    }

}
