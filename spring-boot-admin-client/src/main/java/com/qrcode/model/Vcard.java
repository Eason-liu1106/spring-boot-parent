package com.qrcode.model;

/**
 * 描述:
 *
 * @author LJL
 * @date 2018/6/20 0020 13:40
 */
public class Vcard {
    private static final String NAME = "N:";

    private static final String COMPANY = "ORG:";

    private static final String TITLE = "TITLE:";

    private static final String MOBILEPHONE = "TEL;CELL:";

    private static final String WEB = "URL:";

    private static final String EMAIL = "EMAIL:";

    private static final String ADDRESS = "ADR;WORK:";

    private static final String TELPHONE = "TEL;WORK:";

    private static final String ROLE = "ROLE:";


    private String name;

    private String company;

    private String title;

    private String mobilephone;

    private String email;

    private String address;

    private String website;

    private String role;

    private String telPhone;
    @SuppressWarnings("UnusedDeclaration")
    public Vcard() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BEGIN:VCARD").append(System.lineSeparator());
        sb.append("VERSION:3.0").append(System.lineSeparator());
        if (name != null) {
            sb.append(NAME).append(name);
        }
        if (company != null) {
            sb.append(System.lineSeparator()).append(COMPANY)
                    .append(company);
        }
        if (title != null) {
            sb.append(System.lineSeparator()).append( TITLE)
                    .append(title);
        }
        if (mobilephone != null) {
            sb.append(System.lineSeparator()).append( MOBILEPHONE)
                    .append(mobilephone);
        }
        if (website != null) {
            sb.append(System.lineSeparator()).append(WEB)
                    .append(website);
        }
        if (email != null) {
            sb.append(System.lineSeparator()).append( EMAIL).append(email);
        }
        if (address != null) {
            sb.append(System.lineSeparator()).append( ADDRESS).append(address);
        }
        if (role != null) {
            sb.append(System.lineSeparator()).append( ROLE).append(role);
        }
        if (telPhone != null) {
            sb.append(System.lineSeparator()).append( TELPHONE).append(telPhone);
        }
        sb.append(System.lineSeparator()).append("END:VCARD");
        return sb.toString();
    }
}
