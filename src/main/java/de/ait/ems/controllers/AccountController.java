package de.ait.ems.controllers;

import de.ait.ems.models.Account;
import de.ait.ems.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all users")
    public List<Account> getAllUsers() {
        return accountService.getAllUsers();
    }

    @GetMapping("/byRole/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get users by role")
    public List<Account> getUsersByRole(@PathVariable String role) {
        return accountService.getUsersByRole(role);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update user by ID")
    public Account updateUser(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateUser(id, account);
    }
}