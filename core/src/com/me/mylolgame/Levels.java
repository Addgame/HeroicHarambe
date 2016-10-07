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

import com.badlogic.gdx.math.Vector2;

import edu.lehigh.cse.lol.Actor;
import edu.lehigh.cse.lol.Animation;
import edu.lehigh.cse.lol.Background;
import edu.lehigh.cse.lol.Control;
import edu.lehigh.cse.lol.Destination;
import edu.lehigh.cse.lol.Display;
import edu.lehigh.cse.lol.Effect;
import edu.lehigh.cse.lol.Enemy;
import edu.lehigh.cse.lol.Facts;
import edu.lehigh.cse.lol.Foreground;
import edu.lehigh.cse.lol.Goodie;
import edu.lehigh.cse.lol.Hero;
import edu.lehigh.cse.lol.Level;
import edu.lehigh.cse.lol.LolCallback;
import edu.lehigh.cse.lol.LoseScene;
import edu.lehigh.cse.lol.Obstacle;
import edu.lehigh.cse.lol.PauseScene;
import edu.lehigh.cse.lol.Physics;
import edu.lehigh.cse.lol.PreScene;
import edu.lehigh.cse.lol.ProjectilePool;
import edu.lehigh.cse.lol.Route;
import edu.lehigh.cse.lol.Score;
import edu.lehigh.cse.lol.ScoreHack;
import edu.lehigh.cse.lol.ScreenManager;
import edu.lehigh.cse.lol.Svg;
import edu.lehigh.cse.lol.Tilt;
import edu.lehigh.cse.lol.Util;
import edu.lehigh.cse.lol.WinScene;

/**
 * Levels is where all of the code goes for describing the different levels of
 * the game. If you know how to create methods and classes, you're free to make
 * the big "if" statement in this code simply call to your classes and methods.
 * Otherwise, put your code directly into the parts of the "if" statement.
 */
public class Levels implements ScreenManager {

