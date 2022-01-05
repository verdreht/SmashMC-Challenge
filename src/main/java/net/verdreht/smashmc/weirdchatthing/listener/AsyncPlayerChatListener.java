/*
 *                    _          _     _
 * __   _____ _ __ __| |_ __ ___| |__ | |_
 * \ \ / / _ \ '__/ _` | '__/ _ \ '_ \| __|
 *  \ V /  __/ | | (_| | | |  __/ | | | |_
 *   \_/ \___|_|  \__,_|_|  \___|_| |_|\__|
 *
 * Copyright (c) 2022.
 */

package net.verdreht.smashmc.weirdchatthing.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();

        List<String> finalParts = new ArrayList<>();
        for(String strPart : message.split(" ")) {
            char lastChar = strPart.toCharArray()[strPart.length()-1];
            finalParts.add(lastChar + strPart.substring(0, strPart.length()-1));
        }
        event.setMessage(String.join(" ", finalParts));
    }

}
