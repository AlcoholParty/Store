package com.study.soju.controller;

import com.study.soju.entity.Pay;
import com.study.soju.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    PayService payService;

    @GetMapping("")
    public String myPage(){
        return "MyPage/MyPage";
    }

    @GetMapping("/perchaselist")
    public String perchaseList(Principal principal, Model model){
        //Principal로 유저 emailId 가져오기
        String emailId = principal.getName();
        //유저 이메일로 모든 결제 내역 가져오기
        List<Pay> findPay = payService.findPay(emailId);
        //결제내역 바인딩
        model.addAttribute("payList", findPay);
        return "MyPage/PerchaseList";
    }

    @PostMapping("/perchaselist/refundcheck")
    @ResponseBody
    public String refundCheck(Pay pay){
        //환불 버튼을 눌렀을때 작업할 내용
        String res = "no";
        //가져온 impUid 로 환불할 결제 항목을 가져오기
        Pay resPay = payService.refundCheck(pay.getImpUid());
        if(resPay != null){
            //값이 잘 담겨있다면 내용을 환불진행중으로 바꾸기위해 업데이트
            payService.updatePay(resPay);
            //이후 yes 로 변경해서 값이 잘 변경 되었는지 알려주기
            res = "yes";
        }
        return res;
    }

}
