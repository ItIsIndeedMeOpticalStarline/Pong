import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.lang.Math;
import java.util.Random;

public class Game
{
    ArrayList<Ball> ballList = new ArrayList<Ball>();

    float difficulty;

    Map<Integer, Boolean> keys = new HashMap<Integer, Boolean>();

    ArrayList<Paddle> paddleList = new ArrayList<Paddle>();

    Random random = new Random();

    void Update()
    {
        Controls();

        UpdateEntities();
    };

    void AI()
    {
        for (Ball ball : ballList)
        {
            for (Paddle paddle : paddleList) 
            {
                if (CollisionRect.CollidesWith(ball.hitbox, paddle.hitbox))
                {
                    if (paddle.position.x < Main.windowSize.x / 2)
                        ball.position.x = paddle.position.x + paddle.size.x;
                    else
                        ball.position.x = paddle.position.x - ball.size.x;

                    ball.velocity.x *= -1.1;
                }
            }

            if (ball.position.y + ball.size.y >= Main.windowSize.y || ball.position.y <= 0)
                ball.velocity.y = -ball.velocity.y;

            if (ball.position.x <= 0)
            {
                // increment ai score
                ball.ResetBall(this);
            }

            if (ball.position.x + ball.size.x >= Main.windowSize.x)
            {
                // increment player score
                ball.ResetBall(this);
            }
        }

        for (Paddle paddle : paddleList) 
        {
            if (paddle.aiControlled)
            {
                Ball targetBall;
                float distanceArray[] = new float[ballList.size()];

                for (int i = 0; i < ballList.size(); i++)
                {
                    distanceArray[i] = (float)MathHelper.Distance(paddle.position, ballList.get(i).position);
                }

                targetBall = ballList.get(MathHelper.GetArrayIndex(distanceArray, MathHelper.ArrayMin(distanceArray))); // Target the closest ball

                if ((random.nextFloat() * difficulty) <= (0.9f)) // move smart
                {
                    if (Math.abs(paddle.velocity.y) < (difficulty * 6f))
                    {
                        if (targetBall.position.y + (targetBall.size.y / 2) > paddle.position.y + (paddle.size.y / 2))
                            paddle.velocity.y += 1.2f * (1f + difficulty);
                        else
                            paddle.velocity.y -= 1.2f * (1f + difficulty);
                    }
                }
                else // move random
                {
                    if (Math.abs(paddle.velocity.x) < (difficulty * 6f))
                        paddle.velocity.y += (-difficulty + random.nextFloat() * (1.2f - -difficulty));
                }
            }

            if (paddle.position.y + paddle.size.y >= Main.windowSize.y)
            {
                paddle.position.y = Main.windowSize.y - paddle.size.y;
                paddle.velocity.y -= 1;
            }

            if (paddle.position.y <= 0)
            {
                paddle.position.y = 0;
                paddle.velocity.y += 1;
            }
        }
    }

    void Controls()
    {
        // Lazieness strikes again
        if (keys.get(KeyEvent.VK_UP) != null)
        {
            if (keys.get(KeyEvent.VK_UP))
            {
                for (Paddle paddle : paddleList) 
                {
                    if (!paddle.aiControlled)
                    {
                        if (Math.abs(paddle.velocity.y) < 6f)
                            paddle.velocity.y -= 1.2f;
                    }
                }
            }
        }

        if (keys.get(KeyEvent.VK_DOWN) != null)
        {
            if (keys.get(KeyEvent.VK_DOWN))
            {
                for (Paddle paddle : paddleList) 
                {
                    if (!paddle.aiControlled)
                    {
                        if (Math.abs(paddle.velocity.y) < 6f)
                            paddle.velocity.y += 1.2f;
                    }
                }
            }
        }
    }

    void UpdateEntities()
    {
        AI();

        for (Ball ball : ballList) 
        {
            ball.position.x += ball.velocity.x;
            ball.position.y += ball.velocity.y;
            ball.hitbox = new CollisionRect(ball.position, ball.size);
        }

        for (Paddle paddle : paddleList) 
        {
            paddle.position.x += paddle.velocity.x;
            paddle.position.y += paddle.velocity.y;
            paddle.hitbox = new CollisionRect(paddle.position, paddle.size);
            paddle.velocity.x *= 0.8f;
            paddle.velocity.y *= 0.8f;
        }
    }
}
