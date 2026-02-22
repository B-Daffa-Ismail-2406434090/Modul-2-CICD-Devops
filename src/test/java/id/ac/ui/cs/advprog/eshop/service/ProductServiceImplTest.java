package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product p1;

    @BeforeEach
    void setup() {
        p1 = new Product();
        p1.setProductId("id1");
        p1.setProductName("Name1");
        p1.setProductQuantity(5);
    }

    @Test
    void testCreate() {
        when(productRepository.create(p1)).thenReturn(p1);

        Product res = productService.create(p1);

        assertSame(p1, res);
    }

    @Test
    void testFindAll() {
        Product p2 = new Product();
        p2.setProductId("id2");
        p2.setProductName("Name2");
        p2.setProductQuantity(10);

        List<Product> backing = new ArrayList<>();
        backing.add(p1);
        backing.add(p2);

        when(productRepository.findAll()).thenReturn(backing.iterator());

        List<Product> all = productService.findAll();

        assertEquals(2, all.size());
        assertEquals("id1", all.get(0).getProductId());
        assertEquals("id2", all.get(1).getProductId());
    }

    @Test
    void testFindById() {
        when(productRepository.findById("id1")).thenReturn(p1);
        Product res = productService.findById("id1");
        assertSame(p1, res);
    }

    @Test
    void testEdit() {
        when(productRepository.edit(p1)).thenReturn(p1);
        Product res = productService.edit(p1);
        assertSame(p1, res);

    }

    @Test
    void testDelete() {
        productService.delete("id1");
    }
}
