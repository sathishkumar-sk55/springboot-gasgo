package com.frost.gasgo.userhub.repository;

import com.frost.gasgo.userhub.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {


    UserData findByUsername(String username);
}
