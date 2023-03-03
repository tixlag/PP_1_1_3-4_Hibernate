package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }
    @Override
    public void createUsersTable() {
        try(Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(10)," +
                    "lastName VARCHAR(15)," +
                    "age INT," +
                    "PRIMARY KEY(id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT users(name, last_name, age) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.execute();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM users WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> res = new ArrayList<>();
        try(Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                res.add(new User(rs.getString("name"), rs.getString("last_name"), rs.getByte("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void cleanUsersTable() {
        try(Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
