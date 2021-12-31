import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter
{
    Game game;

    KeyboardInput(Game game)
    {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        int key = keyEvent.getKeyCode();

        if (game.keys.get(key) == null)
            game.keys.put(key, true);
        else
            game.keys.put(key, true);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent)
    {
        int key = keyEvent.getKeyCode();

        if (game.keys.get(key) == null)
            game.keys.put(key, false);
        else
            game.keys.put(key, false);
    }

    @Override
    public void keyTyped(KeyEvent pKeyEvent) {  }
}
