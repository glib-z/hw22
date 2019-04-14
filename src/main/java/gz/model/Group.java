package gz.model;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private int id = 0;
    private String name;
    private String startDate; // timestamp
    private List<Student> students = new ArrayList<>();

    public Group(String name, String startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < students.size(); i++) {
            sb.append(students.get(i).getName());
            if (i < (students.size() - 1)) {
                sb.append(", ");
            }
        }
        sb.append("]");
        String stringStudents = sb.toString();
        return "Group{" +
                "name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", students=" + stringStudents +
                '}';
    }
}
