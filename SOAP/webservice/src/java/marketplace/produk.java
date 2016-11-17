/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace;

import java.sql.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
@WebService(serviceName = "produk")
public class produk {

    String URL = "jdbc:mysql://localhost:3306/karenawbd_marketplace";
    String USER = "root";
    String PASS = "";
    Connection conn;
    Statement stmt;

    private static int checksession(String tok) throws MalformedURLException, IOException {
        URL url = new URL("http://localhost:15376/REST/tokenstatus");
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("token", tok);

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        huc.setRequestMethod("POST");
        huc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        huc.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        huc.setDoOutput(true);
        huc.getOutputStream().write(postDataBytes);
        huc.connect();

        InputStream is = huc.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String data = null;
        String webPage = "";
        while ((data = reader.readLine()) != null) {
            webPage += data;
        }
        System.out.println("aaa" + webPage);
        return Integer.parseInt(webPage);
    }

    private static String[][] errorarray(String tok,String[][] before) throws IOException {
        int x = checksession(tok);
        if (x != 1) {
            String[][] hasil = new String[1][1];
            hasil[0][0] = "galat: token kadaluarsa";
            if (x == 0) {
                hasil[0][0] = "galat: token tidak valid";
            }
            return hasil;
        }
        return before;
    }

    private static String errorstring(String tok, String before) throws IOException {
        int x = checksession(tok);
        if (x != 1) {
            String hasil;
            hasil = "galat: token kadaluarsa";
            if (x == 0) {
                hasil = "galat: token tidak valid";
            }
            return hasil;
        }
        return before;
    }

    private static String[][] copystring(int brs, int klm, ResultSet rs) throws Exception {
        String[][] ret = new String[brs][klm];
        for (int i = 0; i < brs; i++) {
            rs.next();
            for (int j = 0; j < klm; j++) {
                ret[i][j] = rs.getString(j + 1);
            }
        }
        return ret;
    }

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    /*
        mengembalikan daftar produk dari user penjual
        dikembalikan dengan bentuk matriks string
        urutan sama seperti di basis data
     */
    @WebMethod(operationName = "product_of")
    public String[][] product_of(@WebParam(name = "token")
            final String token, @WebParam(name = "penjual")
            final String penjual) {
        //TODO write your implementation code here:
        String query = "";
        try {

            System.out.println("coba");
            ResultSet rs1 = stmt.executeQuery("SELECT count(id) from `produk` where `penjual`=\"".concat(penjual).concat("\";"));
            query = query.concat("SELECT * from `produk` where `penjual`=\"".concat(penjual).concat("\";"));
            rs1.next();
            int x = rs1.getInt(1);
            ResultSet rs2 = stmt.executeQuery(query);
            String[][] hasil;
            hasil = new String[x][8];
            for (int i = 0; i < x; i++) {
                rs2.next();
                for (int j = 0; j < 8; j++) {
                    hasil[i][j] = rs2.getString(j + 1);
                }
            }
            hasil = errorarray(token,hasil);
            return hasil;
        } catch (Exception e) {
            String[][] hasil = new String[1][2];
            hasil[0][0] = e.toString();
            hasil[0][1] = query;
            System.out.println(e);
            return hasil;
        }
    }

