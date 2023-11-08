package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    void saveUser(String name, String surname, int age);
    void removeUser(int id);
    void editUser(int id, User user);
    User showId(int id);
}