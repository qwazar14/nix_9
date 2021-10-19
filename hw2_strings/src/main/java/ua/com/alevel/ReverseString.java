package ua.com.alevel;

public class ReverseString {
    public static String reverse(String src) {
        char[] letters = src.toCharArray();
        StringBuilder newSrc = new StringBuilder();
        for (int i = letters.length - 1; i >= 0; i--) {
            newSrc.append(letters[i]);
        }
        return newSrc.toString();
    }

    public static String reverse1(String src, String dest){
        int firstIndex = src.indexOf(dest.charAt(0));
        int lastIndex = src.indexOf(dest.charAt(dest.length() - 1));

        String check = src.substring(firstIndex, lastIndex + 1);

        if(!check.equals(dest)){
            return src;
        }
        return src.substring(0, firstIndex) + reverse(dest) + src.substring(lastIndex + 1);
    }
}
