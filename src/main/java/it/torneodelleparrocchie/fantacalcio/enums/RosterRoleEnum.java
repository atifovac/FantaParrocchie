package it.torneodelleparrocchie.fantacalcio.enums;

public enum RosterRoleEnum {

    POR("PORTIERE", 1),
    DIF("DIFENSORE", 5),
    CEN("CENTROCAMPISTA", 3),
    ATT("ATTACCANTE", 3),
    EXT("EXTRACOMUNITARIO", 1);

    private String extended;
    private int maxInRoster;

    RosterRoleEnum(String extended, int maxInRoster) {
        this.extended = extended;
        this.maxInRoster = maxInRoster;
    }

    public int getMaxInRoster() {
        return maxInRoster;
    }

    public String getExtended() {
        return extended;
    }
}
