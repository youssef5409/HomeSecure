public class Classifiers
{
    private Classes[] classes;

    private String classifier_id;

    private String name;

    public Classes[] getClasses ()
    {
        return classes;
    }

    public void setClasses (Classes[] classes)
    {
        this.classes = classes;
    }

    public String getClassifier_id ()
    {
        return classifier_id;
    }

    public void setClassifier_id (String classifier_id)
    {
        this.classifier_id = classifier_id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [classes = "+classes+", classifier_id = "+classifier_id+", name = "+name+"]";
    }
}
			
	