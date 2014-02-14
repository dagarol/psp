package serpis.psp;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class prueba {
	String longitud;
	String latitud;
	
	public static String busquedaGoogle(String longitud, String latitud)
	{
		String devuelve = "";
		
		HttpClient comunicacion = new DefaultHttpClient();
		HttpGet peticion = new HttpGet("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitud + "," + longitud +"&sensor=false");
		peticion.setHeader("content-type", "application/json");
		
		try {
		HttpResponse respuesta = comunicacion.execute(peticion);
		String respuestaCad = EntityUtils.toString(respuesta.getEntity());
		JSONObject respuestaJSON = new JSONObject(respuestaCad);
		JSONArray resultJSON = respuestaJSON.getJSONArray("results");
		String direccion ="No existen datos para esas coordenadas";
		if (resultJSON.length()>0)
		{
			direccion = resultJSON.getJSONObject(0).getString("formatted_address");
		}
		devuelve = "Direccion: " + direccion;
		}
		catch (Exception e){
			System.out.println("error");
		}
		System.out.println(devuelve);
		return devuelve;
		
	}
	
	
	
	
	public static void main (String [] args)
	{
		busquedaGoogle ("90", "60");
	}
}