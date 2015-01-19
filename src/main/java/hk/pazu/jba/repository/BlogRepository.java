package hk.pazu.jba.repository;

import java.util.List;

import hk.pazu.jba.entity.Blog;
import hk.pazu.jba.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findByUser(User user);

}