    /**
     * We currently have 92 levels, each of which is described in part of the
     * following function.
     */
    public void display(int whichLevel) {
        //Facts.putGameFact("unlocked", 1);

        // set up level size and physics
        Level.configure(3 * 48, 32);
        Physics.configure(0, -30);

        // create hero, set camera to follow, and set jumping impulses
        float hx, hy; // hero's x and y coords based on current level
        switch (whichLevel) {
            case 4:
                hx = 2;
                hy = 23;
                break;
            case 5:
                hx = 72;
                hy = 25.5f;
                break;
            default:
                hx = 4;
                hy = 5;
        }
        //Hero h = Hero.makeAsBox(hx, hy, 3, 5, "HarambeArt/Harambe Complete.png");
        Hero h = Hero.makeAsBox(hx, hy, 3, 4.95f, "HarambeArt/Harambe Complete.png");
        Level.setCameraChase(h);
        h.setJumpImpulses(0, 25);
        h.setDefaultReverseAnimation(new Animation("HarambeArt/Harambe Walk/BackwardAnim.png", 3, true).to(0, 100).to(2, 80).to(1, 100));//.to(2, 80));
        h.setDefaultAnimation(new Animation("HarambeArt/Harambe Walk/ForwardAnim.png", 3, true).to(0, 100).to(2, 80).to(1, 100));//.to(2, 80));

        // set projectiles
        ProjectilePool.configure(100, 1, 1, "HarambeArt/book.png", 1, 0, true);
        ProjectilePool.setRange(30);

        // add control buttons onto the screen
        Control.addThrowButton(650, 18, 75, 75, "HarambeArt/Throw.png", h, 500, 3, 2.5f, 30, 0);
        Control.addJumpButton(800, 18, 75, 75, "HarambeArt/Jump.png", h);
        Control.addLeftButton(80, 18, 75, 75, "HarambeArt/Left.png", 20, h);
        Control.addRightButton(230, 18, 75, 75, "HarambeArt/Right.png", 20, h);


        if (whichLevel == 1) {

            // set level music
            Level.setMusic("Music/Bosses/Boss 01.ogg");

            // add background
            Background.addHorizontalLayer(0,0, "HarambeArt/Platform/Canada.png", -16, 48, 32);

            // create level borders
            Util.drawBoundingBox(0, 5, 3 * 48, 32, "HarambeArt/Platform/Canada Repeat Platform.png", 1, 0, 1);

            // create platform
            Obstacle.makeAsBox(60, 12, 16, 2, "HarambeArt/Platform/Canada Repeat Platform.png");

            // create enemies
            Enemy.makeAsBox(68, 14, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(45, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(120, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f).disableRotation().setChaseSpeed(3,h,true,false);

            // create level end
            Obstacle f = Obstacle.makeAsBox(138, 5, 2, 3, "HarambeArt/mard.png");
            f.setPhysics(1.0f, 0.3f, 0.6f);
            f.setHeroCollisionCallback(0, 0, 0, 0, 0, new LolCallback() {
                        @Override
                        public void onEvent() {
                            if (Score.getEnemiesDefeated() == ScoreHack.getEnemiesCreated()) {
                                Score.winLevel();
                            }
                        }
                    }
            );

        } else if (whichLevel == 2) {

            // set level music
            Level.setMusic("Music/Bosses/Boss 02.ogg");

            // add background
            Background.addHorizontalLayer(0,0, "HarambeArt/Platform/Detroid.png", -16, 48, 32);

            // create level borders
            Util.drawBoundingBox(0, 5, 3 * 48, 32, "HarambeArt/Platform/Detroid Repeat Platform.png", 1, 0, 1);

            // create platforms
            Obstacle.makeAsBox(39, 12, 16, 2, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(80, 12, 16, 2, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(60, 4.5f, 4, 12, "HarambeArt/Lamp Post.png");

            // create enemies
            Enemy.makeAsBox(45, 14, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(88, 14, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(120, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f).disableRotation().setChaseSpeed(3,h,true,false);

            // create level end
            Obstacle f = Obstacle.makeAsBox(138, 5, 2, 3, "HarambeArt/mard.png");
            f.setPhysics(1.0f, 0.3f, 0.6f);
            f.setHeroCollisionCallback(0, 0, 0, 0, 0, new LolCallback() {
                        @Override
                        public void onEvent() {
                            if (Score.getEnemiesDefeated() == ScoreHack.getEnemiesCreated()) {
                                Score.winLevel();
                            }
                        }
                    }
            );

        }
        else if (whichLevel == 3) {

            // set level music
            Level.setMusic("Music/Bosses/Boss 04.ogg");

            // add background
            Background.addHorizontalLayer(0,0, "HarambeArt/Platform/mexico.png", -16, 48, 32);

            // create level borders
            Util.drawBoundingBox(0, 5, 3 * 48, 32, "HarambeArt/Platform/Detroid Repeat Platform.png", 1, 0, 1);

            // create platforms
            Obstacle.makeAsBox(30, 12, 16, 2, "HarambeArt/Platform/Mexico Repeat Platform.png");
            Obstacle.makeAsBox(80, 18, 16, 3, "HarambeArt/Platform/Mexico Repeat Platform.png");
            Obstacle.makeAsBox(60, 4.5f, 4, 12, "HarambeArt/Lamp Post.png");

            // create enemies
            Enemy.makeAsBox(35, 14, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(90, 21, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(120, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(53, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(71, 5, 5, 9, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f).disableRotation().setChaseSpeed(3,h,true,false);

            // create level end
            Obstacle f = Obstacle.makeAsBox(138, 5, 2, 3, "HarambeArt/mard.png");
            f.setPhysics(1.0f, 0.3f, 0.6f);
            f.setHeroCollisionCallback(0, 0, 0, 0, 0, new LolCallback() {
                        @Override
                        public void onEvent() {
                            if (Score.getEnemiesDefeated() == ScoreHack.getEnemiesCreated()) {
                                Score.winLevel();
                            }
                        }
                    }
            );
        }
        else if (whichLevel == 4) {

            // set level music
            Level.setMusic("Music/Bosses/Boss 01.ogg");

            // add background
            Background.addHorizontalLayer(0,0, "HarambeArt/Platform/Japan.png", -16, 48, 32);

            // create level borders
            Util.drawBoundingBox(0, 5, 3 * 48, 32, "HarambeArt/Platform/Detroid Repeat Platform.png", 1, 0, 1);

            // create platforms
            Obstacle.makeAsBox(0, 22, 16, 3, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(35, 11, 16, 3, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(80, 22, 16, 3, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(105, 11, 4, 3, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(115, 11, 4, 3, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(130, 20, 16, 3, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(65, 4.5f, 4, 12, "HarambeArt/Lamp Post.png");
            Obstacle.makeAsBox(110, 4.5f, 4, 12, "HarambeArt/Lamp Post.png");

            // create enemies
            Enemy.makeAsBox(43, 14, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(6, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(25, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(60, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(77, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(102, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(84, 25, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(91, 25, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(135, 5, 5, 9, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f).disableRotation().setChaseSpeed(3,h,true,false);

            // create level end
            Obstacle f = Obstacle.makeAsBox(138, 23, 2, 3, "HarambeArt/mard.png");
            f.setPhysics(1.0f, 0.3f, 0.6f);
            f.setHeroCollisionCallback(0, 0, 0, 0, 0, new LolCallback() {
                        @Override
                        public void onEvent() {
                            if (Score.getEnemiesDefeated() == ScoreHack.getEnemiesCreated()) {
                                Score.winLevel();
                            }
                        }
                    }
            );
        } else if (whichLevel == 5) {

            // set level music
            Level.setMusic("Music/Bosses/Boss 03 (Final).ogg");

            // add background
            Background.addHorizontalLayer(0,0, "HarambeArt/Platform/cincinatti.png", -16, 48, 32);

            // create level borders
            Util.drawBoundingBox(0, 5, 3 * 48, 32, "HarambeArt/Platform/Detroid Repeat Platform.png", 1, 0, 1);

            // create platforms
            Obstacle.makeAsBox(66, 23.5f, 16, 3, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(11, 11, 13, 3, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(117, 21, 6, 2, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(52, 11, 13, 3, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(82, 11, 13, 3, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(122, 12, 13, 2, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(130, 21, 16, 3, "HarambeArt/Platform/Detroid Repeat Platform.png");
            Obstacle.makeAsBox(71.5f, 4.5f, 4, 12, "HarambeArt/Lamp Post.png");
            Obstacle.makeAsBox(36, 4.5f, 4, 12, "HarambeArt/Lamp Post.png");
            Obstacle.makeAsBox(109, 4.5f, 4, 12, "HarambeArt/Lamp Post.png");

            // create enemies
            Enemy.makeAsBox(90, 14, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(54, 14, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(65, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(50, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(16, 14, 5, 8, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f).disableRotation().setChaseSpeed(3,h,true,false);
            Enemy.makeAsBox(21, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(4, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(102, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(89, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(126, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(138, 5, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);
            Enemy.makeAsBox(129, 14, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f).disableRotation().setChaseSpeed(3,h,true,false);
            Enemy.makeAsBox(137, 24, 3, 6, "HarambeArt/Characters(Good)/soldierS.png").setPhysics(1.0f, 0.3f, 0.6f);

            // create level end
            Obstacle f = Obstacle.makeAsBox(141, 24, 2, 3, "HarambeArt/mard.png");
            f.setPhysics(1.0f, 0.3f, 0.6f);
            f.setHeroCollisionCallback(0, 0, 0, 0, 0, new LolCallback() {
                        @Override
                        public void onEvent() {
                            if (Score.getEnemiesDefeated() == ScoreHack.getEnemiesCreated()) {
                                Score.winLevel();

                            }
                        }
                    }
            );
        }
    }
}