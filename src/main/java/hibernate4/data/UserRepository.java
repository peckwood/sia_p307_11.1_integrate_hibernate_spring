package hibernate4.data;

import hibernate4.domain.User;

public interface UserRepository {

	User save(User user);
  
	User findByUsername(String username);

	void deleteByUsername(String username);

	User findById(long id);
	
}
