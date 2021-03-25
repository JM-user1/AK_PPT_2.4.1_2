package testfolder.dao;

import java.util.*;

import org.springframework.stereotype.Repository;
import testfolder.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserDaoImpl implements UserDao{



  @PersistenceContext(unitName = "entityManagerFactory")
  private EntityManager entityManager;

  @Override
  public List<User> allUser() {
    List<User> users = entityManager.createQuery("SELECT u from User u").getResultList();
    return users;
  }

  @Override
  @Transactional
  public void add(User user) {
    entityManager.persist(user);
  }

  @Override
  @Transactional
  public void delete(int id) {
    User user = entityManager.find(User.class, id);
    entityManager.remove(user);
  }

  @Override
  @Transactional
  public void edit(int id, User editedUser) {
    User updatedUser = getById(id);
    entityManager.detach(updatedUser);
    updatedUser.setFirstName(editedUser.getFirstName());
    updatedUser.setLastName(editedUser.getLastName());
    updatedUser.setAge(editedUser.getAge());
    entityManager.merge(updatedUser);
  }

  @Override
  public User getById(int id) {
    User user = entityManager.find(User.class, id);
    entityManager.detach(user);
    return user;
  }
}
