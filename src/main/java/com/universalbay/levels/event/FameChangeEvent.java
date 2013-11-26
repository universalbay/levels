package com.universalbay.levels.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FameChangeEvent extends Event implements Cancellable {
	public static final HandlerList handlerList = new HandlerList();
	private boolean cancelled;
	private Player player;
	private int oldFame;
	private int newFame;
	
	public FameChangeEvent(Player player, int oldFame, int newFame) {
		this.cancelled = false;
		this.player = player;
		this.oldFame = oldFame;
		this.newFame = newFame;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public int getNewFame() {
		return this.newFame;
	}
	
	public int getOldFame() {
		return this.oldFame;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	@Override
	public final HandlerList getHandlers() {
		return handlerList;
	}
	
	public static HandlerList getHandlerList() {
		return handlerList;
	}
}
