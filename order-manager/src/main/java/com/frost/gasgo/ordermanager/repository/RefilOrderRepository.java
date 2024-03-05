package com.frost.gasgo.ordermanager.repository;

import com.frost.gasgo.ordermanager.entity.RefilOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RefilOrderRepository extends JpaRepository<RefilOrder, Long> {
}
