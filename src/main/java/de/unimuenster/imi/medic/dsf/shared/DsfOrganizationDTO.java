package de.unimuenster.imi.medic.dsf.shared;

import java.util.Locale;

public class DsfOrganizationDTO {

    private String name;
    private String nameUC;
    private DsfOrganizationRole role;
    private String fhirIp;
    private String fhirProxyPassIp;
    private Integer fhirPort;
    private String fhirFrontendSubnet;
    private String bpeIp;
    private String bpeProxyPassIp;
    private Integer bpePort;
    private String bpeFrontendSubnet;
    //TODO: Add secrets OIDC

    public DsfOrganizationDTO(Builder builder) {
        this.name = builder.name;
        this.nameUC = builder.nameUC;
        this.role = builder.role;
        this.fhirIp = builder.fhirIp;
        this.fhirProxyPassIp = builder.fhirProxyPassIp;
        this.fhirPort = builder.fhirPort;
        this.fhirFrontendSubnet = builder.fhirFrontendSubnet;
        this.bpeIp = builder.bpeIp;
        this.bpeProxyPassIp = builder.bpeProxyPassIp;
        this.bpePort = builder.bpePort;
        this.bpeFrontendSubnet = builder.bpeFrontendSubnet;

    }

    public String getNameUC() {
        return nameUC;
    }

    public void setNameUC(String nameUC) {
        this.nameUC = nameUC;
    }

    public static class Builder {
        private String name;
        private String nameUC;
        private DsfOrganizationRole role;
        private String fhirIp;
        private String fhirProxyPassIp;
        private Integer fhirPort;
        private String fhirFrontendSubnet;
        private String bpeIp;
        private String bpeProxyPassIp;
        private Integer bpePort;
        private String bpeFrontendSubnet;

        public Builder name(String name) {
            this.name = name;
            this.nameUC = name.toUpperCase(Locale.ENGLISH);
            return this;
        }

        public Builder role(DsfOrganizationRole role) {
            this.role = role;
            return this;
        }

        public Builder fhirIp(String fhirIp) {
            this.fhirIp = fhirIp;
            return this;
        }

        public Builder fhirProxyPassIp(String fhirProxyPassIp) {
            this.fhirProxyPassIp = fhirProxyPassIp;
            return this;
        }

        public Builder fhirPort(Integer fhirPort) {
            this.fhirPort = fhirPort;
            return this;
        }

        public Builder fhirFrontendSubnet(String fhirFrontendSubnet) {
            this.fhirFrontendSubnet = fhirFrontendSubnet;
            return this;
        }

        public Builder bpeIp(String bpeIp) {
            this.bpeIp = bpeIp;
            return this;
        }

        public Builder bpeProxyPassIp(String bpeProxyPassIp) {
            this.bpeProxyPassIp = bpeProxyPassIp;
            return this;
        }

        public Builder bpePort(Integer bpePort) {
            this.bpePort = bpePort;
            return this;
        }

        public Builder bpeFrontendSubnet(String bpeFrontendSubnet) {
            this.bpeFrontendSubnet = bpeFrontendSubnet;
            return this;
        }

        public DsfOrganizationDTO build() {
            return new DsfOrganizationDTO(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DsfOrganizationRole getRole() {
        return role;
    }

    public void setRole(DsfOrganizationRole role) {
        this.role = role;
    }

    public String getFhirIp() {
        return fhirIp;
    }

    public void setFhirIp(String fhirIp) {
        this.fhirIp = fhirIp;
    }

    public String getBpeIp() {
        return bpeIp;
    }

    public void setBpeIp(String bpeIp) {
        this.bpeIp = bpeIp;
    }

    public String getFhirProxyPassIp() {
        return fhirProxyPassIp;
    }

    public void setFhirProxyPassIp(String fhirProxyPassIp) {
        this.fhirProxyPassIp = fhirProxyPassIp;
    }

    public String getBpeProxyPassIp() {
        return bpeProxyPassIp;
    }

    public void setBpeProxyPassIp(String bpeProxyPassIp) {
        this.bpeProxyPassIp = bpeProxyPassIp;
    }

    public Integer getFhirPort() {
        return fhirPort;
    }

    public void setFhirPort(Integer fhirPort) {
        this.fhirPort = fhirPort;
    }

    public Integer getBpePort() {
        return bpePort;
    }

    public void setBpePort(Integer bpePort) {
        this.bpePort = bpePort;
    }

    public String getFhirFrontendSubnet() {
        return fhirFrontendSubnet;
    }

    public void setFhirFrontendSubnet(String fhirFrontendSubnet) {
        this.fhirFrontendSubnet = fhirFrontendSubnet;
    }

    public String getBpeFrontendSubnet() {
        return bpeFrontendSubnet;
    }

    public void setBpeFrontendSubnet(String bpeFrontendSubnet) {
        this.bpeFrontendSubnet = bpeFrontendSubnet;
    }
}
