package com.universalbay.levels.account.mana;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.universalbay.levels.Levels;

public class Mana {
	private Levels plugin;
	private HashMap<String, Integer> mana = new HashMap<String, Integer>();
	public Mana(Levels plugin) {
		this.plugin = plugin;
	}
	
	public int getMana(Player player) {
		int playerMana;
		// Instantiate the playerMana variable
		try {
			playerMana = this.mana.get(player.getName());
		} catch(NullPointerException e) {
			mana.put(player.getName(), plugin.accountsConfig.getConfig().getInt("maximumMana"));
			playerMana = this.mana.get(player.getName());
		}
		return playerMana;
	}
	
	// Set a new mana value to the HashMap.
	public void setMana(Player player, int playerMana) {
		this.mana.put(player.getName(), playerMana);
	}
	
	// Used for more in-depth actions not involving just the changing of a key's value.
	public HashMap<String, Integer> getManaMap() {
		return this.mana;
	}
}
