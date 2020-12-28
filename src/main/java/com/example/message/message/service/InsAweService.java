package com.example.message.message.service;

import com.example.message.message.dataobject.InsAwesome;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface InsAweService {
    InsAwesome create(InsAwesome insAwesome);
    List<InsAwesome> findByMessageIDInAndUserId(List<Integer> messageIdList,Integer userId);
    Optional<InsAwesome> findOne(Example example);
    InsAwesome save(InsAwesome awesome);
}
