package id.ac.ui.cs.advprog.transactionsevice.model;

import id.ac.ui.cs.advprog.transactionsevice.enums.DeliveryStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTest{
    Product product;
    Set<Tag> productTags = new HashSet<>();

    @BeforeEach
    void setUp() {
        this.product = Product.builder()
                .name("Kursi Kayu")
                .tag(new Tag("Wooden"))
                .tag(new Tag("Chair"))
                .description("Produk ini adalah kursi kayu sederhana yang cocok untuk ruangan mana saja.")
                .image("https://i.ibb.co/KzQjH3p/sam-moghadam-khamseh-kvmds-Tr-GOBM-unsplash.jpg")
                .price(300000)
                .discountPrice(270000)
                .build();
    }

    @Test
    void testCreateTransactionEmptyProduct(){
        assertThrows(IllegalArgumentException.class, () -> {
            Transaction transaction = new Transaction(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"), null, "13652556-012a-4c07-b546-54eb1396d79b", 2);
        });
    }

    @Test
    void testCreateTransactionWithoutPromo() {
        Transaction transaction = new Transaction(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"), product, "13652556-012a-4c07-b546-54eb1396d79b", 2);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", transaction.getUserId());
        assertEquals(DeliveryStatus.WAITING_VERIFICATION.getValue(), transaction.getDeliveryStatus());
        assertEquals(600000, transaction.getTotalPrice());
        assertEquals(2, transaction.getQuantity());
    }

}