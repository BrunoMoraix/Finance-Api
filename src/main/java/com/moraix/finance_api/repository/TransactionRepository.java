package com.moraix.finance_api.repository;

import com.moraix.finance_api.model.Transaction;
import com.moraix.finance_api.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByUserIdAndType(Long userId, TransactionType type);
    List<Transaction> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}