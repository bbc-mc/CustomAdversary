package bbc_mc.CustomAdversary;

import bbc_mc.CustomAdversary.common.CreativeTabCustomAdversary;
import bbc_mc.CustomAdversary.core.ConfigsCore;
import bbc_mc.CustomAdversary.core.EventsCore;
import bbc_mc.CustomAdversary.core.ItemsCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 @author bbc_mc
 @date 2016/08/03
 */

@Mod(
        modid = CustomAdversary.MODID,
        name = CustomAdversary.MODNAME,
        dependencies = CustomAdversary.MOD_DEPENDENCIES,
        useMetadata = true
)
public class CustomAdversary {
    public static final String MODID = "customadversary";
    public static final String MODNAME = "CustomAdversary";
    public static final String MOD_DEPENDENCIES = "";

    @Mod.Instance(CustomAdversary.MODID)
    public static CustomAdversary instance;

    public CustomAdversary(){
        instance = this;
    }

    // Creative tab
    public static final CreativeTabs tabsCustomAdversary = new CreativeTabCustomAdversary(CustomAdversary .MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //
        // 設定読込
        //
        ConfigsCore.preInit(event, this);

        //
        // Item登録
        //
        ItemsCore.preInit(event, this);

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Event登録, GUI handler登録
        EventsCore.init(event, this);

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        ConfigsCore.postInit(event, this);
    }
}
