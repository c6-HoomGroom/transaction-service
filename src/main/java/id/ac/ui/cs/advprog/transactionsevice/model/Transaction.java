package id.ac.ui.cs.advprog.transactionsevice.model;

import id.ac.ui.cs.advprog.transactionsevice.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import java.util.UUID;


@Getter @Setter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "promo_code_id")
    private UUID promoCodeId;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(name = "delivery_status", length = 50)
    private String deliveryStatus;

    @Column(name = "transportation_type", length = 50)
    private String transportationType;

    @Column(name = "shipping_code", length = 50)
    private String shippingCode;

    // Private constructor to enforce the use of the builder
    public Transaction() {
    }

    // Static method to create the builder
    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }

}