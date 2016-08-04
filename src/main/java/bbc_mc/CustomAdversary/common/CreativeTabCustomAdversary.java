package bbc_mc.CustomAdversary.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 @author bbc_mc
 @date 2016/08/04
 */
public class CreativeTabCustomAdversary extends CreativeTabs {
    public CreativeTabCustomAdversary(String modid) {
        super(modid);
    }

    @Override
    public Item getTabIconItem() {
        return Items.SKULL;
    }
}
