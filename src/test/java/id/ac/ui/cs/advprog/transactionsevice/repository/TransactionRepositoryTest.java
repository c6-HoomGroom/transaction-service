//    package id.ac.ui.cs.advprog.transactionsevice.repository;
//
//    import id.ac.ui.cs.advprog.transactionsevice.model.Product;
//    import id.ac.ui.cs.advprog.transactionsevice.model.Tag;
//    import id.ac.ui.cs.advprog.transactionsevice.model.Transaction;
//    import org.junit.jupiter.api.BeforeEach;
//    import org.junit.jupiter.api.Test;
//
//    import java.util.ArrayList;
//    import java.util.UUID;
//    import java.util.List;
//
//    import static org.junit.jupiter.api.Assertions.assertEquals;
//
////    class TransactionRepositoryTest {
////        TransactionRepository transactionRepository;
////
////        List<Transaction> transactions;
////
////        @BeforeEach
////        void setup(){
////            transactionRepository = new TransactionRepository();
////            transactions = new ArrayList<>();
////
////            Product product = Product.builder()
////                                .name("Kursi Kayu")
////                                .tag(new Tag("Wooden"))
////                                .tag(new Tag("Chair"))
////                                .description("Produk ini adalah kursi kayu sederhana yang cocok untuk ruangan mana saja.")
////                                .image("https://i.ibb.co/KzQjH3p/sam-moghadam-khamseh-kvmds-Tr-GOBM-unsplash.jpg")
////                                .price(300000)
////                                .discountPrice(270000)
////                                .build();
////
////            Transaction transaction1 = Transaction.builder()
////                                        .id(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"))
////                                        .product(product)
////                                        .userId("13652556-012a-4c07-b546-54eb1396d79b")
////                                        .quantity(2)
////                                        .build();
////
////            Transaction transaction2 = Transaction.builder()
////                                        .id(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"))
////                                        .product(product)
////                                        .userId("13652556-012a-4c07-b546-54eb1396d79d")
////                                        .quantity(3)
////                                        .build();
////
////            transactions.add(transaction1);
////            transactions.add(transaction2);
////        }
//
//        @Test
//        void testSaveCreate() {
//            Transaction transaction = transactions.get(0);
//            Transaction result = transactionRepository.save(transaction);
//
//            Transaction findResult = transactionRepository.findById(transactions.get(0).getId().toString());
//
//            assertEquals(transaction.getId(), result.getId());
//            assertEquals(transaction.getUserId(), findResult.getUserId());
//            assertEquals(transaction.getProduct(), findResult.getProduct());
//            assertEquals(transaction.getQuantity(), findResult.getQuantity());
//            assertEquals(transaction.getDeliveryStatus(), findResult.getDeliveryStatus());
//        }
//
//        @Test
//        void testSaveUpdate(){
//            Transaction transaction = transactions.get(0);
//            transactionRepository.save(transaction);
//
//            Transaction newTransaction = Transaction.builder()
//                    .id(transaction.getId())
//                    .product(transaction.getProduct())
//                    .userId(transaction.getUserId())
//                    .quantity(6)
//                    .build();
//
//            Transaction results = transactionRepository.save(newTransaction);
//
//            Transaction findResult = transactionRepository.findById(transactions.get(0).getId().toString());
//
//            assertEquals(transaction.getId(), results.getId());
//            assertEquals(transaction.getUserId(), findResult.getUserId());
//            assertEquals(transaction.getProduct(), findResult.getProduct());
//            assertEquals(6, findResult.getQuantity());
//            assertEquals(transaction.getDeliveryStatus(), findResult.getDeliveryStatus());
//        }
//
//
//    }