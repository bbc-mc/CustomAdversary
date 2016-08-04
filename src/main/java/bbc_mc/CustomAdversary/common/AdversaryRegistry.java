package bbc_mc.CustomAdversary.common;

import bbc_mc.CustomAdversary.core.ConfigsCore;
import bbc_mc.CustomAdversary.entity.ai.EntityAINearestAttackableTargetForEntityLiving;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraftforge.fml.common.FMLLog;

import java.util.List;
import java.util.Map;

/**
 敵対関係を登録するためのクラス

 @author bbc_mc
 @date 2016/08/03
 */
public class AdversaryRegistry {

    /**
     攻撃者と、攻撃対象を管理するマップ
     */
    private static Map< Class<? extends Entity>, List<AdversaryRelation>> adversaryMap = Maps.newHashMap();

    /**
     対象 entity が、攻撃者かどうかを確認する関数

     事前登録されたリストのうち、attacker に entity が登録されている場合に true
     登録がない場合に false を返す
     */
    public static boolean isAttackerEntity(EntityLivingBase entity) {
        for(Class<? extends Entity> keyEntity : adversaryMap.keySet()){
            if(entity.getClass() == keyEntity){
                return true;
            }
        }
        return false;
    }

    /**
     攻撃者と、攻撃対象を登録する
     */
    public static void registerAdversary(AdversaryRelation relation){

        // Config ファイル構文ミス等で null な relation が渡される事があるため
        if(relation == null){
            return;
        }

        String attackerClassName = relation.attacker;
        String enemyClassName = relation.enemy;
        int priority = relation.priority;
        boolean checkSight = relation.checkSight;
        boolean onlyNearby = relation.onlyNearby;
        try {
            // Stringから、クラスを得る
            Class attackerClass = EntityList.NAME_TO_CLASS.get(attackerClassName);
            Class enemyClass = EntityList.NAME_TO_CLASS.get(enemyClassName);

            // attacker が EntityLiving を継承しているか確認する
            // 本modは、attacker の targetAITasks に介入するため、attacker が EntityLiving を継承している事が必要になる
            if( ! EntityLiving.class.isAssignableFrom(attackerClass)){
                if(ConfigsCore.isDebugMode) {
                    FMLLog.info("  ERROR: [" + attackerClass.getSimpleName() + "] is not inherited EntityLiving. Ignore registering as attacker.");
                }
                return;
            }

            // バニラの attacker は攻撃対象として EntityLivingBase 継承mob しか取れないため、チェックを行う
            if( ! EntityLivingBase.class.isAssignableFrom(enemyClass)){
                if(ConfigsCore.isDebugMode) {
                    FMLLog.info("  ERRPR: [" + enemyClass.getSimpleName() + "] is not inherited EntityLivingBase. Ignore registering as enemy.");
                }
                return;
            }

            // attacker のもつ、attacked リストを取得
            if(adversaryMap.keySet().contains(attackerClass)){
                List<AdversaryRelation> listAdversaryRelation = adversaryMap.get(attackerClass);
                boolean isRegistered = false;
                for(AdversaryRelation relation2 : listAdversaryRelation){
                    if(relation2.enemy.equals(enemyClassName)){
                        isRegistered = true;
                    }
                }
                // enemy に重複がなければ、登録を行う
                if( ! isRegistered){
                    listAdversaryRelation.add(relation);
                } else {
                    return;
                }
            } else { // attacker が登録されていない場合
                List<AdversaryRelation> listAdversaryRelation = Lists.newArrayList();
                listAdversaryRelation.add(relation);
                adversaryMap.put(attackerClass, listAdversaryRelation);
            }
            if(ConfigsCore.isDebugMode) {
                FMLLog.info("  register adversary mapping: from[" + relation.attacker + "] to[" + relation.enemy + "] pri[" + relation.priority + "] checkSight[" + relation.checkSight + "] onlyNearby[" + relation.onlyNearby + "] [" + adversaryMap.get(attackerClass).size() + "]");
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void registerAdversary(String attackerClassName, String enemyClassName){
        registerAdversary(attackerClassName, enemyClassName, -1, true, false);
    }

    public static void registerAdversary(String attackerClassName, String enemyClassName, int priority, boolean checkSight, boolean onlyNearby){
        AdversaryRelation relation = new AdversaryRelation();
        relation.attacker = attackerClassName;
        relation.enemy = enemyClassName;
        relation.priority = priority;
        relation.checkSight = checkSight;
        relation.onlyNearby = onlyNearby;
        registerAdversary(relation);
    }

    /**
     対象entityへの、攻撃対象設定 AI の注入を行う

     @param attackerEntity
     */
    public static void injectTargetAI(EntityLiving attackerEntity) {
        // 対象 entity のクラスから、攻撃先クラスのリストを取得
        if(!adversaryMap.keySet().contains(attackerEntity.getClass())){
            return;
        }

        for(AdversaryRelation newAdversaryRelation : adversaryMap.get(attackerEntity.getClass()) ){
            Class enemyClass = EntityList.NAME_TO_CLASS.get(newAdversaryRelation.enemy);
            int chance = 10;
            boolean checkSight = newAdversaryRelation.checkSight;
            boolean onlyNearby = newAdversaryRelation.onlyNearby;

            EntityAIBase aiBase = new EntityAINearestAttackableTargetForEntityLiving(attackerEntity, enemyClass, chance, checkSight, onlyNearby, null);
            attackerEntity.targetTasks.addTask(newAdversaryRelation.priority, aiBase);
        }
    }
}
