package com.example.Dasha.repository;

import com.example.Dasha.entity.BankOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankOfficeRepository extends JpaRepository<BankOffice, Long> {
}