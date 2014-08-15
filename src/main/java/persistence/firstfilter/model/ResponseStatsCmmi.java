package persistence.firstfilter.model;

/**
 * Created by evan on 15/08/14.
 */
public class ResponseStatsCmmi {

    private int process;
    private int specificGoal;
    private int specificPractice;
    private int workProduct;

    public ResponseStatsCmmi() {

    }

    public ResponseStatsCmmi(int process, int specificGoal, int specificPractice, int workProduct) {
        this.process = process;
        this.specificGoal = specificGoal;
        this.specificPractice = specificPractice;
        this.workProduct = workProduct;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public int getSpecificGoal() {
        return specificGoal;
    }

    public void setSpecificGoal(int specificGoal) {
        this.specificGoal = specificGoal;
    }

    public int getSpecificPractice() {
        return specificPractice;
    }

    public void setSpecificPractice(int specificPractice) {
        this.specificPractice = specificPractice;
    }

    public int getWorkProduct() {
        return workProduct;
    }

    public void setWorkProduct(int workProduct) {
        this.workProduct = workProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseStatsCmmi that = (ResponseStatsCmmi) o;

        if (process != that.process) return false;
        if (specificGoal != that.specificGoal) return false;
        if (specificPractice != that.specificPractice) return false;
        if (workProduct != that.workProduct) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = process;
        result = 31 * result + specificGoal;
        result = 31 * result + specificPractice;
        result = 31 * result + workProduct;
        return result;
    }
}
