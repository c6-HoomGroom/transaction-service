package id.ac.ui.cs.advprog.transactionsevice.service;


import id.ac.ui.cs.advprog.transactionsevice.model.Product;
import id.ac.ui.cs.advprog.transactionsevice.model.Tag;
import id.ac.ui.cs.advprog.transactionsevice.model.Transaction;
import id.ac.ui.cs.advprog.transactionsevice.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @InjectMocks
    TransactionServiceImpl transactionService;

    @Mock
    TransactionRepository transactionRepository;

    List<Transaction> transactions;

    @BeforeEach
    void setup() {
        transactions = new ArrayList<>();

        Product product = Product.builder()
                .name("Kursi Kayu")
                .tag(new Tag("Wooden"))
                .tag(new Tag("Chair"))
                .description("Produk ini adalah kursi kayu sederhana yang cocok untuk ruangan mana saja.")
                .image("https://i.ibb.co/KzQjH3p/sam-moghadam-khamseh-kvmds-Tr-GOBM-unsplash.jpg")
                .price(300000)
                .discountPrice(270000)
                .build();

        Transaction transaction1 = Transaction.builder()
                .id(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"))
                .product(product)
                .userId("13652556-012a-4c07-b546-54eb1396d79b")
                .quantity(2)
                .build();

        Transaction transaction2 = Transaction.builder()
                .id(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"))
                .product(product)
                .userId("13652556-012a-4c07-b546-54eb1396d79d")
                .quantity(3)
                .build();

        transactions.add(transaction1);
        transactions.add(transaction2);
    }

    @Test
    void testAddTransaction(){
        Transaction transaction = transactions.get(0);

        doReturn(transaction).when(transactionRepository).save(any(Transaction.class));

        Transaction result = transactionService.addTransaction(transaction);

        verify(transactionRepository, times(1)).save(any(Transaction.class));
        assertEquals(transaction.getId(), result.getId());
    }

    @Test
    void testSetDeliveryStatus(){
        Transaction transaction = transactions.get(0);

        Transaction result = transactionService.setDeliveryStatus(transaction, "TIBA");

        assertEquals(transaction.getId(), result.getId());
        assertEquals("TIBA", result.getDeliveryStatus());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void testDeleteTransaction(){
        Product mockProduct = Product.builder()
                .name("Kursi Kayu")
                .tag(new Tag("Wooden"))
                .tag(new Tag("Chair"))
                .description("Produk ini adalah kursi kayu sederhana yang cocok untuk ruangan mana saja.")
                .image("https://i.ibb.co/KzQjH3p/sam-moghadam-khamseh-kvmds-Tr-GOBM-unsplash.jpg")
                .price(300000)
                .discountPrice(270000)
                .build();

        Transaction mockTransaction = Transaction.builder()
                .id(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"))
                .product(mockProduct)
                .userId("13652556-012a-4c07-b546-54eb1396d79b")
                .quantity(2)
                .build();

        when(transactionRepository.delete("13652556-012a-4c07-b546-54eb1396d79b")).thenReturn(mockTransaction);

        Transaction deleted = transactionService.delete("13652556-012a-4c07-b546-54eb1396d79b");

        assertEquals(mockTransaction.getId(), deleted.getId());
    }
}