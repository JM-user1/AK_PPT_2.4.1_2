package testfolder.service;

import java.util.List;

import testfolder.model.User;

public interface UserService {
  List<User> allUser();
  void add(User user);
  void delete(int id);
  void edit(int id, User user);
  User getById(int id);

}
