/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ser;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lifeuniverse
 */
public class General {
    // JDBC driver name and database URL

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/karenawbd_akun?zeroDateTimeBehavior=convertToNull [root on Default schema]";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static class DBX {

        public static Vector<HashMap<String, Object>> query(String sql) {
            Statement stmt = null;
            Connection conn = null;
            ResultSet rs = null;
            Vector<String> columnNames = new Vector<String>();
            Vector<HashMap<String, Object>> retval = new Vector<HashMap<String, Object>>();
            try {
                // Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                // Open a connection
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                // Execute SQL query
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);

                if (rs != null) {
                    ResultSetMetaData columns = rs.getMetaData();
                    int i = 0;
                    while (i < columns.getColumnCount()) {
                        i++;
                        columnNames.add(columns.getColumnName(i));
                    }

                    while (rs.next()) {
                        HashMap<String, Object> rowval = new HashMap<String, Object>();
                        for (i = 0; i < columnNames.size(); i++) {
                            rowval.put(columnNames.get(i), rs.getObject(columnNames.get(i)));
                        }
                        retval.add(rowval);
                    }

                }

                // Clean-up environment
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException se) {
                //Handle errors for JDBC
                se.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException se2) {
                }// nothing we can do
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                }//end finally try
            } //end try

            return retval;
        }

        public static void queryInsertUpdateDelete(String sql) {
            Connection conn = null;
            try {
                // Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                // Open a connection
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.executeUpdate(sql);
                ps.close();
                conn.close();
            } catch (SQLException se) {
                //Handle errors for JDBC
                se.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                }//end finally try
            } //end try
        }

        public static String serializeVector(Vector<String> vs) {
            int len = vs.size() - 1;
            String res = "";
            for (int i = 0; i < len; i++) {
                if (vs.get(i) == null) {
                    res += "NULL, ";
                } else {
                    res += "\"" + vs.get(i) + "\", ";
                }
            }

            if (vs.get(len) == null) {
                res += "NULL";
            } else {
                res += "\"" + vs.get(len) + "\"";
            }

            return res;
        }
        
        public static HashMap<String, Object> getUser(String token){
            Vector<HashMap<String, Object>> retval = null;

            try {
                retval = DBX.query("SELECT * FROM user WHERE token=\"" + token + "\";");
            }catch(Exception e){}
            
            return retval.get(0);
        }
        
        public static HashMap<String, Object> getUserByUsername(String token){
            Vector<HashMap<String, Object>> retval = null;

            try {
                retval = DBX.query("SELECT * FROM user WHERE username=\"" + token + "\";");
            }catch(Exception e){}
            
            return retval.get(0);
        }
    }

    public static Date GetNextDay() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);

        return c.getTime();
    }

    public static HashMap<String, String> parseBodyRequest(BufferedReader reader) {

        Vector<String> vsi = new Vector<String>();
        BufferedReader s = reader;
        String line = null;
        StringBuffer sb = new StringBuffer();
        try {
            while ((line = s.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            /*report an error*/ 
        }
        HashMap<String, String> postReq = new HashMap<String, String>();
        String urlEncoded = sb.toString();
        String[] fields = urlEncoded.split("&");
        if(fields.length > 0)
            for(String kv : fields){
                String[] cr = kv.split("=");
                if(cr.length == 2)
                    postReq.put(cr[0], cr[1]);
            }

        return postReq;
    }

    public static class TokenMaster {

        public static Vector<Object> generateToken() {
            Vector<Object> vs = new Vector<Object>();
            Date tomorrow = GetNextDay();
            vs.add(hashString(tomorrow.toString(), "SHA-256"));
            vs.add(tomorrow);

            return vs;
        }

        private static String convertByteArrayToHexString(byte[] arrayBytes) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < arrayBytes.length; i++) {
                stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            return stringBuffer.toString();
        }

        private static String hashString(String message, String algorithm){
            try {
                MessageDigest digest = MessageDigest.getInstance(algorithm);
                byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

                return convertByteArrayToHexString(hashedBytes);
            } catch (NoSuchAlgorithmException ex) {
                
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return null;
        }

        // Jika tokenStatus() == -1: Kadaluarsa, maka harus login dan create token baru
        public static Vector<String> createTokenExpiryTime(String username, String password) {
            Vector<String> res = tokenStatus(username, password);
            if(res != null)
                return res;
            
            res = new Vector<String>();
            Vector<Object> tokenNExpT = generateToken();
            Date n = (Date) tokenNExpT.get(1);
            String dateString = (n.getYear() + 1900) + "-" + (n.getMonth() + 1) + "-" + n.getDate() + " "
                    + (n.getHours() < 10 ? "0" : "") + n.getHours() + ":" + (n.getMinutes() < 10 ? "0" : "") + n.getMinutes() + ":" + (n.getSeconds() < 10 ? "0" : "") + n.getSeconds();
            DBX.queryInsertUpdateDelete("UPDATE user SET expiry_time=\"" + dateString + "\", token=\"" + (String) tokenNExpT.get(0) + "\" WHERE username=\"" + username + "\" AND password=\"" + password + "\";");

            res.add((String) tokenNExpT.get(0));
            res.add(dateString);
            return res;
        }

        // Jika tokenStatus() == 1: Update expired time setiap kali melakukan koneksi menggunakan token
        public static String updateExpiryTime(String token) {
            Date n = GetNextDay();
            String dateString = (n.getYear() + 1900) + "-" + (n.getMonth() + 1) + "-" + n.getDate() + " "
                    + (n.getHours() < 10 ? "0" : "") + n.getHours() + ":" + (n.getMinutes() < 10 ? "0" : "") + n.getMinutes() + ":" + (n.getSeconds() < 10 ? "0" : "") + n.getSeconds();
            DBX.queryInsertUpdateDelete("UPDATE user SET expiry_time=\"" + dateString + "\" WHERE token=\"" + token + "\";");
            
            return dateString;
        }

        public static void setZeroExpiryTime(String token) {
            DBX.queryInsertUpdateDelete("UPDATE user SET expiry_time=\"1999-12-31 23:59:59\", token=\"\" WHERE token=\"" + token + "\";");
        }

        public static long tokenStatus(String token) {
            long res = 0;
            Vector<HashMap<String, Object>> retval = null;

            try {
                retval = DBX.query("SELECT TIMESTAMPDIFF(SECOND, now(), expiry_time) AS diff FROM user WHERE token=\"" + token + "\";");

                if (retval.size() > 0) {
                    if ((long) retval.get(0).get("diff") > 0) {
                        res = 1;
                        updateExpiryTime(token);
                    } else if ((long) retval.get(0).get("diff") < 0) {
                        res = -1;
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
            }

            return res;
        }
        
        
        public static Vector<String> tokenStatus(String username, String password) {
            long res = 0;
            Vector<HashMap<String, Object>> retval = null;
            Vector<String> retx = null;

            String time = null;
            try {
                retval = DBX.query("SELECT token, TIMESTAMPDIFF(SECOND, now(), expiry_time) AS diff FROM user WHERE username=\"" + username + "\" AND password=\"" + password + "\";");

                if (retval.size() > 0) {
                    if ((long) retval.get(0).get("diff") > 0) {
                        res = 1;
                        time = updateExpiryTime((String) retval.get(0).get("token"));
                    } else if ((long) retval.get(0).get("diff") < 0) {
                        res = -1;
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(res == 1){
                retx = new Vector<String>();
                retx.add((String) retval.get(0).get("token"));
                retx.add(time);
            }
            
            return retx;
        }
    }
}
