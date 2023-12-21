package clean_code.a.introduction.gildedrose;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/24 16:15
 */

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "\n name=" + name + ", " + sellIn + ", " + quality + "\n" ;
    }


}
