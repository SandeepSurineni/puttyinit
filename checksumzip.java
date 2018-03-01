import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

public class checksumzip{

	public static void main(String[] args) throws IOException {
	    
    	BufferedReader reader1 = new BufferedReader(new FileReader(args[0]));
         
	String data = reader1.readLine();
	String md5Hash = getMD5Hash(data);
        String sha256hash = getSHA256Hash(data);
        
       System.out.println("Calculate the MD5 and SHA256 checksum values:");
       //System.out.println("data: \n"+data);
       // while ((data = reader1.readLine()) != null)
        //System.out.println(data);
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

