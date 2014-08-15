package persistence.firstfilter.model;

/**
 * Created by evan on 15/08/14.
 */
public class ResponseStatsIso {

    private String name;
    private int process;
    private int activity;
    private int task;

    public ResponseStatsIso() {

    }

    public ResponseStatsIso(int process, int activity, int task) {
        this.process = process;
        this.activity = activity;
        this.task = task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseStatsIso that = (ResponseStatsIso) o;

        if (activity != that.activity) return false;
        if (process != that.process) return false;
        if (task != that.task) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + process;
        result = 31 * result + activity;
        result = 31 * result + task;
        return result;
    }
}
