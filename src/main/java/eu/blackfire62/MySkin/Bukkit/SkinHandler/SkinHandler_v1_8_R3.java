/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.properties.Property
 *  com.mojang.authlib.properties.PropertyMap
 *  net.minecraft.server.v1_8_R3.Container
 *  net.minecraft.server.v1_8_R3.DataWatcher
 *  net.minecraft.server.v1_8_R3.DedicatedPlayerList
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityHuman
 *  net.minecraft.server.v1_8_R3.EntityPlayer
 *  net.minecraft.server.v1_8_R3.EnumDifficulty
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  net.minecraft.server.v1_8_R3.MathHelper
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata
 *  net.minecraft.server.v1_8_R3.PacketPlayOutHeldItemSlot
 *  net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn
 *  net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo
 *  net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo$EnumPlayerInfoAction
 *  net.minecraft.server.v1_8_R3.PacketPlayOutPosition
 *  net.minecraft.server.v1_8_R3.PacketPlayOutRespawn
 *  net.minecraft.server.v1_8_R3.PlayerConnection
 *  net.minecraft.server.v1_8_R3.PlayerInteractManager
 *  net.minecraft.server.v1_8_R3.PlayerInventory
 *  net.minecraft.server.v1_8_R3.World
 *  net.minecraft.server.v1_8_R3.WorldData
 *  net.minecraft.server.v1_8_R3.WorldProvider
 *  net.minecraft.server.v1_8_R3.WorldServer
 *  net.minecraft.server.v1_8_R3.WorldSettings
 *  net.minecraft.server.v1_8_R3.WorldSettings$EnumGamemode
 *  net.minecraft.server.v1_8_R3.WorldType
 *  org.bukkit.Bukkit
 *  org.bukkit.craftbukkit.v1_8_R3.CraftServer
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitTask
 */
package eu.blackfire62.MySkin.Bukkit.SkinHandler;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import eu.blackfire62.MySkin.Shared.SkinHandler;
import eu.blackfire62.MySkin.Shared.SkinProperty;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.server.v1_8_R3.Container;
import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.DedicatedPlayerList;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EnumDifficulty;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutHeldItemSlot;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutRespawn;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.PlayerInventory;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldData;
import net.minecraft.server.v1_8_R3.WorldProvider;
import net.minecraft.server.v1_8_R3.WorldServer;
import net.minecraft.server.v1_8_R3.WorldSettings;
import net.minecraft.server.v1_8_R3.WorldType;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import us.rfsmassacre.Werewolf.WerewolfPlugin;

public class SkinHandler_v1_8_R3
implements SkinHandler {
    private WerewolfPlugin myskin;

    public SkinHandler_v1_8_R3(WerewolfPlugin myskin) {
        this.myskin = myskin;
    }

    @Override
    public SkinProperty getSkinProperty(Object player) {
        PropertyMap props = ((CraftPlayer)player).getHandle().getProfile().getProperties();
        Iterator propit = props.get("textures").iterator();
        if (!propit.hasNext()) {
            return null;
        }
        Property prop = (Property)propit.next();
        return new SkinProperty(prop.getName(), prop.getValue(), prop.getSignature());
    }

    @Override
    public void setSkinProperty(Object player, SkinProperty property) {
        PropertyMap props = ((CraftPlayer)player).getHandle().getProfile().getProperties();
        props.get(property.name).clear();
        props.put(property.name, new Property(property.name, property.value, property.signature));
    }

    @Override
    public void update(Object player) {
        EntityPlayer ep = ((CraftPlayer)player).getHandle();
        PacketPlayOutEntityDestroy destroyEntity = new PacketPlayOutEntityDestroy(new int[]{ep.getId()});
        PacketPlayOutPlayerInfo removePlayer = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[]{ep});
        PacketPlayOutPlayerInfo addPlayer = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[]{ep});
        PacketPlayOutNamedEntitySpawn spawnEntity = new PacketPlayOutNamedEntitySpawn((EntityHuman)ep);
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(ep.getId(), ep.getDataWatcher(), true);
        PacketPlayOutHeldItemSlot helditem = new PacketPlayOutHeldItemSlot(ep.inventory.itemInHandIndex);
        WorldServer worldserver = (WorldServer)ep.getWorld();
        PacketPlayOutRespawn respawn = new PacketPlayOutRespawn(worldserver.worldProvider.getDimension(), worldserver.getDifficulty(), worldserver.getWorldData().getType(), ep.playerInteractManager.getGameMode());
        PacketPlayOutPosition position = new PacketPlayOutPosition(ep.locX, ep.locY, ep.locZ, ep.yaw, ep.pitch, new HashSet());
        PacketPlayOutEntityHeadRotation headrotation = new PacketPlayOutEntityHeadRotation((Entity)ep, (byte)MathHelper.d((float)(ep.getHeadRotation() * 256.0f / 360.0f)));
        DedicatedPlayerList playerList = ((CraftServer)Bukkit.getServer()).getHandle();
        Bukkit.getScheduler().runTask((Plugin)this.myskin, () -> {
            for (int i = 0; i < playerList.players.size(); ++i) {
                EntityPlayer ep1 = (EntityPlayer)playerList.players.get(i);
                if (!ep1.getBukkitEntity().canSee((Player)ep.getBukkitEntity())) continue;
                PlayerConnection con = ep1.playerConnection;
                con.sendPacket((Packet)removePlayer);
                con.sendPacket((Packet)addPlayer);
                if (ep1.getId() != ep.getId()) {
                    con.sendPacket((Packet)destroyEntity);
                    con.sendPacket((Packet)spawnEntity);
                }
                con.sendPacket((Packet)headrotation);
                for (int j = 0; j < 5; ++j) {
                    ItemStack itemstack = ep.getEquipment(j);
                    if (itemstack != null && itemstack.count > 0) continue;
                    con.sendPacket((Packet)new PacketPlayOutEntityEquipment(ep.getId(), j, itemstack));
                }
            }
            PlayerConnection con = ep.playerConnection;
            con.sendPacket((Packet)metadata);
            con.sendPacket((Packet)respawn);
            con.sendPacket((Packet)position);
            con.sendPacket((Packet)helditem);
            ep.updateAbilities();
            ep.triggerHealthUpdate();
            ep.updateInventory(ep.activeContainer);
            ep.updateInventory(ep.defaultContainer);
        });
    }
}

