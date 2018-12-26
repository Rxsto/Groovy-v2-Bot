/*
 * Groovy Bot - The core component of the Groovy Discord music bot
 *
 * Copyright (C) 2018  Oskar Lang & Michael Rittmeister & Sergej Herdt & Yannick Seeger & Justus Kliem & Leon Kappes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 */

package co.groovybot.bot.commands.music;

import co.groovybot.bot.core.audio.MusicPlayer;
import co.groovybot.bot.core.command.CommandCategory;
import co.groovybot.bot.core.command.CommandEvent;
import co.groovybot.bot.core.command.Result;
import co.groovybot.bot.core.command.permission.Permissions;
import co.groovybot.bot.core.command.voice.SameChannelCommand;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.LinkedList;

public class PreviousCommand extends SameChannelCommand {
    public PreviousCommand() {
        super(new String[]{"previous", "back"}, CommandCategory.MUSIC, Permissions.everyone(), "Lets Groovy play the previous played song", "");
    }

    @Override
    public Result runCommand(String[] args, CommandEvent event, MusicPlayer player) {
        if (player.getPreviousTrack() == null)
            return send(error(event.translate("command.previous.notrack.title"), event.translate("command.previous.notrack.description")));

        ((LinkedList<AudioTrack>) player.getTrackQueue()).addFirst(player.getPlayer().getPlayingTrack());

        player.play(player.getPreviousTrack());
        player.setPreviousTrack(null);
        return send(success(event.translate("command.previous.title"), event.translate("command.previous.description")));
    }
}
