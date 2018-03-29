package invaders;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import base.HitListener;
import base.Sprite;
import base.Velocity;
import biuoop.DrawSurface;
import game.Shots;
import game.Block;
import game.GameLevel;
import game.Paddle;
import geometry.Point;


/**
 * this class represent block group enemy.
 */
public class BlockEnemyCollection implements Sprite, HitListener {
    private GameLevel game;
    private List<Block> blocks;
    //the key is the x/y Value of block, and the value is set of block with the same x/y value.
    private Map<Double, List<Block>> mapColumns;
    private Map<Double, List<Block>> mapRows;
    private double angle;
    private double originalSpeed;
    private double speed;
    private boolean movedDown;
    private boolean finish;
    private long lastTimeShot;
    private double maxX, minX, maxY, minY;
    private boolean doShot;

    /**
     * constructor.
     * @param game the game
     * @param speed the speed of enemy
     */
    public BlockEnemyCollection(GameLevel game, double speed) {
        this.game = game;
        this.blocks = new ArrayList<Block>();
        this.mapColumns = new TreeMap<Double, List<Block>>();
        this.mapRows = new TreeMap<Double, List<Block>>();
        this.angle = 90;
        this.originalSpeed = speed;
        this.speed = speed;
        this.movedDown = false;
        this.finish = false;
        this.lastTimeShot = 0;
        this.maxX = 0;
        this.minX = 800;
        this.maxY = 0;
        this.doShot = true;
    }

    /**
     * check the status of the game an do action accordingly.
     */
    public void check() {
        // compare to find the x max value ,x min value and etc..
        this.maxX = 0;
        this.minX = 800;
        this.maxY = 0;
        this.minY = 600;
        for (Double x : this.mapColumns.keySet()) {
            if (this.maxX < x) {
                this.maxX = x;
            }
            if (this.minX > x) {
                this.minX = x;
            }
        }
        for (Double y : this.mapRows.keySet()) {
            if (this.maxY < y) {
                this.maxY = y;
            }
            if (this.minY > y) {
                this.minY = y;
            }
        }
        // the left edge (according to upperleft point of block.)
        if (this.maxX >= 750) {
            this.angle = 270;
            this.doShot = true;
        }
        // right edge
        if (this.minX <= 0) {
            this.angle = 90;
            this.doShot = true;
        }
        // move down.
        if ((this.minX <= 0 || this.maxX >= 750) && !this.movedDown) {
            this.angle = 180;
            this.speed *= 1.1;
            this.movedDown = true;
            this.doShot = false;
        }
        if (this.angle == 90 || this.angle == 270) {
            this.movedDown = false;
            this.doShot = true;
        }
        // arrived to shild block.
        if (maxY >= 450) {
            this.finish = true;
        }
    }

    /**
     * responssible to move the group together.
     * @param dt the dt
     */
    public void move(double dt) {
        Velocity velocity = null;
        if (this.angle == 180) {
            velocity = Velocity.fromAngleAndSpeed(this.angle, 1000);
        } else {
            velocity = Velocity.fromAngleAndSpeed(this.angle, this.speed);
        }
        for (Block block : this.blocks) {
            block.move(velocity, dt);
        }
    }

    /**
     *  reset the level after decrease live.
     * @param dt the dt
     */
    public void reset(double dt) {
        while (this.minY > 30) {
            this.check();
            this.angle = 0;
            this.move(dt);
            this.updateMaps();
        }
        while (this.minX + this.maxX < 750) {
            this.check();
            this.angle = 90;
            this.move(dt);
            this.updateMaps();
        }

        this.angle = 90;
        this.speed = this.originalSpeed;
        this.movedDown = false;
        this.finish = false;
        this.doShot = true;
    }

    /**
     * update the map.
     */
    public void updateMaps() {
        this.mapColumns.clear();
        this.mapRows.clear();
        for (Block block : this.blocks) {
            this.addBlockMapping(block);
        }
    }

