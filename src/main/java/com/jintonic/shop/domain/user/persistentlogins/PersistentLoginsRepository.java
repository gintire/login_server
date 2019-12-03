package com.jintonic.shop.domain.user.persistentlogins;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersistentLoginsRepository extends JpaRepository<PersistentLogins, String> {

    void deleteByUserId(Long userId);

}
