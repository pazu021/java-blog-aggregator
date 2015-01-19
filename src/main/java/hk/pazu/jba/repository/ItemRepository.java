package hk.pazu.jba.repository;

import java.util.List;

import hk.pazu.jba.entity.Blog;
import hk.pazu.jba.entity.Item;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByBlog(Blog blog,  Pageable pageable);

}
