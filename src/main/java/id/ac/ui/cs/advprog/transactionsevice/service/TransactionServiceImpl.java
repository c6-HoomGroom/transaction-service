package id.ac.ui.cs.advprog.transactionsevice.service;

import id.ac.ui.cs.advprog.transactionsevice.model.Product;
import id.ac.ui.cs.advprog.transactionsevice.model.Transaction;
import id.ac.ui.cs.advprog.transactionsevice.model.TransactionBuilder;
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
    public Transaction addTransaction(Transaction transactionRequest) {

        String productId = transactionRequest.getProductId().toString();

        // Constructing the URL to fetch the product
        String url = "https://product-service-uflspwyoiq-ew.a.run.app/products/api/id/" + productId;

        // Fetch the product using RestTemplate
        Product product = restTemplate.getForObject(url, Product.class);

        if (product == null) {
            throw new NoSuchElementException("Product not found");
        }

        Optional<Transaction> existingTransaction = transactionRepository.findById(transactionRequest.getId());
        if (!existingTransaction.isPresent()) {
            double totalPrice = calculatePrice(product, transactionRequest);
            TransactionBuilder builder = Transaction.builder()
                    .id(transactionRequest.getId())
                    .productId(transactionRequest.getProductId())
                    .userId(transactionRequest.getUserId())
                    .totalPrice(totalPrice)
                    .quantity(transactionRequest.getQuantity());

            // Optionally include promoCodeId if it exists
            if (transactionRequest.getPromoCodeId() != null) {
                builder.promoCodeId(transactionRequest.getPromoCodeId());
            }

            Transaction newTransaction = builder.build();
            transactionRepository.save(newTransaction);
            System.out.println("NEW TRANSACTION: " + newTransaction.getId());
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

    @Override
    public List<Transaction> getTransactionByUserId(UUID id) {
        return transactionRepository.findByUserId(id);
    }

    @Override
    public Transaction updateTransaction(UUID id, Transaction transactionDetails) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            Transaction existingTransaction = optionalTransaction.get();

            TransactionBuilder builder = Transaction.builder()
                    .id(existingTransaction.getId())
                    .userId(existingTransaction.getUserId())
                    .productId(existingTransaction.getProductId())
                    .quantity(existingTransaction.getQuantity())
                    .totalPrice(existingTransaction.getTotalPrice())
                    .promoCodeId(existingTransaction.getPromoCodeId());

            if (transactionDetails.getPromoCodeId() != null) {
                double totalPrice = existingTransaction.getTotalPrice()*0.8;
                builder.promoCodeId(transactionDetails.getPromoCodeId());
                builder.totalPrice(totalPrice);
            }

            if (transactionDetails.getDeliveryStatus() != null) {
                builder.deliveryStatus(transactionDetails.getDeliveryStatus());
            }

            Transaction newTransaction = builder.build();

            return transactionRepository.save(newTransaction);
        } else {
            return null; // Or throw an exception
        }
    }

    public double calculatePrice(Product product, Transaction transactionRequest) {
        if (transactionRequest.getPromoCodeId() != null) {
            if (product.getDiscountPrice() != 0) {
                return transactionRequest.getQuantity() * product.getDiscountPrice() * 0.8;
            } else {
                return transactionRequest.getQuantity() * product.getPrice() * 0.8;
            }
        } else {
            if (product.getDiscountPrice() != 0) {
                return transactionRequest.getQuantity() * product.getDiscountPrice();
            } else {
                return transactionRequest.getQuantity() * product.getPrice();
            }
        }
    }


}
