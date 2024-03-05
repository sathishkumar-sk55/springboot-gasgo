package com.frost.gasgo.userhub.repository;

import com.frost.gasgo.userhub.entity.ContactData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactDataRepository extends JpaRepository<ContactData, Long> {


    @Query(value = "SELECT * \n" +
            "FROM userhub.contact_data\n" +
            "WHERE user_id = :userId", nativeQuery = true)
    List<ContactData> findAllByUserId(long userId);

}
