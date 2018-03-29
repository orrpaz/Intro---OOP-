package io;

import game.BlockFactory;
import game.BlockCreator;
import game.Filling;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Or on 13/06/2017.
 */
public class BlocksDefinitionReader {
    /**
     * spiliting the path.
     * @param s the path
     * @return a path
     */
    public static String splitStringToPath(String s) {
        String[] param = s.split("\\(");
        String path = param[1].split("\\)")[0];
        return path;

    }
    /**
     * reading the block definitions.
     * @param reader the file reader
     * @return block factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        int defaultHeight = 0, defaultWidth = 0, defaultHit = 0;
        int spacerWidth = 0;
        Color stroke = null;
        Map<Integer, Filling> filling = new HashMap<Integer, Filling>();
        Map<String, BlockCreator> symbolAndblocks = new HashMap<String, BlockCreator>();
        Map<String, Integer> spacers = new HashMap<String, Integer>();
        String symbol = null, spacerSymbol = null;
        try {
            do {
                line = ((BufferedReader) bufferedReader).readLine();
                // while ((line = ((BufferedReader) reader).readLine()) != null) {
                String[] param = line.split(" ");
                line = line.trim();
                if (!line.equals("") && !line.startsWith("#")) {
                    if (param[0].equals("default")) {
                        for (int i = 1; i < param.length; i++) {
                            String[] subString = param[i].split(":");
                            if (subString[0].equals("height")) {
                                defaultHeight = Integer.parseInt(subString[1]);
                            } else if (subString[0].equals("width")) {
                                defaultWidth = Integer.parseInt(subString[1]);
                            } else if (subString[0].equals("hit_points")) {
                                defaultHit = Integer.parseInt(subString[1]);
                            } else if (subString[0].equals("stroke")) {
                                stroke = Filling.colorFromString(subString[1]);
                            } else if (subString[0].equals("fill")) {
                                if (subString[1].contains("image")) {
                                    String path = BlocksDefinitionReader.splitStringToPath(subString[1]);
                                    filling.put(0, new Filling(null, Filling.imageFromString(path)));
                                } else {
                                    filling.put(0, new Filling(Filling.colorFromString(subString[1]), null));
                                }
                            } else if (subString[0].contains("fill-")) {

                                if (subString[1].contains("image")) {
                                    String path = BlocksDefinitionReader.splitStringToPath(subString[1]);
                                    int num = Integer.parseInt(subString[0].split("\\-")[1]);
                                    filling.put(num, new Filling(null, Filling.imageFromString(path)));
                                } else {
                                    int num = Integer.parseInt(subString[0].split("\\-")[1]);
                                    filling.put(num, new Filling(Filling.colorFromString(subString[1]), null));
                                }
                            }
                        }
                    } else if (param[0].equals("bdef")) {
                        Map<Integer, Filling> privateFilling = new HashMap<Integer, Filling>();
                        int height = defaultHeight;
                        int width = defaultWidth;
                        int numberOfHit = defaultHit;
                        Color privateStroke = stroke;
                        java.util.List<Integer> keys = new ArrayList<Integer>(filling.keySet());
                        for (int i = 0; i < filling.size(); i++) {
                            privateFilling.put(keys.get(i), filling.get(i));
                        }
                        for (int i = 1; i < param.length; i++) {
                            String[] subString = param[i].split(":");
                            if (subString[0].equals("symbol")) {
                                symbol = subString[1];
                            } else if (subString[0].equals("height")) {
                                height = Integer.parseInt(subString[1]);
                            } else if (subString[0].equals("width")) {
                                width = Integer.parseInt(subString[1]);
                            } else if (subString[0].equals("hit_points")) {
                                numberOfHit = Integer.parseInt(subString[1]);
                            } else if (subString[0].equals("stroke")) {
                                privateStroke = Filling.colorFromString(subString[1]);
                            } else if (subString[0].equals("fill")) {
                                if (subString[1].contains("image")) { //its an image
                                    String path = BlocksDefinitionReader.splitStringToPath(subString[1]);
                                    privateFilling.put(0, new Filling(null, Filling.imageFromString(path)));
                                } else { //its a color!
                                    privateFilling.put(0,
                                            new Filling(Filling.colorFromString(subString[1]), null));
                                }
                            } else if (subString[0].contains("fill-")) {
                                if (subString[1].contains("image")) {
                                    String path = BlocksDefinitionReader
                                            .splitStringToPath(subString[1]);
                                    int num = Integer.parseInt(subString[0].split("\\-")[1]);
                                    privateFilling.put(num, new Filling(null, Filling.imageFromString(path)));
                                } else {
                                    int num = Integer.parseInt(subString[0].split("\\-")[1]);
                                    privateFilling.put(num,
                                            new Filling(Filling.colorFromString(subString[1]), null));
                                }
                            }

                        }
                        // invalid parameters.
                        if (height <= 0 || width <= 0
                                || numberOfHit <= 0
                                || privateFilling.isEmpty() || symbol == null) {
                            System.out.print("Not enough parameters.");
                            System.exit(1);
                        } else {
                            symbolAndblocks.put(symbol, new BlockFactory(
                                    height, width, numberOfHit,
                                    privateStroke, privateFilling));
                        }
                        // start with 'sdef'.
                    } else if (param[0].equals("sdef")) {
                        for (int i = 1; i < param.length; i++) {
                            String[] subString = param[i].split(":");
                            if (subString[0].equals("symbol")) {
                                spacerSymbol = subString[1];
                            } else if (subString[0].equals("width")) {
                                spacerWidth = Integer.parseInt(subString[1]);
                            }
                        }
                        // add to spacers map.
                        spacers.put(spacerSymbol, spacerWidth);
                    }
                }
            } while (line != null);
        } catch (IOException e) {
            System.out.println("Something went wrong while reading the file");
        } finally {
            if (bufferedReader != null) {
                return new BlocksFromSymbolsFactory(symbolAndblocks, spacers);
            }
        }
        return new BlocksFromSymbolsFactory(symbolAndblocks, spacers);
    }
}

