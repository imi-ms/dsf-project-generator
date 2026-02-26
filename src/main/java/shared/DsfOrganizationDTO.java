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
    private String fhirOIDCSecret;
    private String bpeOIDCSecret;
    private IpConfigDTO ipConfig;

    public DsfOrganizationDTO(Builder builder) {
        this.name = builder.name;
        this.setNameUC();
        this.setNamespace();
        this.role = builder.role;
        this.ipConfig = builder.ipConfig;
        this.fhirOIDCSecret = builder.fhirOIDCSecret;
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

    public IpConfigDTO getIpConfig() {
        return this.ipConfig;
    }

    public void setIpConfig(IpConfigDTO ipConfig) {
        this.ipConfig = ipConfig;
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
        private String fhirOIDCSecret;
        private IpConfigDTO ipConfig;
        private String bpeOIDCSecret;


        public Builder name(String name) {
            this.generateSecrets();
            this.name = name;
            return this;
        }

        public Builder role(DsfOrganizationRole role) {
            this.role = role;
            return this;
        }

        public Builder ipConfig(IpConfigDTO ipConfig) {
            this.ipConfig = ipConfig;
            return this;
        }

        public Builder fhirOIDCSecret(String fhirOIDCSecret) {
            this.fhirOIDCSecret = fhirOIDCSecret;
            return this;
        }

        public Builder bpeOIDCSecret(String bpeOIDCSecret) {
            this.bpeOIDCSecret = bpeOIDCSecret;
            return this;
        }

        private void generateSecrets() {
            PasswordGenerator secretGenerator = new PasswordGenerator();
            fhirOIDCSecret = secretGenerator.generateSecret();
            bpeOIDCSecret = secretGenerator.generateSecret();
        }

        public DsfOrganizationDTO build() {
            return new DsfOrganizationDTO(this);
        }
    }
}
