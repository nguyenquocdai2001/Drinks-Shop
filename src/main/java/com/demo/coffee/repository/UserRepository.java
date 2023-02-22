package com.demo.coffee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.demo.coffee.entity.User;


public interface UserRepository extends CrudRepository<User, Integer>{

	Optional<User> findByUsername(String username);
}
