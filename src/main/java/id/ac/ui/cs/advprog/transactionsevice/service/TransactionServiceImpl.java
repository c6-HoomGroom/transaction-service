package id.ac.ui.cs.advprog.transactionsevice.service;

import id.ac.ui.cs.advprog.transactionsevice.model.Product;
import id.ac.ui.cs.advprog.transactionsevice.model.Transaction;
import id.ac.ui.cs.advprog.transactionsevice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction addTransaction(String id, Product product, String userId, int quantity, String voucherId){
        if (transactionRepository.findById(id) == null){
            Transaction transaction = Transaction.builder()
                    .id(UUID.fromString(id))
                    .product(product)
                    .userId(userId)
                    .quantity(quantity)
                    .build();

            transactionRepository.save(transaction);
            return transaction;
        }
        return null;
    }

    @Override
    public Transaction setDeliveryStatus(Transaction transaction, String deliveryStatus){
        if (transaction != null){

            if (deliveryStatus.equals("SUCCESS")){
                transaction.setDeliveryStatus("SUCCESS");
            } else if (deliveryStatus.equals("REJECTED")){
                transaction.setDeliveryStatus("FAILED");
            }

            transactionRepository.save(transaction);
            return transaction;
        }else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Transaction getTransaction(String id){
        return transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> getAllTransactions(){
        return transactionRepository.getAllTransactions();
    }

}