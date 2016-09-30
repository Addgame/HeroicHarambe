package edu.lehigh.cse.lol;

/**
 * Created by CSE User on 9/30/2016.
 */

/*
 * Added for access to enemies created
 */
public class ScoreHack {
    /*
     * Gets enemies created
     */
    public static int getEnemiesCreated() {
        return Lol.sGame.mCurrentLevel.mScore.mEnemiesCreated;
    }
}
