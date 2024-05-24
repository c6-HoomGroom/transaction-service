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

import java.util.List;

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
    public String createProductPost(@ModelAttribute Transaction transactionRequest, Model model) {
            transactionService.addTransaction(transactionRequest);
            return "redirect:/listTransaction";
    }


    @GetMapping("/create")
    public String createProductPage(Model model){
        TransactionRequest transactionRequest = new TransactionRequest();
        model.addAttribute("transactionRequest", transactionRequest);
        return "createTransaction";
    }

    @GetMapping("/listTransaction")
    public String transactionListPage(Model model) {
        List<Transaction> allTransacions = transactionService.getAllTransactions();
        model.addAttribute("transactions", allTransacions);
        return "listTransactions";
    }
}
