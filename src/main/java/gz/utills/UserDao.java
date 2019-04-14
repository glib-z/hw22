package gz.utills;

import gz.model.Group;
import gz.model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    private Connection connection;


    public UserDao() throws SQLException {
        String url = "jdbc:postgresql://127.0.0.1:5432/test?user=postgres&password=123456&ssl=false";
        connection = DriverManager.getConnection(url);
        createGroupsTable();
        createStudentsTable();
        createGrpStdTable();
    }


    private void createGroupsTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS groups (\n" +
                    "gr_id SERIAL PRIMARY KEY,\n" +
                    "name varchar(20),\n" +
                    "start_date varchar(20)\n" +
                    ");");
        }
    }


    private void createStudentsTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS students (\n" +
                    "st_id SERIAL PRIMARY KEY,\n" +
                    "name varchar(20),\n" +
                    "age int\n" +
                    ");");
        }
    }


    private void createGrpStdTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS grp_std (\n" +
                    "gr_id int,\n" +
                    "st_id int,\n" +
                    "PRIMARY KEY (gr_id, st_id)\n" +
                    ");");
        }
    }


    public void clean() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("DELETE FROM groups;");
            System.out.println("Deleted " + count + " rows from table groups");
        }

        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("DELETE FROM students;");
            System.out.println("Deleted " + count + " rows from table users");
        }

        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("DELETE FROM grp_std;");
            System.out.println("Deleted " + count + " rows from table grp_std");
        }
    }


    public void insertGroup(Group group) throws SQLException {
        try (Statement statement = connection.createStatement()) {

            String request = String.format("INSERT INTO groups (name, start_date) VALUES ('%s', '%s');", group.getName(), group.getStartDate());
            statement.execute(request);

            request = String.format("SELECT gr_id FROM groups WHERE name = '%s';", group.getName());
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                group.setId(resultSet.getInt("gr_id"));
            }

        }
    }


    public void insertStudent(Student student) throws SQLException {
        try (Statement statement = connection.createStatement()) {

            String request = String.format("INSERT INTO students (name, age) VALUES ('%s', '%d');", student.getName(), student.getAge());
            statement.execute(request);

            request = String.format("SELECT st_id FROM students WHERE name = '%s';", student.getName());
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                student.setId(resultSet.getInt("st_id"));
            }

        }
    }


    public void insertGrpStd(int gr_id, int st_id) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("INSERT INTO grp_std VALUES ('%d', '%d');", gr_id, st_id);
            statement.execute(request);
        }
    }


}
