package pl.snowboard4humans.service;

import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsPL;

public class SuperService {

    protected String getRequestDispatcher(Model model,
                                        String message,
                                        String objectName,
                                        Object object,
                                        String url) {
        model.addAttribute(ConstantsPL.MESSAGE, message);
        model.addAttribute(objectName, object);

        return url;
    }

}
