package io;

import base.Velocity;
import game.Block;
import game.Filling;
import levels.GenericLevel;
import levels.LevelInformation;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * this class is a level specification reader.
 */
public class LevelSpecificationReader {

    private int numberOfElements = 8;
    /**
     * return the levels list.
     * @param reader the reader
     * @return the levels list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levels = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        String levelName = null;
        List<Velocity> ballVelocities = new ArrayList<Velocity>();
        Map<Integer, String> locationBlocks = new HashMap<Integer, String>();
        List<Block> blocks = new ArrayList<Block>();
        String blockDef = null, image, color, line;
        Filling backGround = null;
        int paddleSpeed = 0, paddleWidth = 0, numberOfBlocks = 0, numberOfBalls = 0, rowHeight = 0;
        Image newImage;
        Color newColor;
        int x = 0, y = 0;
        try {
            // Create file reader and file writer objects and wrap it
            while ((line = ((BufferedReader) bufferedReader).readLine()) != null) {
                line = line.trim();
                if (!line.equals("") && !line.startsWith("#")) {
                    if (line.equals("START_LEVEL")) {
                        line = ((BufferedReader) bufferedReader).readLine();
                    }
                    if (line.equals("START_BLOCKS")) {
                        int i = 1;
                        line = ((BufferedReader) bufferedReader).readLine();
                        while (!line.equals("END_BLOCKS")) {
                            locationBlocks.put(i, line);
                            i++;
                            line = ((BufferedReader) bufferedReader).readLine();
                        }
                        numberOfElements--;
                    }
                    if (line.equals("END_LEVEL")) {
                        if (numberOfElements == 0) {
                            blocks = this.readingBlcoks(locationBlocks, x, y, rowHeight, blockDef, bufferedReader);
                            Map<String, Integer> paddleDetail = new HashMap<String, Integer>();
                            paddleDetail.put("paddle width", paddleWidth);
                            paddleDetail.put("paddle speed", paddleSpeed);
                            GenericLevel genericLevel = new GenericLevel(levelName,
                                    ballVelocities, backGround, paddleDetail, numberOfBlocks, numberOfBalls, blocks);
                            levels.add(genericLevel);
                            // reset the variable.
                            this.numberOfElements = 8;
                            levelName = null;
                            ballVelocities = new ArrayList<Velocity>();
                            backGround = null;
                            image = "";
                            newImage = null;
                            color = "";
                            newColor = null;
                            paddleSpeed = 0;
                            paddleWidth = 0;
                            numberOfBlocks = 0;
                            numberOfBalls = 0;
                            blocks = new ArrayList<Block>();
                            locationBlocks = new HashMap<Integer, String>();
                            x = 0;
                            y = 0;
                            rowHeight = 0;
                            blockDef = null;
                        } else {
                            System.out.println("Not enough parameters");
                        }
                    }
                    String[] parts = line.split(":");
                    switch (parts[0]) {
                        case "level_name":
                            levelName = parts[1];
                            numberOfElements--;
                            break;
                        case "ball_velocities":
                            String[] vel = parts[1].split(" ");
                            for (int i = 0; i < vel.length; i++) {
                                // split by , .
                                String[] param = vel[i].split(",");
                                // took the angle and speed.
                                int angle = Integer.parseInt(param[0]);
                                int speed = Integer.parseInt(param[1]);
                                Velocity newVel = Velocity.fromAngleAndSpeed(angle,
                                        speed);
                                ballVelocities.add(newVel);
                                numberOfBalls++;
                            }
                            numberOfElements--;
                            numberOfElements--;
                            break;
                        case "background":
                            String[] param = parts[1].split("\\(");
                            if (param[0].equals("image")) {
                                image = param[1].split("\\)")[0];
                                newImage = Filling.imageFromString(image);
                                backGround = new Filling(null, newImage);
                                // color.
                            } else if (param[0].equals("color")) {
                                if (param[1].equals("RGB")) {
                                    color = "(" + param[1] + "(" + param[2];
                                } else {
                                    color = param[1].split("\\)")[0];
                                    color = "(" + color + ")";
                                }
                                newColor = Filling.colorFromString(color);
                                backGround = new Filling(newColor, null);
                            }
                            numberOfElements--;
                            break;
                        case "paddle_speed":
                            paddleSpeed = Integer.parseInt(parts[1]);
                            numberOfElements--;
                            break;
                        case "paddle_width":
                            paddleWidth = Integer.parseInt(parts[1]);
                            numberOfElements--;
                            break;
                        case "block_definitions":
                            blockDef = parts[1];
                            break;
                        case "blocks_start_x":
                            x = Integer.parseInt(parts[1]);
                            break;
                        case "blocks_start_y":
                            y = Integer.parseInt(parts[1]);
                            break;
                        case "row_height":
                            rowHeight = Integer.parseInt(parts[1]);
                            break;
                        case "num_blocks":
                            numberOfBlocks = Integer.parseInt(parts[1]);
                            numberOfElements--;
                        default:
                            break;
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levels;
    }
    /**
     * create the block list.
     * @param locationBlocks the location of the blocks
     * @param x the x value that the blocks starts
     * @param y the y value that the blocks starts
     * @param rowHeight the row height
     * @param path the file of the blocks
     * @param reader the reader
     * @return the block list
     */
    public List<Block> readingBlcoks(Map<Integer, String> locationBlocks,
                                     int x, int y, int rowHeight, String path, java.io.Reader reader) {
        List<Block> blockList = new ArrayList<>();
        int xpos = x, ypos = y;
        try {
            reader = new BufferedReader(new InputStreamReader(ClassLoader
                    .getSystemClassLoader().getResourceAsStream(path)));
            BlocksFromSymbolsFactory factory = BlocksDefinitionReader
                    .fromReader(reader);
            for (Integer key : locationBlocks.keySet()) {
                String data = locationBlocks.get(key);
                for (int i = 0; i < data.length(); i++) {
                    String symbol = String.valueOf(data.charAt(i));
                    if (factory.isSpaceSymbol(symbol)) {
                        xpos += factory.getSpacerWidth(symbol);
                    } else if (factory.isBlockSymbol(symbol)) {
                        Block block = factory.getBlock(symbol, xpos, ypos);
                        blockList.add(block);
                        xpos += block.getCollisionRectangle().getWidth();
                    }
                }
                ypos += rowHeight;
                xpos = x;
            }
        } catch (Exception e) {
            System.out.println("fail loading file");
        }
        return blockList;
    }
}

