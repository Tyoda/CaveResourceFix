package net.buddat.wgenerator.caveresourcefix;

import java.io.IOException;
import java.util.Random;

import com.wurmonline.wurmapi.api.WurmAPI;

public class CaveResourceFix {

    public static void main(String[] args) {
        String directory = ".";
        short randSize = 10000;
        int minRange = 40;
        int maxRange = 60;
        if (args.length > 0)
            directory = args[0];
        if (args.length > 1)
            randSize = Short.parseShort(args[1]);
        if (args.length > 2)
            minRange = Integer.parseInt(args[2]);
        if (args.length > 3)
            maxRange = Integer.parseInt(args[3]);

        try {
            Random rand = new Random(System.currentTimeMillis());
            WurmAPI api = WurmAPI.open(directory);
            System.out.println("Map Size: W:" + api.getMapData().getWidth() + ", H:" + api.getMapData().getHeight());

            int count = 0;
            for (int i = 0; i < api.getMapData().getWidth(); i++)
                for (int j = 0; j < api.getMapData().getHeight(); j++) {
                    if (api.getMapData().getCaveResourceCount(i, j) > minRange && api.getMapData().getCaveResourceCount(i, j) < maxRange) {
                        api.getMapData().setCaveResourceCount(i, j, (short) (rand.nextInt(randSize) + minRange));
                        count++;
                    }
                }
            api.getMapData().saveChanges();

            System.out.println(count + " cave tiles successfully updated.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}