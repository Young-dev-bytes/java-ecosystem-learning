package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseARefactoredItemTest {

    public static final int NOT_EXPIRED_SELLIN = 15;
    public static final int DEFAULT_QUALITY = 3;
    public static final int MAXIMUM_QUALITY = 50;
    public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    public static final int EXPIRED_SELLIN = -1;
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final int SELLIN_GREATER_THAN_10 = 15;
    private static final int SELLIN_BETWEEN_5_AND_10 = 7;
    private static final int POSITIVE_SELLIN_LESS_THAN_5 = 2;


    /**
     * Method to test the variation in quality of the item for the non expired
     * Item.
     * <p>
     * The quality should decrease by 1 when the item is not expired
     * and sell in should decrease by 1.
     */
    @Test
    public void unexpiredDefaultItem_qualityDecreaseByOne() {

        // setup
        GildedRose app = createGlidedRoseWithOneItem(DEFAULT_ITEM, NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);

        // invoke
        app.updateQuality();

        // verify
        Item actual = app.items[0];
        Item expected = new Item(DEFAULT_ITEM, NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 1);
        assertItem(expected, actual);
    }


    /**
     * Method to test the variation in quality of the item for the non expired
     * Item.
     * <p>
     * The quality should decrease by 2 when the item is expired(Sell in  < 0) and sell in should decrease by 1.
     */
    @Test
    public void expiredDefaultItem_qualityDecreaseByTwo() {

        // set up
        GildedRose app = createGlidedRoseWithOneItem(DEFAULT_ITEM, EXPIRED_SELLIN, DEFAULT_QUALITY);

        // invoke
        app.updateQuality();

        // verify
        Item actual = app.items[0];
        Item expected = new Item(DEFAULT_ITEM, EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 2);
        assertItem(expected, actual);
    }


    @Test
    public void unexpiredAgedBrie_qualityIncreasesByOne() {

        // set up
        GildedRose app = createGlidedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);

        // invoke
        app.updateQuality();

        // verify
        Item actual = app.items[0];
        Item expected = new Item(AGED_BRIE, NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 1);
        assertItem(expected, actual);
    }

    @Test
    public void expiredAgedBrie_qualityIncreasesByTwo() {
        // set up
        GildedRose app = createGlidedRoseWithOneItem(AGED_BRIE, EXPIRED_SELLIN, DEFAULT_QUALITY);

        // invoke
        app.updateQuality();

        // verify
        Item actual = app.items[0];
        Item expected = new Item(AGED_BRIE, EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 2);
        assertItem(expected, actual);
    }

    @Test
    public void unexpiredAgedBrie_qualityDoesNotGoBeyondMaximum() {
        // set up
        GildedRose app = createGlidedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELLIN, MAXIMUM_QUALITY);

        // invoke
        app.updateQuality();

        // verify
        Item actual = app.items[0];
        Item expected = new Item(AGED_BRIE, NOT_EXPIRED_SELLIN - 1, MAXIMUM_QUALITY);
        assertItem(expected, actual);
    }


    @Test
    public void backstagePassesBeyond10Days_qualityIncreasesByOne() {
        // set up
        GildedRose app = createGlidedRoseWithOneItem(BACKSTAGE_PASSES, SELLIN_GREATER_THAN_10, DEFAULT_QUALITY);

        // invoke
        app.updateQuality();

        // verify
        Item actual = app.items[0];
        Item expected = new Item(BACKSTAGE_PASSES, SELLIN_GREATER_THAN_10 - 1, DEFAULT_QUALITY + 1);
        assertItem(expected, actual);
    }

    @Test
    public void backstagePassesBetween5And10Days_qualityIncreasesByTwo() {
        // set up
        GildedRose app = createGlidedRoseWithOneItem(BACKSTAGE_PASSES, SELLIN_BETWEEN_5_AND_10, DEFAULT_QUALITY);

        // invoke
        app.updateQuality();

        // verify
        Item actual = app.items[0];
        Item expected = new Item(BACKSTAGE_PASSES, SELLIN_BETWEEN_5_AND_10 - 1, DEFAULT_QUALITY + 2);
        assertItem(expected, actual);
    }

    @Test
    public void backStageLessThan5Days_qualityIncreasesByThree() {
        // set up
        GildedRose app = createGlidedRoseWithOneItem(BACKSTAGE_PASSES, POSITIVE_SELLIN_LESS_THAN_5, DEFAULT_QUALITY);

        // invoke
        app.updateQuality();

        // verify
        Item actual = app.items[0];
        Item expected = new Item(BACKSTAGE_PASSES, POSITIVE_SELLIN_LESS_THAN_5 - 1, DEFAULT_QUALITY + 3);
        assertItem(expected, actual);
    }


    private void assertItem(Item expected, Item actual) {
        assertEquals(expected.name, actual.name);
        assertEquals(expected.sellIn, actual.sellIn);
        assertEquals(expected.quality, actual.quality);
    }

    private GildedRose createGlidedRoseWithOneItem(String itemType, int sellin, int quality) {
        Item item = new Item(itemType, sellin, quality);
        Item[] items = new Item[]{item};
        return new GildedRose(items);
    }
}