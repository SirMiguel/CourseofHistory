package test.puigames.courseofhistory.framework.game.screens;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import test.puigames.courseofhistory.framework.engine.GameProperties;
import test.puigames.courseofhistory.framework.engine.gameobjects.MenuButton;
import test.puigames.courseofhistory.framework.engine.gameobjects.imageUIElement;
import test.puigames.courseofhistory.framework.engine.inputfriends.InputBuddy;
import test.puigames.courseofhistory.framework.engine.screen.Level;
import test.puigames.courseofhistory.framework.engine.inputfriends.subfriends.AndroidInput;
import test.puigames.courseofhistory.framework.engine.inputfriends.subfriends.Input;
import test.puigames.courseofhistory.framework.engine.screen.Menu;
import test.puigames.courseofhistory.framework.game.levels.TestLevel;

/**
 * Created by Christopher on 08/02/2017.
 */

public class MainMenu extends Menu {

    imageUIElement backgroundMainMenu, title;
    MenuButton playGame, settings, howToPlay;
    float buttonWidth = 75.f;
    float buttonHeight = 50.f;

    public MainMenu(GameProperties gameProperties){
        super(gameProperties);
        load();
    }

    @Override
    public void update(float deltaTime, AndroidInput input) {
        super.update(deltaTime, input);
    }

    public void load(){
        backgroundMainMenu = null;
        title = null;
        playGame = null;
        settings = null;
        howToPlay = null;

        try{
            backgroundMainMenu = new imageUIElement(resourceFetcher.getBitmapFromFile("images/backgrounds/main_menu_background.png"),
                    480.0f, 320.0f);
            title = new imageUIElement(resourceFetcher.getBitmapFromFile("images/title/coh_title.png"),
                    350.0f, 120.0f);
            playGame = new MenuButton(gameProperties.getResourceFetcher().getBitmapFromFile("images/buttons/button_play.png"),
                    buttonWidth, buttonHeight);
            howToPlay = new MenuButton(gameProperties.getResourceFetcher().getBitmapFromFile("images/buttons/button_how-to-play.png"),
                    buttonWidth, buttonHeight);
            settings = new MenuButton(gameProperties.getResourceFetcher().getBitmapFromFile("images/buttons/button_settings.png"),
                    buttonWidth, buttonHeight);
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }

        backgroundMainMenu.placeUIElement(0.f, 0.f);
        title.placeUIElement(65.0f, 5.0f);
        playGame.placeUIElement((240.f - buttonWidth/2), (125.f));
        howToPlay.placeUIElement((240.f - buttonWidth/2), (200.f));
        settings.placeUIElement((240.f - buttonWidth/2), (275.f));

        uiElements.add(backgroundMainMenu);
        uiElements.add(title);
        uiElements.add(playGame);
        uiElements.add(howToPlay);
        uiElements.add(settings);
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
