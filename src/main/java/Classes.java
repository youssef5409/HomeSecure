
public class Classes {

    private String score;

    private String classif;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getClassif() {
        return classif;
    }

    public void setClass(String classif) {
        this.classif = classif;
    }

    @Override
    public String toString() {
        return "ClassPojo [score = " + score + ", classif = " + classif + "]";
    }
}
