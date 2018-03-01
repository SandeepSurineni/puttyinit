import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.DatatypeConverter;

public class AbsoulteFile {

        public static void filter (String dirname, List<File> result) {
            try {
                for (String f : new File(dirname).list()) {
                    String filename = dirname + f;
                    File theFile = new File(filename);
                    if (theFile.isDirectory()) {
                        filter(filename + "/", result);
                    } else if (new FileFilter() {
                        public boolean accept(File pathname) {
                            return pathname.getName().endsWith(".zip");
                        }
                    }.accept(theFile)) {
                        result.add(theFile);
                    }
                }
            } catch (Exception e) {
                // may raise null-pointer when access denied
            }
        }

        public static void main(String[] args) throws IOException {
            List<File> result = new ArrayList<File>();
            filter("/home/ubuntu/onboardingportal/", result);

            System.out.println(result.size());

        BufferedReader reader1 = new BufferedReader(new FileReader(result.toString()));

 String data = reader1.readLine();
        String md5Hash = getMD5Hash(data);
        String sha256hash = getSHA256Hash(data);

        System.out.println("md5:"+md5Hash);
        System.out.println("sha256:"+sha256hash);
        data = reader1.readLine();

        reader1.close();
        }

           private static String getSHA256Hash(String data) {
                String result = null;
                try {
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] hash = digest.digest(data.getBytes("UTF-8"));
                    return bytesToHex(hash);
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
                return result;
            }


            private static String getMD5Hash(String data) {
                String result = null;
                try {
                    MessageDigest digest = MessageDigest.getInstance("MD5");
                    byte[] hash = digest.digest(data.getBytes("UTF-8"));
                    return bytesToHex(hash);
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
                return result;
            }

           private static String bytesToHex(byte[] hash) {
                return DatatypeConverter.printHexBinary(hash).toLowerCase();

            }
}
