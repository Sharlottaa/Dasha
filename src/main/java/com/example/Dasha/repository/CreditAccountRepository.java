package com.example.Dasha.repository;

import com.example.Dasha.entity.CreditAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CreditAccountRepository extends JpaRepository<CreditAccount, Long> {
    List<CreditAccount> findAllByUserId(Long userId);

}