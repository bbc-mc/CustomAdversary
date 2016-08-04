package bbc_mc.CustomAdversary.core;

import bbc_mc.CustomAdversary.CustomAdversary;
import bbc_mc.CustomAdversary.item.ItemDebugTool;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 @author bbc_mc
 @date 2016/08/03 */
public class ItemsCore {
    public static Item itemDebugTool;

    public static void preInit(FMLPreInitializationEvent event, Object mod){
        itemDebugTool = new ItemDebugTool();
        registerItem(((CustomAdversary)mod).MODID, itemDebugTool, event, itemDebugTool.getCreativeTab(), 0);
    }

    public static void init(FMLInitializationEvent event, Object mod) {
    }

    //
    // private
    //

    /**
     Itemの登録処理

     resourcePath として unlocalizedName を利用するが、. 以降のみを使用する
     foray.npcs.itemName -> itemName

     @return
     */
    private static Item registerItem(String modid, Item targetItem, FMLPreInitializationEvent event, CreativeTabs tabs, int meta){
        String resourceDomainIn = modid;
        String itemName = targetItem.getUnlocalizedName().replace("item.","");
        int length = itemName.split(":").length;
        String resourcePathIn;
        if(length > 0) {
            resourcePathIn = itemName.split(":")[length-1];
        } else {
            resourcePathIn = itemName;
        }

        GameRegistry.register(targetItem, new ResourceLocation(resourceDomainIn, resourcePathIn));

        if(event.getSide().isClient()){
            ModelLoader.setCustomModelResourceLocation(targetItem, meta, new ModelResourceLocation(targetItem.getRegistryName(), "inventory"));
        }
        return targetItem;
    }
}
