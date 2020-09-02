package pl.snowboard4humans.service;

import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.model.Equipment;
import pl.snowboard4humans.repository.EquipmentRepo;

import java.util.LinkedList;
import java.util.List;

public class SuperService {

    protected String getRequestDispatcherWithDefaultMessage(Model model,
                                                            String message,
                                                            String objectName,
                                                            Object object,
                                                            String url) {
        model.addAttribute(ConstantsPL.MESSAGE, message);
        model.addAttribute(objectName, object);

        return url;
    }

    protected String getRequestDispatcherWithOutDefaultMessage(Model model,
                                                               String messageName,
                                                               String message,
                                                               String objectName,
                                                               Object object,
                                                               String url) {
        model.addAttribute(messageName, message);
        model.addAttribute(objectName, object);

        return url;
    }

    protected String getRequestDispatcherWithOutDefaultMessageAsBoolean(Model model,
                                                                        String messageName,
                                                                        boolean message,
                                                                        String objectName,
                                                                        Object object,
                                                                        String url) {
        model.addAttribute(messageName, message);
        model.addAttribute(objectName, object);

        return url;
    }

    protected List<Equipment> getTop4Equipments(List<Equipment> allEquipments) {
        List<Equipment> top4equipments = new LinkedList<>();
        if (allEquipments.size() <= 4) {
            top4equipments = allEquipments;
        } else {
            setRandomEquipmentsToList(allEquipments, top4equipments, true);
            if (top4equipments.size() < 4) {
                setRandomEquipmentsToList(allEquipments, top4equipments, false);
            }
        }
        return top4equipments;
    }

    private List<Equipment> setRandomEquipmentsToList(List<Equipment> allEquipments,
                                                        List<Equipment> top4equipments,
                                                        boolean getEvenNumbers) {
        int counter = 1;
        for (Equipment equipment : allEquipments) {
            if (getEvenNumbers) {
                if (counter % 2 == 0) {
                    top4equipments.add(equipment);
                }
            } else {
                if (counter % 2 != 0) {
                    top4equipments.add(equipment);
                }
            }

            counter++;
            if (top4equipments.size() >= 4) {
                break;
            }
        }

        return top4equipments;
    }

}
