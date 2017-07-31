package hibernate4.data;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hibernate4.domain.User;
@Repository
//or error: No Session found for current thread
//usually added on service classes
@Transactional
public class HibernateUserRepository implements UserRepository{

	@Autowired
	private SessionFactory sessionFactory;
	
	//no @Autowired or @Inject here, as it is supposed to created during runtime!
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public User save(User user) {
		Serializable id = session().save(user);
		user.setId((Long)id);
		return user;
	}
	
	@Override
	public User findById(long id){
		return (User) session().get(User.class, id);
	}
	
	@Override
	public User findByUsername(String username) {
		return (User) session().
			createCriteria(User.class)
			.add(Restrictions.eq("username", username))
			.list()
			.get(0);
	}

	@Override
	public void deleteByUsername(String username) {
		session().delete(findByUsername(username));
	}
	
}
