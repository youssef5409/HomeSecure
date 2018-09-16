                 
public class Images
{
    private Classifiers[] classifiers;

    private String image;

    public Classifiers[] getClassifiers ()
    {
        return classifiers;
    }

    public void setClassifiers (Classifiers[] classifiers)
    {
        this.classifiers = classifiers;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [classifiers = "+classifiers+", image = "+image+"]";
    }
}
			