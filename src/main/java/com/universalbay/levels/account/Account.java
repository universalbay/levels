package com.universalbay.levels.account;

import org.bukkit.entity.Player;

import com.universalbay.levels.Levels;
import com.universalbay.levels.account.mana.Mana;

public class Account {
	Levels plugin;
	Mana mana;
	Player player;
	int level;
	int fame;
	
	public Account(Player player, Levels plugin) {
		this.plugin = plugin;
		/* Instantiate Mana field with universal Mana instance. Use of other Mana instances is not advised
		 * as execution of it's methods may not be recognized by Levels.
		 */
		this.mana = plugin.mana;
		this.player = player;
		this.level = player.getLevel();
		try {
			this.fame = plugin.accountsConfig.getConfig().getInt("Players." + player.getName().toLowerCase() + ".fame");
		} catch (NullPointerException e){
			this.setFame(0);
		}
		
	}
	
	// Retrieve player instance associated with this account.
	public Player getPlayer() {
		return this.player;
	}
	
	/* Used if you wish to retrieve the information of a different player with the same account instance,
	 * although not recommended.
	 */
	public void setPlayer(Player newplayer) {
		this.player = newplayer;
		this.reset();
	}

	public int getLevel() {
		return level;
	}
	
	public void setLevel(int playerlevel) {
		this.level = playerlevel;
		this.player.setLevel(level);
	}
	
	public int getFame() {
		return this.fame;
	}
	
	public void setFame(int playerfame) {
		this.fame = playerfame;
		this.plugin.accountsConfig.getConfig().set("Players." + player.getName().toLowerCase() + ".fame" , fame);
		this.plugin.accountsConfig.saveConfig();
	}
	
	// Getting and setting of Mana is dealt with my the universal Mana instance in order for it to be more dynamic.
	public int getMana() {
		return this.mana.getMana(this.player);
	}
	
	public void setMana(int playerMana) {
		this.mana.setMana(player, playerMana);
	}

	/* Reloads the level and fame fields to ensure an up-to-date result when under inquiry. Used mainly when changing
	 * the player associated with the account.
	 */
	public void reset() {
		this.level = player.getLevel();
		this.fame = plugin.accountsConfig.getConfig().getInt("Players." + player.getName().toLowerCase() + ".fame");
	}
}
