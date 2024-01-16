package com.b.simple.design.business.customer;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;

import java.math.BigDecimal;
import java.util.List;

public class CustomerBOImplRefactored implements CustomerBO {

    @Override
    public Amount getCustomerProductsSum(List<Product> products) throws DifferentCurrenciesException {


        // BigDecimal temp = BigDecimal.ZERO;

        if (products.size() == 0)
            return new AmountImpl(BigDecimal.ZERO, Currency.EURO);

        // Throw Exception If Any of the product has a currency different from
        // the first product

        if (!doAllProductsHaveSameCurrency(products)) {
            throw new DifferentCurrenciesException();
        }

        return calculateSumOfProducts(products);
    }

    private AmountImpl calculateSumOfProducts(List<Product> products) {

        Currency firstProductCurrency = products.get(0).getAmount().getCurrency();

        // Calculate Sum of Products
        BigDecimal sum = products.stream().map(product -> product.getAmount().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Create new product
        return new AmountImpl(sum, firstProductCurrency);
    }

    private boolean doAllProductsHaveSameCurrency(List<Product> products) throws DifferentCurrenciesException {

        Currency firstProductCurrency = products.get(0).getAmount().getCurrency();
        return products.stream()
                .map(product -> product.getAmount().getCurrency())
                .allMatch(currency -> currency.equals(firstProductCurrency));
        /*for (Product product : products) {
            boolean currencySameAsFirstProduct = product.getAmount()
                    .getCurrency().equals(products.get(0).getAmount()
                            .getCurrency());
            if (!currencySameAsFirstProduct) {
                throw new DifferentCurrenciesException();
            }
        }*/
    }
}