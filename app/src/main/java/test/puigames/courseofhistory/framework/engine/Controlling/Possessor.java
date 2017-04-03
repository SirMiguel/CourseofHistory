package test.puigames.courseofhistory.framework.engine.Controlling;

import test.puigames.courseofhistory.framework.game.controllers.Player;

/**
 * Created by Michael on 31/03/2017.
 */

public interface Possessor {
    void possessPlayer(Player player);

    void update(float deltaTime);

    Player getPlayer();


}
