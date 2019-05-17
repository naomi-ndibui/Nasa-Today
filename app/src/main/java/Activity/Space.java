package Activity;

public class Space {
    private String mImage;
    private String mCredits;
    private String mTitle;
    private String mExplanation;
    private String mdate;

    public Space(String image,String credits,String title,String explanation,String date){
        this.mImage = image;
        this.mCredits = credits;
        this.mTitle = title;
        this.mExplanation = explanation;
        this.mdate = date;
    }

    public String getmImage() {
        return mImage;
    }

    public String getmCredits() {
        return mCredits;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmExplanation() {
        return mExplanation;
    }

    public String getMdate() {
        return mdate;
    }
}
