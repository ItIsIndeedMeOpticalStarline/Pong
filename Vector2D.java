public class Vector2D
{
    float x;
    float y;

    public Vector2D()
    {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(float initX, float initY)
    {
        this.x = initX;
        this.y = initY;
    }

    static public Vector2D Add(Vector2D vec1, Vector2D vec2)
    {
        Vector2D vec = new Vector2D();
        vec.x = vec1.x + vec2.x;
        vec.y = vec1.y + vec2.y;
        return vec;
    }

    static public Vector2D Zero()
    {
        Vector2D vec = new Vector2D();
        vec.x = 0;
        vec.y = 0;
        return vec;
    }
}
