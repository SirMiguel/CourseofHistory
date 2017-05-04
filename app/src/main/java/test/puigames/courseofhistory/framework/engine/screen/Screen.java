package test.puigames.courseofhistory.framework.engine.screen;

import android.graphics.Canvas;

import java.util.ArrayList;

import test.puigames.courseofhistory.framework.engine.GameProperties;
import test.puigames.courseofhistory.framework.engine.gameobjects.properties.Drawable;
import test.puigames.courseofhistory.framework.engine.gameobjects.properties.Updateable;
import test.puigames.courseofhistory.framework.engine.inputfriends.InputBuddy;
import test.puigames.courseofhistory.framework.engine.resourceloading.ResourceFetcher;
import test.puigames.courseofhistory.framework.engine.screen.scaling.Scalable;
import test.puigames.courseofhistory.framework.engine.screen.scaling.Scaler;
import test.puigames.courseofhistory.framework.engine.screen.scaling.Viewport;

/**
 * Created by Jordan on 25/10/2016.
 */

public abstract class Screen {
    protected InputBuddy inputBuddy;
    protected final GameProperties gameProperties;
    protected final ResourceFetcher resourceFetcher;
    protected Scaler scaler;
    protected Viewport viewport;
    public ArrayList<Drawable> drawables;
    private ArrayList<Updateable> updateables;
    private ArrayList<Updateable> updateablesToAdd;
    private ArrayList<Updateable> updateablesToRemove;

    public ArrayList<Scalable> scalables;

    public final static float DEFAULT_LEVEL_HEIGHT = 320.f;
    public final static float DEFAULT_LEVEL_WIDTH = 480.f;

    /**
     * Constructor stores passed in GameProperties object
     * instance and stores in a final member available to
     * all subclasses. This way we can achieve two things
     *
     *      1. Access to low-level modules of the GameProperties interface
     *      to play back audio, draw to the screen, get input,
     *      read & write files
     *
     *      2. Can set a new current Screen by GameProperties.setScreen() when
     *      appropriate (eg. transitioning after a button is pressed)
     * @param gameProperties
     */
    public Screen(GameProperties gameProperties) {
        this.gameProperties = gameProperties;
        this.resourceFetcher = this.gameProperties.getResourceFetcher();
        this.inputBuddy = gameProperties.getInput();
        this.viewport = new Viewport(DEFAULT_LEVEL_WIDTH, DEFAULT_LEVEL_HEIGHT);
        this.scaler = new Scaler(gameProperties, viewport);
        this.drawables = new ArrayList<>();
        this.updateables = new ArrayList<>();
        this.scalables = new ArrayList<>();

        this.updateablesToAdd = new ArrayList<>();
        this.updateablesToRemove = new ArrayList<>();
    }

    protected abstract void load();

    public void update(float deltaTime) {
        scaler.scaleTouchInput(gameProperties.getInput());
        processUpdateablesListChanges();
        for (Updateable updateable : updateables)
            updateable.update(deltaTime);
    }

    public boolean isInUpdateables(Updateable updateable) {
        return updateables.contains(updateable);
    }

    private void processUpdateablesListChanges() {
        for (Updateable updateable : updateablesToRemove)
            updateables.remove(updateable);
        for (Updateable updateable : updateablesToAdd)
            updateables.add(updateable);
        resetUpdateablesChangeLists();
    }

    private void resetUpdateablesChangeLists() {
        clearUpdateablesToAdd();
        clearUpdateablesToRemove();
    }

    private void clearUpdateablesToAdd() {
        updateablesToAdd.clear();
    }

    private void clearUpdateablesToRemove() {
        updateablesToRemove.clear();
    }

    public void addToUpdateables(Updateable updateable) {
        updateablesToAdd.add(updateable);
    }

    public void removeFromUpdateables(Updateable updateable) {
        updateablesToRemove.add(updateable);
    }

    public void draw(Canvas canvas, float deltaTime) {
        for (Scalable scalable : scalables)
            scaler.scaleToScreen(scalable);

        for (Drawable drawable : drawables)
            drawable.draw(canvas, deltaTime);
    }

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}
