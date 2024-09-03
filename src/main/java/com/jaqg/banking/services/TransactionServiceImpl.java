package com.jaqg.banking.services;

import com.jaqg.banking.dto.TransactionRequest;
import com.jaqg.banking.dto.TransactionResponse;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Transaction;
import com.jaqg.banking.exceptions.AccountNotFoundException;
import com.jaqg.banking.exceptions.NotEnoughFundsException;
import com.jaqg.banking.mapper.TransactionsMapper;
import com.jaqg.banking.repos.AccountRepository;
import com.jaqg.banking.repos.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Value("${sortcode}")
    private Long sortcode;
    private final TransactionRepository transactionRepo;

    private final AccountRepository accountRepository;

    private final RestTemplate restTemplate;


    public TransactionServiceImpl(TransactionRepository transactionRepo, AccountRepository accountRepository, RestTemplateBuilder restTemplateBuilder) {
        this.transactionRepo = transactionRepo;
        this.accountRepository = accountRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public TransactionResponse withdraw(TransactionRequest request) {
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

    public TransactionResponse executeTransfer(TransactionRequest request) {

        Long recipient_sortcode = request.toAccountSortCode();
        Long sender_sortcode = request.fromAccountSortCode();

        if (Objects.equals(recipient_sortcode, sortcode)){
            //logic of local transfer
            deposit(request);

        } else{
            //logic of remote transfer
            String url = "http://localhost:" + recipient_sortcode + "/transaction";
           TransactionResponse response =  restTemplate.postForObject(url, request, TransactionResponse.class);
        }
        return withdraw(request);

//        Optional<Account> optionalAccount = accountRepository.findById(request.toAccount());
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

    public TransactionResponse deposit(TransactionRequest request) {
        final Account account = accountRepository.findById(request.toAccount())
                .orElseThrow(() -> new AccountNotFoundException(request.toAccount()));

        BigDecimal newBalance = account.getBalance().add(request.amount());
        Transaction transaction = new Transaction(LocalDateTime.now(), request.amount(), request.type(), null, account);

        account.setBalance(newBalance);
        accountRepository.save(account);

        return TransactionsMapper.transactionMapper(transaction);

//        Optional<Account> optionalAccount = accountRepository.findById(request.toAccount());
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
    public List<TransactionResponse> getAllTransactions() {
        List<Transaction> transactions = transactionRepo.findAll();
        return TransactionsMapper.transactionListMapper(transactions);
    }
}
