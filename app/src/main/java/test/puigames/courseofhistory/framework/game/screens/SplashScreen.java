package test.puigames.courseofhistory.framework.game.screens;

import android.graphics.Canvas;
import android.util.Log;

import test.puigames.courseofhistory.framework.engine.GameProperties;
import test.puigames.courseofhistory.framework.engine.screen.Menu;
import test.puigames.courseofhistory.framework.engine.ui.ImageUIElement;

/**
 * Created by Christopher on 24/11/2016.
 */

public class SplashScreen extends Menu {

    private ImageUIElement logo;
    private float screenDuration = 0.0f, splashScreenDelay = 3.0f;
    private float bgImageWidth = 480.0f, bgImageHeight = 320.0f;
    private float bgImageX = 240.0f, bgImageY = 160.0f;

    public SplashScreen(GameProperties gameProperties) {
        super(gameProperties);
        load();
    }

    @Override
    public void load() {
        try {
            logo = new ImageUIElement(this, resourceFetcher.getBitmapFromFile("images/splashscreen/splash.png"),
                    bgImageWidth, bgImageHeight);
        }
        catch(NullPointerException e) {
            Log.d("Error", "Can't load UI elements");
            this.gameProperties.setScreen(new SplashScreen(this.gameProperties));
        }

        logo.place(this, bgImageX, bgImageY);

        uiElements.add(logo);
    }

    //Allows for the splash image to display and then swaps screen to main menu after a set time
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if(screenDuration > splashScreenDelay)
                this.gameProperties.setScreen(new MainMenu(this.gameProperties));

        screenDuration += deltaTime;
    }

    @Override
    public void draw(Canvas canvas, float deltaTime) {
        super.draw(canvas, deltaTime);
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

    //Getters and Setters
    public ImageUIElement getLogo() {
        return logo;
    }

    public void setLogo(ImageUIElement logo) {
        this.logo = logo;
    }

    public float getScreenDuration() {
        return screenDuration;
    }

    public void setScreenDuration(float screenDuration) {
        this.screenDuration = screenDuration;
    }

    public float getSplashScreenDelay() {
        return splashScreenDelay;
    }

    public void setSplashScreenDelay(float splashScreenDelay) {
        this.splashScreenDelay = splashScreenDelay;
    }

    public float getBgImageWidth() {
        return bgImageWidth;
    }

    public void setBgImageWidth(float bgImageWidth) {
        this.bgImageWidth = bgImageWidth;
    }

    public float getBgImageHeight() {
        return bgImageHeight;
    }

    public void setBgImageHeight(float bgImageHeight) {
        this.bgImageHeight = bgImageHeight;
    }

    public float getBgImageX() {
        return bgImageX;
    }

    public void setBgImageX(float bgImageX) {
        this.bgImageX = bgImageX;
    }

    public float getBgImageY() {
        return bgImageY;
    }

    public void setBgImageY(float bgImageY) {
        this.bgImageY = bgImageY;
    }

}
