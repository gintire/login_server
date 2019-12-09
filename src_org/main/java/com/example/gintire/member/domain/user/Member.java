package com.example.gintire.member.domain.user;

import com.example.gintire.member.domain.BaseEntity;
import com.example.gintire.member.domain.MemberVerifiable;
import com.example.gintire.member.domain.user.persistentlogins.PersistentLogins;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Member extends BaseEntity implements MemberVerifiable, UserDetails {
    @OneToOne(mappedBy = "member")
    private PersistentLogins persistentLogins;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Set<Role> authorities;

    public Member(Long id) {
        super(id);
    }

    public Member(String email, String password) {
        this();
        this.password = new BCryptPasswordEncoder().encode(password);
        this.email = email;
    }

    private Member() {
        authorities = new HashSet<>();
        authorities.add(Role.MEMBER);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void update(Member reqUser) {
        password = (reqUser.password.isEmpty()) ? password : reqUser.password;
        name = (reqUser.name.isEmpty()) ? name : reqUser.name;
        email = (reqUser.email.isEmpty()) ? email : reqUser.email;
    }

    @Override
    public boolean verifyMember(String username) {
        return this.username.equals(username);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
