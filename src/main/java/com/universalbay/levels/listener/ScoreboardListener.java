package com.universalbay.levels.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.universalbay.levels.Levels;
import com.universalbay.levels.event.FameChangeEvent;
import com.universalbay.levels.event.ManaChangeEvent;
import com.universalbay.levels.scoreboard.ScoreboardManager;

public class ScoreboardListener implements Listener {
	private Levels plugin;
	
	public ScoreboardListener(Levels plugin) {
		this.plugin = plugin;
	}
	
	// Displays stats scoreboard on join.
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		ScoreboardManager.initiateScoreboard(event.getPlayer());
	}
	
	/*
	 * Updates fame readings if they change.
	 * Only will work if fame change is used in correlation with a FameChangeEvent.
	 */
	@EventHandler
	public void onFameChange(FameChangeEvent event) {
		ScoreboardManager.update(event.getPlayer());
	}
	
	/*
	 *  Updates mana readings if they change.
	 *  Only will work if mana change is used in correlation with a ManaChangeEvent.
	 */
	@EventHandler
	public void onManaChange(ManaChangeEvent event) {
		if (event.getNewMana() <= plugin.getConfig().getInt("maximumMana") + 1) {
			ScoreboardManager.update(event.getPlayer());
		}
		else {
			event.setCancelled(true);
		}
	}
}
