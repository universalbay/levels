package com.universalbay.levels;

import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import com.universalbay.classes.ClassManager;
import com.universalbay.levels.account.mana.Mana;
import com.universalbay.levels.account.mana.ManaScheduler;
import com.universalbay.levels.configuration.AccountsConfiguration;
import com.universalbay.levels.listener.ClassListener;
import com.universalbay.levels.listener.LevelListener;
import com.universalbay.levels.listener.ScoreboardListener;

public class Levels extends JavaPlugin {
	private static ClassManager classManager;
	public AccountsConfiguration accountsConfig;
	public Mana mana;
	private static Levels levels;
	
	public void onEnable() {
		this.saveDefaultConfig();
		this.getServer().getLogger().info("Loading Accounts Config...");
		this.accountsConfig = new AccountsConfiguration(this);
		this.getServer().getLogger().info("Enabling Listeners...");
		this.getServer().getPluginManager().registerEvents(new LevelListener(this), this);
		this.getServer().getPluginManager().registerEvents(new ScoreboardListener(this), this);
		// Currently unused code.  Intended to later allow display of class type on scoreboard.
		if (this.getConfig().getBoolean("useClassesPlugin") == true) {
			try {
				this.classManager = this.getServer().getServicesManager().load(ClassManager.class);
				this.getServer().getPluginManager().registerEvents(new ClassListener(this), this);
			} catch(NoClassDefFoundError e) {
				this.getServer().getLogger().warning("An exception has occured while loading the Universal-Bay Classes plugin! Is it installed?");
			}
		}
		// Register plugin and mana instance for use in static classes.
		this.mana = new Mana(this);
		this.getServer().getServicesManager().register(Mana.class, this.mana, this, ServicePriority.Normal);
		// If enabled, start scheduler to control mana regeneration.
		if (this.getConfig().getInt("manaInterval") != 0) {
			BukkitTask task = new ManaScheduler(this).runTaskTimer(this, 20, this.getConfig().getInt("manaInterval") * 20);
		}
		this.getServer().getServicesManager().register(Levels.class, this, this, ServicePriority.Normal);
		levels = this;
	}
	
	// Part of future class checking system.
	public static ClassManager getClassManager() {
		return classManager;
	}
	
	public static Levels getPlugin() {
		return levels;
	}
}
