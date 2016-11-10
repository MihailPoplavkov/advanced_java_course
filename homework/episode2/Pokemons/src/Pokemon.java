import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihailpoplavkov on 09.11.2016.
 */
public class Pokemon {
    public enum State { UNDEFINED, FIGHTING, TRAINING, SLEEPING, EATING, PLAYING };
    public enum Direction { HEAD, BODY, FEET };
    public enum Combat { UNDEFINED, ATTACK, DEFEND };

    private String mName;
    private int mHealthPoints = 100;
    private int mPower;
    private int mEnergy = 100;
    private State mState;
    private Combat mCombat = Combat.UNDEFINED;
    private Direction mDirection = null;
    private Pokemon mEnemy;

    private final Logger LOGGER = Logger.getLogger(Pokemon.class.getName() + " " + mName);

    private Pokemon(String name, int power,State state, Pokemon enemy) {
        mName = name;
        mPower = power;
        mState = state;
        mEnemy = enemy;
        String firstWords = "I was born! My power is " + mPower;
        if (mEnemy != null) {
            firstWords += ", my enemy is " + mEnemy.getName();
        }
        say(firstWords);
    }

    public Pokemon(String name, int power) {
        this(name, power, State.UNDEFINED, null);
    }

    public Pokemon(String name, int power, Pokemon enemy) {
        this(name, power, State.UNDEFINED, enemy);
    }

    public void setAction(Combat combat, Direction direction) {
        mCombat = combat;
        mDirection = direction;
        switch (mCombat) {
            case ATTACK:
                say("Trying to attack pokemon " + mEnemy.getName() + " to " + mDirection);
                break;
            case DEFEND:
                say("Defending " + mDirection);
        }
    }

    public void go() {
        if (isAlive()) {
            if (mCombat == Combat.ATTACK && hasEnergy()) {
                if ((mEnemy.getCombat() == Combat.DEFEND) && (mEnemy.getDirection() == mDirection)) {
                    say("Hit was parried");
                } else {
                    say("Attack was successful");
                    mEnemy.takeAHit(mPower);
                }
                mEnergy -= 30;
                if (!hasEnergy()) {
                    say("Ohhh, I'm tired! I lost all points of my energy...");
                }
            }
        }
    }

    public void takeAHit(int value) {
        say("I take a hit!");
        mHealthPoints -= value;
        if (mHealthPoints <= 0) {
            say("I died...");
        } else {
            say("My health points: " + mHealthPoints);
        }
    }

    public boolean isAlive() {
        return mHealthPoints > 0;
    }

    public boolean hasEnergy() {
        return mEnergy > 0;
    }

    public String getName() {
        return mName;
    }

    public int getHealthPoints() {
        return mHealthPoints;
    }

    public int getPower() {
        return mPower;
    }

    public State getState() {
        return mState;
    }

    public void setState(State state) {
        mState = state;
    }

    public Pokemon getEnemy() {
        return mEnemy;
    }

    public void setEnemy(Pokemon enemy) {
        say("Now my enemy is " + enemy.getName());
        mEnemy = enemy;
    }

    public Combat getCombat() {
        return mCombat;
    }

    public Direction getDirection() {
        return mDirection;
    }
    
    public void say(String info) {
        System.out.println("---------- " + mName + ": " + info);
    }
}
