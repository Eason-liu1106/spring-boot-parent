package com.stream;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.io.*;

/**
 * @author JialinLiu
 * @date 2018/8/6 0006 15:39
 */
public class StreamTest {
    public static void main(String args[]) throws Exception {
        File file = new File("d:\\test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file,true);
        //fileOutputStream.write(b);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print("我不难过0.0");
        //printWriter.append("hi嗯哼IEhi");


        printWriter.close();
//        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
//        dataOutputStream.writeUTF("hi嗯哼IE");
//        dataOutputStream.close();
        FileInputStream fileInputStream = new FileInputStream("d:\\test.txt");

    }
}
