
/*
 *                    _          _     _
 * __   _____ _ __ __| |_ __ ___| |__ | |_
 * \ \ / / _ \ '__/ _` | '__/ _ \ '_ \| __|
 *  \ V /  __/ | | (_| | | |  __/ | | | |_
 *   \_/ \___|_|  \__,_|_|  \___|_| |_|\__|
 *
 * Copyright (c) 2022.
 */

package net.verdreht.smashmc.weirdchatthing;

import com.google.common.reflect.ClassPath;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class WeirdChatThing extends JavaPlugin {

    private final String prefix = "§8[§a§lSmashMC§8] §7";
    private static WeirdChatThing instance;

    @Override
    public void onDisable() {
        getServer().getLogger().log(Level.INFO, prefix + "Plugin §eWeirdChatThing §7has been disabled§8.");
    }

    @Override
    public void onEnable() {
        instance = this;
        registerListener(this, "net.verdreht.smashmc.weirdchatthing.listener");

        getServer().getLogger().log(Level.INFO, prefix + "Plugin §eWeirdChatThing §7has been enabled§8.");
    }

    private void registerListener(JavaPlugin plugin, String path) {
        try {
            ClassLoader classLoader = plugin.getClass().getClassLoader();
            for(ClassPath.ClassInfo info : ClassPath.from(classLoader).getTopLevelClasses(path)) {
                Object obj = Class.forName(info.getName(), true, classLoader).newInstance();
                if(obj instanceof Listener) {
                    plugin.getServer().getPluginManager().registerEvents((Listener)obj, plugin);
                    getServer().getConsoleSender().sendMessage(this.prefix + " §7Registered §eListener§8: §a" + obj.getClass().getName().replace("net.verdreht.smashmc.weirdchatthing.listener.", ""));
                }
            }
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(prefix + " §cA error was thrown, while registering Listeners." );
            e.printStackTrace();
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public static WeirdChatThing getInstance() {
        return instance;
    }

}
