import java.util.Random;

public class Initalize 
{
    static boolean Init(Game game)
    {
        boolean success = true;

        game.difficulty = 0.5f;

        game.paddleList.add(new Paddle(new Vector2D(50, 50), new Vector2D(30, 100), new Vector2D(0, 0)));
        game.paddleList.add(new Paddle(new Vector2D(Main.windowSize.x - 80, 50), new Vector2D(30, 100), new Vector2D(0, 0), true));
        game.ballList.add(new Ball(new Vector2D(Main.windowSize.x / 2 - 10, Main.windowSize.y / 2 - 10), new Vector2D(20, 20), new Vector2D(0, 0)));

        for (Ball ball : game.ballList)
        {
            InitalizeBallVelocity(game, ball);
        }

        return success;
    };

    static boolean InitalizeBallVelocity(Game game, Ball ball)
    {
        boolean success = true;

        Random random = new Random();
        Vector2D initalBallVelocity = new Vector2D(4f * game.difficulty, 4f * game.difficulty);
        float rand = random.nextFloat();

        if (rand <= 0.33f)
        {
            initalBallVelocity.x *= -1;
        }
        else if (rand > 0.33f && rand <= 0.66f)
        {
            initalBallVelocity.y *= -1;
        }
        else
        {
            initalBallVelocity.x *= -1;
            initalBallVelocity.y *= -1;
        }

        ball.velocity = initalBallVelocity; 

        return success; 
    }
}
