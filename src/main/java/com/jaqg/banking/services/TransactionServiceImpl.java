package com.jaqg.banking.services;

import com.jaqg.banking.dto.TransactionRequestDTO;
import com.jaqg.banking.dto.TransactionDTO;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Transaction;
import com.jaqg.banking.exceptions.AccountNotFoundException;
import com.jaqg.banking.exceptions.NotEnoughFundsException;
import com.jaqg.banking.mapper.TransactionsMapper;
import com.jaqg.banking.repos.AccountRepository;
import com.jaqg.banking.repos.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepo;

    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepo, AccountRepository accountRepository) {
        this.transactionRepo = transactionRepo;
        this.accountRepository = accountRepository;
    }

    @Override
    public TransactionDTO withdraw(TransactionRequestDTO request) {
        final Account account = accountRepository.findById(request.fromAccount())
                .orElseThrow(() -> new AccountNotFoundException(request.fromAccount()));

        BigDecimal newBalance = account.getBalance().subtract(request.amount());
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughFundsException(request.fromAccount());
        }

        Transaction transaction = new Transaction(LocalDateTime.now(), request.amount(), request.type(), account, null);

        account.setBalance(newBalance);
        accountRepository.save(account);

        return TransactionsMapper.transactionMapper(transaction);
    }

    public TransactionDTO executeTransfer(TransactionRequestDTO request) {
        deposit(request);
        return withdraw(request);

//        Optional<Account> optionalAccount = accountRepository.findByIdAndIsRemovedFalse(request.toAccount());
//        if (optionalAccount.isPresent()){
//            Account account = optionalAccount.get();
//            Transaction transaction = new Transaction(LocalDateTime.now(), request.amount(), request.type(), account, null);
//            account.addDebitTransaction(transaction);
//            //account.setBalance(account.getBalance().add(request.amount()));
//            accountRepository.save(account);
//            return TransactionsMapper.transactionMapper(transaction);
//        } else {
//            throw new TransactionNotFoundException("Transaction could not be found");
//        }
    }

    public TransactionDTO deposit(TransactionRequestDTO request) {
        final Account account = accountRepository.findByIdAndIsRemovedFalse(request.toAccount())
                .orElseThrow(() -> new AccountNotFoundException(request.toAccount()));

        BigDecimal newBalance = account.getBalance().add(request.amount());
        Transaction transaction = new Transaction(LocalDateTime.now(), request.amount(), request.type(), null, account);

        account.setBalance(newBalance);
        accountRepository.save(account);

        return TransactionsMapper.transactionMapper(transaction);

//        Optional<Account> optionalAccount = accountRepository.findByIdAndIsRemovedFalse(request.toAccount());
//        if (optionalAccount.isPresent()){
//            Account account = optionalAccount.get();
//            Transaction transaction = new Transaction(LocalDateTime.now(), request.amount(), request.type(), account, null);
//            account.addDebitTransaction(transaction);
//            account.setBalance(account.getBalance().add(request.amount()));
//            accountRepository.save(account);
//            return TransactionsMapper.transactionMapper(transaction);
//        } else {
//            throw new AccountNotFoundException(request.toAccount());
//        }
    }

    //    @Override
//    public List<TransactionResponse> getAllTransactions() {
//        List<Transaction> transactions = transactionRepo.findAll();
//        return transactions.stream().map(e -> TransactionsMapper.transactionListMapper(e)).toList();
//    }
    @Override
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepo.findAll();
        return TransactionsMapper.transactionListMapper(transactions);
    }
}
