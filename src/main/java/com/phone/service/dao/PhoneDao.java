package com.phone.service.dao;

import com.phone.domain.Phone;
import com.phone.domain.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PhoneDao {

    @Autowired
    PhoneRepository phoneRepository;

    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    public Phone findById(UUID phoneId) {
        return phoneRepository.getOne(phoneId);
    }

}
