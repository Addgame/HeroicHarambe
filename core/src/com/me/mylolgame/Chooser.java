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

import edu.lehigh.cse.lol.Facts;
import edu.lehigh.cse.lol.Level;
import edu.lehigh.cse.lol.Lol;
import edu.lehigh.cse.lol.LolCallback;
import edu.lehigh.cse.lol.Obstacle;
import edu.lehigh.cse.lol.Physics;
import edu.lehigh.cse.lol.ScreenManager;
import edu.lehigh.cse.lol.Util;

/**
 * Chooser draws the level chooser screens. Our chooser code is pretty
 * straightforward. However, the different screens are drawn in different ways,
 * to show how we can write more effective code once we are comfortable with
 * loops and basic geometry.
 */
public class Chooser implements ScreenManager {

    /**
     * This is a helper function for drawing a level button. If the level is
     * locked, the button isn't playable. Otherwise, the player can tap the
     * button to start a level.
     *
     * @param x      X coordinate of the bottom left corner of the button
     * @param y      Y coordinate of the bottom left corner of the button
     * @param width  width of the button
     * @param height height of the button
     * @param level  which level to play when the button is tapped
     */
    static void drawLevelButton(float x, float y, float width, float height, final int level) {
        // figure out the last unlocked level
        int unlocked = Facts.getGameFact("unlocked", 1);

        // for each button, start by drawing an obstacle
        Obstacle tile = Obstacle.makeAsBox(x, y, width, height, "leveltile.png");

        // if this level is unlocked, or if we are in unlocked mode, then attach
        // a callback and print the level number with a touchCallback, and then
        // put text on top of it
        if (level <= unlocked || Lol.getUnlockMode()) {
            tile.setTouchCallback(0, 0, 0, 0, false, new LolCallback() {
                public void onEvent() {
                    Lol.doLevel(level);
                }
            });
            Util.drawTextCentered(x + width / 2, y + height / 2, "" + level, 255, 255, 255, "arial.ttf", 56, 0);
        }
        // otherwise, just print an X
        else {
            Util.drawTextCentered(x + width / 2, y + height / 2, "X", 255, 255, 255, "arial.ttf", 56, 0);
        }
    }

    /**
     * This helper function is for drawing the button that takes us to the previous chooser screen
     *
     * @param x            X coordinate of bottom left corner of the button
     * @param y            Y coordinate of bottom left corner of the button
     * @param width        width of the button
     * @param height       height of the button
     * @param chooserLevel The chooser screen to create
     */
    static void drawPrevButton(float x, float y, float width, float height, final int chooserLevel) {
        Obstacle prev = Obstacle.makeAsBox(x, y, width, height, "leftarrow.png");
        prev.setTouchCallback(0, 0, 0, 0, false, new LolCallback() {
            public void onEvent() {
                Lol.doChooser(chooserLevel);
            }
        });
    }

    /**
     * This helper function is for drawing the button that takes us to the next chooser screen
     *
     * @param x            X coordinate of bottom left corner of the button
     * @param y            Y coordinate of bottom left corner of the button
     * @param width        width of the button
     * @param height       height of the button
     * @param chooserLevel The chooser screen to create
     */
    static void drawNextButton(float x, float y, float width, float height, final int chooserLevel) {
        Obstacle prev = Obstacle.makeAsBox(x, y, width, height, "rightarrow.png");
        prev.setTouchCallback(0, 0, 0, 0, false, new LolCallback() {
            public void onEvent() {
                Lol.doChooser(chooserLevel);
            }
        });
    }

    /**
     * This helper function is for drawing the button that takes us back to the splash screen
     *
     * @param x      X coordinate of bottom left corner of the button
     * @param y      Y coordinate of bottom left corner of the button
     * @param width  width of the button
     * @param height height of the button
     */
    static void drawSplashButton(float x, float y, float width, float height) {
        Obstacle prev = Obstacle.makeAsBox(x, y, width, height, "backarrow.png");
        prev.setTouchCallback(0, 0, 0, 0, false, new LolCallback() {
            public void onEvent() {
                Lol.doSplash();
            }
        });
    }

    /**
     * Describe how to draw each level of the chooser. Our chooser will have 15
     * levels per screen, so we need 7 screens.
     */
    public void display(int which) {
        // screen 1: show 1-->15
        //
        // NB: in this screen, we assume you haven't done much programming, so
        // we draw each button with its own line of code, and we don't use any
        // variables.
        if (which == 1) {
            Level.configure(48, 32);
            Physics.configure(0, 0);

            // set up background and music
            Util.drawPicture(0, 0, 48, 32, "HarambeArt/Platform/world map.png", 0);
            Util.drawPicture(8.5f, 16, 5, 5, "HarambeArt/Platform/canadian flag.png", 0);
            drawLevelButton(8.5f, 10, 5, 5, 1);
            Util.drawPicture(15f, 16, 5, 5, "HarambeArt/Platform/Detroid.png", 0);
            drawLevelButton(15f, 10, 5, 5, 2);
            Util.drawPicture(21.5f, 16, 5, 5, "HarambeArt/Platform/mexican flag.png", 0);
            drawLevelButton(21.5f, 10, 5, 5, 3);
            Util.drawPicture(28f, 16, 5, 5, "HarambeArt/Platform/japan Flag.png", 0);
            drawLevelButton(28f, 10, 5, 5, 4);
            Util.drawPicture(34.5f, 16, 5, 5, "HarambeArt/Platform/cincinatti.png", 0);
            drawLevelButton(34.5f, 10, 5, 5, 5);

            Level.setMusic("Music/BGM/BGM (Menu).ogg");

            // for each button, draw an obstacle with a touchCallback, and then
            // put text on top of it. Our buttons are 5x5, we have 1.5 meters
            // between buttons, there's an 8.5 meter border on the left and
            // right, and there's an 11 meter border on the top





            // draw the navigation buttons
            drawSplashButton(0, 0, 5, 5);
        }

        // screen 2: show levels 16-->30
        //
        // NB: this time, we'll use three loops to create the three rows. By
        // using some variables in the loops, we get the same effect as the
        // previous screen. The code isn't simpler yet, but it's still pretty
        // easy to understand.
        else if (which == 2) {
            Level.configure(48, 32);
            Physics.configure(0, 0);

            // set up background and music
            Util.drawPicture(0, 0, 48, 32,"HarambeArt/Platform/Final scene.png", 0);
            Level.setMusic("Music/BGM/Yeee(Producer song).ogg");



            // draw the navigation buttons
            drawSplashButton(0, 28, 4, 4);
        }
    }
}
