package Web;

public class President {
    
    private int termNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private int startYear;
    private int endYear;
    private String party;
    
    /*
     * constructors
     */
    public President(){
        super();
        this.setTermNumber(0);
        this.setFirstName("");
        this.setMiddleName("");
        this.setLastName("");
        this.setStartYear(0);
        this.setEndYear(0);
        this.setParty("");
    }
    
    public President(int termNumber, String firstName, String middleName, String lastName, int startYear, int endYear,
            String party) {
        super();
        this.setTermNumber(termNumber);
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.setStartYear(startYear);
        this.setEndYear(endYear);
        this.setParty(party);
    }
    
    /*
     * getters/setters
     */
    
    
    public int getTermNumber() {
        return termNumber;
    }
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public void setTermNumber(int termNumber) {
        this.termNumber = termNumber;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getStartYear() {
        return startYear;
    }
    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }
    public String getParty() {
        return party;
    }
    public void setParty(String party) {
        this.party = party;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TOSTRING: President [termNumber=" + termNumber + ", firstName=" + firstName + ", middleName=" + middleName
                + ", lastName=" + lastName + ", startYear=" + startYear + ", endYear=" + endYear + ", party=" + party + "]\n";
    }
    
    

}
