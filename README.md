# About this mod / 本modについて

## Caution / 警告
- 適当実装の自己環境用ツールです。ご注意ください。

## Abstract / 概要
- Add function to set mobs relationship.
  - If you set "Wolf attack to Villager", Wolf will attack Villager as soon as they find.

- mob の攻撃関係を設定できます
  - 例えば、"オオカミは村人を攻撃する" とした場合、オオカミは見つけ次第村人を襲います

## How to set up / 使用方法

0. make sure Forge is alread installed. / Forge が導入済みであることを確認してください
1. put this mod "CustomAdversary.jar" into mods folder / mods フォルダに本mod の jar ファイルを入れてください
2. run Minecraft once. / 一度マインクラフトを起動してください
3. change setting by mods/CustomAdversary/config.json. / mods フォルダ内に CustomAdversary フォルダが生成されています。その中の config.json で、mob 同士の関係の設定を変更できます。

## config.json sample

-- from here --
'''
[
  {
    "attacker": "Zombie",
    "enemy": "Slime",
    "priority": -1
  },
  {
    "attacker": "Skeleton",
    "enemy": "Bat",
    "priority": -1
  },
  {
    "attacker": "EnderDragon",
    "enemy": "Slime",
    "priority": -1,
    "chance": 10,
    "checkSight": true,
    "onlyNearby": true
  },
  {
    "attacker": "VillagerGolem",
    "enemy": "Villager",
    "priority": -1,
    "chance": 10,
    "checkSight": true,
    "onlyNearby": false
  }
]
'''
-- file end here --