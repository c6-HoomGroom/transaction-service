package id.ac.ui.cs.advprog.transactionsevice.model;

import id.ac.ui.cs.advprog.transactionsevice.enums.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import java.util.UUID;

@Getter @Setter
public class TransactionRequest {
    private UUID id;
    private String productId;
    private String userId;
    private String promoCodeId;
    private int quantity;

    public TransactionRequest() {
        this.id = UUID.randomUUID();
    }

    public TransactionRequest(UUID id, String productId, String userId, String promoCodeId, int quantity) {
        this.id = (id != null ? id : UUID.randomUUID());
        this.productId = productId;
        this.userId = userId;
        this.promoCodeId = promoCodeId;
        this.quantity = quantity;
    }
}
