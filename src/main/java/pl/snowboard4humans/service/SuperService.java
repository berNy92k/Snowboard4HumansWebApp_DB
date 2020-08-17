package pl.snowboard4humans.service;

import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsPL;

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

}
