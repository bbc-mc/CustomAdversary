package bbc_mc.CustomAdversary.event;

import bbc_mc.CustomAdversary.common.AdversaryRegistry;
import bbc_mc.CustomAdversary.core.ConfigsCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 @author bbc_mc
 @date 2016/08/01 */
public class EventEntityJoinWorld {

    @SubscribeEvent
    public void onEntityJoinWorldEvet(EntityJoinWorldEvent event){
        World world = event.getWorld();
        Entity entity = event.getEntity();

        if(world.isRemote){
            return;
        }

        if(entity instanceof EntityLiving){
            if(AdversaryRegistry.isAttackerEntity((EntityLiving)entity)){
                AdversaryRegistry.injectTargetAI((EntityLiving)entity);
                if(ConfigsCore.isDebugMode) {
                    FMLLog.info("[EVENT] AI injected to new Entity [" + entity.getClass().getSimpleName() + "]");
                }
            }
        }
    }
}
