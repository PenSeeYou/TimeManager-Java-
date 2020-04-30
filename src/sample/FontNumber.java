package sample;

public class FontNumber {
    public static String FontNumber (int number) {
        if (number < 10) {
            return "0" + number;
        } else {
            return number + "";
        }
    }
}
