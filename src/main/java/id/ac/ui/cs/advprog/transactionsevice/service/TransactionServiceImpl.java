package id.ac.ui.cs.advprog.transactionsevice.service;

import id.ac.ui.cs.advprog.transactionsevice.model.Product;
import id.ac.ui.cs.advprog.transactionsevice.model.Transaction;
import id.ac.ui.cs.advprog.transactionsevice.repository.ProductRepository;
import id.ac.ui.cs.advprog.transactionsevice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Transaction addTransaction(Transaction transactionRequest){

        String productId = transactionRequest.getProductId().toString();

        // Constructing the URL to fetch the product
        String url = "https://product-service-uflspwyoiq-ew.a.run.app/products/api/id/" + productId;

        // Fetch the product using RestTemplate
        Product product = restTemplate.getForObject(url, Product.class);

        if (product == null) {
            throw new NoSuchElementException("Product not found");
        }

        System.out.println(transactionRequest.getId());
        System.out.println(transactionRequest.getProductId());
        System.out.println(transactionRequest.getUserId());
        System.out.println(transactionRequest.getQuantity());
        System.out.println(productId);

        Optional<Transaction> existingTransaction = transactionRepository.findById(transactionRequest.getId());
        if (!existingTransaction.isPresent()) {
            double totalPrice = calculatePrice(product, transactionRequest);
            Transaction newTransaction = Transaction.builder()
                    .id(transactionRequest.getId())
                    .productId(transactionRequest.getProductId())
                    .userId(transactionRequest.getUserId())
                    .quantity(transactionRequest.getQuantity())
                    .totalPrice(totalPrice)
                    .build();
            transactionRepository.save(newTransaction);
            return newTransaction;
        }
        return null;
    }

    @Override
    public Transaction setDeliveryStatus(Transaction transaction, String deliveryStatus) {
        if (transaction != null) {
            transaction.setDeliveryStatus(deliveryStatus);
            return transactionRepository.save(transaction);
        } else {
            throw new NoSuchElementException("Transaction not found");
        }
    }

    @Override
    public Transaction getTransaction(UUID id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public void deleteTransaction(UUID id) {
        transactionRepository.deleteById(id);
    }

    public double calculatePrice(Product product, Transaction transactionRequest) {
        if (transactionRequest.getPromoCodeId() != null) {
            return transactionRequest.getQuantity() * product.getDiscountPrice();
        } else {
            return transactionRequest.getQuantity() * product.getPrice();
        }
    }
}
