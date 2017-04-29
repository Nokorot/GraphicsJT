import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebHandeler {

//	private static String url = "https://murmuring-refuge-44875.herokuapp.com/webapp";
	private static String url = "http://127.0.0.1:8000/webapp/";
	
	public static void main(String[] args)  {
		WebHandeler handeler = new WebHandeler();
		handeler.open();
	}
	
	public WebHandeler() {
	}
	
	public void open(){
		String key = read("openline");
		System.out.println(key);
		System.out.println(read(key));
	}
	
	public String read(String extention){
		URL oracle;
		try {
			oracle = new URL(url + extention);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null)
				builder.append(line);
			in.close();
			return builder.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
