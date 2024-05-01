package id.ac.ui.cs.advprog.transactionsevice.service;

import id.ac.ui.cs.advprog.transactionsevice.model.Product;
import id.ac.ui.cs.advprog.transactionsevice.model.Transaction;
import id.ac.ui.cs.advprog.transactionsevice.model.TransactionRequest;
import id.ac.ui.cs.advprog.transactionsevice.repository.ProductRepository;
import id.ac.ui.cs.advprog.transactionsevice.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;
    private ProductRepository productRepository;
    @Override
    public Transaction addTransaction(TransactionRequest transactionRequest){

        String productId = transactionRequest.getProductId();

        // Constructing the URL to fetch the product
        String url = "https://product-service-uflspwyoiq-ew.a.run.app/products/api/id/" + productId;

        // Fetch the product using RestTemplate
        Product product = restTemplate.getForObject(url, Product.class);

        if (transactionRepository.findById(transactionRequest.getId().toString()) == null){
            Transaction newTransaction = Transaction.builder()
                    .id(transactionRequest.getId())
                    .product(product)
                    .userId(transactionRequest.getUserId())
                    .quantity(transactionRequest.getQuantity())
                    .build();

            System.out.println(newTransaction.getUserId());

            transactionRepository.save(newTransaction);
            return newTransaction;
        }
        return null;
    }

    @Override
    public Transaction setDeliveryStatus(Transaction transaction, String deliveryStatus){
        if (transaction != null){
            Transaction updatedTransaction = Transaction.builder() // Start a new builder
                    .id(transaction.getId()) // Copy existing attributes
                    .product(transaction.getProduct())
                    .userId(transaction.getUserId())
                    .promoCodeId(transaction.getPromoCodeId())
                    .quantity(transaction.getQuantity())
                    .deliveryStatus(deliveryStatus) // Set the new delivery status
                    .build(); // Build the new transaction

            transactionRepository.save(updatedTransaction);
            return updatedTransaction;
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

    @Override
    public Transaction delete(String id){
        return transactionRepository.delete(id);
    }
}