package de.unimuenster.imi.medic.dsf.shared;

import java.util.Locale;
import java.util.UUID;
import de.unimuenster.imi.medic.dsf.utils.helper.PasswordGeneratorHelper;

public class DsfOrganizationDTO {

    private String name;
    private String nameUC;
    private String namespace;
    private DsfOrganizationRole role;
    private String orgaDsfVersion;
    private String fhirOIDCSecret;
    private String bpeOIDCSecret;
    private IpConfigDTO ipConfig;
    private final KeycloakSettingsDTO keycloakSettings;
    private final String organizationId;
    private final String organizationAffiliationId;
    private final String endpointId;

    public DsfOrganizationDTO(Builder builder) {
        this.name = builder.name;
        this.setNameUC();
        this.setNamespace();
        this.role = builder.role;
        this.orgaDsfVersion = builder.dsfVersion;
        this.ipConfig = builder.ipConfig;
        this.fhirOIDCSecret = builder.fhirOIDCSecret;
        this.bpeOIDCSecret = builder.bpeOIDCSecret;
        this.keycloakSettings = new KeycloakSettingsDTO();
        this.organizationId = UUID.randomUUID().toString();
        this.organizationAffiliationId = UUID.randomUUID().toString();
        this.endpointId = UUID.randomUUID().toString();
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

    public String getNamespaceDash() {
        return this.namespace.replaceAll("_", "-");
    }

    public DsfOrganizationRole getRole() {
        return this.role;
    }

    public void setRole(DsfOrganizationRole role) {
        this.role = role;
    }

    public String getOrgaDsfVersion() {
        return this.orgaDsfVersion;
    }

    public void setOrgaDsfVersion(String orgaDsfVersion) {
        this.orgaDsfVersion = orgaDsfVersion;
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

    public KeycloakSettingsDTO getKeycloakSettings() {
        return this.keycloakSettings;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public String getOrganizationAffiliationId() {
        return organizationAffiliationId;
    }

    public String getEndpointId() {
        return endpointId;
    }



    public static class Builder {

        private String name;
        private DsfOrganizationRole role;
        private String fhirOIDCSecret;
        private IpConfigDTO ipConfig;
        private String dsfVersion;
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
            PasswordGeneratorHelper secretGenerator = new PasswordGeneratorHelper();
            this.fhirOIDCSecret = secretGenerator.generateSecret();
            this.bpeOIDCSecret = secretGenerator.generateSecret();
        }

        public DsfOrganizationDTO build() {
            return new DsfOrganizationDTO(this);
        }

        public Builder dsfVersion(DsfVersion version) {
            this.dsfVersion = version.getVersion();
            return this;
        }
    }
}