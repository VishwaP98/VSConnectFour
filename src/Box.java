/**
 * Created by Vishwa on 3/10/2016.
 */
public class Box {
    private boolean isFilled;
    private boolean isX;
    public Box()
    {
        this.isFilled = false;
    }
    public void setFilled(boolean isX)
    {
        this.isFilled = true;
        this.isX = isX;
    }
    public boolean getFilled()
    {
        return isFilled;
    }
    public boolean isX(){
        return isX;
    }
}
