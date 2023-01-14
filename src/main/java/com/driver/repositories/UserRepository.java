package com.driver.repositories;

import com.driver.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    com.driver.models.User findByUsername(String username);



}
