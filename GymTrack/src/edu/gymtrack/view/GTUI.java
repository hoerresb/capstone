package edu.gymtrack.view;

import java.util.Stack;

public abstract class GTUI{
	private static Stack<GTUI> previous = new Stack<GTUI>();
	private static GTUI current;
	
	protected abstract GTUI showUI(GymTrack gym);
	
	public final void switchUI(GymTrack gym){
		if(current != null)
			previous.push(current);
		
		current = showUI(gym);
	}
	
	public void reloadPage(GymTrack gym){
		current.showUI(gym);
	}
	
	public void goBack(GymTrack gym){
		if(previous.isEmpty())
			return;
		
		current = previous.pop();
		current.showUI(gym);
	}
	
	public void logOut(GymTrack gym) {
		while(!previous.isEmpty()) {
			current = previous.pop();
		}
		
		gym.destroy();
		gym = new GymTrack();
		gym.start();
		current.showUI(gym);
	}
}
