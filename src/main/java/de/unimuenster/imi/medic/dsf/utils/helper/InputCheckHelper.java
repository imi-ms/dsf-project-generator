package de.unimuenster.imi.medic.dsf.utils.helper;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class InputCheckHelper {

    public static String checkIfDomainExists(String projectOrganization) {
        try {
            InetAddress.getByName(projectOrganization);
            return projectOrganization.toLowerCase();
        } catch (UnknownHostException e) {
            return "dsf.dev";
        }
    }

    public static String checkIfValidProjectName(String projectName) {
        if (Pattern.matches("[a-zA-Z-]+", projectName)) {
            return projectName.toLowerCase();
        }
        return projectName.replaceAll("[^a-zA-Z-]", "").toLowerCase();
    }

    public static String checkIfValidOrganizationName(String organizationName) {
        return organizationName.replaceAll("[^a-zA-Z-.]", "").toLowerCase();
    }
}
