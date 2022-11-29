package za.co.metropolitan.getup.client.helper;

import org.apache.commons.lang3.StringUtils;
import java.util.stream.Stream;

public class ServiceHelper {

    public static int convertToconsentTypeId(String consentType){

        int id = 0;

        switch(consentType) {

            case "New Solutions":
                return -1;
            case "Share Info Internally":
                return -2;
            case "Marketing Research":
                return -3;
        }

        return id;
    }

    public static String titleCaseConversion(String inputString)
    {
        if (StringUtils.isBlank(inputString)) {
            return "";
        }

        if (StringUtils.length(inputString) == 1) {
            return inputString.toUpperCase();
        }

        StringBuffer resultPlaceHolder = new StringBuffer(inputString.length());

        Stream.of(inputString.split(" ")).forEach(stringPart -> {
            char[] charArray = stringPart.toLowerCase().toCharArray();
            charArray[0] = Character.toUpperCase(charArray[0]);
            resultPlaceHolder.append(new String(charArray)).append(" ");
        });

        return StringUtils.trim(resultPlaceHolder.toString());
    }
}

