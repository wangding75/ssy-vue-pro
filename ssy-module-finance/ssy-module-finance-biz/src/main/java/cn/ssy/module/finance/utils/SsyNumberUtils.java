package cn.ssy.module.finance.utils;

public class SsyNumberUtils {

    public static int doubleToInt(double value, int extendFactor) {
        return Double.valueOf(value * extendFactor).intValue();
    }

    public static int doubleStringToInt(String value, int extendFactor) {
        return doubleToInt(Double.parseDouble(value), extendFactor);
    }

    public static int doubleStringPercentToInt(String value, int extendFactor) {
        return doubleToInt(Double.parseDouble(value.replace("%", "")), extendFactor);

    }

}
