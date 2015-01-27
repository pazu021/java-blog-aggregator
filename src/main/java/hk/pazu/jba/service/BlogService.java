package hk.pazu.jba.service;

import hk.pazu.jba.entity.Blog;
import hk.pazu.jba.entity.User;
import hk.pazu.jba.repository.BlogRepository;
import hk.pazu.jba.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private UserRepository userRepository;

	public void save(Blog blog, String name) {
		User user = userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);

	}

	public void delete(int id) {
		blogRepository.delete(id);

	}

	public Blog findOne(int id) {
		return blogRepository.findOne(id);
	}

	@PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Blog blog) {
		blogRepository.delete(blog);
	}
}
