package shared;

import java.io.IOException;
import java.util.Locale;
import utils.NetworkHandler;
import utils.PasswordGenerator;

public class DsfOrganizationDTO {

    private String name;
    private String nameUC;
    private String namespace;
    private DsfOrganizationRole role;
    private String fhirIp;
    private String fhirProxyPassIp;
    private Integer fhirPort;
    private String fhirFrontendSubnet;
    private String fhirOIDCSecret;
    private String bpeIp;
    private String bpeProxyPassIp;
    private Integer bpePort;
    private String bpeFrontendSubnet;
    private String bpeOIDCSecret;

    public DsfOrganizationDTO(Builder builder) {
        this.name = builder.name;
        this.setNameUC();
        this.setNamespace();
        this.role = builder.role;
        this.fhirIp = builder.fhirIp;
        this.fhirProxyPassIp = builder.fhirProxyPassIp;
        this.fhirPort = builder.fhirPort;
        this.fhirFrontendSubnet = builder.fhirFrontendSubnet;
        this.fhirOIDCSecret = builder.fhirOIDCSecret;
        this.bpeIp = builder.bpeIp;
        this.bpeProxyPassIp = builder.bpeProxyPassIp;
        this.bpePort = builder.bpePort;
        this.bpeFrontendSubnet = builder.bpeFrontendSubnet;
        this.bpeOIDCSecret = builder.bpeOIDCSecret;
    }

    public String getNameUC() {
        return nameUC;
    }

    private void setNameUC() {
        this.nameUC = this.name.toUpperCase(Locale.ENGLISH);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.setNameUC();
        this.setNamespace();
    }

    private void setNamespace() {
        this.namespace = this.name.replaceAll("-", "_").toLowerCase();
    }

    public String getNamespace() {
        return this.namespace;
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

    public String getFhirOIDCSecret() {
        return fhirOIDCSecret;
    }

    public void setFhirOIDCSecret(String fhirOIDCSecret) {
        this.fhirOIDCSecret = fhirOIDCSecret;
    }

    public String getBpeOIDCSecret() {
        return bpeOIDCSecret;
    }

    public void setBpeOIDCSecret(String bpeOIDCSecret) {
        this.bpeOIDCSecret = bpeOIDCSecret;
    }

    public static class Builder {

        private String name;
        private String nameUC;
        private DsfOrganizationRole role;
        private String fhirIp;
        private String fhirProxyPassIp;
        private Integer fhirPort;
        private String fhirFrontendSubnet;
        private String fhirOIDCSecret;
        private String bpeIp;
        private String bpeProxyPassIp;
        private Integer bpePort;
        private String bpeFrontendSubnet;
        private String bpeOIDCSecret;


        public Builder name(String name) {
            this.name = name;
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

        public Builder fhirOIDCSecret(String fhirOIDCSecret) {
            this.fhirOIDCSecret = fhirOIDCSecret;
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

        public Builder bpeOIDCSecret(String bpeOIDCSecret) {
            this.bpeOIDCSecret = bpeOIDCSecret;
            return this;
        }

        public Builder generateValidSettings(NetworkHandler networkHandler) throws IOException {
            generateSecrets();
            setValidNetworkSettings(networkHandler);
            return this;
        }

        private void generateSecrets() {
            PasswordGenerator secretGenerator = new PasswordGenerator();
            fhirOIDCSecret = secretGenerator.generateSecret();
            bpeOIDCSecret = secretGenerator.generateSecret();
        }

        private void setValidNetworkSettings(NetworkHandler networkHandler) throws IOException {
            fhirIp = networkHandler.getAndRemoveValidIp();
            bpeIp = networkHandler.getAndRemoveValidIp();
            fhirProxyPassIp = networkHandler.getAndRemoveValidIp();
            bpeProxyPassIp = networkHandler.getAndRemoveValidIp();

            fhirFrontendSubnet = networkHandler.getAndRemoveValidIp() + "/28";
            bpeFrontendSubnet = networkHandler.getAndRemoveValidIp() + "/28";

            fhirPort = networkHandler.findFreePortInRange();
            bpePort = networkHandler.findFreePortInRange();
        }


        public DsfOrganizationDTO build() {
            return new DsfOrganizationDTO(this);
        }
    }
}
