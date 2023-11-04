package com.example.hack1.Repository;

import com.example.hack1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer >{
    public User getUserByEmail(String email);
    public User getUserByUserId(Integer id);


}