    /**
     * Web service operation
     */
    /* Inisialisasi basis data 
        fungsi ini wajib dilakukan sebelum memulai operasi yang memerlukan basis
        data marketplace
     */
    @WebMethod(operationName = "init")
    public int init() {
        //TODO write your implementation code here:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select 1 as a");
            int hasil;
            rs.next();
            hasil = rs.getInt(1);
            return hasil;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    /**
     * Web service operation
     */
    /*
        Mengembalikan semua produk pada basis data
     */
    @WebMethod(operationName = "get_all_produk")
    public String[][] get_all_produk() {
        //TODO write your implementation code here:
        String[][] hasil;
        try {
            ResultSet rs1 = stmt.executeQuery("SELECT count(id) FROM `produk`;");
            rs1.next();
            int x = rs1.getInt(1);
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM `produk`;");
            hasil = copystring(x, 8, rs2);
        } catch (Exception e) {
            hasil = new String[1][1];
            hasil[0][0] = e.toString();
        }
        return hasil;
    }

    /**
     * Web service operation
     */
    /*
        Menambahkan produk ke dalam basis data
     */
    @WebMethod(operationName = "add_produk")
    public String add_produk(@WebParam(name = "token")
            final String token,@WebParam(name = "name")
            final String name, @WebParam(name = "desc")
            final String desc, @WebParam(name = "price")
            final int price, @WebParam(name = "url_pict")
            final String url_pict, @WebParam(name = "penjual")
            final String penjual) {
        //TODO write your implementation code here:
        try {
            String valid = errorstring(token,null);
            if (valid!=null)
                return valid;
            int row = stmt.executeUpdate("INSERT INTO `produk`(`name`,`desc`,`price`"
                    + ",`url_pict`,`penjual`,`j_like`,`tanggal_add`) VALUES('" + name + "','" + desc + "'," + price
                    + ",'" + url_pict + "','" + penjual + "',0,now());");
            if (row != 1) {
                return "insert gagal";
            }
            return "ok";
        } catch (Exception e) {
            return e.toString();
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "like")
    public String like(@WebParam(name = "token")
            final String token,@WebParam(name = "user_id")
            final int user_id, @WebParam(name = "produk_id")
            final int produk_id) {
        //TODO write your implementation code here:
        try {
            String valid = errorstring(token,null);
            if (valid!=null)
                return valid;
            int row1 = stmt.executeUpdate("INSERT INTO `like` VALUES(" + user_id + "," + produk_id + ");");
            int row2 = stmt.executeUpdate("UPDATE `produk` set `j_like`=`j_like`+1 where id=" + produk_id + ";");
            if (!((row1 == 1) && (row2 == 1))) {
                return "galat";
            }
            return "ok";
        } catch (Exception e) {
            return "galat: " + e.toString();
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "search")
    public String[][] search(@WebParam(name = "keyword")
            final String keyword, @WebParam(name = "by")
            final String by) {
        //TODO write your implementation code here:
        String[][] hasil = null;
        try {
            if (by.equalsIgnoreCase("product")) {
                //pencarian berdasar produk
                ResultSet rs1 = stmt.executeQuery("select count(id) from produk where name like '%" + keyword + "%';");
                rs1.next();
                int x = rs1.getInt(1);
                ResultSet rs2 = stmt.executeQuery("select * from produk where name like '%" + keyword + "%';");
                hasil = copystring(x, 8, rs2);
            } else if (by.equalsIgnoreCase("store")) {
                //pencarian berdasar store (penjual)
                ResultSet rs1 = stmt.executeQuery("select count(id) from produk where penjual like '%" + keyword + "%';");
                rs1.next();
                int x = rs1.getInt(1);
                ResultSet rs2 = stmt.executeQuery("select * from produk where penjual like'%" + keyword + "%';");
                hasil = copystring(x, 8, rs2);
            }
        } catch (Exception e) {
            hasil = new String[1][1];
            hasil[0][0] = "galat: " + e.toString();
        }
        return hasil;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "edit_product")
    public String edit_product(@WebParam(name = "token")
            final String token,@WebParam(name = "id")
            final int id, @WebParam(name = "name")
            final String name, @WebParam(name = "desc")
            final String desc, @WebParam(name = "price")
            final int price) {
        //TODO write your implementation code here:
        try {
            String valid = errorstring(token,null);
            if (valid!=null)
                return valid;
            int row = stmt.executeUpdate("update `produk` set `name`='" + name + "',`desc`='" + desc + "',`price`=" + price + " where `id`=" + id + ";");
            if (row == 1) {
                return "ok";
            }
        } catch (Exception e) {
            return "galat: " + e.toString();
        }
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "delete_product")
    public String delete_product(@WebParam(name = "token")
            final String token,@WebParam(name = "id")
            final int id) {
        //TODO write your implementation code here:
        try {
            String valid = errorstring(token,null);
            if (valid!=null)
                return valid;
            int r = stmt.executeUpdate("delete from `like` where `produk_id`=" + id + ";");
            int row = stmt.executeUpdate("delete from `produk` where `id`=" + id + ";");
            return "ok";
        } catch (Exception e) {
            return "galat: " + e.toString();
        }
    }

    /**
     * Web service operation
     */
    /*
        melakukan pembelian dengan format
        consignee, address, postal_code,phone,id_produk,jumlah,penjual,pembeli
     */
    @WebMethod(operationName = "beli")
    public String beli(@WebParam(name = "token")
            final String token,@WebParam(name = "consignee") String consignee, @WebParam(name = "address") String address, @WebParam(name = "postal_code") String postal_code, @WebParam(name = "phone") String phone, @WebParam(name = "id_produk") int id_produk, @WebParam(name = "jumlah") int jumlah, @WebParam(name = "penjual") String penjual, @WebParam(name = "pembeli") String pembeli) {
        //TODO write your implementation code here:
        String query = "";
        try {
            String valid = errorstring(token,null);
            if (valid!=null)
                return valid;
            query = "insert into pembelian(`consignee`,`address`,`postal_code`,`phone`,id_produk,`nama_produk`,`url_pict`,`harga`,`jumlah`,`penjual`,`pembeli`,`tanggal_beli`)";
            query = query.concat(" values('" + consignee + "','" + address + "','" + postal_code + "','" + phone + "'," + id_produk + ",'uh','oh',0," + jumlah + ",'" + penjual + "','" + pembeli + "',now());");
            int row = stmt.executeUpdate(query);
            ResultSet rs = stmt.executeQuery("select name,url_pict,price from produk where id=" + id_produk + ";");
            rs.next();
            String nama = rs.getString("name");
            String url = rs.getString("url_pict");
            String harga = rs.getString("price");
            int row2 = stmt.executeUpdate("update pembelian as p set nama_produk=\"" + nama + "\",url_pict=\"" + url + "\",harga=" + harga + " where id_produk=" + id_produk + ";");
            if ((row == 1) && (row2 == 1)) {
                return "ok";
            } else {
                return "galat";
            }
        } catch (Exception e) {
            return "galat: " + e.toString();
        }
    }

    /**
     * Web service operation
     */
    /*
        Mengembalikan daftar produk yang telah dijual/dibeli
        mode adalah string purchases/sales
     */
    @WebMethod(operationName = "get_sales_purchase")
    public String[][] get_sales_purchase(@WebParam(name = "token")
            final String token,@WebParam(name = "x")
            final String x, @WebParam(name = "mode") String mode) {
        //TODO write your implementation code here:
        String y = "pembeli";
        if (mode.equalsIgnoreCase("sales")) {
            y = "penjual";
        }
        String[][] hasil;
        try {
            ResultSet rs1 = stmt.executeQuery("select count(id) from `pembelian` where `" + y + "`=\"" + x + "\";");
            rs1.next();
            int brs = rs1.getInt(1);
            ResultSet rs2 = stmt.executeQuery("select * from `pembelian` where `" + y + "`=\"" + x + "\";");
            hasil = copystring(brs, 13, rs2);
            hasil = errorarray(token,hasil);
        } catch (Exception e) {
            hasil = new String[1][1];
            hasil[0][0] = "galat: " + e.toString();
        }
        
        return hasil;
    }
}
