package Web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.servlet.ServletContext;

public class ImageLinkDAO {
	private static final String filename = "image-links.csv";
	private static ServletContext servletContext;
	private HashMap<Integer, String> imageSourceMap;

	public ImageLinkDAO(ServletContext context) {
		servletContext = context;
		imageSourceMap = new HashMap<>();
		loadMap();

	}

	private void loadMap() {
		InputStream is = servletContext.getResourceAsStream(filename);
		try (BufferedReader buf = new BufferedReader(new InputStreamReader(is))) {
			String line;
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split(",");
				imageSourceMap.put(Integer.parseInt(tokens[0].trim()), tokens[1].trim());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getImageURL(int term) {
		String imageURL = imageSourceMap.get(term);
		return imageURL;
	}

	public HashMap<Integer, String> getImageSourceMap() {
		return imageSourceMap;
	}

}