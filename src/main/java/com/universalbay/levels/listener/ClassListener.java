package com.universalbay.levels.listener;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.universalbay.classes.event.ClassChangeEvent;
import com.universalbay.levels.Levels;

/*
 * Contains listeners used only for Universal-Bay Classes integration.
 * Optional usage in config.
 */

public class ClassListener implements Listener {
	private Levels plugin;
	
	public ClassListener(Levels plugin) {
		this.plugin = plugin;
	}
	
	// Part of Universal-Bay Classes integration.  Resets level if player changes level.
	@EventHandler
	public void onClassChange(ClassChangeEvent event) {
		HumanEntity entity = event.getWho();
		if (entity instanceof Player) {
			Player player = (Player) entity;
			player.setLevel(0);
		}
	}
}
