package com.study.soju.repository;

import com.study.soju.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepository extends JpaRepository<Pay, Object> {

}
