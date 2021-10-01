package es.joseluisgs.dam.blog.utils;

import java.security.MessageDigest;

public class Cifrador {
    private static Cifrador cif = null;

    public static Cifrador getInstance() {
        if (cif == null) {
            cif = new Cifrador();
        }
        return cif;
    }

    private Cifrador() { }

    public String SHA512(String cadena) {
        MessageDigest md = null;
        byte[] hash = null;
        // Llamamos a la función de hash de java
        try {
            md = MessageDigest.getInstance("SHA-1");
            hash = md.digest(cadena.getBytes("UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return convertToHex(hash);
    }

    private String convertToHex(byte[] raw) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < raw.length; i++) {
            sb.append(Integer.toString((raw[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

}