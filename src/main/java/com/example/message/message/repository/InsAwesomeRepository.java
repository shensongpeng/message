package com.example.message.message.repository;

import com.example.message.message.dataobject.InsAwesome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface InsAwesomeRepository extends JpaRepository<InsAwesome, Integer>, JpaSpecificationExecutor<InsAwesome> {
    List<InsAwesome> findAllByMessageIdInAndUserIdAndType(List<Integer> messageId, Integer userId, Integer type);
}