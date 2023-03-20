package fr.esigelec.jee;

public class EquipementType {
    private String equipTypeCode;
    private String equipTypeLib;
    private String equipFamille;
    private String equipCategorie;
    public EquipementType(String equipTypeCode, String equipTypeLib, String equipFamille, String equipCategorie) {
        this.equipTypeCode = equipTypeCode;
        this.equipTypeLib = equipTypeLib;
        this.equipFamille = equipFamille;
        this.equipCategorie = equipCategorie;
    }

    public String getEquipTypeCode() {
        return equipTypeCode;
    }

    public void setEquipTypeCode(String equipTypeCode) {
        this.equipTypeCode = equipTypeCode;
    }

    public String getEquipTypeLib() {
        return equipTypeLib;
    }

    public void setEquipTypeLib(String equipTypeLib) {
        this.equipTypeLib = equipTypeLib;
    }

    public String getEquipFamille() {
        return equipFamille;
    }

    public void setEquipFamille(String equipFamille) {
        this.equipFamille = equipFamille;
    }

    public String getEquipCategorie() {
        return equipCategorie;
    }

    public void setEquipCategorie(String equipCategorie) {
        this.equipCategorie = equipCategorie;
    }
}
