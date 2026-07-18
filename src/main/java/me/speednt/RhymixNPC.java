package me.speednt;

import io.papermc.paper.datacomponent.item.ResolvableProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mannequin;

public final class RhymixNPC {

    private static Mannequin mannequin;

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

        mannequin.customName(Component.text("Rhymix"));
        mannequin.setCustomNameVisible(true);

        mannequin.setDescription(Component.text("Performance matters."));

        mannequin.setProfile(
                ResolvableProfile.resolvableProfile()
                        .name("Rhymix")
                        .build()
        );

        mannequin.setPersistent(true);
        mannequin.setRemoveWhenFarAway(false);
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

        mannequin.remove();
        mannequin = null;
    }

}