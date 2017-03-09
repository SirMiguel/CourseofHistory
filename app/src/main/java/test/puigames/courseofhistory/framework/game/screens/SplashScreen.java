package test.puigames.courseofhistory.framework.game.screens;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import test.puigames.courseofhistory.framework.engine.GameProperties;
import test.puigames.courseofhistory.framework.engine.inputfriends.subfriends.AndroidInput;
import test.puigames.courseofhistory.framework.engine.screen.Menu;

/**
 * Created by Christopher on 24/11/2016.
 */

public class SplashScreen extends Menu
{

    Bitmap logo
    Bitmap scaledLogo;
    int width = 640;
    int height = 400;
    float duration = 0;

    public SplashScreen(GameProperties gameProperties) {
        super(gameProperties);
        load();
    }

    public void load() {
        logo = resourceFetcher.getBitmapFromFile("splash.png");
        scaledLogo = Bitmap.createScaledBitmap(logo, width, height, true);
    }

    @Override
    public void update(float deltaTime, AndroidInput input) {
        super.update(deltaTime, input);
        duration += deltaTime;
        if(duration > 5)
            gameProperties.setScreen(new MainMenu(gameProperties));

    }

    @Override
    public void draw(Canvas canvas, float deltaTime) {
        canvas.drawBitmap(scaledLogo, 0.f, 0.f, null);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
