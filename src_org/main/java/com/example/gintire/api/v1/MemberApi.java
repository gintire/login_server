package com.example.gintire.api.v1;

import com.example.gintire.member.domain.user.exception.InvalidUserException;
import com.example.gintire.member.dto.MemberDto;
import com.example.gintire.member.service.JwtUserService;
import com.example.gintire.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class MemberApi {
    @Autowired
    private JwtUserService jwtUserService;
    @Autowired
    private MemberService memberService;

    @GetMapping("")
    public List<MemberDto> list() {
        return memberService.findAll()
                .stream().map(MemberDto::of).collect(Collectors.toList());
    }

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid MemberDto memberDto) {
        memberService.save(memberDto);
    }

    @GetMapping("/{username}")
    public MemberDto detail(@PathVariable String useremail,
                            @RequestHeader(value="Authorization") String authorization) {
        if(jwtUserService.isValidUser(authorization, useremail)) {
            return MemberDto.of(memberService.findOne(useremail));
        } else {
            throw new InvalidUserException();
        }
    }

    @PutMapping("/{username}")
    public void modify(@PathVariable String useremail,
                       @RequestBody MemberDto memberDto,
                       @RequestHeader(value = "Authorization") String authorization) {
        if (useremail.equals(memberDto.getEmail()) && jwtUserService.isValidUser(authorization, useremail)) {
            memberDto.setId(memberService.findOne(useremail).getId());
            memberService.update(memberDto);
        } else {
            throw new InvalidUserException();
        }
    }
}
