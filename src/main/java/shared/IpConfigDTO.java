package shared;

public class IpConfigDTO {
    private final String fhirProxyIp;
    private final String bpeProxyIp;
    private final String fhirFrontendIp;
    private final String bpeFrontendIp;
    private final String fhirSubnetIp;
    private final String bpeSubnetIp;
    private final String fhirPort;
    private final String bpePort;

    public IpConfigDTO(String fhirProxyIp, String bpeProxyIp, String fhirFrontendIp, String bpeFrontendIp,
                       String fhirSubnetIp, String bpeSubnetIp, String fhirPort, String bpePort) {
        this.fhirProxyIp = fhirProxyIp;
        this.bpeProxyIp = bpeProxyIp;
        this.fhirFrontendIp = fhirFrontendIp;
        this.bpeFrontendIp = bpeFrontendIp;
        this.fhirSubnetIp = fhirSubnetIp;
        this.bpeSubnetIp = bpeSubnetIp;
        this.fhirPort = fhirPort;
        this.bpePort = bpePort;
    }

    public String getFhirFrontendIp() {
        return fhirFrontendIp;
    }

    public String getBpeFrontendIp() {
        return bpeFrontendIp;
    }

    public String getFhirProxyIp() {
        return fhirProxyIp;
    }

    public String getBpeProxyIp() {
        return bpeProxyIp;
    }

    public String getFhirSubnetIp() {
        return fhirSubnetIp;
    }

    public String getBpeSubnetIp() {
        return bpeSubnetIp;
    }

    public String getFhirPort() {
        return fhirPort;
    }

    public String getBpePort() {
        return bpePort;
    }
}
