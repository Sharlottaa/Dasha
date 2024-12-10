package com.example.Dasha.repository;

import com.example.Dasha.entity.BankAtm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BankAtmRepository extends JpaRepository<BankAtm, Long> {
}