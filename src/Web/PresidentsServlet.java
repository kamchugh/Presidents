package Web;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@SuppressWarnings("serial")
@WebServlet("/Presidents")
public class PresidentsServlet extends HttpServlet {
    private HttpSession session;
    private int term;
    private List<President> sessionPresList;
    private ServletContext context;
    private PresidentDAO presidentDAO;
    private String party;
    private String input;
    private String buttonInput;
    private ImageLinkDAO imageLinkDAO;
    public void init() throws ServletException {
        context = getServletContext();
        PresidentDAO presidentDAO = new PresidentDAO(context);
        imageLinkDAO = new ImageLinkDAO(context);
        context.setAttribute("presidentDAO", presidentDAO);
        term=1; //initialize term to one, in case user presses go with a blank box on a new page.
        
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // set variables
        session = req.getSession();
        sessionPresList = new ArrayList<>();
        sessionPresList = getSessionPresidentList();
        input = req.getParameter("term");
        buttonInput = req.getParameter("changePresident");
        context = getServletContext();
        presidentDAO = (PresidentDAO) context.getAttribute("presidentDAO");
        // if there is no presidents attribute for the session, make one.
        makePresidentsSessionAttribute();
        handlePresidentChangeInputs();
        
        // set the image link
        // if there was a term input, add the current president to the list
        // and start the site
        if ((input != null) || (buttonInput != null)) {
            // get the president list and add the new president for this term.
            sessionPresList = getSessionPresidentList();
            sessionPresList.add(presidentDAO.getPresident(term));
            // set the last president in the list to the "presidentForSlideshow"
            // attribute
            session.setAttribute("presidentForSlideshow", sessionPresList.get(sessionPresList.size() - 1));
            // deploy the page
            deployIndex(req, resp);
        }
        // if there was a party input instead of a term input, set and display
        // the corresponding attributes and start the site
        else if (req.getParameter("party") != null) {
            // set party equal to the party selected from the menu
            party = req.getParameter("party");
            // get the full president list and set that to the attribute
            // "fullPresidentList" (display logic is in the index.jsp file)
            session.setAttribute("fullPresidentList", presidentDAO.getAllPresidents());
            // set party to the "partyToDisplay" attribute
            session.setAttribute("partyToDisplay", party);
            // deploy the page
            deployIndex(req, resp);
        }
        else {
            System.out.println("THERE WAS AN ERROR");
            deployIndex(req, resp);
        }
    }// doGet
    private void makePresidentsSessionAttribute() {
        // if there is nothing set for the "presidents" attribute, set it to a
        // new array list.
        if (session.getAttribute("presidents") == null) {
            session.setAttribute("presidents", new ArrayList<President>());
        }
    }
    private void handlePresidentChangeInputs() {
        // if the user gave a term number (by typing a number)
        if (input != "" && input != null) {
            term = Integer.parseInt(input);
            // set to one if a non existent term is entered
            if (term > 44 || term < 1) {
                term = 1;
            }
        }
        // if the user did not give a term number
        else {
            // get the term number from the session if it is not blank
            if ((sessionPresList != null) && (sessionPresList.size() > 0)) {
                if(sessionPresList.get(sessionPresList.size() - 1) != null){
                    term = sessionPresList.get(sessionPresList.size() - 1).getTermNumber();
                }else{
                    term = 1; 
                    
                }
            }
            //if it is blank
            else{
                term = 1;
            }
            // if there is button input, call the changeTerm method to handle
            // the change
            if (buttonInput != null && buttonInput != "") {
                term = changeTerm(buttonInput);
            }
        }
    }
    private int changeTerm(String buttonInput) {
        // increment or decrement the term based on the input
        switch (buttonInput) {
        case "next":
            //if hitting next makes the term go past 44
            //set the term to 44
            if (term < 44) {
                term++;
            } else {
                term = 1;
            }
            break;
        case "Previous":
            //if hitting previous makes the term go below 1
            //set the term to 1
            if (term > 1) {
                term--;
            } else {
                term = 44;
            }
            break;
        default:
            term = 1;
            break;
        }
        return term;
    }
    @SuppressWarnings("unchecked")
    private List<President> getSessionPresidentList() {
        // return a list of presidents for the session
        return (List<President>) session.getAttribute("presidents");
    }
    private void deployIndex(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //add the images
        //putting this here ensures that it is done at the right time
        if (term > 0 && term < 45) {
            setImageURLAttribute();
        }
        // deploy the site :D
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    private void setImageURLAttribute() {
        String imageLink = imageLinkDAO.getImageURL(term);
        session.setAttribute("imageURL", imageLink);
    }
}