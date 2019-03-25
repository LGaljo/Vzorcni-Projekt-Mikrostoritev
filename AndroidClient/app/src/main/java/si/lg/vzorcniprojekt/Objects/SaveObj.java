package si.lg.vzorcniprojekt.Objects;

import java.util.ArrayList;

public class SaveObj {
    public static ArrayList<Tag> tags;
    public static ArrayList<Tag> selectedTags;
    public static ArrayList<Question> questions;
    public static String baseAPIURL = "http://shrouded-earth-24148.herokuapp.com/v1/api/";

    public SaveObj() {
        if (questions == null) {
            questions = new ArrayList<>();
        }
        if (tags == null) {
            tags = new ArrayList<>();
        }
        if (selectedTags == null) {
            selectedTags = new ArrayList<>();
        }
    }
}
