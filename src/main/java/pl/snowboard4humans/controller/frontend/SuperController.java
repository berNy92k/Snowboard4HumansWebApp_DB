package pl.snowboard4humans.controller.frontend;

import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsPL;

public class SuperController {

  protected String getRequestDispatcherWithDefaultMessage(final Model model,
                                                          final String message,
                                                          final String objectName,
                                                          final Object object,
                                                          final String url) {
    model.addAttribute(ConstantsPL.MESSAGE, message);
    model.addAttribute(objectName, object);

    return url;
  }

  protected String getRequestDispatcherWithOutDefaultMessage(final Model model,
                                                             final String messageName,
                                                             final String message,
                                                             final String objectName,
                                                             final Object object,
                                                             final String url) {
    model.addAttribute(messageName, message);
    model.addAttribute(objectName, object);

    return url;
  }

  protected String getRequestDispatcherWithOutDefaultMessageAsBoolean(final Model model,
                                                                      final String messageName,
                                                                      final boolean message,
                                                                      final String objectName,
                                                                      final Object object,
                                                                      final String url) {
    model.addAttribute(messageName, message);
    model.addAttribute(objectName, object);

    return url;
  }

}
