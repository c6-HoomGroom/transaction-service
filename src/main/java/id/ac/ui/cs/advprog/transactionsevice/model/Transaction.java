package id.ac.ui.cs.advprog.transactionsevice.model;

import id.ac.ui.cs.advprog.transactionsevice.enums.DeliveryStatus;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.Singular;

import java.util.Set;
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

    public Transaction(UUID id, Product product, String userId, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException();
        } else {
            this.id = id;
            this.product = product;
            this.userId = userId;
            this.quantity = quantity;
            this.deliveryStatus = DeliveryStatus.WAITING_VERIFICATION.getValue();
            this.totalPrice = product.getPrice()*quantity;
        }
    }
}