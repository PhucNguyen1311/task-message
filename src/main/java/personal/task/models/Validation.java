package personal.task.models;

public class Validation {
    private Status status;

    private Count count;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Count getCount() {
        return count;
    }

    public void setCount(Count count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Validation{" +
                "status=" + status +
                ", count=" + count +
                '}';
    }
}
