package pl.snowboard4humans.utils;

import org.apache.commons.io.FileUtils;
import pl.snowboard4humans.constants.ConstantsUtils;

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
    // TODO - change to get relative path
    public static String getBase64Image(byte[] image) throws IOException {
        String base64Image;
        if (image != null) {
            base64Image = Base64.getEncoder().encodeToString(image);
        } else {
            byte[] pictureError = FileUtils.readFileToByteArray(new File(ConstantsUtils.PICTURE_ERROR));
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

        if (object == null) {
            return isEmpty;
        }

        if (object instanceof String) {
            if (!object.equals("") &&
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
}
