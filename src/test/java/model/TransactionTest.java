import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    void testCreateOrderEmptyProduct(){
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", "13652556-012a-4c07-b546-54eb1396d79c", null);
        });
    }

    @Test
    void testCreateOrder() {
        Order order = new Order("eb558e9f-1c39-460e-8860-71af6af63bd6", product, "13652556-012a-4c07-b546-54eb1396d79b", 2, 1000, "WAITING");
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", order.getIdUser());
        assertEquals(ShippingStatus.WAITING.getValue(), order.getShippingStatus());
        assertEquals(1000, order.getTotalPrice);
        assertEquals(2, order.getQuantity);
    }

}