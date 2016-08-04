package bbc_mc.CustomAdversary.core;

import bbc_mc.CustomAdversary.CustomAdversary;
import bbc_mc.CustomAdversary.event.EventEntityJoinWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 @author bbc_mc
 @date 2016/08/03 */
public class EventsCore {
    public static void init(FMLInitializationEvent event, CustomAdversary customAdversary) {
        MinecraftForge.EVENT_BUS.register(new EventEntityJoinWorld());
    }
}
