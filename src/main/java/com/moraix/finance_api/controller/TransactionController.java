package com.moraix.finance_api.controller;

import com.moraix.finance_api.model.Transaction;
import com.moraix.finance_api.model.TransactionType;
import com.moraix.finance_api.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody Map<String, String> body) {
        Transaction t = transactionService.create(
                Long.parseLong(body.get("userId")),
                Long.parseLong(body.get("categoryId")),
                body.get("description"),
                new BigDecimal(body.get("amount")),
                LocalDate.parse(body.get("date")),
                TransactionType.valueOf(body.get("type"))
        );
        return ResponseEntity.ok(t);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> findByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.findByUser(userId));
    }

    @GetMapping("/user/{userId}/period")
    public ResponseEntity<List<Transaction>> findByPeriod(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(transactionService.findByUserAndPeriod(userId, start, end));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}