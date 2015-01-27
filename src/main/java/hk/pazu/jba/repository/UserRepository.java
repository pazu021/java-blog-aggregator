package hk.pazu.jba.repository;

import hk.pazu.jba.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByName(String name);

}
