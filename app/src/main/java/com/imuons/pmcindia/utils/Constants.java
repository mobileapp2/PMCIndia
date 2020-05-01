package com.imuons.pmcindia.utils;
/*
 * Created by Tabish on 23-04-2020.
 */

public class Constants {

    //Regex Constants
    public static final long ONE_SECOND = 1000;
    public static String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    public static String EMAIL_REGEX = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static final long TEN_SECONDS = 10 * ONE_SECOND;
    //Network Code Constants
    public static int RESPONSE_CODE_OK = 200;
    public static String currency_Ruppes_symbol = "₹";
    public static String currency_Dollor_symbol = "$";
}
