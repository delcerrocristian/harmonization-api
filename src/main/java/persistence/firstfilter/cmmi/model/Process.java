package persistence.firstfilter.cmmi.model;

/**
 * Created by Cristian del Cerro
 */
public class Process {

    private int id;
    private String name;
    private String areaCategory;
    private String maturityLevel;
    private String purposeStatement;
    private int standard;

    public Process() {

    }

    public Process(int id, String name, String areaCategory, String maturityLevel,
                   String purposeStatement, int standard) {
        this.id = id;
        this.name = name;
        this.areaCategory = areaCategory;
        this.maturityLevel = maturityLevel;
        this.purposeStatement = purposeStatement;
        this.standard = standard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaturityLevel() {
        return maturityLevel;
    }

    public void setMaturityLevel(String maturityLevel) {
        this.maturityLevel = maturityLevel;
    }

    public String getPurposeStatement() {
        return purposeStatement;
    }

    public void setPurposeStatement(String purposeStatement) {
        this.purposeStatement = purposeStatement;
    }

    public String getAreaCategory() {
        return areaCategory;
    }

    public void setAreaCategory(String areaCategory) {
        this.areaCategory = areaCategory;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Process process = (Process) o;

        if (id != process.id) return false;
        if (standard != process.standard) return false;
        if (areaCategory != null ? !areaCategory.equals(process.areaCategory) : process.areaCategory != null)
            return false;
        if (maturityLevel != null ? !maturityLevel.equals(process.maturityLevel) : process.maturityLevel != null)
            return false;
        if (name != null ? !name.equals(process.name) : process.name != null) return false;
        if (purposeStatement != null ? !purposeStatement.equals(process.purposeStatement) : process.purposeStatement != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (maturityLevel != null ? maturityLevel.hashCode() : 0);
        result = 31 * result + (areaCategory != null ? areaCategory.hashCode() : 0);
        result = 31 * result + (purposeStatement != null ? purposeStatement.hashCode() : 0);
        result = 31 * result + standard;
        return result;
    }
}
