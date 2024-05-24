package id.ac.ui.cs.advprog.transactionsevice.controller;

import id.ac.ui.cs.advprog.transactionsevice.model.Transaction;
import id.ac.ui.cs.advprog.transactionsevice.service.TransactionService;
import id.ac.ui.cs.advprog.transactionsevice.model.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/transactions")
public class TransactionRestController
{
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/id/{id}")
    public Transaction getTransactionById(@PathVariable UUID id) {
        Transaction transaction = transactionService.getTransaction(id);
        return transaction;
    }

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transactionRequest) {
        transactionRequest.setId(UUID.randomUUID());
        Transaction createdTransaction = transactionService.addTransaction(transactionRequest);
        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping("/all-transactions")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}