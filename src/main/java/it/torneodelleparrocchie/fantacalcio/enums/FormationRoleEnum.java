package it.torneodelleparrocchie.fantacalcio.enums;

public enum FormationRoleEnum {

    POR("PORTIERE"),
    DIF("DIFENSORE"),
    CEN("CENTROCAMPISTA"),
    ATT("ATTACCANTE");

    private String extended;

    FormationRoleEnum(String extended) {
        this.extended = extended;
    }

    public String getExtended() {
        return extended;
    }
}
