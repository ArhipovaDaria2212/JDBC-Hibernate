package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService service = new UserServiceImpl();

        service.createUsersTable();

        service.saveUser("Гарри", "Поттер", (byte) 12);
        service.saveUser("Гермиона", "Грейнджер", (byte) 11);
        service.saveUser("Рон", "Уизли", (byte) 12);
        service.saveUser("Драко", "Малфой", (byte) 13);

        service.getAllUsers().forEach(System.out::println);
        service.cleanUsersTable();
        service.getAllUsers().forEach(System.out::println);

        service.dropUsersTable();
    }
}
