package testfolder.dao;

import java.util.List;

import testfolder.model.User;

public interface UserDao {

  List<User> allUser();
  void add(User user);
  void delete(int id);
  void edit(int id, User user);
  User getById(int id);

}
