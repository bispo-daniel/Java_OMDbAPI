package projectPack;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.json.JSONObject;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;
import com.jayway.jsonpath.spi.json.JsonOrgJsonProvider;

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
			try {
				String response = res.body().string();
				
				JSONObject json = new JSONObject(response);
				
				Configuration config = Configuration.builder().jsonProvider(new JsonOrgJsonProvider()).build();
				
				JsonPath movieTitlePath = JsonPath.compile("$.Title");
				Object movieTitleObj = movieTitlePath.read(json, config);
				
				JsonPath movieYearPath = JsonPath.compile("$.Year");
				Object movieYearObj = movieYearPath.read(json, config);

				JsonPath movieReleaseDatePath = JsonPath.compile("$.Released");
				Object movieReleaseDateObj = movieReleaseDatePath.read(json, config);

				JsonPath movieRuntimePath = JsonPath.compile("$.Runtime");
				Object movieRuntimeObj = movieRuntimePath.read(json, config);
			
				JsonPath movieGenrePath = JsonPath.compile("$.Genre");
				Object movieGenreObj = movieGenrePath.read(json, config);
				
				JsonPath moviePlotPath = JsonPath.compile("$.Plot");
				Object moviePlotObj = moviePlotPath.read(json, config);
				

				jsonValues.add(movieTitleObj.toString());
				jsonValues.add(movieYearObj.toString());
				jsonValues.add(movieReleaseDateObj.toString());
				jsonValues.add(movieRuntimeObj.toString());
				jsonValues.add(movieGenreObj.toString());
				jsonValues.add(moviePlotObj.toString());

				JsonPath IMDbMovieRatingsPath = JsonPath.compile("$.Ratings[0].Value");
				Object IMDbMovieRatingsObj = IMDbMovieRatingsPath.read(json, config);
	
				JsonPath rottenTomatoesMovieRatingsPath = JsonPath.compile("$.Ratings[1].Value");
				Object rottenTomatoesMovieRatingsObj = rottenTomatoesMovieRatingsPath.read(json, config);
				
				JsonPath metacriticMovieRatingsPath = JsonPath.compile("$.Ratings[2].Value");
				Object metacriticMovieRatingsObj = metacriticMovieRatingsPath.read(json, config);
	
				jsonValues.add(IMDbMovieRatingsObj.toString());
				jsonValues.add(rottenTomatoesMovieRatingsObj.toString());
				jsonValues.add(metacriticMovieRatingsObj.toString());

			} catch (JsonPathException e) {
				JOptionPane.showMessageDialog(null, "Something different happened...\nTry again");
				main(null);
			}

		} else {
			JOptionPane.showMessageDialog(null, "Something different happened...\nTry again");
			main(null);
		}

		return jsonValues;
	}
	
	static void menu() throws IOException {
		MyPrivateKey myPrivateKey = new MyPrivateKey();
		String key = myPrivateKey.KEY;
		String URL = "http://www.omdbapi.com/?t=%s&apikey=%s";
	
		String dialog = "Welcome to the Java Movie App!!\n\nType a Movie Title\nType 0 to exit";
		String query = JOptionPane.showInputDialog(null, dialog);

		if (query.equals("0")) {
			System.exit(0);
		} else {
			String formatedURL = String.format(URL, query, key);

			ArrayList<String> jsonValues = getJSON(formatedURL);
	
			String html = "<html> <center width=200px> <h1>%s - %s</h1> <div>%s</div> </center> </html>";
	
			String movieInfo = "<h3>Released:</h3> %s <br> <h3>Runtime:</h3> %s <br> <h3>Genre:</h3> %s <br> <h3>Plot:</h3> %s <br> <h3>IMDb Rating:</h3> %s <br> <h3>Rotten Tomatoes rating:</h3> %s <br> <h3>Metacritic rating:</h3> %s <br>";
	
			String movieTitle = jsonValues.get(0);
			String movieYear = jsonValues.get(1);
			String movieReleaseDate = jsonValues.get(2);
			String movieRuntime = jsonValues.get(3);
			String movieGenre = jsonValues.get(4);
			String moviePlot = jsonValues.get(5);
			String IMDbMovieRating = jsonValues.get(6);
			String rottenTomatoesMovieRating = jsonValues.get(7);
			String metacriticMovieRating = jsonValues.get(8);

			String formatedMovieInfo = String.format(movieInfo, movieReleaseDate, movieRuntime, movieGenre, moviePlot, IMDbMovieRating, rottenTomatoesMovieRating, metacriticMovieRating);
			String formatedHTML = String.format(html, movieTitle, movieYear, formatedMovieInfo);
			JOptionPane.showMessageDialog(null, formatedHTML, movieTitle, JOptionPane.INFORMATION_MESSAGE, new ImageIcon());
			
			main(null);
		}
	}

	public static void main(String[] args) throws IOException {
		menu();
	}
}