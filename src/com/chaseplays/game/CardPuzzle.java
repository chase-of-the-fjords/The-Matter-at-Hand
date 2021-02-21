package com.chaseplays.game;
import java.util.ArrayList;

import com.chaseplays.engine.Game;
import com.chaseplays.engine.sound.Sound;
import com.chaseplays.game.card.Action;
import com.chaseplays.game.card.Card;
import com.chaseplays.game.level.Level;

public class CardPuzzle extends Game {
	
	public static CardPuzzle game = new CardPuzzle();
	
	public Level level;
	
	public Player player = new Player();
	
	public boolean card = false;
	
	public Card cardInPlay;
	
	public int selectedCard = 0;
	
	public volatile boolean ready = true;
	
	public ArrayList<Level> levels = new ArrayList<Level>();
	
	public int currentLevel = 0;
	
	public volatile long timeSinceDoneSomething;
	
	public Sound song = new Sound("sounds/amatteroftime.wav");
	
	public static void main (String[] args) {
		
		game.setTitle("The Matter at Hand || Ludum Dare 41");
		
		game.setIcon("/move_3_icon.png");
		
		game.setDimensions(144, 144, 4);
		
		game.start();
		
	}
	
	public void loadLevel (Level l) {
		
		player.x = l.spawnX;
		player.y = l.spawnY;
		
		this.level = l;
		
		this.selectedCard = 0;
		
		Level.solidColor = 0;
		
		Level.redLocked = true;
		Level.blueLocked = true;
		
		timeSinceDoneSomething = System.currentTimeMillis();
		
	}
	
	public void setupGame () {
		
		game.setIcon("/move_3_icon.png");
		
		song.loop();
		
		levels.add(new Level("/levels/intro.png", "Start"));
		levels.add(new Level("/levels/level_1.png", "Move 3", "Move 2"));
		levels.add(new Level("/levels/level_2.png", "Move 3", "Move 3", "Move 1", "Move 2", "Move 3", "Move 3", "Move 3"));
		levels.add(new Level("/levels/level_3.png", "Move 3", "Move 2", "Move 2", "Move 1", "Move 3", "Move 2", "Move 2", "Move 1"));
		levels.add(new Level("/levels/level_4.png", "Move 1", "Warp", "Move 1", "Warp", "Move 1"));
		levels.add(new Level("/levels/level_5.png", "Warp", "Move 3", "Move 2", "Move 2", "Move 2", "Warp"));
		levels.add(new Level("/levels/level_6.png", "Move 2", "Warp", "Move 2", "Warp", "Move 2"));
		levels.add(new Level("/levels/level_7.png", "Move 3", "Move 2", "Warp", "Warp", "Warp", "Warp", "Warp", "Move 2", "Move 2", "Move 2"));
		levels.add(new Level("/levels/level_8.png", "Move 1", "Switch", "Move 2", "Move 2", "Switch", "Move 2", "Move 2"));
		levels.add(new Level("/levels/level_9.png", "Move 2", "Switch", "Move 2", "Move 2", "Move 3", "Move 2", "Warp"));
		levels.add(new Level("/levels/level_10.png", "Switch", "Switch", "Move 3", "Move 3", "Move 3", "Move 3", "Move 2", "Move 2"));
		levels.add(new Level("/levels/level_11.png", "Switch", "Switch", "Move 1", "Move 1", "Move 2", "Switch", "Warp", "Warp", "Switch", "Warp", "Warp", "Warp", "Warp"));
		levels.add(new Level("/levels/level_12.png", "Move 3", "Move 3", "Move 2", "Move 2"));
		levels.add(new Level("/levels/level_13.png", "Move 3", "Move 3", "Move 2", "Move 3", "Move 3", "Move 2", "Move 2"));
		levels.add(new Level("/levels/level_13.png", "Move 3", "Move 3", "Move 2", "Return", "Move 2"));
		levels.add(new Level("/levels/level_15.png", "Return", "Move 2", "Move 2", "Move 2", "Move 2", "Warp", "Warp", "Warp", "Warp", "Warp", "Move 2", "Move 2"));
		levels.add(new Level("/levels/thanks.png", "Exit"));
		
		loadLevel(levels.get(currentLevel));
		
	}
	
