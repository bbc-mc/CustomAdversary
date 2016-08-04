1. 本modについて

1-1. 概要
  攻撃者と、攻撃対象を追加登録する

1-2. 内部動作
  targetAITask に、攻撃対象を設定する AI を追加する。

1-3. Configファイル書式
  サンプルConfigファイルを参照のこと。
  基本的には、以下の通り。
    attacker: 攻撃者。
    enemy: 攻撃対象。
    priority: AI 実行優先度。

  例) attacker: Zombie, enemy: Skeleton, priority: 2
      Zombie は、villager や golem (priority:3) よりは、設定した対象 Skeleton (priority:2) を優先して襲う。
      Player を priority:2 で襲うため、player と Skeleton だと、近いもので早い者勝ち。

  例) attacker: Zombie, enemy: Skeleton, priority: 3
      Zombie は、Player (priority:2) を優先して襲う。
      Skeleton(3), villager(3), golem(3) は、近いもので早い者勝ち。

1-4. 注意点
  Configファイルで指定できる priority で動作するので、対象Entityの通常のtarget設定AIとの関係性には注意が必要。
  適切な設定には、各Entity の AI の priority を知る必要がある。

1-5. 条件

  attacker は、"EntityLiving" を継承している必要がある
  enemy は、"EntityLivingBase" を継承している必要がある


2. 対象Entity名の調べ方

  Creative Mode に、"Debug tool" というアイテムを追加している。
  このアイテムを持って調べたい Entity を右クリックすると、本 mod で使用している Entity 名が表示される。


3. バニラで登録されている名前は、以下の通り。mc 1.9.4
  @seealso EntityList.class
  
  1-5.条件から、指定しても使用できないEntity名があるので、注意が必要。

  EntityItem.class, "Item"
  EntityXPOrb.class, "XPOrb"
  EntityAreaEffectCloud.class, "AreaEffectCloud"
  EntityEgg.class, "ThrownEgg"
  EntityLeashKnot.class, "LeashKnot"
  EntityPainting.class, "Painting"
  EntityTippedArrow.class, "Arrow"
  EntitySnowball.class, "Snowball"
  EntityLargeFireball.class, "Fireball"
  EntitySmallFireball.class, "SmallFireball"
  EntityEnderPearl.class, "ThrownEnderpearl"
  EntityEnderEye.class, "EyeOfEnderSignal"
  EntityPotion.class, "ThrownPotion"
  EntityExpBottle.class, "ThrownExpBottle"
  EntityItemFrame.class, "ItemFrame"
  EntityWitherSkull.class, "WitherSkull"
  EntityTNTPrimed.class, "PrimedTnt"
  EntityFallingBlock.class, "FallingSand"
  EntityFireworkRocket.class, "FireworksRocketEntity"
  EntitySpectralArrow.class, "SpectralArrow"
  EntityShulkerBullet.class, "ShulkerBullet"
  EntityDragonFireball.class, "DragonFireball"
  EntityArmorStand.class, "ArmorStand"
  EntityBoat.class, "Boat"
  EntityMinecartEmpty.class, "MinecartRideable"
  EntityMinecartChest.class, "MinecartChest"
  EntityMinecartFurnace.class, "MinecartFurnace"
  EntityMinecartTNT.class, "MinecartTNT"
  EntityMinecartHopper.class, "MinecartSpawner"
  EntityMinecartMobSpawner.class, "MinecartHopper"
  EntityMinecartCommandBlock.class, "MinecartCommandBlock"
  EntityLiving.class, "Mob"
  EntityMob.class, "Monster"
  EntityCreeper.class, "Creeper"
  EntitySkeleton.class, "Skeleton"
  EntitySpider.class, "Spider"
  EntityGiantZombie.class, "Giant"
  EntityZombie.class, "Zombie"
  EntitySlime.class, "Slime"
  EntityGhast.class, "Ghast"
  EntityPigZombie.class, "PigZombie"
  EntityEnderman.class, "Enderman"
  EntityCaveSpider.class, "CaveSpider"
  EntitySilverfish.class, "Silverfish"
  EntityBlaze.class, "Blaze"
  EntityMagmaCube.class, "LavaSlime"
  EntityDragon.class, "EnderDragon"
  EntityWither.class, "WitherBoss"
  EntityBat.class, "Bat"
  EntityWitch.class, "Witch"
  EntityEndermite.class, "Endermite"
  EntityGuardian.class, "Guardian"
  EntityShulker.class, "Shulker"
  EntityPig.class, "Pig"
  EntitySheep.class, "Sheep"
  EntityCow.class, "Cow"
  EntityChicken.class, "Chicken"
  EntitySquid.class, "Squid"
  EntityWolf.class, "Wolf"
  EntityMooshroom.class, "MushroomCow"
  EntitySnowman.class, "SnowMan"
  EntityOcelot.class, "Ozelot"
  EntityIronGolem.class, "VillagerGolem"
  EntityHorse.class, "EntityHorse"
  EntityRabbit.class, "Rabbit"
  EntityVillager.class, "Villager"
  EntityEnderCrystal.class, "EnderCrystal"

  なお、プレイヤーを enemy として登録する場合は "Player" として登録することで、設定できる

----