package it.torneodelleparrocchie.fantacalcio.enums;

import java.util.HashMap;
import java.util.Map;

public enum FormationModuleEnum {
    F_3_2_1("3-2-1"),
    F_3_1_2("3-1-2"),
    F_4_1_1("4-1-1"),
    F_2_3_1("2-3-1");

    Map<FormationRoleEnum, Integer> numberForRole;

    FormationModuleEnum(String value) {
        String[] formation = value.split("-");
        numberForRole = new HashMap<>();
        numberForRole.put(FormationRoleEnum.POR, 1);
        numberForRole.put(FormationRoleEnum.DIF, Integer.valueOf(formation[0]));
        numberForRole.put(FormationRoleEnum.CEN, Integer.valueOf(formation[1]));
        numberForRole.put(FormationRoleEnum.ATT, Integer.valueOf(formation[2]));
    }

    public Integer getNumberForRole(FormationRoleEnum role) {
        return numberForRole.get(role);
    }

}
