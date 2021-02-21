package com.chaseplays.game;

import com.chaseplays.engine.Engine;
import com.chaseplays.engine.screen.Sprite;
import com.chaseplays.game.card.Action;
import com.chaseplays.game.level.Level;

public class Player {
	
	public int x, y;
	
	public double camX, camY;
	
	public static Sprite sprite = new Sprite("/player.png");
	public static Sprite dead = new Sprite("/deadplayer.png");
	
	public void update (Engine e) {
		
		if (!Action.warping && !Action.returning) {
			
			if (camX != 0) camX *= 0.95;
			if (camY != 0) camY *= 0.95;
			
			if (Math.abs(camX) < 0.5) camX = 0;
			if (Math.abs(camY) < 0.5) camY = 0;
			
		} else {
			
			if (camX != 0) camX *= 0.992;
			if (camY != 0) camY *= 0.992;
			
			if (Math.abs(camX) < 0.5) camX = 0;
			if (Math.abs(camY) < 0.5) camY = 0;
			
		}
		
	}
	
	public void render (Engine e) {
		
		if (Action.warping) e.screen.in.render(2 + (int) ((x * 9) + camX), (int) (8 + (y * 9) + camY + Level.camY + Level.y), sprite.tinted(0xFF00FF00, 0.65).opacity(0.55));
		else if (Action.returning) e.screen.in.render(2 + (int) ((x * 9) + camX), (int) (8 + (y * 9) + camY + Level.camY + Level.y), sprite.tinted(0xFFFF66FF, 0.65).opacity(0.55));
		else if (CardPuzzle.game.currentLevel != 0 && CardPuzzle.game.currentLevel != 16 && Action.isMoveable(x, y)) e.screen.in.render(2 + (int) ((x * 9) + camX), (int) (8 + (y * 9) + camY + Level.camY + Level.y), sprite);
		else if (!Action.isMoveable(x, y)) e.screen.in.render((int) ((x * 9) + camX), (int) (7 + (y * 9) + camY + Level.camY + Level.y), dead);
		
	}
	
}
