package com.universalbay.levels.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ManaChangeEvent extends Event implements Cancellable {
	public static final HandlerList handlerList = new HandlerList();
	private boolean cancelled;
	private Player player;
	private int oldMana;
	private int newMana;
	
	public ManaChangeEvent(Player player, int oldMana, int newMana) {
		this.cancelled = false;
		this.player = player;
		this.oldMana = oldMana;
		this.newMana = newMana;
	}

	public boolean isCancelled() {
		return this.cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public int getNewMana() {
		return this.newMana;
	}
	
	public int getOldMana() {
		return this.oldMana;
	}

	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
	
	public static HandlerList getHandlerList() {
		return handlerList;
	}
}
