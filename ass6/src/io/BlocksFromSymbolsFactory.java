package io;
import game.Block;
import game.BlockCreator;
import java.util.Map;


/**
 * Created by Or on 13/06/2017.
 */

public class BlocksFromSymbolsFactory {
    private Map<String, BlockCreator> blockCreators;
    private Map<String, Integer> spacerWidths;

    /**
     * constructor.
     * @param spacerWidths a map of spacer width
     * @param blockCreators a map of block creator
     */
    public BlocksFromSymbolsFactory(Map<String, BlockCreator> blockCreators,
                                    Map<String, Integer> spacerWidths) {
        this.blockCreators = blockCreators;
        this.spacerWidths = spacerWidths;
    }
    /**
     * returns true if 's' is a valid space symbol.
     * @param s the symbol key
     * @return spacerWidths
     */
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }
    /**
     * returns true if 's' is a valid block symbol.
     * @param s the symbol
     * @return if its block or not
     */
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }
    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     * @param s a string
     * @return the space width
     */
    public int getSpacerWidth(String s) {
        return this.spacerWidths.get(s);
    }
    /**
     * Return a block according to the definitions associated.
     * @param s the symbol
     * @param xpos the x val of point
     * @param ypos the y val of point
     * @return a block
     */
    public Block getBlock(String s, int xpos, int ypos) {
         Block block = this.blockCreators.get(s).create(xpos, ypos);
         return block;
    }
}