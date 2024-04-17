package id.ac.ui.cs.advprog.transactionsevice.model;

import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.Singular;

import java.util.Set;
import java.util.UUID;

@Getter @Setter
public class Product {
    private UUID id;
    private String name;
    private Set<Tag> tags;
    private String description;
    private String image;
    private double price;
    private double discountPrice;

    public Product() {}

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }
}