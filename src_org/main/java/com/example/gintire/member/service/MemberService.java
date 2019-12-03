package com.example.gintire.member.service;

import com.example.gintire.member.domain.MemberVerifiable;
import com.example.gintire.member.domain.user.Member;
import com.example.gintire.member.domain.user.MemberRepository;
import com.example.gintire.member.dto.MemberDto;
import com.example.gintire.member.dto.exception.CustomValidationException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(useremail);
        if(member == null) {
            throw new UsernameNotFoundException(useremail);
        }
        return member;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member save(MemberDto dto) {
        duplicateCheckForEmail(dto);
        return memberRepository.save(dto.toEntity());

    }

    public Member findOne(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        return optionalMember.get();
    }

    public Member findOne(String useremail) {
        return memberRepository.findByEmail(useremail);
    }

    @Transactional
    public Member update(MemberDto dto) {
        Optional<Member> optionalMember = memberRepository.findById(dto.getId());
        Member member = optionalMember.get();
        if (!dto.getEmail().equals(member.getEmail())) {
            duplicateCheckForEmail(dto);
        }
        member.update(dto.toEntity());
        return member;
    }

    public void permissionCheck(String membername, MemberVerifiable entity) {
        if (!entity.verifyMember(membername)) {
            throw new AuthenticationCredentialsNotFoundException("권한이 없습니다");
        }
    }
    private void duplicateCheckForEmail(MemberDto dto) {
        if (memberRepository.findByEmail(dto.getEmail()) != null) {
            throw new CustomValidationException("memberDto", "email", "사용중인 이메일 입니다");
        }
    }
}
