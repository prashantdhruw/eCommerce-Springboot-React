import React, { useState, useEffect } from 'react';
import { Row, Col, Card, Button, Container, Carousel } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';
import { Product } from '../types';
import { productsAPI } from '../services/api';
import { useCart } from '../context/CartContext';

const Home: React.FC = () => {
  const [latestProducts, setLatestProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(true);
  const { addToCart } = useCart();

  useEffect(() => {
    const fetchLatestProducts = async () => {
      try {
        const products = await productsAPI.getLatest();
        setLatestProducts(products);
      } catch (error) {
        console.error('Error fetching latest products:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchLatestProducts();
  }, []);

  const handleAddToCart = (product: Product) => {
    addToCart(product);
  };

  if (loading) {
    return (
      <Container className="text-center mt-5">
        <div className="spinner-border" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      </Container>
    );
  }

  return (
    <div>
      {/* Hero Section */}
      <Carousel className="mb-5">
        <Carousel.Item>
          <div 
            className="d-block w-100 bg-primary text-white text-center py-5"
            style={{ height: '400px', display: 'flex', alignItems: 'center', justifyContent: 'center' }}
          >
            <div>
              <h1>Welcome to E-Shop</h1>
              <p className="lead">Discover amazing products at great prices</p>
              <LinkContainer to="/products">
                <Button variant="light" size="lg">Shop Now</Button>
              </LinkContainer>
            </div>
          </div>
        </Carousel.Item>
        <Carousel.Item>
          <div 
            className="d-block w-100 bg-success text-white text-center py-5"
            style={{ height: '400px', display: 'flex', alignItems: 'center', justifyContent: 'center' }}
          >
            <div>
              <h1>Latest Electronics</h1>
              <p className="lead">Check out our newest tech gadgets</p>
              <LinkContainer to="/products">
                <Button variant="light" size="lg">Explore</Button>
              </LinkContainer>
            </div>
          </div>
        </Carousel.Item>
        <Carousel.Item>
          <div 
            className="d-block w-100 bg-info text-white text-center py-5"
            style={{ height: '400px', display: 'flex', alignItems: 'center', justifyContent: 'center' }}
          >
            <div>
              <h1>Free Shipping</h1>
              <p className="lead">On orders over $50</p>
              <LinkContainer to="/products">
                <Button variant="light" size="lg">Start Shopping</Button>
              </LinkContainer>
            </div>
          </div>
        </Carousel.Item>
      </Carousel>

      {/* Latest Products Section */}
      <Container>
        <h2 className="mb-4">Latest Products</h2>
        <Row>
          {latestProducts.slice(0, 8).map((product) => (
            <Col key={product.id} md={6} lg={3} className="mb-4">
              <Card className="h-100 product-card">
                <Card.Img 
                  variant="top" 
                  src={product.imageUrl} 
                  className="product-image"
                />
                <Card.Body className="d-flex flex-column">
                  <Card.Title className="text-truncate">{product.name}</Card.Title>
                  <Card.Text className="text-muted small flex-grow-1">
                    {product.description.length > 100 
                      ? `${product.description.substring(0, 100)}...` 
                      : product.description
                    }
                  </Card.Text>
                  <div className="mt-auto">
                    <div className="d-flex justify-content-between align-items-center mb-2">
                      <h5 className="price-display mb-0">${product.price}</h5>
                      <small className="text-muted">Stock: {product.stockQuantity}</small>
                    </div>
                    <div className="d-grid gap-2">
                      <LinkContainer to={`/products/${product.id}`}>
                        <Button variant="outline-primary" size="sm">View Details</Button>
                      </LinkContainer>
                      <Button 
                        variant="primary" 
                        size="sm"
                        onClick={() => handleAddToCart(product)}
                        disabled={product.stockQuantity === 0}
                      >
                        {product.stockQuantity === 0 ? 'Out of Stock' : 'Add to Cart'}
                      </Button>
                    </div>
                  </div>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
        
        <div className="text-center mt-4">
          <LinkContainer to="/products">
            <Button variant="primary" size="lg">View All Products</Button>
          </LinkContainer>
        </div>
      </Container>

      {/* Features Section */}
      <Container className="mt-5 pt-5 border-top">
        <Row className="text-center">
          <Col md={4} className="mb-4">
            <div className="bg-light p-4 rounded">
              <h4>🚚 Free Shipping</h4>
              <p>Free shipping on orders over $50</p>
            </div>
          </Col>
          <Col md={4} className="mb-4">
            <div className="bg-light p-4 rounded">
              <h4>🔒 Secure Payment</h4>
              <p>Your payment information is safe with us</p>
            </div>
          </Col>
          <Col md={4} className="mb-4">
            <div className="bg-light p-4 rounded">
              <h4>📞 24/7 Support</h4>
              <p>Get help whenever you need it</p>
            </div>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default Home;