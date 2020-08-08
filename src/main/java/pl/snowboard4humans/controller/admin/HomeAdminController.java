package pl.snowboard4humans.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.model.Equipment;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/admin")
public class HomeAdminController {

    @GetMapping
    public String getHomePage(Model model) {
        return "admin/index";
    }

    // onas - start
    @GetMapping(value = ConstantsFrontendPL.O_NAS)
    public String getAboutUsUrl() {
        return ConstantsFrontendPL.O_NAS;
    }

    @GetMapping(value = ConstantsFrontendPL.O_FIRMIE)
    public String getAboutCompanyUrl() {
        return ConstantsFrontendPL.O_FIRMIE;
    }

    @GetMapping(value = ConstantsFrontendPL.PRACA)
    public String getWorkUrl() {
        return ConstantsFrontendPL.PRACA;
    }

    @GetMapping(value = ConstantsFrontendPL.SERWIS_I_WYPOZYCZALNIA)
    public String getSIWUrl() {
        return ConstantsFrontendPL.SERWIS_I_WYPOZYCZALNIA;
    }

    @GetMapping(value = ConstantsFrontendPL.NASZ_SKLEP_STACJONARNY)
    public String getNSSUrl() {
        return ConstantsFrontendPL.NASZ_SKLEP_STACJONARNY;
    }

    @GetMapping(value = ConstantsFrontendPL.REGULAMIN_SKLEPU)
    public String getRSUrl() {
        return ConstantsFrontendPL.REGULAMIN_SKLEPU;
    }

    @GetMapping(value = ConstantsFrontendPL.AKTUALNE_PROMOCJE)
    public String getAPUrl() {
        return ConstantsFrontendPL.AKTUALNE_PROMOCJE;
    }

    @GetMapping(value = ConstantsFrontendPL.KONTAKT)
    public String getKUrl() {
        return ConstantsFrontendPL.KONTAKT;
    }
    // onas - stop

    // strefa-klienta - start
    @GetMapping(value = ConstantsFrontendPL.BLOG)
    public String getBlogUrl() {
        return ConstantsFrontendPL.BLOG;
    }

    @GetMapping(value = ConstantsFrontendPL.DANE_KONTAKTOWE)
    public String getDKUrl() {
        return ConstantsFrontendPL.DANE_KONTAKTOWE;
    }

    @GetMapping(value = ConstantsFrontendPL.DOSTAWA_I_PLATNOSCI)
    public String getDIPUrl() {
        return ConstantsFrontendPL.DOSTAWA_I_PLATNOSCI;
    }

    @GetMapping(value = ConstantsFrontendPL.FAQ)
    public String getFaqUrl() {
        return ConstantsFrontendPL.FAQ;
    }

    @GetMapping(value = ConstantsFrontendPL.ODBIOR_OSOBISTY)
    public String getOSUrl() {
        return ConstantsFrontendPL.ODBIOR_OSOBISTY;
    }

    @GetMapping(value = ConstantsFrontendPL.POLITYKA_PRYWATNOSCI)
    public String getPPUrl() {
        return ConstantsFrontendPL.POLITYKA_PRYWATNOSCI;
    }

    @GetMapping(value = ConstantsFrontendPL.REKLAMACJE_I_ZWROTY)
    public String getRIZUrl() {
        return ConstantsFrontendPL.REKLAMACJE_I_ZWROTY;
    }

    @GetMapping(value = ConstantsFrontendPL.STREFA_KLIENTA)
    public String getSKUrl() {
        return ConstantsFrontendPL.STREFA_KLIENTA;
    }

    @GetMapping(value = ConstantsFrontendPL.ZAGRANICZNA_WYSYLKA)
    public String getZWUrl() {
        return ConstantsFrontendPL.ZAGRANICZNA_WYSYLKA;
    }
    // strefa-klienta - stop
}
