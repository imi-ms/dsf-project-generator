package de.unimuenster.imi.medic.dsf.utils;


import de.unimuenster.imi.medic.dsf.shared.IpConfigDTO;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

/**
 * Helper class to automatically get network configurations
 * for the Organization config.
 */
public class NetworkHandler {

    private static final Integer LOWER_BORDER = 1;
    private static final Integer UPPER_BORDER = 127;
    private static final Integer LOWER_PORT = 5000;
    private static final Integer UPPER_PORT = 20000;
    private final List<IpConfigDTO> ipConfigs = new ArrayList<>();
    private static Set<String> availableIPs;
    private static Set<Integer> usedPorts = new HashSet<>();

    public NetworkHandler() {
        this.generateValidIps();
    }

    /**
     * Generates a set of docker ips specified by the upper
     * and lower border.
     * Ips will all start with "172.20.0."
     */
    private void generateValidIps() {
        /*
        availableIPs = new HashSet<>();
        for (int i = LOWER_BORDER; i <= UPPER_BORDER; i++) {
            availableIPs.add("172.20.0." + i);
        }
        */

        this.ipConfigs.add(new IpConfigDTO("172.20.0.66", "172.20.0.114", "172.20.0.67", "172.20.0.115", "172.20.0.64", "172.20.0.112", "5000", "5003"));
        this.ipConfigs.add(new IpConfigDTO("172.20.0.82", "172.20.0.130", "172.20.0.83", "172.20.0.131", "172.20.0.80", "172.20.0.128", "5001", "5004"));
        this.ipConfigs.add(new IpConfigDTO("172.20.0.98", "172.20.0.146", "172.20.0.99", "172.20.0.147", "172.20.0.96", "172.20.0.144", "5002", "5005"));
        this.ipConfigs.add(new IpConfigDTO("172.20.0.188", "172.20.0.236", "172.20.0.189", "172.20.0.237", "172.20.0.176", "172.20.0.224", "5006", "5009"));
    }

    /**
     * Gets a random ip from the available set and
     * removes it to prevent future use in other
     * classes
     * @return String representation of the ip
     */
    public IpConfigDTO getAndRemoveValidIp() {
        /*
        String[] setArray = availableIPs.toArray(new String[0]);
        Random rndm = new Random();
        int rndmNumber = rndm.nextInt(availableIPs.size());

        String ipToUse = setArray[rndmNumber];
        availableIPs.remove(ipToUse);
        return ipToUse;

         */
        if (this.ipConfigs.isEmpty()) { return null; }
        return this.ipConfigs.removeFirst();
    }

    public int getIpCount() {
        return this.ipConfigs.size();
    }

    /**
     * Automatically finds a free port in the defined range of
     * LOWER_PORT to UPPER_PORT.
     * The function tries to create a new socket on the port.
     * This does not guarantee, that later on the ports will
     * still be available.
     * @return Available port
     * @throws IOException if no port is found
     */
    public int findFreePortInRange() throws IOException {
        for (int port = LOWER_PORT; port <= UPPER_PORT; port++) {
            if (!usedPorts.contains(port)) {
                try (ServerSocket ss = new ServerSocket(port)) {
                    ss.setReuseAddress(true);
                    usedPorts.add(port);
                    return port;
                } catch (Exception ignored) {
                }
            }
        }
        throw new IOException("No free port found in the specified range");
    }


}
