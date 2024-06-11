package com.ak.db.mysqlTwo.repo;

import com.ak.db.mysqlTwo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