	public void update () {
		
		if (card && key.escape.typed) {
			
			this.timeSinceDoneSomething = System.currentTimeMillis();
			
			ready = false;
			card = false;
			cardInPlay = null;
			
			new Thread () {
				
				public void run () {
					
					CardPuzzle.game.level.hand.get(selectedCard).camY -= 49;
					CardPuzzle.game.level.hand.get(selectedCard).y += 49;
					Level.arrowCamY -= 49;
					Level.arrowY += 49;
					
					if (key.r.typed) {
						
						try {
							Thread.sleep(250);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}
					
					level.hand.remove(selectedCard);
					
					for (int c = 0; c < level.hand.size(); c++) {
						
						level.hand.get(c).camY += 50;
						level.hand.get(c).y -= 50;
						
					}
					
					Level.arrowCamY += 49;
					Level.arrowY -= 49;
					
					Level.camY = 30;
					Level.y = 0;
					
					if (selectedCard >= level.hand.size()) selectedCard = level.hand.size() - 1;
					
					ready = true;
					
				}
				
			}.start();
			
		}
		
		if (ready && !card && key.left.typed && selectedCard > 0) {
			
			this.timeSinceDoneSomething = System.currentTimeMillis();
			
			selectedCard--;
			Level.cardCamX -= 32;
			
			level.hand.get(selectedCard + 1).camY -= 10;
			level.hand.get(selectedCard).camY += 10;
			
		}
		
		else if (ready && !card && key.right.typed && selectedCard < level.hand.size() - 1) {
			
			this.timeSinceDoneSomething = System.currentTimeMillis();
			
			selectedCard++;
			Level.cardCamX += 32;
			
			level.hand.get(selectedCard - 1).camY -= 10;
			level.hand.get(selectedCard).camY += 10;
			
		}
		
		else if (ready && !card && (key.space.typed || key.z.typed || key.up.typed)) {
			
			this.timeSinceDoneSomething = System.currentTimeMillis();
			
			card = true;
			
			cardInPlay = level.hand.get(selectedCard);
			
			if (cardInPlay.actions.get(0).pans()) {
				
				for (int c = 0; c < level.hand.size(); c++) {
					
					if (c != selectedCard) {
						
						level.hand.get(c).camY -= 50;
						level.hand.get(c).y += 50;
						
					} else {
						
						level.hand.get(c).camY -= 30;
						level.hand.get(c).y += 30;
						
					}
					
				}
				
				Level.camY = -30;
				Level.y = 30;
				
			}
			
		}
		
		else if (card) if (cardInPlay.actions.get(0).doAction(this)) {
			
			if (this.currentLevel != 0) cardInPlay.actions.remove(0);
			
			this.timeSinceDoneSomething = System.currentTimeMillis();
			
		}
		
		if (card) {
			
			if (level.tiles[player.x][player.y].id == 2) {
				
				this.timeSinceDoneSomething = System.currentTimeMillis();
				
				ready = false;
				card = false;
				cardInPlay = null;
				
				new Thread () {
					
					public void run () {
						
						try {
							Thread.sleep(250);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						level.hand.remove(selectedCard);
						
						Level.cardCamY = -49;
						Level.cardY = 49;
						
						Level.camY = 82;
						Level.y = -82;
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						Level.cardCamY += 49;
						Level.cardY = 0;
						
						Level.camY = -82;
						Level.y = 0;
						
						currentLevel++;
						
						loadLevel(levels.get(currentLevel));
						
						ready = true;
						
						timeSinceDoneSomething = System.currentTimeMillis();
						
					}
					
				}.start();
				
			}
			
			if (level.tiles[player.x][player.y].id == 8) Level.redLocked = false;
			if (level.tiles[player.x][player.y].id == 9) Level.blueLocked = false;
			
		}
		
		if (card) if (cardInPlay.actions.size() == 0) {
			
			ready = false;
			card = false;
			
			new Thread () {
				
				public void run () {
					
					if (cardInPlay.pans()) CardPuzzle.game.level.hand.get(selectedCard).camY -= 49;
					if (cardInPlay.pans()) CardPuzzle.game.level.hand.get(selectedCard).y += 49;
					if (cardInPlay.pans()) Level.arrowCamY -= 49;
					if (cardInPlay.pans()) Level.arrowY += 49;
					
					try {
						if (cardInPlay.pans()) Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if (cardInPlay.getID() != 6) {
						
						level.hand.remove(selectedCard);
						
						if (selectedCard >= level.hand.size()) selectedCard = level.hand.size() - 1;
						else level.hand.get(selectedCard).camY += 10;
						
						if (cardInPlay.pans() && level.hand.size() > 0 ) for (int c = 0; c < level.hand.size(); c++) {
							
							level.hand.get(c).camY += 50;
							level.hand.get(c).y -= 50;
							
						} else if (!cardInPlay.pans() && level.hand.size() > 0) {
							
							if (selectedCard != level.hand.size() - 1) {
								
								for (int c = selectedCard; c < level.hand.size(); c++) {
									
									level.hand.get(c).camX += 32;
									
								}
								
							} else {
								
								for (int c = 0; c < level.hand.size(); c++) {
									
									level.hand.get(c).camX -= 32;
									
								}
								
							}
							
						}
						
						if (cardInPlay.pans()) Level.arrowCamY += 49;
						if (cardInPlay.pans()) Level.arrowY -= 49;
						
						if (cardInPlay.pans()) Level.camY = 30;
						if (cardInPlay.pans()) Level.y = 0;
						
						try {
							Thread.sleep(250);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						ready = true;
						
						cardInPlay = null;
						
					}
					
				}
				
			}.start();
			
		}
		
		if (ready && (level.hand.size() == 0 || key.r.typed || !Action.isMoveable(player.x, player.y))) {
			
			this.timeSinceDoneSomething = System.currentTimeMillis();
			
			ready = false;
			card = false;
			cardInPlay = null;
			
			new Thread () {
				
				public void run () {
					
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					Level.cardCamY = -59;
					Level.cardY = 59;
					
					Level.camY = 82;
					Level.y = -82;
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					loadLevel(new Level(level.path, level.cards));
					
					Level.cardCamY += 49;
					Level.cardY = 0;
					
					Level.camY = -82;
					Level.y = 0;
					
					ready = true;
					
					timeSinceDoneSomething = System.currentTimeMillis();
					
				}
				
			}.start();
			
		}
		
		player.update(this);
		
		level.update(this);
		
	}
	
	public void render () {
		
		screen.color(0xFF262B44);
		
		level.render(this);
		
		player.render(this);
		
	}
	
}
