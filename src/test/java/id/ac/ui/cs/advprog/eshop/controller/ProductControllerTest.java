package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductController controller;

    private Product sample;

    @BeforeEach
    void setup() {
        sample = new Product();
        sample.setProductId("p1");
        sample.setProductName("Sample");
        sample.setProductQuantity(3);
    }

    @Test
    void testCreateProductPage() {
        Model model = new ConcurrentModel();
        String view = controller.createProductPage(model);
        assertEquals("CreateProduct", view);
        assertNotNull(model.getAttribute("product"));
    }

    @Test
    void testCreateProductPostSample() {
        Model model = new ConcurrentModel();
        String view = controller.createProductPost(sample, model);
        verify(service).create(sample);
        assertEquals("redirect:/product/list", view);
    }

    @Test
    void testProductListPage() {
        List<Product> list = new ArrayList<>();
        list.add(sample);
        when(service.findAll()).thenReturn(list);

        Model model = new ConcurrentModel();
        String view = controller.productListPage(model);

        assertEquals("ProductList", view);
        assertSame(list, model.getAttribute("products"));
    }

    @Test
    void testEditProductPageNotFound() {
        when(service.findById("nope")).thenReturn(null);
        Model model = new ConcurrentModel();
        String view = controller.editProductPage("nope", model);
        assertEquals("redirect:/product/list", view);
    }

    @Test
    void testEditProductPageFound() {
        when(service.findById("p1")).thenReturn(sample);
        Model model = new ConcurrentModel();
        String view = controller.editProductPage("p1", model);
        assertEquals("EditProduct", view);
        assertSame(sample, model.getAttribute("product"));
    }

    @Test
    void testEditProductPost() {
        String view = controller.editProductPost(sample);
        assertEquals("redirect:/product/list", view);
    }

    @Test
    void testDeleteProduct() {
        String view = controller.deleteProduct("p1");
        assertEquals("redirect:/product/list", view);
    }
}
