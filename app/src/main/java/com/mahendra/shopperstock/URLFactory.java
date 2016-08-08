package com.mahendra.shopperstock;

/**
 * Created by MK on 8/1/2016.
 */
public class URLFactory {


    public static String imageBasicUrl = "http://www.shopperstock.co.nf/api/image/";
    public static String apiBasicUrl = "http://www.shopperstock.co.nf/api/";
    // JSON Node names
    public static final String TAG_SUCCESS = "success";


    public static String userentry(){
        return apiBasicUrl+"userentry.php";
    }



}
