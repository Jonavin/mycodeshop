package codeshop.xfire.service;

import codeshop.xfire.domain.User;

public interface UserService {

	public int addUser(User user);
	
	public int updateUser(User user);
	
	public int deleteUser(Long id);
	
	public User queryUser(Long id);
	
}
