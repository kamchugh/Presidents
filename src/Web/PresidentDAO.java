package Web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

public class PresidentDAO {
	private static final String filename = "Presidents.csv";
	private ServletContext servletContext;
	private List<President> presidents;

	/*
	 * president stuff
	 */
	private int termNumber;
	private String firstName;
	private String middleName;
	private String lastName;
	private int startYear;
	private int endYear;
	private String party;

	public PresidentDAO(ServletContext context) {
		
		servletContext = context;
		presidents = new ArrayList<>();
		loadPresidents();

	}

	private void loadPresidents() {

		InputStream is = servletContext.getResourceAsStream(filename);

		try (BufferedReader buf = new BufferedReader(new InputStreamReader(is))) {
			
			String line;
			while ((line = buf.readLine()) != null) {

				String[] tokens = line.split(",");
				termNumber = Integer.parseInt(tokens[0]);
				firstName = tokens[1].trim();
				middleName = tokens[2].trim();
				lastName = tokens[3].trim();
				String[] dates = tokens[4].trim().split("-");
				startYear = Integer.parseInt(dates[0].trim());
				endYear = Integer.parseInt(dates[1].trim());
				party = tokens[5].trim();
				presidents.add(new President(termNumber, firstName, middleName, lastName, startYear, endYear, party));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public President getPresident(int termNumber) {
		//iterate through the array of presidents, and if one matches the term
		//number passed into the method, return it.
		if (presidents.size() != 0) {
			for (President president : presidents) {
				if (president.getTermNumber() == termNumber)
					return president;

			}

		}
		return null;

	}

	public List<President> getAllPresidents() {
		return presidents;
	}

}
