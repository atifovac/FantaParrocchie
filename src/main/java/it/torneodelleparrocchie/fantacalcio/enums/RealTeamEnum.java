package it.torneodelleparrocchie.fantacalcio.enums;


public enum RealTeamEnum {
    CARMELO_C("Carmelo C"),
    SACRO_CUORE_STOMAURO("Sacro Cuore Stomauro"),
    SAN_FRANCESCO("San Francesco"),
    SACRO_CUORE("Sacro Cuore"),
    SAN_PIETRO("San Pietro"),
    SAN_TIMOTEO("San Timoteo"),
    CARMELO_B("Carmelo B"),
    NOLIMITS("NoLimits"),
    SVINCOLATO("Svincolato");

    private String value;

    RealTeamEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
