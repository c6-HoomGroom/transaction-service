package id.ac.ui.cs.advprog.transactionsevice.service;

import java.util.List;

import id.ac.ui.cs.advprog.transactionsevice.model.Product;
import id.ac.ui.cs.advprog.transactionsevice.model.Transaction;
import id.ac.ui.cs.advprog.transactionsevice.model.TransactionRequest;
import java.util.UUID;


public interface TransactionService {
    Transaction addTransaction(Transaction transaction);
    Transaction setDeliveryStatus(Transaction transaction, String deliveryStatus);
    Transaction getTransaction(UUID id);
    List<Transaction> getAllTransactions();
    void deleteTransaction(UUID id);
    Transaction updateTransaction(UUID id, Transaction transactionDetails);

    List<Transaction> getTransactionByUserId(UUID id);

}
