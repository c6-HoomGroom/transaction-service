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
            Transaction transaction = Transaction.builder().id(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b")).product(null).userId("13652556-012a-4c07-b546-54eb1396d79b").quantity(2).build();
        });
    }

    @Test
    void testCreateTransactionWithoutPromo() {
        Transaction transaction = Transaction.builder().id(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b")).product(product).userId("13652556-012a-4c07-b546-54eb1396d79b").quantity(2).build();
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", transaction.getUserId());
        assertEquals(DeliveryStatus.WAITING_VERIFICATION.getValue(), transaction.getDeliveryStatus());
        assertEquals(540000, transaction.getTotalPrice());
        assertEquals(2, transaction.getQuantity());
    }

    @Test
    void testCreateTransactionWithPromo() {
        Transaction transaction = Transaction.builder().id(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b")).product(product).userId("13652556-012a-4c07-b546-54eb1396d79b").quantity(2).promoCodeId("23452556-012a-4c07-b546-54eb1396d79b").build();
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", transaction.getUserId());
        assertEquals(DeliveryStatus.WAITING_VERIFICATION.getValue(), transaction.getDeliveryStatus());
        assertEquals(432000, transaction.getTotalPrice());
        assertEquals(2, transaction.getQuantity());
        assertEquals("23452556-012a-4c07-b546-54eb1396d79b", transaction.getPromoCodeId());
    }

}