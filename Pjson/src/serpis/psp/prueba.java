package serpis.psp;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

public class prueba {
	String longitud;
	String latitud;
	
	String busquedaGoogle(String longitud, String latitud)
	{
		String devuelve = "";
		
		HttpClient comunicacion = new DefaultHttpCLient();
		HttpGet peticion = new HttpGet("http://maps.googleapis.com/maps/api/geocode/json?" +)
			"latlng=" + latitud + "," + longitud +"&sensor=false");
		peticion.setHeader("content-type", "application/json");
		
			
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
			
		return devuelve;
	}
	
	
	
	
	public static void main (String [] args)
	{
		
	}
}