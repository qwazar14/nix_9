package ua.com.alevel;

public class StringReverseUtil {
  private static String reversedString = "";

  public static String reverse(String text) { /**Reverse all string*/
    for (int i = text.length() - 1; i >= 0; i--) reversedString += text.charAt(i);
    return text.replaceAll(text, reversedString);
  }

  public static String reverse(String text, String part) { /**Reverse part of the string*/
    String reversedString = "";
    for (int i = part.length() - 1; i >= 0; i--) reversedString += part.charAt(i);
    return text.replaceAll(part, reversedString);
  }

  public static String reverse(String text, int firstIndex, int lastIndex) { /**Reverse part of the string by index*/
    String reversedString = "";
    String part = text.substring(firstIndex, lastIndex);
    for (int i = part.length() - 1; i >= 0; i--) reversedString += part.charAt(i);
    return text.replaceAll(part, reversedString);
  }
}
