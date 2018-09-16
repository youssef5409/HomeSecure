/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Youssef Mohamed
 */
public class MyPojo
{
    private String custom_classes;

    private String images_processed;

    private Images[] images;

    public String getCustom_classes ()
    {
        return custom_classes;
    }

    public void setCustom_classes (String custom_classes)
    {
        this.custom_classes = custom_classes;
    }

    public String getImages_processed ()
    {
        return images_processed;
    }

    public void setImages_processed (String images_processed)
    {
        this.images_processed = images_processed;
    }

    public Images[] getImages ()
    {
        return images;
    }

    public void setImages (Images[] images)
    {
        this.images = images;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [custom_classes = "+custom_classes+", images_processed = "+images_processed+", images = "+images+"]";
    }
}