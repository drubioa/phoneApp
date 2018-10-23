package com.phone.service;

import com.phone.dao.PhoneDao;
import com.phone.domain.Phone;
import com.phone.model.PhoneDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PhoneService {

    @Autowired
    private PhoneDao phoneDao;

    /**
     * Obtains catalog with all phones
     *
     * @return
     */
    public List<PhoneDto> getPhoneCatalog(){
        List<Phone> phones = phoneDao.findAll();
        log.info("Obtains catalog with phones " + phones);
        return phonesToPhonesDto(phones);
    }

    private static List<PhoneDto> phonesToPhonesDto(List<Phone> phones){
        List<PhoneDto> phoneDtos = new ArrayList<>();
        phones.stream().forEach( p -> {
            PhoneDto phoneDto = new PhoneDto();
            phoneDto.setId(p.getId().toString());
            phoneDto.setName(p.getName());
            phoneDto.setPrice(p.getPrice().toString());
            phoneDto.setReference(p.getReference());
            phoneDtos.add(phoneDto);
        });
        return phoneDtos;
    }


}
