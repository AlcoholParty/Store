package com.study.soju.service;

import com.study.soju.entity.Member;
import com.study.soju.entity.Store;
import com.study.soju.repository.MemberRepository;
import com.study.soju.repository.StoreRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class PayService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    StoreRepository storeRepository;
    //멤버 객체 정보 조회
    public Member findAll(String emailId){
        Member member = memberRepository.findByEmailId(emailId);
        return member;
    }

    //모든 상품 리스트로 끌어오기
    public List<Store> findList(String category){
        List<Store> list = storeRepository.findByCategory(category);
        return list;
    }

    public Store findStore(String itemName){
        Store payItem = storeRepository.findByItemName(itemName);
        return payItem;
    }


}