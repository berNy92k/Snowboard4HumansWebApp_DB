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
    if (isCartContainEquipment(equipment)) {
      Object[] objects = getAndRemoveFromMap(equipment);
      Integer counter = (Integer) objects[0] + 1;
      Equipment equipmentToAdd = (Equipment) objects[1];
      shoppingCart.put(equipmentToAdd, counter);
    } else {
      shoppingCart.put(equipment, 1);
    }
  }

  public void removeEquipmentByEquipment(Equipment equipment) {
    getAndRemoveFromMap(equipment);
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

  //TODO
  public void updateShoppingCart(Map<Integer, Integer> eqIdAndQuantity) {
    for (Map.Entry<Integer, Integer> integerIntegerEntry : eqIdAndQuantity.entrySet()) {
      Equipment key = new Equipment();
      key.setId(integerIntegerEntry.getKey());
      Integer value = integerIntegerEntry.getValue();

      shoppingCart.put(key, value);
    }
  }

  public void updateShoppingCart() {
    Map<Equipment, Integer> shoppingCart = getShoppingCart();
  }

  public int getTotalQuantityOfEquipments() {
    return shoppingCart.size();
  }

  public void clearShoppingCart() {
    shoppingCart.clear();
  }

  public void setShoppingCart(Map<Equipment, Integer> shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  public Map<Equipment, Integer> getShoppingCart() {
    return shoppingCart;
  }

  private boolean isCartContainEquipment(Equipment equipment) {
    boolean isCartContainEquipment = false;
    for (Map.Entry<Equipment, Integer> shoppingCartMap : shoppingCart.entrySet()) {
      Equipment equipmentFromMap = shoppingCartMap.getKey();
      if (equipmentFromMap.getId().equals(equipment.getId())) {
        isCartContainEquipment = true;
        break;
      }
    }

    return isCartContainEquipment;
  }

  private Object[] getAndRemoveFromMap(Equipment equipment) {
    Object[] objects = new Object[2];
    Equipment equipmentFromMap = null;
    Integer equipmenCounter = null;
    for (Map.Entry<Equipment, Integer> shoppingCartMap : shoppingCart.entrySet()) {
      equipmentFromMap = shoppingCartMap.getKey();
      equipmenCounter = shoppingCartMap.getValue();
      if (equipmentFromMap.getId().equals(equipment.getId())) {
        shoppingCart.remove(equipmentFromMap);
        break;
      }
    }

    objects[0] = equipmenCounter;
    objects[1] = equipmentFromMap;

    return objects;
  }
}
