import java.util.*;
import java.util.stream.*;

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Product{name='" + name + "', category='" + category + "', price=" + price + "}";
    }
}

public class ProcessProducts {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("iPhone", "Electronics", 999.99),
                new Product("Samsung TV", "Electronics", 799.99),
                new Product("Nike Shoes", "Footwear", 120.00),
                new Product("Adidas Shoes", "Footwear", 110.00),
                new Product("Dell Laptop", "Electronics", 1200.00),
                new Product("Bata Sandals", "Footwear", 60.00)
        );

        // 1. Group by category
        System.out.println("Grouped by Category:");
        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        grouped.forEach((category, prodList) -> {
            System.out.println(category + ": " + prodList);
        });

        // 2. Most expensive product per category
        System.out.println("\nMost Expensive Product per Category:");
        Map<String, Optional<Product>> maxPriceProduct = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))
                ));
        maxPriceProduct.forEach((category, product) ->
                System.out.println(category + ": " + product.orElse(null)));

        // 3. Average price of all products
        double avgPrice = products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
        System.out.println("\nAverage Price of All Products: " + avgPrice);
    }
}
