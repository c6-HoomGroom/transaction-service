package id.ac.ui.cs.advprog.transactionsevice.model;

import id.ac.ui.cs.advprog.transactionsevice.enums.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Transaction {
    private UUID id;
    private Product product;
    private String userId;
    private String promoCodeId;
    private int quantity;
    private double totalPrice;
    private String deliveryStatus;

    // Private constructor to enforce the use of the builder
    public Transaction() {
    }

    // Static method to create the builder
    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }
}