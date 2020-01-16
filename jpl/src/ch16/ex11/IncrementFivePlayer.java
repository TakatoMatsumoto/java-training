package ch16.ex11;

import ch16.ex11.Game.Reply;

public class IncrementFivePlayer extends Player
{

    @Override
    public int strategy(int previous, Reply result)
    {
        switch (result)
        {
            case EQUAL:
                return previous;
            case GREATER:
                return previous - 1;
            case LESS:
                return previous + 5;
            default:
                return previous;
        }
    }

}
