package wdsr.exercise5.models;

public class Faculty {
    private long pkey;
    private String name;

    public Faculty(String name) {
        this.name = name;
    }

    public long getPkey() {
        return pkey;
    }

    public void setPkey(long pkey) {
        this.pkey = pkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
