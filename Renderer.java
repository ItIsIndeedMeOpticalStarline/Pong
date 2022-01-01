import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JFrame;

import java.awt.Toolkit;

public class Renderer extends JFrame
{
    Game game;

    Renderer(Game game, Vector2D size)
    {
        this.game = game;

        this.addKeyListener(new KeyboardInput(game));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int)size.x, (int)size.y); // Must be before setLocationRelativeTo(Component)
        this.setLocationRelativeTo(null); // Spawn origin, pass in null for screen center
        this.setResizable(false);
        this.setTitle("PONG");
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) // Envoked by itself
    {
        this.Clear(graphics); // Split my method for cleanliness
        Draw(graphics);
    }

    public void Draw(Graphics graphics)
    {      
        for (Ball ball : game.ballList)
        {
            this.DrawEntity(graphics, ball, "Oval");
        }

        for (Paddle paddle : game.paddleList)
        {
            this.DrawEntity(graphics, paddle, "Rectangle");

            if (paddle.aiControlled)
            {
                this.DrawString(graphics, new Vector2D((Main.windowSize.x / 3) * 2, 50), String.valueOf(paddle.score), Font.SANS_SERIF);
                if (paddle.score >= 10)
                {
                    game.ended = true;
                    this.DrawString(graphics, new Vector2D(Main.windowSize.x / 2, 50), "Player 2 Wins!", Font.SANS_SERIF);
                    this.DrawString(graphics, new Vector2D(Main.windowSize.x / 2, 80), "Press R to reset", Font.SANS_SERIF);
                }
            }
            else
            {
                this.DrawString(graphics, new Vector2D(Main.windowSize.x / 3, 50), String.valueOf(paddle.score), Font.SANS_SERIF);
                if (paddle.score >= 10)
                {
                    game.ended = true;
                    this.DrawString(graphics, new Vector2D(Main.windowSize.x / 2, 50), "Player 1 Wins!", Font.SANS_SERIF);
                    this.DrawString(graphics, new Vector2D(Main.windowSize.x / 2, 80), "Press R to reset", Font.SANS_SERIF);
                }
            }
        }
        this.DrawString(graphics, new Vector2D(Main.windowSize.x / 2, Main.windowSize.y - 50), "Press R to reset, left and right to control the difficulty, and up and down top control the paddle", Font.SANS_SERIF);
        Toolkit.getDefaultToolkit().sync(); // Stops my frames from stuttering. Not sure why
    }

    public void Clear(Graphics graphics)
    {
        graphics.clearRect(0, 0, (int)Main.windowSize.x, (int)Main.windowSize.y);
    }

    public void DrawEntity(Graphics graphics, GameEntity entity, String drawType)
    {
        switch (drawType) 
        {
            case "Rectangle":
                graphics.fillRect((int)entity.position.x, (int)entity.position.y, (int)entity.size.x, (int)entity.size.y); 
                break;

            case "Oval":       
                graphics.fillOval((int)entity.position.x, (int)entity.position.y, (int)entity.size.x, (int)entity.size.y); 
                break;

            default:
                System.out.println("PONG error: Unspecified drawType in Renderer::DrawEntity(Graphics, GameEntity, String)");
                break;
        }
    }

    public void DrawString(Graphics graphics, Vector2D position, String str, String fontName) // Centers string when drawing
    {
        Font font = Font.getFont(fontName);

        graphics.setFont(font);

        FontMetrics metrics = graphics.getFontMetrics();

        Vector2D pos = new Vector2D(position.x + (-metrics.stringWidth(str) / 2), 
            position.y + (-metrics.getHeight() / 2) + metrics.getAscent());

        graphics.drawString(str, (int)pos.x, (int)pos.y);
    }
}
