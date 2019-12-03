package com.example.gintire.member.controller;

import com.example.gintire.member.dto.MemberDto;
import com.example.gintire.member.dto.exception.CustomValidationException;
import com.example.gintire.member.service.MemberService;
import com.example.gintire.member.service.SessionUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    SessionUserService sessionUserService;

    @Autowired
    MemberService memberService;

    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    @GetMapping("/user")
    public String list(Model model) {
        List<MemberDto> memberList = memberService.findAll()
                .stream().map(MemberDto::of).collect(Collectors.toList());
        model.addAttribute("list", memberList);
        return "member/list";
    }

    @PreAuthorize("#id==principal.id")
    @PutMapping("/user/{id}")
    public String modify(@PathVariable Long id, MemberDto memberDto) {
        sessionUserService.applyAuthToCtxHolder(memberService.update(memberDto));
        return "redirect:/user";
    }
    @GetMapping("/signup")
    public String signup(MemberDto memberDto) {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberDto memberDto, BindingResult binding) {
        if (binding.hasErrors()) {
            return "user/signup";
        }
        try {
            sessionUserService.applyAuthToCtxHolder(memberService.save(memberDto));
        } catch (CustomValidationException e) {
            binding.addError(e.getError());
            return "user/signup";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
}
