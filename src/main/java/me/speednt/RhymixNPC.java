package me.speednt;

import io.papermc.paper.datacomponent.item.ResolvableProfile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mannequin;

import java.util.List;
import java.util.Random;

public final class RhymixNPC {

    private static Mannequin mannequin;
    private static final List<String> QUOTES = List.of(
            "Performance matters.",
            "Another beautiful tick.",
            "Have you optimized your plugins today?",
            "Paper is running smoothly.",
            "20 TPS is the dream.",
            "Clean code makes happy servers.",
            "Remember to profile before optimizing.",
            "Garbage collection is not always the enemy.",
            "I wonder how many plugins are loaded...",
            "Keep your server healthy.",
            "The main thread appreciates you.",
            "No lag today.",
            "Chunk loading is interesting.",
            "I like optimized servers.",
            "I didn't say these things..."
    );

    private static final Random RANDOM = new Random();
    private static WanderTask wanderTask;

    private RhymixNPC() {}

    public static boolean exists() {
        return mannequin != null && mannequin.isValid();
    }

    public static Mannequin get() {
        return mannequin;
    }

    public static void spawn(Location location) {

        if (exists()) {
            return;
        }

        mannequin = (Mannequin) location.getWorld().spawnEntity(
                location,
                EntityType.MANNEQUIN
        );

        mannequin.customName(Component.text("RhymixGPT"));
        mannequin.setCustomNameVisible(true);

        mannequin.setDescription(Component.text("Performance matters."));

        mannequin.setProfile(
                ResolvableProfile.resolvableProfile()
                        .name("Rhymix")
                        .build()
        );

        Bukkit.broadcast(
                Component.text("RhymixNPC has joined the game", NamedTextColor.YELLOW)
        );

        mannequin.setPersistent(true);
        mannequin.setRemoveWhenFarAway(false);
        wanderTask = new WanderTask();
        wanderTask.start();
    }

    public static void chat() {

        if (!exists()) {
            return;
        }

        String message = QUOTES.get(RANDOM.nextInt(QUOTES.size()));

        mannequin.getWorld().getPlayers().forEach(player ->
                player.sendMessage(
                        Component.text("<Rhymix> ", NamedTextColor.YELLOW)
                                .append(Component.text(message, NamedTextColor.WHITE))
                )
        );
    }

    public static void teleport(Location location) {

        if (!exists()) {
            return;
        }

        mannequin.teleport(location);
    }

    public static void remove() {

        if (!exists()) {
            return;
        }

        if (wanderTask != null) {
            wanderTask.cancel();
            wanderTask = null;
        }

        mannequin.remove();
        mannequin = null;
    }

}
