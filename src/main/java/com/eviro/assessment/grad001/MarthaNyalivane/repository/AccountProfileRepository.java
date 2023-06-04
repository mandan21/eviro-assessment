package com.eviro.assessment.grad001.MarthaNyalivane.repository;

import com.eviro.assessment.grad001.MarthaNyalivane.model.AccountProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountProfileRepository extends CrudRepository<AccountProfile, Long> {
    List<AccountProfile> findByAccountHolderName(String accountHolderName);
    List<AccountProfile> findByAccountHolderSurname(String accountHolderSurname);
    AccountProfile findByAccountHolderNameAndAccountHolderSurname(String accountHolderName, String accountHolderSurname);
    // Additional methods can be added here if needed
}
