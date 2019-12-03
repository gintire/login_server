package com.example.gintire.member.dto;

import com.example.gintire.member.domain.user.Member;
import com.example.gintire.member.dto.validation.FieldsMatcher;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@FieldsMatcher(baseField = "password", matchField = "confirmpassword", message = "비밀번호가 일치하지 않습니다")
@Getter
@Setter
public class MemberDto extends BaseDto {
    @NotBlank(message = "이메일 주소를 입력해주세요")
    @Email(message = "올바른 이메일 주소가 아닙니다")
    private String email;

    @Size(min = 4, message = "비밀번호는 4글자 이상 입력해주세요")
    private String password;

    private String confirmpassword;

    public MemberDto() {}
    public MemberDto(Member member) {
        super(member);
        this.email = member.getEmail();
    }
    public static MemberDto of(Member it) {
        return new MemberDto(it);
    }

    public Member toEntity() {
        return new Member(email, password);
    }
}
