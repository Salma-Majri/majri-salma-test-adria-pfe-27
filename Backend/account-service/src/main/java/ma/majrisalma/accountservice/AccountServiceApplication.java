package ma.majrisalma.accountservice;

import ma.majrisalma.accountservice.domain.Account;
import ma.majrisalma.accountservice.domain.AccountStatus;
import ma.majrisalma.accountservice.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountRepository accountRepository) {
        return args -> {
            accountRepository.save(Account.builder()
                    .customerReference("CUST-001")
                    .status(AccountStatus.ACTIVE)
                    .currency("MAD")
                    .balance(7500.00)
                    .build());

            accountRepository.save(Account.builder()
                    .customerReference("CUST-002")
                    .status(AccountStatus.ACTIVE)
                    .currency("EUR")
                    .balance(1500.50)
                    .build());

            accountRepository.save(Account.builder()
                    .customerReference("CUST-003")
                    .status(AccountStatus.BLOCKED)
                    .currency("USD")
                    .balance(300.00)
                    .build());

            accountRepository.findAll().forEach(acc -> {
                System.out.println("Account ID: " + acc.getId() + " | Ref: " + acc.getCustomerReference() + " | Balance: " + acc.getBalance());
            });
        };
    }
}