import java.awt.Canvas;

public class Main 
{
    public static Vector2D windowSize = new Vector2D(1280, 720);

    public static void main(String[] args) 
    {
        double amountOfTicks =60.0;

        double delta = 0;

        Game game = new Game();

        boolean init = false;

        long lastTime = System.nanoTime();

        double nanoSeconds = 1000000000 / amountOfTicks;

        boolean quit = false;

        Renderer renderer = new Renderer(game, windowSize);

        Canvas canvas = new Canvas();
        canvas.setSize((int)windowSize.x, (int)windowSize.y); // redundant??

        do // Game Loop
        {
            if (!init)
            {
                if (!Initalize.Init(game))
                    quit = true;

                init = true;
            }

            long now = System.nanoTime();
			delta += (now - lastTime) / nanoSeconds;
			lastTime = now;

			if (delta >= 1) 
            {
                renderer.repaint();

				game.Update();

				delta--;
			}

        } while (!quit);

        renderer.dispose();

        return;
    }
}
