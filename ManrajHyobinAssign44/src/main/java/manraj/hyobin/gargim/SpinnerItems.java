/*
        Manraj Garg s991541957
        Hyobin Im s991526068
        This is assignment 4 completed via pair programming - displays the use of multiple fragments in a nav drawer */

package manraj.hyobin.gargim;

public class SpinnerItems {

    private String mImageName;
    private int mLogoImage;

    public SpinnerItems(String imageName, int logoImage)
    {
        mImageName = imageName;
        mLogoImage = logoImage;
    }

    public String getImageName()
    {
        return mImageName;
    }

    public int getLogoImage()
    {
        return mLogoImage;
    }
}
