/**
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org>
 */

package com.me.mylolgame;

import edu.lehigh.cse.lol.Lol;
import edu.lehigh.cse.lol.Media;

/**
 * The starting point for a Lol game is right here. This code does two important
 * configuration tasks: it loads all the assets (images and sounds) used by the
 * game, and it tells the Lol engine about all of the other configuration that
 * needs to be done.
 *
 * Be sure to look at the Levels.java file for how each level of the game is
 * drawn, as well as Splash.java, Chooser.java, Help.java, and Store.java.
 */
public class MyGame extends Lol {
    /**
     * Set up all the global configuration options for the game
     */
    @Override
    public void configure() {
        // to see documentation for any of these variables, hover your mouse
        // over the word on the left side of the equals sign
        mWidth = 960;
        mHeight = 640;
        mNumLevels = 93;
        mEnableVibration = true;
        mUnlockAllLevels = true;
        mShowDebugBoxes = true;
        mStorageKey = "com.me.mylolgame.prefs";
        mDefaultFontFace = "arial.ttf";
        mDefaultFontSize = 32;
        mDefaultFontRed = 0;
        mDefaultFontGreen = 0;
        mDefaultFontBlue = 0;
        mDefaultWinText = "Good Job";
        mDefaultLoseText = "Try Again";
        mGameTitle = "My Lol Game";
        mEnableChooser = true;

        // don't change these lines unless you know what you are doing
        mLevels = new Levels();
        mChooser = new Chooser();
        mHelp = new Help();
        mSplash = new Splash();
        mStore = new Store();
    }

    /**
     * Load all the images and sounds used by our game
     */
    @Override
    public void loadResources() {
        // load regular (non-animated) images
        Media.registerImage("greenball.png");
        Media.registerImage("mustardball.png");
        Media.registerImage("red.png");
        Media.registerImage("leftarrow.png");
        Media.registerImage("rightarrow.png");
        Media.registerImage("backarrow.png");
        Media.registerImage("redball.png");
        Media.registerImage("blueball.png");
        Media.registerImage("purpleball.png");
        Media.registerImage("msg1.png");
        Media.registerImage("msg2.png");
        Media.registerImage("fade.png");
        Media.registerImage("greyball.png");
        Media.registerImage("leveltile.png");
        Media.registerImage("audio_on.png");
        Media.registerImage("audio_off.png");
        Media.registerImage("HarambeArt/book.png");
        Media.registerImage("HarambeArt/bullet.png");
        Media.registerImage("HarambeArt/guy.png");
        Media.registerImage("HarambeArt/guy2.png");
        Media.registerImage("HarambeArt/Harambe Arm.png");
        Media.registerImage("HarambeArt/Harambe Body.png");
        Media.registerImage("HarambeArt/Harambe Complete.png");
        Media.registerImage("HarambeArt/hat.png");
        Media.registerImage("HarambeArt/Jump.png");
        Media.registerImage("HarambeArt/knife guy1.png");
        Media.registerImage("HarambeArt/knife guy2.png");
        Media.registerImage("HarambeArt/Lamp Post.png");
        Media.registerImage("HarambeArt/Left.png");
        Media.registerImage("HarambeArt/mard.png");
        Media.registerImage("HarambeArt/pistol guuy2.png");
        Media.registerImage("HarambeArt/pistol guy1.png");
        Media.registerImage("HarambeArt/Right.png");
        Media.registerImage("HarambeArt/shotgun1.png");
        Media.registerImage("HarambeArt/shotgun2.png");
        Media.registerImage("HarambeArt/Throw.png");
        Media.registerImage("HarambeArt/Platform/Canada Repeat Platform.png");
        Media.registerImage("HarambeArt/Platform/Cincinatti Repeat Platform.png");
        Media.registerImage("HarambeArt/Platform/Detroid Repeat Platform.png");
        Media.registerImage("HarambeArt/Platform/Mexico Repeat Platform.png");
        Media.registerImage("HarambeArt/Characters(Good)/businessmanS.png");
        Media.registerImage("HarambeArt/Characters(Good)/businessmanW.png");
        Media.registerImage("HarambeArt/Characters(Good)/doctorS.png");
        Media.registerImage("HarambeArt/Characters(Good)/doctorW.png");
        Media.registerImage("HarambeArt/Characters(Good)/lehighS.png");
        Media.registerImage("HarambeArt/Characters(Good)/lehighW.png");
        Media.registerImage("HarambeArt/Characters(Good)/soldierS.png");
        Media.registerImage("HarambeArt/Characters(Good)/soldierW.png");
        Media.registerImage("HarambeArt/Harabe Walk/BackwardAnim.png");
        Media.registerImage("HarambeArt/Harabe Walk/ForwardAnim.png");
        Media.registerImage("HarambeArt/Harabe Walk/Harambe Complete (Backward).png");
        Media.registerImage("HarambeArt/Harabe Walk/harambeW1(backward).png");
        Media.registerImage("HarambeArt/Harabe Walk/harambeW2(backward).png");
        Media.registerImage("HarambeArt/Harabe Walk/harambeW1.png");
        Media.registerImage("HarambeArt/Harabe Walk/harambeW2.png");


        // load the image we show on the main screen
        Media.registerImage("splash.png");

        // load the image we show on the chooser screen
        Media.registerImage("chooser.png");

        // load background images
        Media.registerImage("mid.png");
        Media.registerImage("front.png");
        Media.registerImage("back.png");

        // load animated images (a.k.a. Sprite Sheets)
        Media.registerAnimatableImage("stars.png", 8, 1);
        Media.registerAnimatableImage("stars_flipped.png", 8, 1);
        Media.registerAnimatableImage("flystar.png", 2, 1);
        Media.registerAnimatableImage("starburst.png", 4, 1);
        Media.registerAnimatableImage("colorstar.png", 8, 1);

        // load sounds
        Media.registerSound("hipitch.ogg");
        Media.registerSound("lowpitch.ogg");
        Media.registerSound("losesound.ogg");
        Media.registerSound("slowdown.ogg");
        Media.registerSound("woowoowoo.ogg");
        Media.registerSound("fwapfwap.ogg");
        Media.registerSound("winsound.ogg");

        // load background music
        Media.registerMusic("tune.ogg", true);
        Media.registerMusic("Music/Bosses/Boss 01.ogg", true);
        Media.registerMusic("Music/Bosses/Boss 02.ogg", true);
        Media.registerMusic("Music/Bosses/Boss 03 (Final).ogg", true);
        Media.registerMusic("Music/Bosses/Boss 04.ogg", true);
        Media.registerMusic("Music/Effects/Explosion.ogg", true);
        Media.registerMusic("Music/Effects/Game Over.ogg", true);
        Media.registerMusic("Music/Effects/Japanese News.ogg", true);
        Media.registerMusic("Music/Effects/Landing.ogg", true);
        Media.registerMusic("Music/Effects/Man crying.ogg", true);
        Media.registerMusic("Music/Effects/Open Close Meun.ogg", true);
        Media.registerMusic("Music/Effects/Rock explode.ogg", true);
        Media.registerMusic("Music/Effects/Run.ogg", true);
        Media.registerMusic("Music/Effects/Throwing.ogg", true);
        Media.registerMusic("Music/Effects/Woman Crying.ogg", true);
    }
}