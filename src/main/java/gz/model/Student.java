package gz.model;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private int id = 0;
    private String name;
    private int age;
    private List<Group> groups = new ArrayList<>();

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public List<Group> getGroups() {
        return groups;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < groups.size(); i++) {
            sb.append(groups.get(i).getName());
            if (i < (groups.size() - 1)) {
                sb.append(", ");
            }
        }
        sb.append("]");
        String stringGroups = sb.toString();
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", groups=" + stringGroups +
                '}';
    }
}
