package com.workbook;

import java.math.BigDecimal;

/**
 * @author JialinLiu
 * @date 2018/8/1 0001 16:59
 */
public class DoubleTest {
    public static void main(String arg[]){
        Double d = new Double("2.000");
        String test = getPrettyNumber(d);
        String c =null;
        String f = null;
        //System.out.println((f/c));
        System.out.println(d);
        System.out.println(test);
    }
    public static String getPrettyNumber(Double d) {
        String plainString = BigDecimal.valueOf(d)
                .stripTrailingZeros().toPlainString();
        if(plainString.equals("0.0")){
            plainString = "0";
        }
        return plainString;
    }

}
