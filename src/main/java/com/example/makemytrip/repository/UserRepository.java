package com.example.makemytrip.repository;

import com.example.makemytrip.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUserid(Integer userid);
}
