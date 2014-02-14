package serpis.psp;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class Usuario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        estableceComunicacion();
    }

    private static void estableceComunicacion() {
        Map <String,String> datosEnv=new HashMap<String,String>();
        datosEnv.put("nombre","pepe");
        datosEnv.put("apellido","Gonzalez");       
        Gson gson = new Gson();
        String jsonOutput = gson.toJson(datosEnv);      
       try{
          
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          
          URL url=new URL("http://localhost/serv/index.php");
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));
         
          String s=recv.readLine();
          while(s!=null){
                  System.out.println(s);
                  s=recv.readLine();
                }
       }catch (Exception e){
           System.out.println(e.getMessage());
       }

    }

}