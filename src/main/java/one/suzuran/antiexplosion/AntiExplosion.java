package one.suzuran.antiexplosion;

import io.papermc.paper.event.player.PlayerBedFailEnterEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiExplosion extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginDescriptionFile VarUtilType = this.getDescription();
        getLogger().info("AntiExplosion V" + VarUtilType.getVersion() + " starting...");
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("AntiExplosion V" + VarUtilType.getVersion() + " started!");
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onExplosionPrimeEvent(final ExplosionPrimeEvent e) {
        e.setCancelled(false);
    }

    // 地形破坏
    @EventHandler
    public void onEntityExplodeEvent(final EntityExplodeEvent e) {
        if (e.getEntity().getType().equals(EntityType.CREEPER)) {
            e.setCancelled(getConfig().getBoolean("CREEPER"));
        }
        // 末地水晶 不破坏方块
        /*if (e.getEntity().getType().equals(EntityType.ENDER_CRYSTAL)) {
            e.setCancelled(getConfig().getBoolean("ENDER_CRYSTAL"));
        }*/
        // 被激活的TNT
        if (e.getEntity().getType().equals(EntityType.PRIMED_TNT)) {
            e.setCancelled(getConfig().getBoolean("TNT"));
        }
        if (e.getEntity().getType().equals(EntityType.WITHER_SKULL)) {
            e.setCancelled(getConfig().getBoolean("WITHER_SKULL"));
        }
        if (e.getEntity().getType().equals(EntityType.WITHER)) {
            e.setCancelled(getConfig().getBoolean("WITHER"));
        }
        if (e.getEntity().getType().equals(EntityType.FIREBALL)) {
            e.setCancelled(getConfig().getBoolean("FIREBALL"));
        }
        // TNT矿车
        if (e.getEntity().getType().equals(EntityType.MINECART_TNT)) {
            e.setCancelled(getConfig().getBoolean("MINECART_TNT"));
        }
    }

    // bed爆炸
    @EventHandler
    public void bedExplosion(final PlayerBedFailEnterEvent e) {
        Player player = e.getPlayer();

        if (player.getWorld().getName().equals("world_nether") && getConfig().getBoolean("NETHER_BED")) {
            e.setCancelled(true);
            player.sendMessage("AntiExplosion: 地狱睡觉，睡nm");
        }

        if (player.getWorld().getName().equals("world_the_end") && getConfig().getBoolean("END_BED")) {
            e.setCancelled(true);
            player.sendMessage("AntiExplosion: 末地睡觉，睡nm");
        }

    }
}
