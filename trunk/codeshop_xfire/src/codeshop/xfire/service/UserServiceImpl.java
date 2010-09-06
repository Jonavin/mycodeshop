package codeshop.xfire.service;

import codeshop.xfire.domain.User;

public class UserServiceImpl implements UserService {

	@Override
	public int addUser(User user) {
		System.out.println("addUser");
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		return 1;
	}

	@Override
	public int deleteUser(Long id) {
		System.out.println("deleteUser");
		System.out.println("id : " + id);
		return 1;
	}

	@Override
	public User queryUser(Long id) {
		System.out.println("queryUser");
		User user  = new User();
		user.setId(id);
		user.setUsername("name_" + id);
		user.setPassword("pwd_" + id);
		System.out.println("id : " + id);
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		return user;
	}

	@Override
	public int updateUser(User user) {
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		return 1;
	}

}
