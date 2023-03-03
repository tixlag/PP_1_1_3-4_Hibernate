package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();

        service.createUsersTable();

        service.saveUser("Вася", "Васин", (byte) 25);

        service.saveUser("Петя", "Петин", (byte) 24);
        service.saveUser("Маша", "Машина", (byte) 22);
        service.saveUser("Оля", "Олина", (byte) 26);

        System.out.println(service.getAllUsers());

        service.cleanUsersTable();
//        service.dropUsersTable();
    }
}
