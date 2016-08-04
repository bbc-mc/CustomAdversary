# About this mod / 本modについて

- Add function to set mobs relationship.
  - If you set "Wolf attack to Villager", Wolf will attack Villager as soon as they find.

- mob の攻撃関係を設定できます
  - 例えば、"オオカミは村人を攻撃する" とした場合、オオカミは見つけ次第村人を襲います

## How to set up / 使用方法

1. put this mod "CustomAdversary.jar" into mods folder / mods フォルダに本mod の jar ファイルを入れてください
2. make ConfigAdversary folder in mods folder / mods フォルダ内に ConfigAdversary フォルダを作成してください
3. put config.json into ConfigAdversary folder / ConfigAdversary フォルダ内に、config.json ファイルを配置してください
  config.json is included in jar file / config.json は jar ファイル内に同梱されています

## config.json sample

-- from here --
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
-- file end here --