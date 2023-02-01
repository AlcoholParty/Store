package com.study.soju.controller;

import com.study.soju.entity.Member;
import com.study.soju.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {
    // 회원가입 및 로그인 인증 서비스
    @Autowired
    SignUpService signUpService;

    // 비밀번호 암호화 메소드
    @Autowired
    PasswordEncoder passwordEncoder;

    // 로그인 진행 URL
    @PostMapping("/loginform/login")
    public void login(@RequestParam(value = "emailId") String emailId) { // 1. 파라미터로 로그인 할때 작성한 아이디를 받아온다.
        // 2. 서비스 파라미터로 로그인 할때 작성한 아이디를 넘겨준다.
        signUpService.loadUserByUsername(emailId);
        // 로그인 성공 및 실패 후 이동 페이지는 Spring Security가 각 핸들러를 통해 잡고 있기에 여기서 굳이 잡아줄 필요가 없다.
        //return "Main";
    }

    // 회원가입 페이지
    @GetMapping("/joinform")
    public String joinform(Model model) {
        // 0. Entity 대신 DTO를 사용하기 위해 사용할 DTO를 모델로 바인딩 한다.
        model.addAttribute("memberDto", new Member.rqJoinMember());
        return "SignUp/JoinForm";
    }

    // 회원가입 진행 URL
    @PostMapping("/joinform/join")
    public String join(Member.rqJoinMember rqJoinMember, Model model) { // 1. 파라미터로 form에서 넘어온 DTO를 받아온다.
        // 2. 서비스 파라미터로 MemberDTO와 비밀번호 암호화 메소드를 같이 넘겨준다.
        Member.rpJoinMember member = signUpService.joinMember(rqJoinMember, passwordEncoder);
        // 11. 반환받은 DTO를 모델로 바인딩 한다.
        model.addAttribute("member", member);
        return "SignUp/Welcome";
    }

    // 로그아웃 페이지
    @PostMapping("/logout")
    public String logout() {
        return "Main";
    }
}
