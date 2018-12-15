package util;

public class TransCharsetUtil {
    public static String transISOToUTF(String string){
        try{
            if(string != null){
                string = new String(string.getBytes("ISO-8859-1"), "utf-8");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }
}
