package com.universalbay.levels.listener;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import com.universalbay.classes.event.ClassChangeEvent;
import com.universalbay.levels.Levels;
import com.universalbay.levels.account.Account;
import com.universalbay.levels.event.FameChangeEvent;

public class LevelListener implements Listener {
	Levels plugin;
	
	public LevelListener(Levels plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void levelUp(PlayerLevelChangeEvent event) {
		Player player = event.getPlayer();
		Account a = new Account(player, plugin);
		// Checks to see if player has increased or decreased in level.
		if (event.getNewLevel() > event.getOldLevel()) {
			FameChangeEvent fameEvent = new FameChangeEvent(player, a.getFame(), a.getFame() + 1);
			plugin.getServer().getPluginManager().callEvent(fameEvent);
			// Checks if fameEvent has been cancelled. If not, award fame.
			if (!fameEvent.isCancelled()) {
				a.setFame(a.getFame() + 1);
			}
		}
		// Set's player back to maximum allowed level if they exceed said amount.
		if (event.getNewLevel() > plugin.getConfig().getInt("maximumLevel")) {
			player.setLevel(20);
		}
	}

	// Reset's player's level on death. (More of a stability feature since this happens anyway)
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		event.getEntity().setLevel(0);
	}
}
