public class Ball extends GameEntity
{
    CollisionRect hitbox;

    public Ball(Vector2D pos, Vector2D siz, Vector2D vel)
    {
        this.size = siz;
        this.position = pos;
        this.velocity = vel;
        this.hitbox = new CollisionRect(this.position, this.size);
    }

    public Ball()
    {
        this.size = new Vector2D();
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.hitbox = new CollisionRect(this.position, this.size);
    }

    public void ResetBall(Game game)
    {
        this.position = new Vector2D(Main.windowSize.x / 2 - 10, Main.windowSize.y / 2 - 10);
        Initalize.InitalizeBallVelocity(game, this);
    }
}
