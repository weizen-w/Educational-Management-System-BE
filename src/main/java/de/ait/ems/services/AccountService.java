package de.ait.ems.services;

import com.amazonaws.services.kms.model.NotFoundException;
import de.ait.ems.models.Account;
import de.ait.ems.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Method to get all accounts
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Method to get an account by ID
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    // Method to create a new account
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    // Method to update existing account
    public Account updateAccount(Long id, Account updatedAccount) {
        Optional<Account> existingAccount = accountRepository.findById(id);
        if (existingAccount.isPresent()) {
            // Updating account fields
            Account account = existingAccount.get();
            account.setHashPassword(updatedAccount.getHashPassword());
            account.setFirstName(updatedAccount.getFirstName());
            account.setLastName(updatedAccount.getLastName());
            account.setEmail(updatedAccount.getEmail());
            account.setRole(updatedAccount.getRole());
            account.setAccountState(updatedAccount.getAccountState());
            account.setPhotoLink(updatedAccount.getPhotoLink());

            // Save the updated account
            return accountRepository.save(account);
        } else {
            // Account not found
            return null;
        }
    }

    // Method for deleting an account by ID
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public List<Account> getAllUsers() {
        return accountRepository.findAll();
    }

    public List<Account> getUsersByRole(String role) {
        return accountRepository.findByRole(role);
    }

    // Update user information (administrator available)
    public Account updateUser(Long id, Account account) {
        // Checking user existence
        Account existingUser = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        // Updating user information
        existingUser.setFirstName(account.getFirstName());
        existingUser.setLastName(account.getLastName());
        existingUser.setEmail(account.getEmail());
        existingUser.setRole(account.getRole());
        existingUser.setAccountState(account.getAccountState());
        existingUser.setPhotoLink(account.getPhotoLink());

        // Saving updated data
        return accountRepository.save(existingUser);
    }
}