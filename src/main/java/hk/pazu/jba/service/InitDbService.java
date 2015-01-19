package hk.pazu.jba.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import hk.pazu.jba.entity.Blog;
import hk.pazu.jba.entity.Item;
import hk.pazu.jba.entity.Role;
import hk.pazu.jba.entity.User;
import hk.pazu.jba.repository.BlogRepository;
import hk.pazu.jba.repository.ItemRepository;
import hk.pazu.jba.repository.RoleRepository;
import hk.pazu.jba.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	public void init() {
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		User userAdmin = new User();
		userAdmin.setName("admin");
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);

		Blog blogPazu = new Blog();
		blogPazu.setName("Pazu");
		blogPazu.setUrl("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js");
		blogPazu.setUser(userAdmin);
		blogRepository.save(blogPazu);

		Item item1 = new Item();
		item1.setBlog(blogPazu);
		item1.setTitle("first");
		item1.setLink("https://maxcdn.bootstrapcdn.com/");
		item1.setPublishedDate(new Date());
		itemRepository.save(item1);
		
		Item item2 = new Item();
		item2.setBlog(blogPazu);
		item2.setTitle("second");
		item2.setLink("https://maxcdn.bootstrapcdn.com/");
		item2.setPublishedDate(new Date());
		itemRepository.save(item2);
	}
}
