package com.example.gintire.member.domain.user.persistentlogins;

import com.example.gintire.member.domain.user.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class PersistentLogins {
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name="fk_persistent_logins"))
    private Member member;

    @Id
    private String series;

    private String token;

    private LocalDate lastUsed;

    public PersistentLogins() {
    }

    public PersistentLogins(Member member, String series, String token, LocalDate lastUsed) {
        this.member = member;
        this.series = series;
        this.token = token;
        this.lastUsed = lastUsed;
    }
    public Member getMember() {
        return member;
    }

    public void setMember(Member user) {
        this.member = user;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDate getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(LocalDate lastUsed) {
        this.lastUsed = lastUsed;
    }
    public void update(String newSeries, String newToken, LocalDate newLastUsed) {
        this.series = newSeries;
        this.token = newToken;
        this.lastUsed = newLastUsed;
    }
}
