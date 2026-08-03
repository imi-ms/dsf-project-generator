package de.unimuenster.imi.medic.dsf.shared;

public enum DsfVersion {
    V_2_0_1,
    V_2_0_2,
    V_2_1_0;

    public String getVersion() {
        if (this == DsfVersion.V_2_0_1) {
            return "2.0.1";
        }
        if (this == DsfVersion.V_2_0_2) {
            return "2.0.2";
        }
        if (this == DsfVersion.V_2_1_0) {
            return "2.1.0";
        }
        return "2.0.1";
    }

    public static DsfVersion getLatestVersion() {
        return DsfVersion.values()[DsfVersion.values().length - 1];
    }
}
