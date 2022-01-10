package ua.com.alevel.util;

import java.util.Arrays;

import static ua.com.alevel.util.SplitDate.split;

public class Validator {
    private static final String[] MONTH = {"Jan", "Feb", "Mar", "Apr", "May",
            "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};


    public static boolean isDate(String str, int method) {
        boolean validFormat = true;
        int days = 1;
        int month = 1;
        int years = 0;
        String[] date = str.split(" ");
        if (date.length != 2 && date.length != 1) {
            validFormat = DateError();
        } else {
            switch (method) {
                case 1 -> {
                    String[] dateValue = split(date[0], '/');
                    if (dateValue.length != 3
                            || (!dateValue[0].isEmpty() && dateValue[0].length() != 2)
                            || (!dateValue[1].isEmpty() && dateValue[1].length() != 2)
                            || (dateValue[2].length() == 1)) {
                        validFormat = DateError();
                    } else try {
                        if (!dateValue[0].isEmpty()) {
                            days = Integer.parseInt(dateValue[0]);
                        }
                        if (!dateValue[1].isEmpty()) {
                            month = Integer.parseInt(dateValue[1]);
                        }
                        if (!dateValue[2].isEmpty()) {
                            years = Integer.parseInt(dateValue[2]);
                            validFormat = LimitError(years);
                        }
                    } catch (NumberFormatException e) {
                        validFormat = DateError();
                    }
                }
                case 2 -> {
                    String[] dateValue = split(date[0], '/');
                    if (dateValue.length != 3 || (!dateValue[2].isEmpty() && dateValue[2].length() < 4)) {
                        validFormat = DateError();
                    } else try {
                        if (!dateValue[1].isEmpty()) {
                            days = Integer.parseInt(dateValue[1]);
                        }
                        if (!dateValue[0].isEmpty()) {
                            month = Integer.parseInt(dateValue[0]);
                        }
                        if (!dateValue[2].isEmpty()) {
                            years = Integer.parseInt(dateValue[2]);
                            validFormat = LimitError(years);
                        }
                    } catch (NumberFormatException e) {
                        validFormat = DateError();
                    }
                }
                case 3 -> {
                    String[] dateValue = split(date[0], '-');
                    if (dateValue.length != 3 || (dateValue[2].length() == 1)) {
                        validFormat = DateError();
                    } else try {
                        if (!dateValue[1].isEmpty()) {
                            days = Integer.parseInt(dateValue[1]);
                        }
                        if (!dateValue[0].isEmpty()) {
                            month = Arrays.asList(MONTH).indexOf(dateValue[0].toLowerCase());
                        }
                        if (!dateValue[2].isEmpty()) {
                            years = Integer.parseInt(dateValue[2]);
                            validFormat = LimitError(years);
                        }
                    } catch (NumberFormatException e) {
                        validFormat = DateError();
                    }
                }
                case 4 -> {
                    String[] dateValue = split(date[0], '-');
                    if (dateValue.length != 3
                            || (!dateValue[0].isEmpty() && dateValue[0].length() != 2)
                            || (!dateValue[2].isEmpty() && dateValue[2].length() < 4)) {
                        validFormat = DateError();
                    } else try {
                        if (!dateValue[0].isEmpty()) {
                            days = Integer.parseInt(dateValue[0]);
                        }
                        if (!dateValue[1].isEmpty()) {
                            month = Arrays.asList(MONTH).indexOf(dateValue[1].toLowerCase());
                        }
                        if (!dateValue[2].isEmpty()) {
                            years = Integer.parseInt(dateValue[2]);
                            validFormat = LimitError(years);
                        }
                    } catch (NumberFormatException e) {
                        validFormat = DateError();
                    }
                }
            }
            if (validFormat) {
                if (days < 1 || month < 1 || month > 12 || years < 0) {
                    validFormat = DateError();
                }
                boolean leapYear = (years % 4 == 0 && years % 100 != 0) || years % 400 == 0;
                if ((month == 1 && days > 31) || (month == 2 && days > 29 && leapYear)
                        || (month == 2 && days > 28 && !leapYear)
                        || (month == 3 && days > 31)
                        || (month == 4 && days > 30)
                        || (month == 5 && days > 31)
                        || (month == 6 && days > 30)
                        || (month == 7 && days > 31)
                        || (month == 8 && days > 31)
                        || (month == 9 && days > 30)
                        || (month == 10 && days > 31)
                        || (month == 11 && days > 30)
                        || (month == 12 && days > 31)) {
                    validFormat = DateError();
                }
            }
            if (date.length == 2) {
                String[] timeValue = split(date[1], ':');
                try {
                    if (!timeValue[0].isEmpty()) {
                        int hour = Integer.parseInt(timeValue[0]);
                        if (hour < 0 || hour > 23) {
                            validFormat = DateError();
                        } else {
                            if (!timeValue[1].isEmpty()) {
                                int min = Integer.parseInt(timeValue[1]);
                                if (min < 0 || min > 59) {
                                    validFormat = DateError();
                                } else {
                                    if (timeValue.length > 2) {
                                        if (!timeValue[2].isEmpty()) {
                                            int sec = Integer.parseInt(timeValue[2]);
                                            if (sec < 0 || sec > 59) {
                                                validFormat = DateError();
                                            } else if (timeValue.length > 3) {
                                                if (!timeValue[3].isEmpty()) {
                                                    int ms = Integer.parseInt(timeValue[3]);
                                                    if (ms < 0 || ms > 999) {
                                                        validFormat = DateError();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    validFormat = DateError();
                }
            }
        }
        return validFormat;
    }

    public static boolean DateError() {
        System.out.println("Incorrect date. Please try again");
        return false;
    }

    public static boolean LimitError(long year) {
        if ((year + 1) * 365.25 > 106751991167L) {
            System.out.println("Calendar is above limits. Please try again");
            return false;
        }
        return true;
    }
}
