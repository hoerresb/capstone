package edu.gymtrack.view;

import java.util.Stack;

public abstract class GTUI{
	private static Stack<GTUI> previous = new Stack<GTUI>();
	private static GTUI current;
	
	protected abstract GTUI showUI(GymTrack gym);
	
	public final void switchUI(GymTrack gym){
		previous.push(current);
		current = showUI(gym);
	}
	
	public static void goBack(GymTrack gym){
		previous.pop().showUI(gym);
	}
	
	private static void drawNav(){
		
	}
}
