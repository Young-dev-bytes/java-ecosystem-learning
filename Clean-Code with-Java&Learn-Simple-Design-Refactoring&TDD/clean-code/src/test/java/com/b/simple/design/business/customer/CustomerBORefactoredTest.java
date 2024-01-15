package com.b.simple.design.business.customer;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CustomerBORefactoredTest {

    private CustomerBO customerBO = new CustomerBOImpl();

    @Test
    public void testCustomerProductSum_TwoProductsSameCurrencies()
            throws DifferentCurrenciesException {

        Amount[] amounts = {
                new AmountImpl(new BigDecimal("5.0"), Currency.EURO),
                new AmountImpl(new BigDecimal("6.0"), Currency.EURO)
        };


        // set up
        List<Product> products = createProductsWithAmounts(amounts);


        // invoke
        Amount actual = customerBO.getCustomerProductsSum(products);
        Amount expected = new AmountImpl(new BigDecimal("11.0"), Currency.EURO);


        // verify
        assertAmount(expected, actual);
    }


    @Test
    public void testCustomerProductSum1() {

        List<Product> products = new ArrayList<Product>();

        products.add(new ProductImpl(100, "Product 15",
                ProductType.BANK_GUARANTEE,
                new AmountImpl(new BigDecimal("5.0"), Currency.INDIAN_RUPEE)));

        products.add(
                new ProductImpl(120, "Product 20", ProductType.BANK_GUARANTEE,
                        new AmountImpl(new BigDecimal("6.0"), Currency.EURO)));

        @SuppressWarnings("unused")
        Amount temp = null;

        try {
            temp = customerBO.getCustomerProductsSum(products);
            fail("DifferentCurrenciesException is expected");
        } catch (DifferentCurrenciesException e) {
        }
    }

    @Test
    public void testCustomerProductSum2() {

        List<Product> products = new ArrayList<Product>();

        Amount temp = null;

        try {
            temp = customerBO.getCustomerProductsSum(products);
        } catch (DifferentCurrenciesException e) {
        }
        assertEquals(Currency.EURO, temp.getCurrency());
        assertEquals(BigDecimal.ZERO, temp.getValue());
    }


    private void assertAmount(Amount expected, Amount actual) {
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getValue(), actual.getValue());
    }

    private List<Product> createProductsWithAmounts(Amount[] amounts) {

        /*List<Product> products = new ArrayList<>();
        for (Amount amount : amounts) {
            products.add(new ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE, amount));
        }
        return products;*/

        return Arrays.stream(amounts)
                .map(amount -> new ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE, amount))
                .collect(Collectors.toList());
    }

}