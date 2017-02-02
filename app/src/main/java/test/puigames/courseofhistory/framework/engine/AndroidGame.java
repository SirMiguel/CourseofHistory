package test.puigames.courseofhistory.framework.engine;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import test.puigames.courseofhistory.framework.FileIO;
import test.puigames.courseofhistory.framework.audio.AndroidAudio;
import test.puigames.courseofhistory.framework.audio.Audio;
import test.puigames.courseofhistory.framework.graphics.AndroidGraphics;
import test.puigames.courseofhistory.framework.graphics.Graphics;
import test.puigames.courseofhistory.framework.engine.resourceloading.AndroidFileIO;
import test.puigames.courseofhistory.framework.engine.resourceloading.GraphicsIO;
import test.puigames.courseofhistory.framework.input.AndroidInput;
import test.puigames.courseofhistory.framework.input.Input;

/**
 * Created by Jordan on 08/11/2016.
 */

public class AndroidGame extends Activity implements Game, Runnable
{
    AndroidFastRenderView renderView;
    Graphics graphics;
    GraphicsIO graphicsIO;
    Audio audio;
    AndroidInput input;
    FileIO fileIO;
    Screen screen;
    WakeLock wakeLock;
    Thread renderThread = null;

    volatile boolean running = false;
    volatile boolean drawNeeded = false;



    long startTime = System.nanoTime();

    int screenWidth;
    int screenHeight;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);



        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);


        boolean isLandscape = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;



        renderView = new AndroidFastRenderView(this);


        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        graphics = new AndroidGraphics(getAssets());
        graphicsIO = new GraphicsIO(this);
        screen = getStartScreen();
        setContentView(renderView);


        //Power management - prevent screen from dimming
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");

        /*Display display= ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();*/



    }

    //Game Loop
    @Override
    public void run() {
        while(running) {
            //synchronises with the UI thread
            synchronized (renderView) {
                //checks if a draw is needed
                if (drawNeeded) {
                    drawNeeded = false;
                    //tells UI thread to call it's onDraw(Canvas) method
                    //Update and Draw calls for screen are in there
                    renderView.postInvalidate();
                }
            }
        }

    }

    @Override
    public void onResume()
    {
        super.onResume();
        drawNeeded = true;
        wakeLock.acquire();
        screen.resume();
        renderView.resume();

        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();
        boolean joined = false;
        while (!joined) {
            try {
                renderThread.join();
                joined = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(isFinishing())   //if activity is destroyed, clean up
            screen.dispose();


    }


    public void setScreen(Screen screen) throws IllegalArgumentException {
        if(screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0, input);
        this.screen = screen;
    }

    public void calculateScreenSize() {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }

    public int getScreenWidth(){return screenWidth;}

    public int getScreenHeight(){return screenHeight;}


    public Input getInput()
    {
        return input;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    public GraphicsIO getGraphicsIO() { return graphicsIO; }

    @Override
    public Audio getAudio() {
        return audio;
    }

    public FileIO getFileIO()
    {
        return fileIO;
    }

    public Screen getCurrentScreen()
    {
        return screen;
    }

    @Override
    public Screen getStartScreen()
    {
        return null;
    }



}
