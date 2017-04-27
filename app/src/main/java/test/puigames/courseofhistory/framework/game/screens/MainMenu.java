package test.puigames.courseofhistory.framework.game.screens;

import android.graphics.Canvas;

import test.puigames.courseofhistory.framework.engine.GameProperties;
import test.puigames.courseofhistory.framework.engine.screen.Menu;
import test.puigames.courseofhistory.framework.engine.ui.ImageUIElement;
import test.puigames.courseofhistory.framework.engine.ui.MenuButton;

/**
 * Created by Christopher on 08/02/2017.
 */



public class MainMenu extends Menu {

    ImageUIElement backgroundMainMenu, title;
    MenuButton playGame, settings, howToPlay;
    float buttonWidth = 75.f;
    float buttonHeight = 50.f;

    public MainMenu(GameProperties gameProperties){
        super(gameProperties);
        load();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

     /*   for (Input.TouchEvent touchEvent : this.gameProperties.getInput().getTouchEvents()) {
            if (playGame.boundingBox.isTouchOn(touchEvent))
                gameProperties.setScreen(new TestLevel(this.gameProperties));
            else if (howToPlay.boundingBox.isTouchOn(touchEvent))
                gameProperties.setScreen(new HowToPlayMenu(this.gameProperties));
            else if (settings.boundingBox.isTouchOn(touchEvent))
                gameProperties.setScreen(new SettingsMenu(this.gameProperties));
        }*/
    }

    public void load(){

        backgroundMainMenu = null;
        title = null;
        playGame = null;
        howToPlay = null;
        settings = null;
/*
        try{
           backgroundMainMenu = new ImageUIElement(resourceFetcher.getBitmapFromFile("images/backgrounds/main_menu_background.png"),
                  480.0f, 320.0f);
            title = new ImageUIElement(resourceFetcher.getBitmapFromFile("images/title/coh_title.png"),
                    340.0f, 100.0f);
            playGame = new MenuButton(gameProperties.getResourceFetcher().getBitmapFromFile("images/buttons/button_play.png"),
                    buttonWidth, buttonHeight);
            howToPlay = new MenuButton(gameProperties.getResourceFetcher().getBitmapFromFile("images/buttons/button_how-to-play.png"),
                    buttonWidth, buttonHeight);
            settings = new MenuButton(gameProperties.getResourceFetcher().getBitmapFromFile("images/buttons/button_settings.png"),
                    buttonWidth, buttonHeight);
        }
        catch(NullPointerException e){
            Log.d("Error", "UI Element loading has failed");
        }

        backgroundMainMenu.initPlacement(240.f, 160.f);
        title.initPlacement(240.0f, 30.0f);
        playGame.initPlacement((240.f), (100.f));
        howToPlay.initPlacement((240.f), (175.f));
        settings.initPlacement((240.f), (250.f));

        uiElements.add(backgroundMainMenu);
        uiElements.add(title);
        uiElements.add(playGame);
        uiElements.add(howToPlay);
        uiElements.add(settings);
*/
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

}
