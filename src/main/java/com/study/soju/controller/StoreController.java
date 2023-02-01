package com.study.soju.controller;

import com.study.soju.entity.Member;
import com.study.soju.entity.Pay;
import com.study.soju.entity.PayRequest;
import com.study.soju.entity.Store;
import com.study.soju.repository.MemberRepository;
import com.study.soju.repository.StoreRepository;
import com.study.soju.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/store")
public class StoreController {
    @Autowired
    PayService payService;
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("")
    public String store(){
        return"Store/Store";
    }

    @GetMapping("/membershiplist")
    public String membershipList(Model model, Principal principal){
        //결제를 하려는 멤버객체를 불러온다.
        String emailId = principal.getName();
        Member member = payService.findAll(emailId);
        model.addAttribute("member", member);
        //카테고리 별로 나눠서 검색
        //파라미터로 membership을 넘겨줘서
        //카테고리가 멤버쉽인 모든 객체들을 리스트로 반환
        List<Store> memberShipList = payService.findList("membership");
        model.addAttribute("categoryList", memberShipList);
        return "Store/MemberShipList";
    }

    @GetMapping("/booklist")
    public String bookList(Model model, Principal principal){
        String emailId = principal.getName();
        Member member = payService.findAll(emailId);
        model.addAttribute("member", member);
        List<Store> bookList = payService.findList("book");
        model.addAttribute("categoryList", bookList);
        return "Store/BookList";
    }

    @GetMapping("/etclist")
    public String etcList(Model model, Principal principal){
        String emailId = principal.getName();
        Member member = payService.findAll(emailId);
        model.addAttribute("member", member);
        List<Store> etcList = payService.findList("etc");
        model.addAttribute("categoryList", etcList);
        return "Store/EtcList";
    }

    @GetMapping("/pay")
    public String pay(Store store, Model model){
        //스토어에 아이템네임 값으로 조회 해와서 하면 되겠다
        System.out.println(store.getItemName());
        Store payItem = payService.findStore(store.getItemName());
        model.addAttribute("item", payItem);
        return "Store/Pay";
    }

    @GetMapping("/purchase")
    public String popupPay(Store store, Pay pay, Model model, Principal principal){
        int itemCount = pay.getItemCount();
        model.addAttribute("itemCount", itemCount);
        Store payItem = payService.findStore(store.getItemName());
        model.addAttribute("item", payItem);
        String emailId = principal.getName();
        Member member = payService.findAll(emailId);
        model.addAttribute("member", member);
        return "Store/PayPopup";
    }

    @RequestMapping(value = {"/complete"}, method = {RequestMethod.POST})
    @ResponseBody
    public String payComplete(Pay pay){
        String res = "no";
        if(pay.getImpUid() != null && pay.getMerchantUid() != null){
            res = "yes";
        }
        return res;
    }
}
