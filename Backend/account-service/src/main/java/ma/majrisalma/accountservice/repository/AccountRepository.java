package ma.majrisalma.accountservice.repository;

import ma.majrisalma.accountservice.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
}