package ma.majrisalma.transactionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "account-service", url = "${account.service.url:http://localhost:8081}")
public interface AccountClient {

    @GetMapping("/api/accounts/{id}")
    Object getAccountById(@PathVariable("id") UUID id);
}