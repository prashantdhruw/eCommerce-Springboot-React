# Full-Stack E-Commerce Platform (Spring Boot + React)

<img width="245" height="112" alt="image" src="https://github.com/user-attachments/assets/b21b454b-a11f-4e53-a71b-32789035288e" />


This is a complete, full-stack e-commerce web application built with a modern technology stack. The backend is powered by **Spring Boot**, providing a robust RESTful API, while the frontend is a dynamic and responsive single-page application built with **React** and **TypeScript**.

The application features user authentication, product catalog browsing, a fully functional shopping cart, and an order processing system.

## ✨ Features

### Backend (Spring Boot)
- **RESTful API**: Clean, well-structured API for all frontend interactions.
- **Authentication & Authorization**: Secure user authentication using Spring Security and JSON Web Tokens (JWT).
- **Role-Based Access Control**: Differentiated access for regular users (`ROLE_USER`) and administrators (`ROLE_ADMIN`).
- **Product Management**: Endpoints for fetching products with pagination, sorting, and filtering by category, price range, or search query.
- **Category Management**: API to list all product categories.
- **Order Management**: Users can create orders and view their order history. Admins can manage and update order statuses.
- **Data Persistence**: Uses Spring Data JPA and Hibernate with an in-memory H2 database.
- **Database Seeding**: `DataInitializer` populates the database with sample categories, products, and default users on startup for easy testing.

### Frontend (React)
- **Modern UI**: Clean, responsive, and user-friendly interface built with React and React Bootstrap.
- **TypeScript**: Ensures type safety and improves code quality and maintainability.
- **Component-Based Architecture**: Modular and reusable components for different UI elements.
- **Client-Side Routing**: Seamless navigation without page reloads using React Router.
- **Global State Management**: `AuthContext` and `CartContext` manage global state for authentication and shopping cart functionality.
- **Product Catalog**: Users can browse all products, filter by category, sort by price/name, and perform searches.
- **Shopping Cart**: Fully functional cart to add, update quantity, and remove items, with state persisted in `localStorage`.
- **User Authentication**: Secure login and registration forms that interact with the backend API.
- **Checkout Process**: A multi-step checkout flow for authenticated users to place orders.

## 🛠️ Tech Stack

| Component | Technology |
|---|---|
| **Backend** | Java 17, Spring Boot 3, Spring Security, Spring Data JPA, JWT, H2 Database, Maven |
| **Frontend**| React 19, TypeScript, React Router, React Bootstrap, Axios, Context API |
| **Database**| H2 (In-Memory) |

## 📂 Project Structure

The project is organized into two main modules within a monorepo structure:

```
└── prashantdhruw-ecommerce-springboot-react/
    ├── ecommerce-backend/      # Spring Boot REST API
    │   ├── src/main/java/      # Java source code
    │   ├── pom.xml             # Maven dependencies
    │   └── ...
    └── ecommerce-frontend/     # React TypeScript SPA
        ├── src/                # React source code
        ├── public/
        └── package.json        # NPM dependencies
```

## 🚀 Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- **Java Development Kit (JDK)**: Version 17 or later.
- **Maven**: Version 3.6+
- **Node.js**: Version 16.x or later.
- **npm**: Version 8.x or later (or yarn).

### Installation & Setup

1.  **Clone the repository:**
    ```bash
    git@github.com:your-username/eCommerce-Springboot-React.git
    cd eCommerce-Springboot-React
    ```

2.  **Run the Backend (Spring Boot):**
    ```bash
    # Navigate to the backend directory
    cd ecommerce-backend

    # Build and run the application using Maven
    mvn spring-boot:run
    ```
    - The backend server will start on `http://localhost:12000`.
    - The API will be available at `http://localhost:12000/api`.
    - You can access the H2 in-memory database console at `http://localhost:12000/h2-console` (JDBC URL: `jdbc:h2:mem:ecommerce`, User: `sa`, Pass: `password`).

3.  **Run the Frontend (React):**
    ```bash
    # Open a new terminal and navigate to the frontend directory
    cd ecommerce-frontend

    # Install dependencies
    npm install

    # Create a .env.development.local file in the root of the frontend directory
    # and add the following line:
    echo "REACT_APP_API_BASE_URL=http://localhost:12000/api" > .env.development.local

    # Start the React development server
    npm start
    ```
    - The frontend application will be accessible at `http://localhost:3000`.

## 🧑‍💻 Demo Credentials

The application is pre-populated with two demo users for easy testing:

-   **Admin User:**
    -   **Username:** `admin`
    -   **Password:** `admin123`
-   **Regular User:**
    -   **Username:** `user`
    -   **Password:** `user123`

## 📝 API Endpoints

Here are some of the key API endpoints provided by the backend:

| Method | Endpoint | Description | Access |
|---|---|---|---|
| `POST` | `/api/auth/signin` | Authenticate a user and get a JWT token. | Public |
| `POST` | `/api/auth/signup` | Register a new user. | Public |
| `GET` | `/api/products` | Get all products with pagination and sorting. | Public |
| `GET` | `/api/products/{id}` | Get a single product by its ID. | Public |
| `GET` | `/api/products/category/{categoryId}` | Get products by category ID. | Public |
| `GET` | `/api/products/search?name=...` | Search for products by name. | Public |
| `GET` | `/api/products/latest` | Get the 8 latest products. | Public |
| `GET` | `/api/categories` | Get all product categories. | Public |
| `POST`| `/api/orders` | Create a new order. | Authenticated |
| `GET` | `/api/orders/my-orders` | Get the authenticated user's order history. | Authenticated |
| `PUT` | `/api/orders/{id}/status` | Update the status of an order. | Admin Only |

## ⚙️ Configuration

-   **Backend**: Key settings like server port, database connection, and JWT secret are configured in `ecommerce-backend/src/main/resources/application.properties`.
-   **Frontend**: The API base URL is configured in `ecommerce-frontend/.env.development.local`.

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
