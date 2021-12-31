import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

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
        }
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
}
