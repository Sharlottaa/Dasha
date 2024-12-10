package com.example.Dasha.repository;

import com.example.Dasha.entity.PaymentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentAccountRepository extends JpaRepository<PaymentAccount, Long> {
}