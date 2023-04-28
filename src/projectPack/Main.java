package projectPack;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main {
	static OkHttpClient client = new OkHttpClient();
	
	static ArrayList<String> getJSON(String URL) throws IOException {
		ArrayList<String> jsonValues = new ArrayList<>();
		Request req = new Request.Builder().url(URL).build();
		Response res = client.newCall(req).execute();
		
		if(res.code() == 200) {
			String response = res.body().string();
			System.out.println("getJSON function response: " + response);
		}
		
		return jsonValues;
	}
	
	static void menu() throws IOException {
		MyPrivateKey myPrivateKey = new MyPrivateKey();
		String key = myPrivateKey.KEY;
		String URL = "http://www.omdbapi.com/?t=%s&apikey=%s";
			
		String dialog = "Welcome to the Java Movie App!!\n\nType a movie title\nType 0 to exit";
		//String movieTitle = JOptionPane.showInputDialog(null, dialog);
		String movieTitle = "titanic";
		
		String formatedURL = String.format(URL, movieTitle, key);
		
		ArrayList<String> jsonValues = getJSON(formatedURL);
		
		System.out.println("2nd function arraylist: " + jsonValues);
	}

	public static void main(String[] args) throws IOException {
		menu();
	}
}
