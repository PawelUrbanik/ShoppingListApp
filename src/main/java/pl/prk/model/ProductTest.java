package pl.prk.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit5 Tests.
 * @author ...
 */

class ProductTest {


    @Test
    public void testGetterAndSetterOfId() {
        Product product = new Product();
        product.setId(1);
        Integer expected = 1;
        Integer actual = product.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetterAndSetterOfName() {
        Product product = new Product();
        product.setName("masło");
        String expected = "masło";
        String actual = product.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetterAndSetterOfListId() {
        Product product = new Product();
        product.setListId(2);
        Integer expected = 2;
        Integer actual = product.getListId();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetterAndSetterOfIsBought() {
        Product product = new Product();
        product.setBought(true);
        assertTrue(product.isBought());
    }

    @Test
    public void testGetterAndSetterOfAddedBy() {
        Product product = new Product();
        product.setAddedBy("Paweł");
        String expected = "Paweł";
        String actual = product.getAddedBy();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetterAndSetterOfSetCount() {
        Product product = new Product();
        product.setCount(9);
        int expected = 9;
        int actual = product.getCount();
        assertEquals(expected, actual);
    }
}