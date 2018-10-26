package com.phone.domain.repository;

import com.phone.domain.OrderPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderPhoneRepository extends JpaRepository<OrderPhone,UUID> {

}
