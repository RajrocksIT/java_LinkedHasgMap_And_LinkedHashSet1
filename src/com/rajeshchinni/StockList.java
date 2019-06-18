package com.rajeshchinni;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    // constructor. select none
    public StockList() {
        this.list = new HashMap<>();
    }

    // method
    public int addStock(StockItem item){
        if (item != null){
            // check if already have quantities of this item
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // if there are already stocks on this item, adjust the quantity
            if (inStock != item){
                item.adjustStock(inStock.quantityInStock());
            }
            list.put(item.getName(), item);
            return item.quantityInStock();
        }
        return 0;
    }

    // method
    public int sellStock(String item, int quantity){
        StockItem inStock = list.getOrDefault(item, null);
        if ((quantity > 0) && (inStock != null) && (inStock.quantityInStock() >= quantity) ){
            inStock.adjustStock(-quantity);
            return quantity;
        }
        return 0;
    }

    public StockItem get(String key){
        return list.get(key);
    }

    public Map<String, StockItem> Items(){
        return Collections.unmodifiableMap(list);  // https://www.geeksforgeeks.org/collections-unmodifiablelist-method-in-java-with-examples/
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item: list.entrySet() ){  // https://www.geeksforgeeks.org/map-entry-interface-java-example/
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();

            s = s + stockItem + ". There are " + stockItem.quantityInStock() + " in stock. Value of items:";
            s = s + String.format("%.2f",itemValue) + "\n";      // changes made here
            totalCost = totalCost + itemValue;
        }
        return s + "Total stock value " + totalCost;
    }
}
