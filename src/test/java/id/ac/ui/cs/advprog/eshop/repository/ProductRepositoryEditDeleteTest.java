package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryEditDeleteTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setup() {
    }

    @Test
    void testEditExistingProduct() {
        Product product = new Product();
        product.setProductName("OldName");
        product.setProductQuantity(10);
        productRepository.create(product);

        String id = product.getProductId();

        Product edited = new Product();
        edited.setProductId(id);
        edited.setProductName("NewName");
        edited.setProductQuantity(20);

        Product result = productRepository.edit(edited);
        assertEquals(id, result.getProductId());
        assertEquals("NewName", result.getProductName());
        assertEquals(20, result.getProductQuantity());

        Product saved = productRepository.findById(id);
        assertEquals("NewName", saved.getProductName());
    }

    @Test
    void testEditNonExistingProduct() {
        Product p = new Product();
        p.setProductId("nonExistantId");
        p.setProductName("Whatever");
        p.setProductQuantity(1);
        Product result = productRepository.edit(p);
        assertNull(result);
    }

    @Test
    void testDeleteExistingProduct() {
        Product product = new Product();
        product.setProductName("ToBeDeleted");
        product.setProductQuantity(5);
        productRepository.create(product);
        String id = product.getProductId();

        productRepository.delete(id);
        assertNull(productRepository.findById(id));

        Iterator<Product> it = productRepository.findAll();
        assertFalse(it.hasNext());
    }

    @Test
    void testDeleteNonExistingProduct() {
        Product product = new Product();
        product.setProductName("Keeps");
        product.setProductQuantity(3);
        productRepository.create(product);
        String id = product.getProductId();

        productRepository.delete("noSuchId");
        assertNotNull(productRepository.findById(id));
    }

    @Test
    void testUpdateSecondProductInList() {
        Product first = new Product();
        first.setProductName("First");
        first.setProductQuantity(1);
        productRepository.create(first);

        Product second = new Product();
        second.setProductName("Second");
        second.setProductQuantity(2);
        productRepository.create(second);

        String secondId = second.getProductId();

        Product edited = new Product();
        edited.setProductId(secondId);
        edited.setProductName("SecondEdited");
        edited.setProductQuantity(20);

        Product result = productRepository.edit(edited);
        assertEquals(secondId, result.getProductId());
        assertEquals("SecondEdited", result.getProductName());

        Product saved = productRepository.findById(secondId);
        assertEquals("SecondEdited", saved.getProductName());
    }
}
