package com.moraix.finance_api.service;

import com.moraix.finance_api.model.Transaction;
import com.moraix.finance_api.model.TransactionType;
import com.moraix.finance_api.model.User;
import com.moraix.finance_api.model.Category;
import com.moraix.finance_api.repository.CategoryRepository;
import com.moraix.finance_api.repository.TransactionRepository;
import com.moraix.finance_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Transaction create(Long userId, Long categoryId, String description,
                              BigDecimal amount, LocalDate date, TransactionType type) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

        Transaction transaction = Transaction.builder()
                .user(user)
                .category(category)
                .description(description)
                .amount(amount)
                .date(date)
                .type(type)
                .build();
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findByUser(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<Transaction> findByUserAndType(Long userId, TransactionType type) {
        return transactionRepository.findByUserIdAndType(userId, type);
    }

    public List<Transaction> findByUserAndPeriod(Long userId, LocalDate start, LocalDate end) {
        return transactionRepository.findByUserIdAndDateBetween(userId, start, end);
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }
}