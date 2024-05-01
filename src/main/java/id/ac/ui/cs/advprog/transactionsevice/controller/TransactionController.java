package id.ac.ui.cs.advprog.transactionsevice.controller;
import id.ac.ui.cs.advprog.transactionsevice.model.Transaction;
import id.ac.ui.cs.advprog.transactionsevice.model.TransactionRequest;
import id.ac.ui.cs.advprog.transactionsevice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("")
    public String goToHomePage(Model model){
        return "homePage";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProductPost(@ModelAttribute TransactionRequest transactionRequest, Model model){
        try {
            transactionService.addTransaction(transactionRequest);
            return new ResponseEntity<>("Transaction created successfully.", HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Invalid data provided: " + ex.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
        } catch (Exception ex) {
            return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    @GetMapping("/create")
    public String createProductPage(Model model){
        TransactionRequest transactionRequest = new TransactionRequest();
        model.addAttribute("transactionRequest", transactionRequest);
        return "createTransaction";
    }
}
