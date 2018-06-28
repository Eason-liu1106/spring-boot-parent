package com.code;

import net.glxn.qrgen.core.image.ImageType;
    import net.glxn.qrgen.core.vcard.VCard;
    import net.glxn.qrgen.javase.QRCode;

    import java.io.*;

    public class CreateQrCodeVCard {

        public static void main(String... args){
            VCard vCard = new VCard();
            vCard.setName("何春婷");
            vCard.setAddress("重庆两江新区财富大道19号重庆高科财富园三号B幢");
            vCard.setCompany("重庆高科");
            vCard.setPhoneNumber("023-63118686");
            vCard.setTitle("重庆高科");
            vCard.setEmail("910527645@qq.com");
            vCard.setWebsite("www.cqgaokecom");



            ByteArrayOutputStream bout =
                    QRCode.from(vCard)
                            .withCharset("utf-8")
                            .withSize(250, 250)
                            .to(ImageType.PNG)
                            .stream();

            try {
                OutputStream out = new FileOutputStream("qr-code-vcard.png");
                bout.writeTo(out);
                out.flush();
                out.close();

            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }