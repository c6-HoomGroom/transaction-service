package id.ac.ui.cs.advprog.transactionsevice.service;

import java.util.List;

import id.ac.ui.cs.advprog.transactionsevice.model.Product;
import id.ac.ui.cs.advprog.transactionsevice.model.Transaction;

public interface TransactionService {
    Transaction addTransaction(String id, Product product, String userId, int quantity, String voucherId);
    Transaction setDeliveryStatus(Transaction transaction, String deliveryStatus);
    Transaction getTransaction(String id);
    List<Transaction> getAllTransactions();

}
