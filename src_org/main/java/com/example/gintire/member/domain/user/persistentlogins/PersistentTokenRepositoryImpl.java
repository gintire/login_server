package com.example.gintire.member.domain.user.persistentlogins;

import com.example.gintire.member.domain.user.Member;
import com.example.gintire.member.domain.user.MemberRepository;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
public class PersistentTokenRepositoryImpl implements PersistentTokenRepository {

    private PersistentLoginsRepository persistentLoginsRepository;
    private MemberRepository memberRepository;

    public PersistentTokenRepositoryImpl(PersistentLoginsRepository persistentLoginsRepository,
                                         MemberRepository memberRepository) {
        this.persistentLoginsRepository = persistentLoginsRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        Member member = memberRepository.findByUsername(persistentRememberMeToken.getUsername());
        PersistentLogins persistentLogins =
                new PersistentLogins(
                        member
                        , persistentRememberMeToken.getSeries()
                        , persistentRememberMeToken.getTokenValue()
                        , dateToLocalDate(persistentRememberMeToken.getDate())
                );
        persistentLoginsRepository.save(persistentLogins);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        Optional<PersistentLogins> persistentLoginsRepositoryById =  persistentLoginsRepository.findById(series);
        PersistentLogins persistentLogins = persistentLoginsRepositoryById.get();
        persistentLogins.update(series, tokenValue, dateToLocalDate(lastUsed));
        persistentLoginsRepository.save(persistentLogins);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
        Optional<PersistentLogins> persistentLoginsRepositoryById =  persistentLoginsRepository.findById(series);
        PersistentLogins persistentLogins = persistentLoginsRepositoryById.get();
        try {
            return new PersistentRememberMeToken(persistentLogins.getMember().getUsername(), series,
                    persistentLogins.getToken(), localDateToDate(persistentLogins.getLastUsed()));
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Transactional
    @Override
    public void removeUserTokens(String useremail) {
        Member member = memberRepository.findByEmail(useremail);
        if(member!=null) {
            //persistentLoginsRepository.deleteByUserId(member.getId());
        }
    }

    private LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
