package com.automaticclean.entity;

import com.automaticclean.Definition;
import com.automaticclean.utils.WhitelistAndBlacklist;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;

public class CustomMonsterEntity {
    private final ResourceLocation registryName;

    public CustomMonsterEntity(MonsterEntity entity) {
        this.registryName = EntityType.getKey(entity.getType());
    }

    public boolean filtrate() {
        if (Definition.config.getMonsterClean().isWhitelistMode()) {
            for (String s : Definition.config.getMonsterClean().getMonsterEntitiesWhitelist()) {
                return WhitelistAndBlacklist.nameMatch(s, this.registryName);
            }
            return false;
        } else {
            for (String s : Definition.config.getMonsterClean().getMonsterEntitiesBlacklist()) {
                return !WhitelistAndBlacklist.nameMatch(s, this.registryName);
            }
            return true;
        }
    }
}
