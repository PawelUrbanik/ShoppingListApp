package pl.prk.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ProductTest {


    @Test
    public void getNameTest() {
        Product product = new Product();
        product.setName("masło");
        String expected = "masło";
        String actual = product.getName();
        assertEquals(expected, actual);
    }
}