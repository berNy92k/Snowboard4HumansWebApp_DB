package pl.snowboard4humans.model.shoppingCartAndPayment;

import pl.snowboard4humans.model.Equipment;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Equipment, Integer> shoppingCart;

    public ShoppingCart() {
        this.shoppingCart = new HashMap<>();
    }

    public void addEquipment(Equipment equipment) {
        if (shoppingCart.containsKey(equipment)) {
            Integer counter = shoppingCart.get(equipment);
            counter = counter + 1;
            shoppingCart.put(equipment, counter);
        } else {
            shoppingCart.put(equipment, 1);
        }
    }

    public void removeEquipmentByEquipment(Equipment equipment) {
        shoppingCart.remove(equipment);
    }

    public void removeEquipmentById(int id) {
        for (Map.Entry<Equipment, Integer> equipmentIntegerEntry : shoppingCart.entrySet()) {
            if (equipmentIntegerEntry.getKey().getId() == id) {
                shoppingCart.remove(equipmentIntegerEntry.getKey());
                break;
            }
        }
    }

    public int getTotalQuantity() {
        int sumQuantity = 0;
        for (Map.Entry<Equipment, Integer> equipments : shoppingCart.entrySet()) {
            sumQuantity = sumQuantity + equipments.getValue();
        }
        return sumQuantity;
    }

    public float getTotalAmount() {
        float sumAmount = 0;
        for (Map.Entry<Equipment, Integer> equipments : shoppingCart.entrySet()) {
            sumAmount = sumAmount + equipments.getValue() * equipments.getKey().getPrice();
        }
        return sumAmount;
    }

    public void updateShoppingCart(Map<Integer, Integer> eqIdAndQuantity) {
        for (Map.Entry<Integer, Integer> integerIntegerEntry : eqIdAndQuantity.entrySet()) {
            Equipment key = new Equipment();
            key.setId(integerIntegerEntry.getKey());
            Integer value = integerIntegerEntry.getValue();

            shoppingCart.put(key, value);
        }
    }

    public int getTotalQuantityOfEquipments() {
        return shoppingCart.size();
    }

    public void clearShoppingCart() {
        shoppingCart.clear();
    }

    public Map<Equipment, Integer> getShoppingCart() {
        return shoppingCart;
    }
}
