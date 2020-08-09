package pl.snowboard4humans.utils;

import org.apache.commons.io.FileUtils;
import pl.snowboard4humans.constants.ConstantsUtils;
import pl.snowboard4humans.enums.CategoryEnum;
import pl.snowboard4humans.enums.CategoryPLEnum;
import pl.snowboard4humans.enums.SexEnum;
import pl.snowboard4humans.enums.SexPLEnum;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class Utils {

    /**
     * Get base64Image.
     * If image != null return as String
     * If image == null return "pictureError.jpg" to avoid empty space and show for
     * administrator that picture is missing
     *
     * @param image -> byte[]
     * @return String
     * @throws IOException IOException
     */
    public static String getBase64Image(byte[] image) throws IOException {
        String base64Image;
        if (image != null) {
            base64Image = Base64.getEncoder().encodeToString(image);
        } else {
            String path = System.getProperty(ConstantsUtils.USER_DIR);
            byte[] pictureError = FileUtils.readFileToByteArray(new File(path + ConstantsUtils.PICTURE_ERROR));
            base64Image = Base64.getEncoder().encodeToString(pictureError);
        }
        return base64Image;
    }

    /**
     * Get boolean with information if object is empty.
     * For String, Long, Collections
     *
     * @param object -> object to check if is empty
     * @return boolean
     */
    // TODO - finish for all types
    public static boolean isEmpty(Object object) {
        boolean isEmpty = true;

        if (object == ConstantsUtils.NULL) {
            return isEmpty;
        }

        if (object instanceof String) {
            if (!object.equals(ConstantsUtils.EMPTY_MESSAGE) &&
                    ((String) object).length() > 0) {
                isEmpty = false;
            }
        } else if (object instanceof List) {
            if (((List) object).size() > 0) {
                isEmpty = false;
            }
        }

        return isEmpty;
    }

    /**
     * get sex name by equipmentSex
     *
     * @param equipmentSex
     * @return String
     */
    public static String choiceSex(String equipmentSex) {
        switch (equipmentSex) {
            case ConstantsUtils.ALL:
                return SexEnum.ALL.getSex();
            case ConstantsUtils.WOMAN:
                return SexEnum.WOMAN.getSex();
            case ConstantsUtils.MAN:
                return SexEnum.MAN.getSex();
            case ConstantsUtils.CHILD:
                return SexEnum.CHILD.getSex();
            default:
                return SexEnum.ZERO.getSex();
        }
    }

    /**
     * get sex name in polish language by equipmentSex
     *
     * @param equipmentSex
     * @return String
     */
    public static String plVersionOfSex(String equipmentSex) {
        switch (equipmentSex) {
            case ConstantsUtils.ALL:
                return SexPLEnum.ALL.getSex();
            case ConstantsUtils.WOMAN:
                return SexPLEnum.WOMAN.getSex();
            case ConstantsUtils.MAN:
                return SexPLEnum.MAN.getSex();
            case ConstantsUtils.CHILD:
                return SexPLEnum.CHILD.getSex();
            default:
                return SexPLEnum.ZERO.getSex();
        }
    }

    /**
     * get category number by equipmentCategory
     *
     * @param equipmentCategory
     * @return int
     */
    public static int choiceCategory(String equipmentCategory) {
        switch (equipmentCategory) {
            case ConstantsUtils.SNOWBOARDS:
                return CategoryEnum.SNOWBOARDS.getCategory();
            case ConstantsUtils.SNOWBOARD_SHOES:
                return CategoryEnum.SNOWBOARDSHOES.getCategory();
            case ConstantsUtils.SNOWBOARD_BINDINGS:
                return CategoryEnum.SNOWBOARDBINDINGS.getCategory();
            case ConstantsUtils.SNOWBOARD_GLOVES:
                return CategoryEnum.SNOWBOARDGLOVES.getCategory();
            case ConstantsUtils.SNOWBOARD_GOGGLES:
                return CategoryEnum.SNOWBOARDGOGGLES.getCategory();
            case ConstantsUtils.SNOWBOARD_HELMETS:
                return CategoryEnum.SNOWBOARDHELMETS.getCategory();
            case ConstantsUtils.THERMOACTIVE_CLOTHING:
                return CategoryEnum.THERMOACTIVECLOTHING.getCategory();
            default:
                return CategoryEnum.ZERO.getCategory();
        }
    }

    /**
     * get category name in polish language by equipmentCategory
     *
     * @param equipmentCategory
     * @return String
     */
    public static String plVersionOfCategory(String equipmentCategory) {
        switch (equipmentCategory) {
            case ConstantsUtils.SNOWBOARDS:
                return CategoryPLEnum.SNOWBOARDS.getCategory();
            case ConstantsUtils.SNOWBOARD_SHOES:
                return CategoryPLEnum.SNOWBOARDSHOES.getCategory();
            case ConstantsUtils.SNOWBOARD_BINDINGS:
                return CategoryPLEnum.SNOWBOARDBINDINGS.getCategory();
            case ConstantsUtils.SNOWBOARD_GLOVES:
                return CategoryPLEnum.SNOWBOARDGLOVES.getCategory();
            case ConstantsUtils.SNOWBOARD_GOGGLES:
                return CategoryPLEnum.SNOWBOARDGOGGLES.getCategory();
            case ConstantsUtils.SNOWBOARD_HELMETS:
                return CategoryPLEnum.SNOWBOARDHELMETS.getCategory();
            case ConstantsUtils.THERMOACTIVE_CLOTHING:
                return CategoryPLEnum.THERMOACTIVECLOTHING.getCategory();
            default:
                return CategoryPLEnum.ZERO.getCategory();
        }
    }
}
