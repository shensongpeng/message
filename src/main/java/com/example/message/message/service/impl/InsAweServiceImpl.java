package com.example.message.message.service.impl;/*
 *
 * @ClassName AweServiceImpl
 * @Author shensongpeng
 * @Date 2020/12/28 :15:31
 * @Version 1.0
 * */

import com.example.message.message.dataobject.InsAwesome;
import com.example.message.message.repository.InsAwesomeRepository;
import com.example.message.message.service.InsAweService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsAweServiceImpl implements InsAweService {
    final InsAwesomeRepository insAwesomeRepository;

    public InsAweServiceImpl(InsAwesomeRepository insAwesomeRepository) {
        this.insAwesomeRepository = insAwesomeRepository;
    }

    @Override
    public InsAwesome save(InsAwesome insAwesome) {
        return insAwesomeRepository.save(insAwesome);
    }

    @Override
    public Optional<InsAwesome> findOne(Example example) {

        return insAwesomeRepository.findOne(example);
    }

    @Override
    public List<InsAwesome> findByMessageIDInAndUserId(List<Integer> messageIdList, Integer userId) {
        return null;
    }

    @Override
    public InsAwesome create(InsAwesome insAwesome) {
        return null;
    }
}
