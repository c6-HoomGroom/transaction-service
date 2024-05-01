package id.ac.ui.cs.advprog.transactionsevice.service;

import java.util.List;

import id.ac.ui.cs.advprog.transactionsevice.model.Product;
import id.ac.ui.cs.advprog.transactionsevice.model.Transaction;
import id.ac.ui.cs.advprog.transactionsevice.model.TransactionRequest;

public interface TransactionService {
    Transaction addTransaction(TransactionRequest transaction);
    Transaction setDeliveryStatus(Transaction transaction, String deliveryStatus);
    Transaction getTransaction(String id);
    List<Transaction> getAllTransactions();
    Transaction delete(String id);

}
