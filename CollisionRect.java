public class CollisionRect 
{
    float posX;
    float posY;
    float width;
    float height;

    public CollisionRect()
    {
        this.posX = 0;
        this.posY = 0;
        this.width = 0;
        this.height = 0;
    }

    public CollisionRect(Vector2D pos, Vector2D size)
    {
        this.posX = pos.x;
        this.posY = pos.y;
        this.width = size.x;
        this.height = size.y;
    }

    public CollisionRect(float x, float y, float w, float h)
    {
        this.posX = x;
        this.posY = y;
        this.width = w;
        this.height = h;
    }

    public static boolean CollidesWith(CollisionRect rect1, CollisionRect rect2)
    {
        boolean collides = false;

        if (rect1.posX < rect2.posX + rect2.width && // probably a better way to do this
            rect1.posX + rect1.width > rect2.posX &&
            rect1.posY < rect2.posY + rect2.height &&
            rect1.height + rect1.posY > rect2.posY)
        {
            collides = true;
        }

        return collides;
    }
}
