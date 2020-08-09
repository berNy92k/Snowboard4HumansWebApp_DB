package pl.snowboard4humans.constants;

public interface ConstantsFrontendPL {

    // ---------- LOGIN / REGISTER ----------
    String LOGIN_FORM_PAGE = "/homepage/logIn";
    String LOGIN_CUSTOMER_OBJECT = "newLoginCustomer";

    String REGISTER_FORM_PAGE = "/homepage/register";
    String REGISTER_CUSTOMER_OBJECT = "newRegisterCustomer";

    String LOGGED_CUSTOMER = "customerLogged";
    String LOGIN_FAILED = "Zły email bądź hasło. Spróbuj ponownie.";
    String LOGIN_SUCCESS = "Logowanie zakończone sukcesem.";

    String REGISTER_SUCCESS = "Rejestracja zakończona sukcesem. Zaloguj się.";
    String REGISTER_FAILED = "Rejestracja nie zakończyła się sukcesem. Email istnieje w bazie.";

    // -------------- HOMEPAGE --------------
    // URL
    String HOMEPAGE_URL = "homepage/index";
    String HOMEPAGE_PAGE = "";

    // MESSAGES
    String SEARCH_BY_WORD = "Wyszukane po słowie";

    // ------------- MANUFACTURER -------------
    // URL
    String MANUFACTURER_LIST_URL = "/homepage/manufacturer_list.html";
    String MANUFACTURER_LIST_OBJECT = "manufacturerList";

    // MESSAGES
    String LACK_OF_MANUFACTURER_IN_DB = "Brak informacji o producencie sprzętu - zapraszamy wkrótce";

    // -------------- EQUIPMENT --------------
    // URL
    String EQUIPMENT_LIST_URL = "/homepage/equipment_list.html";
    String EQUIPMENT_VIEW_URL = "/homepage/equipment_view.html";

    // MESSAGES
    String LACK_OF_EQUIPMENT_IN_DB = "Brak sprzętu w bazie - zapraszamy wkrótce";

    // OBJECT NAMES
    String EQUIPMENT_SHORT_LIST = "equipmentShortList";


    // --------------- REVIEW ---------------
    // URL
    String REVIEW_HOMEPAGE_CREATE_URL = "/homepage/review_create.html";

    // MESSAGES

    // --------------- SHOPPING CART ---------------
    // URL
    String SHOPPING_CART_HOMEPAGE_URL = "/homepage/shoppingCart.html";
    String SHOPPING_CART_CHECKOUT_HOMEPAGE_URL = "/homepage/shoppingCartCheckout.html";
    String SHOPPING_CART_FINAL_CHECKOUT_HOMEPAGE_URL = "/homepage/shoppingCartFinalCheckout.html";
    String PAYMENT_HOMEPAGE_URL = "/homepage/payment.html";

    // MESSAGES
    String SHOPPING_CART_EQUIPMENT_WAS_ADDED = "Dodano nowy sprzęt do koszyka";
    String SHOPPING_CART_EQUIPMENT_WAS_DELETED = "Usunięto sprzęt z koszyka";
    String SHOPPING_CART_EQUIPMENT_WAS_UPDATED = "Dokonano aktualizacji sprzętu z koszyka - proszę o weryfikację ilości oraz kwoty";
    String SHOPPING_CART_EQUIPMENT_WAS_CLEARED = "Wyczyszczono koszyk z zakupami";
    String SHOPPING_CART_CUSTOMER_NOT_LOGGED = "Użytkownik nie jest zalogowny. " +
            "Proszę o zalogowanie się a następnie powtórzenie czynności.";
    String TRANSACTION_FINISHED_WITH_SUCCESFULL = "Zamówienie przyjęte. " +
            "W ciąglu kilku najbliższych dniach przesyłka zostanie do Ciebie dostarczona " +
            "o czym zostaniesz poinformowany mailowo.";
    String TRANSACTION_FAILED = "Błąd w trakcie transakcji.";

    // --------------- MY ACCOUNT ---------------
    // URL
    String MY_ACCOUNT_VIEW_URL = "/homepage/myAccount_view.html";
    String MY_ACCOUNT_CREATE_URL = "/homepage/myAccount_create.html";

    // MESSAGES
    String MY_ACCOUNT_CUSTOMER_WAS_UPDATED = "Klient został zaktualizowany";
    String MY_ACCOUNT_CUSTOMER_WAS_NOT_UPDATED = "Klient nie został zaktualizowany. ";
    String MY_ACCOUNT_COULD_NOT_FIND_CUSTOMER_BY_ID = "Nie udało znaleźć się klienta z ID: ";
    String MY_ACCOUNT_CUSTOMER_NAME_ALREADY_EXIST_IN_DB = " --> Email klienta znajduje się już w bazie danych";
    String MY_ACCOUNT_DELETED_BY_ANOTHER_CUSTOMER_ADMIN = ", albo mogło zostać usunięte przez innego administratora.";
    String MY_ACCOUNT_CUSTOMER_WAS_DELETED = "Klient został usunięty.";

    // --------------- URL for controllers (all others) ---------------
    // URL - onas
    String O_NAS = "/onas/o-nas";
    String O_FIRMIE = "/onas/o-firmie";
    String PRACA = "/onas/praca";
    String SERWIS_I_WYPOZYCZALNIA = "/onas/serwis-i-wypozyczalnia";
    String NASZ_SKLEP_STACJONARNY = "/onas/nasz-sklep-stacjonarny";
    String REGULAMIN_SKLEPU = "/onas/regulamin-sklepu";
    String AKTUALNE_PROMOCJE = "/onas/aktualne-promocje";
    String KONTAKT = "/onas/kontakt";

    // URL - strefa-klienta
    String BLOG = "/strefa-klienta/blog";
    String DANE_KONTAKTOWE = "/strefa-klienta/dane-kontaktowe";
    String DOSTAWA_I_PLATNOSCI = "/strefa-klienta/dostawa-i-platnosci";
    String FAQ = "/strefa-klienta/faq";
    String ODBIOR_OSOBISTY = "/strefa-klienta/odbior-osobisty";
    String POLITYKA_PRYWATNOSCI = "/strefa-klienta/polityka-prywatnosci";
    String REKLAMACJE_I_ZWROTY = "/strefa-klienta/reklamacje-i-zwroty";
    String STREFA_KLIENTA = "/strefa-klienta/strefa-klienta";
    String ZAGRANICZNA_WYSYLKA = "/strefa-klienta/zagraniczna-wysylka";
}