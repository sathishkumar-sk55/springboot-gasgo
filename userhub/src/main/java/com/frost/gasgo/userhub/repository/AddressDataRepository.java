package com.frost.gasgo.userhub.repository;

import com.frost.gasgo.userhub.entity.AddressData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AddressDataRepository extends JpaRepository<AddressData, Long> {
    @Query(value = "SELECT * \n" +
            "FROM userhub.address_data\n" +
            "WHERE user_id = :userId", nativeQuery = true)
    List<AddressData> findAllByUserId(long userId);
}
