package testfolder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testfolder.dao.UserDao;
import testfolder.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

  @PersistenceContext(unitName = "entityManagerFactory")
  private EntityManager entityManager;
  private final UserDao userDao;

  @Autowired
  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  @Transactional
  public List<User> allUser() {
    return userDao.allUser();
  }

  @Override
  @Transactional
  public void add(User user) {
    userDao.add(user);
  }

  @Override
  public void delete(int id) {
    userDao.delete(id);
  }

  @Override
  @Transactional
  public void edit(int id, User user){
    User updatedUser = getById(id);
    updatedUser.setFirstName(user.getFirstName());
    updatedUser.setLastName(user.getLastName());
    updatedUser.setAge(user.getAge());
    entityManager.merge(updatedUser);
  }



  @Override
  public User getById(int id) {
    return userDao.getById(id);
  }
}
