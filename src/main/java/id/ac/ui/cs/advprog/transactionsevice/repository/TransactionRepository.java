package id.ac.ui.cs.advprog.transactionsevice.repository;

import id.ac.ui.cs.advprog.transactionsevice.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class TransactionRepository {
    public List<Transaction> transactionData = new ArrayList<>();

    public Transaction save(Transaction transaction){
        UUID transactionId = transaction.getId(); // Ensure UUID for new transaction
        int i = 0;

        for (Transaction savedOrder : transactionData){
            UUID savedOrderId = savedOrder.getId(); // Ensure UUID for comparison
            if (savedOrderId.equals(transactionId)){
                transactionData.remove(i);
                transactionData.add(i,transaction);
                return transaction;
            }
            i += 1;
        }
        transactionData.add(transaction);
        return transaction;
    }

    public Transaction findById(String id){
        UUID uuidId = UUID.fromString(id); // Convert input ID to UUID
        for (Transaction savedOrder : transactionData){
            UUID savedOrderId = UUID.fromString(savedOrder.getId().toString()); // Ensure comparison is UUID-based
            if (savedOrderId.equals(uuidId)){
                return savedOrder;
            }
        }
        return null;
    }

    public List<Transaction> getAllTransactions() {
        return transactionData;
    }
}