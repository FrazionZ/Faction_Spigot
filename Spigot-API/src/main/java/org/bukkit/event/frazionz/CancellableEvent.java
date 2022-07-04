package org.bukkit.event.frazionz;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event.Result;

public class CancellableEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
    
    private Result result = Result.DEFAULT;
	
    public CancellableEvent() {
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Sets the result of this event. This will change whether or not this
     * event is considered cancelled.
     *
     * @see #isCancelled()
     * @param newResult the new {@link Result} for this event
     */
    public void setResult(Result newResult) {
        result = newResult;
    }

    /**
     * Gets the {@link Result} of this event. The Result describes the
     * behavior that will be applied to the inventory in relation to this
     * event.
     *
     * @return the Result of this event.
     */
    public Result getResult() {
        return result;
    }

    /**
     * Gets whether or not this event is cancelled. This is based off of the
     * Result value returned by {@link #getResult()}.  Result.ALLOW and
     * Result.DEFAULT will result in a returned value of false, but
     * Result.DENY will result in a returned value of true.
     * <p>
     * {@inheritDoc}
     *
     * @return whether the event is cancelled
     */
    public boolean isCancelled() {
        return getResult() == Result.DENY;
    }

    /**
     * Proxy method to {@link #setResult(Event.Result)} for the Cancellable
     * interface. {@link #setResult(Event.Result)} is preferred, as it allows
     * you to specify the Result beyond Result.DENY and Result.ALLOW.
     * <p>
     * {@inheritDoc}
     *
     * @param toCancel result becomes DENY if true, ALLOW if false
     */
    public void setCancelled(boolean toCancel) {
        setResult(toCancel ? Result.DENY : Result.ALLOW);
    }

}
