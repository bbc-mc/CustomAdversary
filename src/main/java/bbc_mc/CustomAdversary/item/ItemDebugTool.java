package bbc_mc.CustomAdversary.item;

import bbc_mc.CustomAdversary.CustomAdversary;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;

/**
 @author bbc_mc
 @date 2016/08/04 */
public class ItemDebugTool extends Item {
    public ItemDebugTool(){
        super();
        this.setMaxStackSize(1);
        this.setCreativeTab(CustomAdversary.tabsCustomAdversary);
        this.setUnlocalizedName("customadversary_debugtool");
    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target instanceof EntityLiving) {
            String entityClassName = EntityList.getEntityString(target);
            if(playerIn.worldObj.isRemote) {
                playerIn.addChatMessage(new TextComponentString(entityClassName));
            }
            return true;
        } else {
            return super.itemInteractionForEntity(stack, playerIn, target, hand);
        }
    }
}
