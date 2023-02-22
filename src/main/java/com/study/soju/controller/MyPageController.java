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
        //구매내역 갖고와서 리스트로 뿌려주자
        String emailId = principal.getName();
        List<Pay> findPay = payService.findPay(emailId);
        model.addAttribute("payList", findPay);
        return "MyPage/PerchaseList";
    }

    @PostMapping("/perchaselist/refundcheck")
    @ResponseBody
    public String refundCheck(Pay pay){
        String res = "no";
        //이제 db 에서내용을 바꿔주고 관리자에게 연락이 가게 해야지 근데 그건 아직 방법을 못정해서 암튼 ㄱㄷ
        Pay resPay = payService.refundCheck(pay.getImpUid());
        if(resPay != null){
            payService.updatePay(resPay);
            //이제 관리자한테 연락만 가면 되는데
            //어케하지
            res = "yes";
        }
        return res;
    }

}
