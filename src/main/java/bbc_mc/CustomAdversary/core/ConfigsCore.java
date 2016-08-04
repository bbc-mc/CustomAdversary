package bbc_mc.CustomAdversary.core;

import bbc_mc.CustomAdversary.CustomAdversary;
import bbc_mc.CustomAdversary.common.AdversaryRegistry;
import bbc_mc.CustomAdversary.common.AdversaryRelation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 @author bbc_mc
 @date 2016/08/03 */
public class ConfigsCore {

    //
    // System
    //
    public static boolean isDebugMode;
    public static String configFileName;
    public static int priorityOfAI;

    public static void preInit(FMLPreInitializationEvent event, CustomAdversary customAdversary) {
        Configuration conf = new Configuration(event.getSuggestedConfigurationFile());
        try{
            // 設定読出し
            conf.load();

            //
            // debug
            //
            isDebugMode = conf.getBoolean("debugMode", "debug", false, "B:isDebugMode");

            //
            // system
            //
            configFileName = conf.getString("configFileName", "system", "config.json", "Name of configfile for setting.");

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (conf.hasChanged()) {
                conf.save();
            }
        }
    }

    public static void postInit(FMLPostInitializationEvent event, CustomAdversary customAdversary) {
        try {
            // config.json を読む
            File saveDir = FMLClientHandler.instance().getSavesDir().getParentFile();
            File file0 = new File(saveDir, "mods");
            File file1 = new File(file0, "ConfigAdversary");
            if(!file1.exists()){
                file1.mkdir();
            }
            File file2 = new File(file1, ConfigsCore.configFileName);
            if(!file2.exists()){
                file2 = makeInitialConfig(file2);
            }

            BufferedReader br = new BufferedReader(new FileReader(file2));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while( (line = br.readLine() ) != null ){
                stringBuilder.append(line);
            }
            registerAdv(stringBuilder.toString());
            br.close();
        } catch (Exception e) {
            if(ConfigsCore.isDebugMode) {
                FMLLog.info(" #### Error on loading config file : [" + ConfigsCore.configFileName + "] ");
            }
            e.printStackTrace();
        }
    }

    private static void registerAdv(String jsonDataString){
        try {
            // gson を使って json としてロードする
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<AdversaryRelation>>() {
            }.getType();
            List<AdversaryRelation> relationList = gson.fromJson(jsonDataString, collectionType);

            for (AdversaryRelation relation : relationList) {
                AdversaryRegistry.registerAdversary(relation);
            }

            // 項目分けして、registerAdversary していく
            // AdversaryRegistry.registerAdversary("Slime", "Skeleton");
            // AdversaryRegistry.registerAdversary("Skeleton", "Skeleton");
            // AdversaryRegistry.registerAdversary("Wolf", "Skeleton");
            // AdversaryRegistry.registerAdversary("Zombie", "Slime");
            // AdversaryRegistry.registerAdversary("VillagerGolem", "Villager");
            // AdversaryRegistry.registerAdversary("VillagerGolem", "VillagerGolem", -1, true, false);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static File makeInitialConfig(File file2){
        try {
            file2.createNewFile();
            FileWriter filewriter = new FileWriter(file2);

            filewriter.write("[\n");
            filewriter.write("    {\n");
            filewriter.write("        \"attacker\": \"Zombie\",\n");
            filewriter.write("        \"enemy\": \"Slime\",\n");
            filewriter.write("        \"priority\": -1\n");
            filewriter.write("    },\n");
            filewriter.write("    {\n");
            filewriter.write("        \"attacker\": \"Skeleton\",\n");
            filewriter.write("        \"enemy\": \"Bat\",\n");
            filewriter.write("        \"priority\": -1\n");
            filewriter.write("    },\n");
            filewriter.write("    {\n");
            filewriter.write("        \"attacker\": \"Wolf\",\n");
            filewriter.write("        \"enemy\": \"Slime\",\n");
            filewriter.write("        \"priority\": -1,\n");
            filewriter.write("        \"checkSight\": true,\n");
            filewriter.write("        \"onlyNearby\": false\n");
            filewriter.write("    }\n");
            filewriter.write("]");

            filewriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return file2;
    }
}
