public class Paddle extends GameEntity
{
    boolean aiControlled;
    CollisionRect hitbox;
    int score;

    public Paddle(Vector2D pos, Vector2D siz, Vector2D vel)
    {
        this.size = siz;
        this.position = pos;
        this.velocity = vel;
        this.aiControlled = false;
        this.hitbox = new CollisionRect(this.position, this.size);
        this.score = 0;
    }

    public Paddle(Vector2D pos, Vector2D siz, Vector2D vel, boolean aiCtrl)
    {
        this.size = siz;
        this.position = pos;
        this.velocity = vel;
        this.aiControlled = aiCtrl;
        this.hitbox = new CollisionRect(this.position, this.size);
        this.score = 0;
    }

    public Paddle()
    {
        this.size = new Vector2D();
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.hitbox = new CollisionRect(this.position, this.velocity);
        this.score = 0;
    }
}
