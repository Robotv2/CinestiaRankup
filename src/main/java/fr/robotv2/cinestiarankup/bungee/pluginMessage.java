package fr.robotv2.cinestiarankup.bungee;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import fr.robotv2.cinestiarankup.main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import static fr.robotv2.cinestiarankup.utility.setExp;
import static fr.robotv2.cinestiarankup.utility.setLevel;

public class pluginMessage implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, @NotNull Player player, byte[] message) {
        if(channel.equals(main.INSTANCE.channel)) {

            final ByteArrayDataInput in = ByteStreams.newDataInput(message);
            final String sub = in.readUTF();

            switch(sub.toLowerCase()) {
                case "set-level":
                    int level = in.readInt();
                    setLevel(player, level);
                    break;
                case "set-exp":
                    double exp = in.readDouble();
                    setExp(player, exp);
                    break;
            }
        }
    }
}
