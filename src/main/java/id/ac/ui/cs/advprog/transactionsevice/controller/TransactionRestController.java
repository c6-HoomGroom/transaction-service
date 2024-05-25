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
    public ResponseEntity<Transaction> getTransactionById(@PathVariable UUID id) {
        Transaction transaction = transactionService.getTransaction(id);
        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transactionRequest) {
        transactionRequest.setId(UUID.randomUUID());
        Transaction createdTransaction = transactionService.addTransaction(transactionRequest);
        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Transaction>> getAllUserTransactions(@PathVariable UUID id) {
        List<Transaction> transactions = transactionService.getTransactionByUserId(id);
        return ResponseEntity.ok(transactions);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Transaction>> deleteTransactions(@PathVariable UUID id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable UUID id, @RequestBody Transaction transactionDetails) {
        Transaction updatedTransaction = transactionService.updateTransaction(id, transactionDetails);
        if (updatedTransaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTransaction);
    }
}