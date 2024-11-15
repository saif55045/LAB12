import java.sql.Timestamp;

public class Student {
    private final int id;
    private final String name;
    private final String major;
    private final Timestamp timestamp;

    public Student(int id, String name, String major, Timestamp timestamp) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.timestamp = timestamp;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getMajor() { return major; }
    public Timestamp getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', major='" + major + "', timestamp=" + timestamp + '}';
    }
}
