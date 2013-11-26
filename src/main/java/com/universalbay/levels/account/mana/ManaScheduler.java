package com.universalbay.levels.account.mana;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.universalbay.levels.Levels;
import com.universalbay.levels.account.Account;
import com.universalbay.levels.event.ManaChangeEvent;

public class ManaScheduler extends BukkitRunnable {
	Levels plugin;
	
	public ManaScheduler(Levels plugin) {
		this.plugin = plugin;
	}
	
	public void run() {
		//Attempt to give 1 mana to each online player.
		for (Player player : Bukkit.getOnlinePlayers()) {
			Account account = new Account(player, plugin);
			ManaChangeEvent manaEvent = new ManaChangeEvent(player, account.getMana(), account.getMana() + 1);
			plugin.getServer().getPluginManager().callEvent(manaEvent);
			//Check to see if manaEvent is cancelled by outside source. If not, award mana.
			if (!manaEvent.isCancelled()) {
				account.setMana(account.getMana() + 1);
			}
		}
	}
}
