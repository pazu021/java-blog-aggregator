package hk.pazu.jba.service;

import hk.pazu.jba.entity.Blog;
import hk.pazu.jba.entity.Item;
import hk.pazu.jba.entity.Role;
import hk.pazu.jba.entity.User;
import hk.pazu.jba.repository.BlogRepository;
import hk.pazu.jba.repository.ItemRepository;
import hk.pazu.jba.repository.RoleRepository;
import hk.pazu.jba.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private RoleRepository roleRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	public User findOne(String username) {
		return userRepository.findByName(username);
	}

	@Transactional
	public User findOneWithBlogs(int id) {
		User user = userRepository.findOne(id);
		List<Blog> blogs = blogRepository.findByUser(user);
		for (Blog blog : blogs) {
			List<Item> items = itemRepository.findByBlog(blog, new PageRequest(
					0, 10, Direction.DESC, "publishedDate"));
			blog.setItems(items);
		}
		user.setBlogs(blogs);
		return user;
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		// System.out.println(user.getPassword());
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);

		userRepository.save(user);

	}

	public User findOneWithBlogs(String name) {
		User user = userRepository.findByName(name);
		return findOneWithBlogs(user.getId());

	}

	public void delete(int id) {
		userRepository.delete(id);
	}

}