    /**
     * remove block from list and call to remove from map.
     * @param block - block enemy
     */
    public void removeBlock(Block block) {
        this.blocks.remove(block);
        this.removeBlockMapping(block);


    }

    /**
     * map each block to the suitable map.
     * @param block - block (enemy)
     */
    public void addBlockMapping(Block block) {
        if (!this.mapRows.containsKey(
                block.getCollisionRectangle().getUpperLeft().getY())) {
            List<Block> lstBlocks = new ArrayList<>();
            this.mapRows.put(
                    block.getCollisionRectangle().getUpperLeft().getY(), lstBlocks);
        }
        if (!this.mapColumns.containsKey(
                block.getCollisionRectangle().getUpperLeft().getX())) {
            List<Block> lstBlocks = new ArrayList<>();
            this.mapColumns.put(
                    block.getCollisionRectangle().getUpperLeft().getX(), lstBlocks);
        }
        this.mapColumns.get(
                block.getCollisionRectangle().getUpperLeft().getX()).add(block);
        this.mapRows.get(
                block.getCollisionRectangle().getUpperLeft().getY()).add(block);
    }

    /**
     * remove block from mapColumns and mapRows thr block.
     * @param block block enemy.
     */
    public void removeBlockMapping(Block block) {
        this.mapColumns.get(block.getCollisionRectangle().getUpperLeft().getX()).remove(block);
        this.mapRows.get(block.getCollisionRectangle().getUpperLeft().getY()).remove(block);
    }

    /**
     * add block to the list and call other function.
     * @param block block enemy
     */
    public void addBlock(Block block) {
        this.blocks.add(block);
        this.addBlockMapping(block);
    }

    /**
     * return if the blocks enemy arrived to shild.
     * @return return true if the block enemy arrived to shield
     */
    public boolean isFinish() {
        return this.finish;
    }

    /**
     * responsible to decide which block can shoot.
     * @param gameLevel the game.
     */
    public void shoot(GameLevel gameLevel) {
        if (this.doShot) {


            if (System.currentTimeMillis() - lastTimeShot > 500) {
                List<Block> lowest = new ArrayList<Block>();

                for (Double x : this.mapColumns.keySet()) {
                    List<Block> column = mapColumns.get(x);
                    if (column.size() == 0) {
                        continue;
                    } else {
                        // find the lowest block
                        Block lowestBlock = new Block();
                        lowestBlock.setUpperLeft(0, -1);
                        // check which block is the lowest in set.
                        for (Block block : column) {
                            if (block.getCollisionRectangle().getUpperLeft().getY()
                                    > lowestBlock.getCollisionRectangle().
                                    getUpperLeft().getY()) {
                                lowestBlock = block;
                            }
                        }
                        lowest.add(lowestBlock);
                    }
                }
                // rand block from the list.
                Random random = new Random();
                // rand from list block that shoot.
                int shooterIndex = random.nextInt(lowest.size());
                Shots shot = gameLevel.getEnemyShoot(new Point(lowest.get(shooterIndex).
                        getCollisionRectangle().
                        getDown().middle().getX(), lowest.get(shooterIndex).
                        getCollisionRectangle().
                        getDown().middle().getY() + 5));
                shot.setVelocity(
                        Velocity.fromAngleAndSpeed(180, 300));
                shot.addToGame(gameLevel);
                this.lastTimeShot = System.currentTimeMillis();
            }
        }
    }


    @Override
    public void drawOn(DrawSurface d) {

    }

    @Override
    public void timePassed(double dt) {
        this.check();
        this.move(dt);
        this.updateMaps();
        this.shoot(this.game);
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    @Override
    public void hitEvent(Block beingHit, Shots hitter) {
        if (beingHit.isAlienBlock() && hitter.isAlienShot()) {
           // boolean flag = true;
            return;
        }
        this.removeBlock(beingHit);

    }

    @Override
    public void hitEvent(Paddle beingHit, Shots hitter) {
    }
}





