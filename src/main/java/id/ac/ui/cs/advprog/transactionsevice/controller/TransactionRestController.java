package id.ac.ui.cs.advprog.transactionsevice.controller;

import id.ac.ui.cs.advprog.transactionsevice.model.Transaction;
import id.ac.ui.cs.advprog.transactionsevice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionRestController
{
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/id/{id}")
    public Transaction getTransactionById(@PathVariable String id) {
        Transaction transaction = transactionService.getTransaction(id);
        return transaction;
    }

    @GetMapping("/all-transactions")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}