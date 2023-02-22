package com.study.soju.service;

import com.study.soju.entity.Member;
import com.study.soju.entity.Pay;
import com.study.soju.entity.PayRequest;
import com.study.soju.entity.Store;
import com.study.soju.repository.MemberRepository;
import com.study.soju.repository.PayRepository;
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
    @Autowired
    PayRepository payRepository;

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

    public void insertPay(Pay requestPay){
        //결제가 완료 되었으면 0로 저장
        requestPay.setIsPaid(0);
        payRepository.save(requestPay);
    }

    public List<Pay> findPay(String emailId){
        List<Pay> payList = payRepository.findByBuyerEmail(emailId);
        return payList;
    }

    public Pay refundCheck(String impUid){
        Pay resPay = payRepository.findByImpUid(impUid);
        return resPay;
    }

    public void updatePay(Pay pay){
        //결제 완료 된것을 환불로 변경하기 위해서 1로 고정
        pay.setIsPaid(1);
        payRepository.save(pay);
    }

}