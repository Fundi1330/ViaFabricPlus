/*
 * This file is part of ViaFabricPlus - https://github.com/FlorianMichael/ViaFabricPlus
 * Copyright (C) 2021-2023 FlorianMichael/EnZaXD
 * Copyright (C) 2023      RK_01/RaphiMC and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.florianmichael.viafabricplus.fixes;

import de.florianmichael.viafabricplus.event.ChangeProtocolVersionCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.raphimc.vialoader.util.VersionEnum;

import java.util.LinkedHashMap;
import java.util.Map;

public class EntityHitboxUpdateListener {

    private static final Map<EntityType<?>, Map<VersionEnum, EntityDimensions>> ENTITY_DIMENSIONS = linkedHashMap(
            EntityType.WITHER, linkedHashMap(
                    VersionEnum.r1_7_6tor1_7_10, EntityDimensions.changing(0.9F, 4.0F),
                    VersionEnum.r1_8, EntityType.WITHER.getDimensions()
            ),
            EntityType.SILVERFISH, linkedHashMap(
                    VersionEnum.r1_7_6tor1_7_10, EntityDimensions.changing(0.3F, 0.7F),
                    VersionEnum.r1_8, EntityType.SILVERFISH.getDimensions()
            ),
            EntityType.SNOW_GOLEM, linkedHashMap(
                    VersionEnum.r1_7_6tor1_7_10, EntityDimensions.changing(0.4F, 1.8F),
                    VersionEnum.r1_8, EntityType.SNOW_GOLEM.getDimensions()
            ),
            EntityType.ZOMBIE, linkedHashMap(
                    VersionEnum.r1_7_6tor1_7_10, EntityDimensions.changing(0.6F, 1.8F),
                    VersionEnum.r1_8, EntityDimensions.fixed(EntityType.ZOMBIE.getDimensions().width, EntityType.ZOMBIE.getDimensions().height),
                    VersionEnum.r1_9, EntityType.ZOMBIE.getDimensions()
            ),
            EntityType.CHICKEN, linkedHashMap(
                    VersionEnum.b1_7tob1_7_3, EntityDimensions.changing(0.3F, 0.4F),
                    VersionEnum.r1_7_6tor1_7_10, EntityDimensions.changing(0.3F, 0.7F),
                    VersionEnum.r1_8, EntityType.CHICKEN.getDimensions()
            ),
            EntityType.SHEEP, linkedHashMap(
                    VersionEnum.c0_28toc0_30, EntityDimensions.changing(1.4F, 1.72F),
                    VersionEnum.a1_0_15, EntityType.SHEEP.getDimensions()
            ),
            EntityType.OCELOT, linkedHashMap(
                    VersionEnum.r1_7_6tor1_7_10, EntityDimensions.changing(0.6F, 0.8F),
                    VersionEnum.r1_8, EntityType.OCELOT.getDimensions()
            ),
            EntityType.BOAT, linkedHashMap(
                    VersionEnum.r1_8, EntityDimensions.changing(1.5F, 0.6F),
                    VersionEnum.r1_9, EntityType.BOAT.getDimensions()
            ),
            EntityType.CREEPER, linkedHashMap(
                    VersionEnum.r1_8, EntityDimensions.changing(0.6F, 1.8F),
                    VersionEnum.r1_9, EntityType.CREEPER.getDimensions()
            ),
            EntityType.IRON_GOLEM, linkedHashMap(
                    VersionEnum.r1_8, EntityDimensions.changing(1.4F, 2.9F),
                    VersionEnum.r1_9, EntityType.IRON_GOLEM.getDimensions()
            ),
            EntityType.SKELETON, linkedHashMap(
                    VersionEnum.r1_7_6tor1_7_10, EntityDimensions.changing(0.6F, 1.8F),
                    VersionEnum.r1_8, EntityDimensions.changing(0.6F, 1.95F),
                    VersionEnum.r1_9, EntityType.SKELETON.getDimensions()
            ),
            EntityType.WITHER_SKELETON, linkedHashMap(
                    VersionEnum.r1_4_6tor1_4_7, EntityDimensions.changing(0.72F, 2.16F),
                    VersionEnum.r1_7_6tor1_7_10, EntityDimensions.changing(0.72F, 2.34F),
                    VersionEnum.r1_8, EntityDimensions.changing(0.72F, 2.535F),
                    VersionEnum.r1_9, EntityType.WITHER_SKELETON.getDimensions()
            ),
            EntityType.COW, linkedHashMap(
                    VersionEnum.r1_8, EntityDimensions.changing(0.9F, 1.3F),
                    VersionEnum.r1_9, EntityType.COW.getDimensions()
            ),
            EntityType.HORSE, linkedHashMap(
                    VersionEnum.r1_8, EntityDimensions.changing(1.4F, 1.6F),
                    VersionEnum.r1_9, EntityType.HORSE.getDimensions()
            ),
            EntityType.MOOSHROOM, linkedHashMap(
                    VersionEnum.r1_8, EntityDimensions.changing(0.9F, 1.3F),
                    VersionEnum.r1_9, EntityType.MOOSHROOM.getDimensions()
            ),
            EntityType.RABBIT, linkedHashMap(
                    VersionEnum.r1_8, EntityDimensions.changing(0.6F, 0.7F),
                    VersionEnum.r1_9, EntityType.RABBIT.getDimensions()
            ),
            EntityType.SQUID, linkedHashMap(
                    VersionEnum.r1_8, EntityDimensions.changing(0.95F, 0.95F),
                    VersionEnum.r1_9, EntityType.SQUID.getDimensions()
            ),
            EntityType.VILLAGER, linkedHashMap(
                    VersionEnum.r1_8, EntityDimensions.changing(0.6F, 1.8F),
                    VersionEnum.r1_9, EntityType.VILLAGER.getDimensions()
            ),
            EntityType.WOLF, linkedHashMap(
                    VersionEnum.r1_1, EntityDimensions.changing(0.8F, 0.8F),
                    VersionEnum.r1_8, EntityDimensions.changing(0.6F, 0.8F),
                    VersionEnum.r1_9, EntityType.WOLF.getDimensions()
            ),
            EntityType.DRAGON_FIREBALL, linkedHashMap(
                    VersionEnum.r1_10, EntityDimensions.changing(0.3125F, 0.3125F),
                    VersionEnum.r1_11, EntityType.DRAGON_FIREBALL.getDimensions()
            ),
            EntityType.LEASH_KNOT, linkedHashMap(
                    VersionEnum.r1_16_4tor1_16_5, EntityDimensions.changing(0.5F, 0.5F),
                    VersionEnum.r1_17, EntityType.LEASH_KNOT.getDimensions()
            ),
            EntityType.SLIME, linkedHashMap(
                    VersionEnum.r1_13_2, EntityDimensions.changing(2F, 2F),
                    VersionEnum.r1_14, EntityType.SLIME.getDimensions()
            ),
            EntityType.MAGMA_CUBE, linkedHashMap(
                    VersionEnum.r1_13_2, EntityDimensions.changing(2F, 2F),
                    VersionEnum.r1_14, EntityType.MAGMA_CUBE.getDimensions()
            ),
            EntityType.ARROW, linkedHashMap(
                    VersionEnum.c0_28toc0_30, EntityDimensions.changing(0.3F, 0.5F),
                    VersionEnum.a1_0_15, EntityType.ARROW.getDimensions()
            )
    );

    public static void init() {
        ChangeProtocolVersionCallback.EVENT.register((oldVersion, newVersion) -> MinecraftClient.getInstance().execute(() -> ENTITY_DIMENSIONS.forEach((entityType, dimensionMap) -> {
            for (Map.Entry<VersionEnum, EntityDimensions> entry : dimensionMap.entrySet()) {
                final VersionEnum version = entry.getKey();
                final EntityDimensions dimensions = entry.getValue();
                if (oldVersion.isNewerThan(version) && newVersion.isOlderThanOrEqualTo(version)) {
                    entityType.dimensions = dimensions;
                    break;
                }
                if (newVersion.isNewerThanOrEqualTo(version) && oldVersion.isOlderThanOrEqualTo(version)) {
                    entityType.dimensions = dimensions;
                }
            }
        })));
    }

    private static <K, V> Map<K, V> linkedHashMap(final Object... objects) {
        if (objects.length % 2 != 0) throw new IllegalArgumentException("Uneven object count");

        final Map<K, V> map = new LinkedHashMap<>();
        for (int i = 0; i < objects.length; i += 2) map.put((K) objects[i], (V) objects[i + 1]);
        return map;
    }

}