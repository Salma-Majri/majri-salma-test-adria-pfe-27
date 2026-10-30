package ma.majrisalma.accountservice.service;

import ma.majrisalma.accountservice.domain.Account;
import java.util.List;
import java.util.UUID;

public interface AccountService {
    Account createAccount(Account account);
    List<Account> getAllAccounts();
    Account getAccountById(UUID id);
    double getAccountBalance(UUID id);
}