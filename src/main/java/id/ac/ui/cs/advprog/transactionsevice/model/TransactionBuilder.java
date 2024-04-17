package id.ac.ui.cs.advprog.transactionsevice.model;

import id.ac.ui.cs.advprog.transactionsevice.enums.DeliveryStatus;
import java.util.UUID;

public class TransactionBuilder {
    private UUID id = UUID.randomUUID();
    private Product product;
    private String userId;
    private String promoCodeId;
    private int quantity = 1;  // Default quantity
    private double totalPrice;
    private String deliveryStatus = DeliveryStatus.WAITING_VERIFICATION.getValue();

    public TransactionBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public TransactionBuilder product(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        this.product = product;
        return this;
    }

    public TransactionBuilder userId(String userId) {
        this.userId = userId;
        return this;
    }

    public TransactionBuilder promoCodeId(String promoCodeId) {
        this.promoCodeId = promoCodeId;
        return this;
    }

    public TransactionBuilder quantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.quantity = quantity;
        return this;
    }

    public TransactionBuilder deliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public Transaction build() {
        if (this.product == null) {
            throw new IllegalStateException("Product is not set");
        }

        if (this.product.getDiscountPrice() != 0) {
            this.totalPrice = this.product.getDiscountPrice() * this.quantity;
        } else {
            this.totalPrice = this.product.getPrice() * this.quantity;
        }

        if (this.promoCodeId != null && !this.promoCodeId.isEmpty()) {
            this.totalPrice *= 0.8;
        }


        Transaction transaction = new Transaction();
        transaction.setId(this.id);
        transaction.setProduct(this.product);
        transaction.setUserId(this.userId);
        transaction.setPromoCodeId(this.promoCodeId);
        transaction.setQuantity(this.quantity);
        transaction.setDeliveryStatus(this.deliveryStatus);
        transaction.setTotalPrice(this.totalPrice);
        return transaction;

    }
}
