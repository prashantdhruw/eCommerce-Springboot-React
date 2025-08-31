package com.ecommerce.backend.config;

import com.ecommerce.backend.entity.Category;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repository.CategoryRepository;
import com.ecommerce.backend.repository.ProductRepository;
import com.ecommerce.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        // Create Categories
        Category electronics = new Category("Electronics", "Electronic devices and gadgets");
        Category clothing = new Category("Clothing", "Fashion and apparel");
        Category books = new Category("Books", "Books and literature");
        Category home = new Category("Home & Garden", "Home improvement and garden supplies");
        Category sports = new Category("Sports", "Sports and outdoor equipment");

        categoryRepository.save(electronics);
        categoryRepository.save(clothing);
        categoryRepository.save(books);
        categoryRepository.save(home);
        categoryRepository.save(sports);

        // Create Products
        // Electronics
        productRepository.save(new Product("iPhone 15 Pro", "Latest Apple smartphone with advanced features", 
            new BigDecimal("999.99"), 50, "https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=400", electronics));
        
        productRepository.save(new Product("Samsung Galaxy S24", "Premium Android smartphone with excellent camera", 
            new BigDecimal("899.99"), 45, "https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=400", electronics));
        
        productRepository.save(new Product("MacBook Air M2", "Lightweight laptop with Apple M2 chip", 
            new BigDecimal("1199.99"), 30, "https://images.unsplash.com/photo-1541807084-5c52b6b3adef?w=400", electronics));
        
        productRepository.save(new Product("Sony WH-1000XM5", "Premium noise-canceling headphones", 
            new BigDecimal("399.99"), 75, "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400", electronics));

        // Clothing
        productRepository.save(new Product("Classic White T-Shirt", "100% cotton comfortable t-shirt", 
            new BigDecimal("19.99"), 100, "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=400", clothing));
        
        productRepository.save(new Product("Denim Jeans", "Classic blue denim jeans", 
            new BigDecimal("59.99"), 80, "https://images.unsplash.com/photo-1542272604-787c3835535d?w=400", clothing));
        
        productRepository.save(new Product("Leather Jacket", "Genuine leather jacket for style", 
            new BigDecimal("199.99"), 25, "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=400", clothing));
        
        productRepository.save(new Product("Running Shoes", "Comfortable athletic shoes for running", 
            new BigDecimal("89.99"), 60, "https://images.unsplash.com/photo-1549298916-b41d501d3772?w=400", clothing));

        // Books
        productRepository.save(new Product("The Great Gatsby", "Classic American novel by F. Scott Fitzgerald", 
            new BigDecimal("12.99"), 200, "https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=400", books));
        
        productRepository.save(new Product("To Kill a Mockingbird", "Timeless novel by Harper Lee", 
            new BigDecimal("14.99"), 150, "https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=400", books));
        
        productRepository.save(new Product("1984", "Dystopian novel by George Orwell", 
            new BigDecimal("13.99"), 180, "https://images.unsplash.com/photo-1495640388908-05fa85288e61?w=400", books));

        // Home & Garden
        productRepository.save(new Product("Coffee Maker", "Programmable drip coffee maker", 
            new BigDecimal("79.99"), 40, "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?w=400", home));
        
        productRepository.save(new Product("Indoor Plant Set", "Set of 3 low-maintenance indoor plants", 
            new BigDecimal("49.99"), 35, "https://images.unsplash.com/photo-1416879595882-3373a0480b5b?w=400", home));
        
        productRepository.save(new Product("Kitchen Knife Set", "Professional chef knife set", 
            new BigDecimal("129.99"), 20, "https://images.unsplash.com/photo-1593618998160-e34014e67546?w=400", home));

        // Sports
        productRepository.save(new Product("Yoga Mat", "Non-slip exercise yoga mat", 
            new BigDecimal("29.99"), 90, "https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?w=400", sports));
        
        productRepository.save(new Product("Basketball", "Official size basketball", 
            new BigDecimal("24.99"), 70, "https://images.unsplash.com/photo-1546519638-68e109498ffc?w=400", sports));
        
        productRepository.save(new Product("Tennis Racket", "Professional tennis racket", 
            new BigDecimal("149.99"), 15, "https://images.unsplash.com/photo-1551698618-1dfe5d97d256?w=400", sports));

        // Create default admin user
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User("admin", "admin@ecommerce.com", 
                passwordEncoder.encode("admin123"), "Admin", "User");
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);
        }

        // Create default regular user
        if (!userRepository.existsByUsername("user")) {
            User user = new User("user", "user@ecommerce.com", 
                passwordEncoder.encode("user123"), "John", "Doe");
            user.setAddress("123 Main St, City, State 12345");
            user.setPhone("555-0123");
            userRepository.save(user);
        }
    }
}