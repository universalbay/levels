package com.universalbay.levels.configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.universalbay.levels.Levels;

public class AccountsConfiguration {
	File file = null;
	FileConfiguration config = null;
	Levels l;
	
	public AccountsConfiguration(Levels level) {
		l = level;
	}
	
	public void reloadConfig() {
	    if (file == null) {
	    file = new File(l.getDataFolder(), "accounts.yml");
	    }
	    config = YamlConfiguration.loadConfiguration(file);
	 
	    InputStream defConfigStream = l.getResource("accounts.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        config.setDefaults(defConfig);
	    }
	}
	public FileConfiguration getConfig() {
	    if (config == null) {
	        this.reloadConfig();
	    }
	    return config;
	}
	public void saveConfig() {
	    if (config == null || file == null) {
	    return;
	    }
	    try {
	        getConfig().save(file);
	    } catch (IOException ex) {
	        l.getLogger().log(Level.SEVERE, "Could not save config to " + file, ex);
	    }
	}
}
