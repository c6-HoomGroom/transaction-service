package id.ac.ui.cs.advprog.transactionsevice.model;

import id.ac.ui.cs.advprog.transactionsevice.enums.DeliveryStatus;
import java.util.UUID;

public class TransactionBuilder {
    private UUID id = UUID.randomUUID();
    private UUID productId;
    private UUID userId;
    private UUID promoCodeId;
    private int quantity = 1;  // Default quantity
    private double totalPrice;
    private String deliveryStatus = DeliveryStatus.WAITING_VERIFICATION.getValue();

    private String transportationType;
    private String shippingCode;

    public TransactionBuilder() {
        this.id = UUID.randomUUID();
    }

    public TransactionBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public TransactionBuilder productId(UUID productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        this.productId = productId;
        return this;
    }

    public TransactionBuilder userId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public TransactionBuilder promoCodeId(UUID promoCodeId) {
        this.promoCodeId = promoCodeId;
        return this;
    }

    public TransactionBuilder transportationType(String transportationType) {
        this.transportationType = transportationType;
        return this;
    }

    public TransactionBuilder shippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
        return this;
    }

    public TransactionBuilder quantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.quantity = quantity;
        return this;
    }

    public TransactionBuilder totalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public TransactionBuilder deliveryStatus(String deliveryStatus) {
        if (DeliveryStatus.contains(deliveryStatus)) {  // Ensure it's a valid status
            this.deliveryStatus = deliveryStatus;
        } else {
            throw new IllegalArgumentException("Invalid delivery status: " + deliveryStatus);
        }
        return this;
    }

    public Transaction build() {
        Transaction transaction = new Transaction();
        transaction.setId(this.id);
        transaction.setProductId(this.productId);
        transaction.setUserId(this.userId);
        transaction.setPromoCodeId(this.promoCodeId);
        transaction.setQuantity(this.quantity);
        transaction.setDeliveryStatus(this.deliveryStatus);
        transaction.setTotalPrice(this.totalPrice);
        transaction.setTransportationType(this.transportationType);
        transaction.setShippingCode(this.shippingCode);
        return transaction;

    }
}
