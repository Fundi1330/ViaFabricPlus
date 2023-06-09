/*
 * This file is part of ViaFabricPlus - https://github.com/FlorianMichael/ViaFabricPlus
 * Copyright (C) 2021-2023 FlorianMichael/EnZaXD and contributors
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
package de.florianmichael.viafabricplus.definition.v1_18_2;

import de.florianmichael.viafabricplus.ViaFabricPlus;
import de.florianmichael.viafabricplus.protocolhack.ProtocolHack;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.raphimc.vialoader.util.VersionEnum;

public class ClientPlayerInteractionManager1_18_2 {
    private final static Object2ObjectLinkedOpenHashMap<Pair<BlockPos, PlayerActionC2SPacket.Action>, PositionAndRotation> unAckedActions = new Object2ObjectLinkedOpenHashMap<>();

    public final static String ACK_TRANSFORMER_IDENTIFIER = "viafabricplus:acknowledge_player_digging";

    public static void trackBlockAction(final PlayerActionC2SPacket.Action action, final BlockPos blockPos) {
        final var player = MinecraftClient.getInstance().player;
        if (player == null) return;

        var rotation = new Vec2f(player.getYaw(), player.getPitch());
        if (ProtocolHack.getTargetVersion().isNewerThan(VersionEnum.r1_16_2)) {
            rotation = null;
        }
        unAckedActions.put(new Pair<>(blockPos, action), new PositionAndRotation(player.getPos().x, player.getPos().y, player.getPos().z, rotation));
    }

    public static void handleBlockBreakAck(final ClientWorld world, final BlockPos blockPos, final BlockState blockState, final PlayerActionC2SPacket.Action action, final boolean allGood) {
        final var player = MinecraftClient.getInstance().player;
        if (player == null) return;

        final var next = unAckedActions.remove(new Pair<>(blockPos, action));
        final var blockStateFromPos = world.getBlockState(blockPos);

        if ((next == null || !allGood || action != PlayerActionC2SPacket.Action.START_DESTROY_BLOCK && blockStateFromPos != blockState) && blockStateFromPos != blockState) {
            world.setBlockState(blockPos, blockState);
            if (next != null && world == player.getWorld() && player.collidesWithStateAtPos(blockPos, blockState)) {
                if (next.rotation != null) {
                    player.updatePositionAndAngles(next.x, next.y, next.z, next.rotation.x, next.rotation.y);
                } else {
                    player.updatePosition(next.x, next.y, next.z);
                }
            }
        }

        while (unAckedActions.size() >= 50) {
            ViaFabricPlus.LOGGER.error("Too many unacked block actions, dropping {}", unAckedActions.firstKey());
            unAckedActions.removeFirst();
        }
    }

    public record PositionAndRotation(double x, double y, double z, Vec2f rotation) {
    }
}