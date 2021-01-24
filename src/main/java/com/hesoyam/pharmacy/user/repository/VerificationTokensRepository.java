package com.hesoyam.pharmacy.user.repository;

import com.hesoyam.pharmacy.user.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokensRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken getByToken(String token);
}
