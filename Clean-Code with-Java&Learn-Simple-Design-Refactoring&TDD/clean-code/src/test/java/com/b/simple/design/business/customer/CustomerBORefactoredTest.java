package com.b.simple.design.business.customer;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testCustomerProductSum_TwoProductsDifferentCurrencies()
            throws DifferentCurrenciesException {

        Amount[] amounts = {
                new AmountImpl(new BigDecimal("5.0"), Currency.INDIAN_RUPEE),
                new AmountImpl(new BigDecimal("6.0"), Currency.EURO)
        };
        List<Product> products = createProductsWithAmounts(amounts);

        Assertions.assertThrows(DifferentCurrenciesException.class, () -> {
            customerBO.getCustomerProductsSum(products);

        });

        /*try {
            actual = customerBO.getCustomerProductsSum(products);
            fail("DifferentCurrenciesException is expected");
        } catch (DifferentCurrenciesException e) {

        }*/
    }

    @Test
    public void testCustomerProductSum_EmptyProducts() throws DifferentCurrenciesException {

        Amount actual = customerBO.getCustomerProductsSum(new ArrayList<>());

        Amount expected = new AmountImpl(BigDecimal.ZERO,Currency.EURO);
        assertAmount(expected, actual);
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