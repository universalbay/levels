package com.universalbay.levels.scoreboard;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.universalbay.levels.Levels;
import com.universalbay.levels.account.Account;
import com.universalbay.levels.account.mana.Mana;

public class ScoreboardManager {
	private static Levels plugin = Bukkit.getServer().getServicesManager().load(Levels.class);
	private static HashMap<String, Scoreboard> activeScoreboards = new HashMap<String, Scoreboard>();
	
	/*
	 * Currently has some glitches that may cause readings off by 1 point.
	 * Feature is currently limited to Bukkit's bad scoreboard API
	 * TODO: Improve stability and readability of scoreboard manager.
	 */
	
	public static void initiateScoreboard(Player player) {
		Account account = new Account(player, plugin);
		Scoreboard scoreboard = plugin.getServer().getScoreboardManager().getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("stats", "dummy");
		objective.setDisplayName("Stats");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		Score fameScore = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Fame:"));
		fameScore.setScore(account.getFame());
		Score mannaScore = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Mana:"));
		mannaScore.setScore(account.getMana());
		player.setScoreboard(scoreboard);
		activeScoreboards.put(player.getName(), scoreboard);
	}
	
	public static void update(Player player) {
		Account account = new Account(player, plugin);
		Scoreboard scoreboard = activeScoreboards.get(player.getName());
		scoreboard.getObjective("stats").getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Fame:")).setScore(account.getFame());
		scoreboard.getObjective("stats").getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Mana:")).setScore(account.getMana());
	}
}
