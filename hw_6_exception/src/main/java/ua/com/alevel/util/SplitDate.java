package ua.com.alevel.util;

public class SplitDate {
    static String[] split(String date, char separator) {
        String[] splitStr = (date + " ").split(String.valueOf(separator));
        if (splitStr[splitStr.length - 1] != null && splitStr[splitStr.length - 1].length() > 0) {
            splitStr[splitStr.length - 1] = splitStr[splitStr.length - 1].substring(0, splitStr[splitStr.length - 1].length() - 1);
        }
        return splitStr;
    }
}
