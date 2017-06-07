package wdsr.exercise5.models;

public class Enrollment {

    private long fkey_student;
    private long fkey_class;

    public Enrollment(long fkey_student, long fkey_class) {
        this.fkey_student = fkey_student;
        this.fkey_class = fkey_class;
    }

    public long getFkey_student() {
        return fkey_student;
    }

    public void setFkey_student(long fkey_student) {
        this.fkey_student = fkey_student;
    }

    public long getFkey_class() {
        return fkey_class;
    }

    public void setFkey_class(long fkey_class) {
        this.fkey_class = fkey_class;
    }

}
