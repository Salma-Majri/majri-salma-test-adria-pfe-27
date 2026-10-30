package ma.majrisalma.transactionservice.service;

import ma.majrisalma.transactionservice.client.AccountClient;
import ma.majrisalma.transactionservice.domain.Transaction;
import ma.majrisalma.transactionservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountClient accountClient;

    public TransactionService(TransactionRepository transactionRepository, AccountClient accountClient) {
        this.transactionRepository = transactionRepository;
        this.accountClient = accountClient;
    }

    public Transaction createTransaction(UUID accountId, BigDecimal amount, String type) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transaction amount must be positive");
        }
        try {
            Object account = accountClient.getAccountById(accountId);
            if (account == null) {
                throw new IllegalArgumentException("Account not found with ID: " + accountId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error communicating with Account Service or Account does not exist", e);
        }

        // 3. Creation d la transaction
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID());
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setTimestamp(Instant.now());

        return transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}