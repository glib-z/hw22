package gz;

import gz.model.Group;
import gz.model.Student;
import gz.utills.UserDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Group> groups = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        // Creating groups
        groups.add(new Group("Java", "2019-Jan-22"));
        groups.add(new Group("Pyton", "2019-Feb-03"));
        groups.add(new Group("Web", "2019-Mar-01"));

        // Creating students
        students.add(new Student("Alex", 19));
        students.add(new Student("Den", 20));
        students.add(new Student("Ivan", 18));
        students.add(new Student("Pedro", 22));
        students.add(new Student("Maria", 21));
        students.add(new Student("Katie", 20));
        students.add(new Student("Jasper", 19));

        // Student1 -> java
        groups.get(0).getStudents().add(students.get(0));
        students.get(0).getGroups().add(groups.get(0));

        // Student2 -> java, pyton
        groups.get(0).getStudents().add(students.get(1));
        students.get(1).getGroups().add(groups.get(0));
        groups.get(1).getStudents().add(students.get(1));
        students.get(1).getGroups().add(groups.get(1));

        // Student3 -> java, web
        groups.get(0).getStudents().add(students.get(2));
        students.get(2).getGroups().add(groups.get(0));
        groups.get(2).getStudents().add(students.get(2));
        students.get(2).getGroups().add(groups.get(2));

        // Student4 -> lazy boy

        // Student5 -> pyton
        groups.get(1).getStudents().add(students.get(4));
        students.get(4).getGroups().add(groups.get(1));

        // Student6 -> pyton
        groups.get(1).getStudents().add(students.get(5));
        students.get(5).getGroups().add(groups.get(1));

        // Student7 -> pyton, web
        groups.get(1).getStudents().add(students.get(6));
        students.get(6).getGroups().add(groups.get(1));
        groups.get(2).getStudents().add(students.get(6));
        students.get(6).getGroups().add(groups.get(2));

        printData();

        try {
            UserDao userDao = new UserDao();
            userDao.clean();

            for (Student student : students) {
                userDao.insertStudent(student);
            }

            for (Group group : groups) {
                userDao.insertGroup(group);
            }

            for (Group group : groups) {
                for (Student student : students) {
                    for (int k = 0; k < student.getGroups().size(); k++) {
                        if (student.getGroups().get(k).getId() == group.getId()) {
                            userDao.insertGrpStd(group.getId(), student.getId());
                        }
                    }
                }
            }


            String grpName = "Pyton";
            List<Student> st = userDao.getGroupInfo(grpName);
            System.out.print("\n" + grpName + " group members: ");
            for (int i = 0; i < st.size(); i++) {
                System.out.print(st.get(i).getName());
                if (i < (st.size() - 1)) {
                    System.out.print(", ");
                }
            }
            System.out.println();


            String stdName = "Den";
            List<Group> gr = userDao.getStudentInfo(stdName);
            System.out.print("\n" + stdName + "\'s groups: ");
            for (int i = 0; i < gr.size(); i++) {
                System.out.print(gr.get(i).getName());
                if (i < (st.size() - 1)) {
                    System.out.print(", ");
                }
            }
            System.out.println();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private static void printData(){
        System.out.println("\nInformation about groups...");
        for (Group group : groups) {
            System.out.println(group);
        }
        System.out.println("\nInformation about students...");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

}
