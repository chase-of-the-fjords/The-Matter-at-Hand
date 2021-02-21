package com.chaseplays.game.level;

import java.util.ArrayList;

import com.chaseplays.engine.Engine;
import com.chaseplays.engine.screen.Sprite;
import com.chaseplays.game.CardPuzzle;
import com.chaseplays.game.card.Card;
import com.chaseplays.game.tile.*;

public class Level {
	
	public Tile[][] tiles = new Tile[16][8];
	public ArrayList<Card> hand = new ArrayList<Card>();
	
	public static Sprite wallSheet = new Sprite("/tiles/wall.png");
	
	public static Sprite wall_u0r0d0l0 = new Sprite(wallSheet, 0, 0, 9, 9);
	public static Sprite wall_u1r1d1l1 = new Sprite(wallSheet, 9, 0, 9, 9);
	public static Sprite wall_u1r0d1l0 = new Sprite(wallSheet, 18, 0, 9, 9);
	public static Sprite wall_u0r1d0l1 = new Sprite(wallSheet, 27, 0, 9, 9);
	
	public static Sprite wall_u0r1d1l0 = new Sprite(wallSheet, 0, 9, 9, 9);
	public static Sprite wall_u0r0d1l1 = new Sprite(wallSheet, 9, 9, 9, 9);
	public static Sprite wall_u1r0d0l1 = new Sprite(wallSheet, 18, 9, 9, 9);
	public static Sprite wall_u1r1d0l0 = new Sprite(wallSheet, 27, 9, 9, 9);
	
	public static Sprite wall_u1r1d0l1 = new Sprite(wallSheet, 0, 18, 9, 9);
	public static Sprite wall_u1r1d1l0 = new Sprite(wallSheet, 9, 18, 9, 9);
	public static Sprite wall_u0r1d1l1 = new Sprite(wallSheet, 18, 18, 9, 9);
	public static Sprite wall_u1r0d1l1 = new Sprite(wallSheet, 27, 18, 9, 9);
	
	public static Sprite wall_u0r0d0l1 = new Sprite(wallSheet, 0, 27, 9, 9);
	public static Sprite wall_u0r0d1l0 = new Sprite(wallSheet, 9, 27, 9, 9);
	public static Sprite wall_u0r1d0l0 = new Sprite(wallSheet, 18, 27, 9, 9);
	public static Sprite wall_u1r0d0l0 = new Sprite(wallSheet, 27, 27, 9, 9);
	
	public static Sprite levelEnd = new Sprite("/tiles/end.png");
	public static Sprite hole = new Sprite("/tiles/hole.png");
	
	public static Sprite solidRed = new Sprite("/tiles/solid_red.png");
	public static Sprite emptyRed = new Sprite("/tiles/empty_red.png");
	public static Sprite solidBlue = new Sprite("/tiles/solid_blue.png");
	public static Sprite emptyBlue = new Sprite("/tiles/empty_blue.png");
	public static Sprite casingRed = new Sprite("/tiles/casing_red.png");
	public static Sprite casingBlue = new Sprite("/tiles/casing_blue.png");
	
	public static Sprite lockedRed = new Sprite("/tiles/lockedRed.png");
	public static Sprite unlockedRed = new Sprite("/tiles/unlockedRed.png");
	public static Sprite plateRed = new Sprite("/tiles/plateRed.png");
	
	public static Sprite lockedBlue = new Sprite("/tiles/lockedBlue.png");
	public static Sprite unlockedBlue = new Sprite("/tiles/unlockedBlue.png");
	public static Sprite plateBlue = new Sprite("/tiles/plateBlue.png");
	
	public Sprite board = new Sprite("/board.png");
	public Sprite reminder = new Sprite("/reminder.png");
	public Sprite instruction = new Sprite("/instructions.png");
	public Sprite intro = new Sprite("/intro.png");
	public static Sprite arrow = new Sprite("/arrow.png");
	
	public static Sprite start = new Sprite("/tiles/start.png");
	public static Sprite thanks = new Sprite("/thanks.png");
	
	public static double cardCamX = 0;
	public static double cardCamY = 0;
	public static double arrowCamY = 0;
	public static int cardY = 0;
	
	public static double camY = 0;
	public static double arrowY = 0;
	public static int y = 0;
	
	public int spawnX, spawnY;
	
	public String path;
	public String board_path;
	public String[] cards;
	
	public static int solidColor = 0;
	
	public static double instructionOpacity = 1;
	public static double reminderOpacity = 0;
	
	public static boolean redLocked = true;
	public static boolean blueLocked = true;
	
	public long lastTime = System.currentTimeMillis();
	
	public Level (String path, String... cards) {
		
		this.path = path;
		
		this.board = new Sprite(144, 76);
		
		Sprite levelSheet = new Sprite(path);
		
		this.cards = cards;
		
		for (int c = 0; c < cards.length; c++) {
			
			this.hand.add(Card.getCard(cards[c]));
			
		}
		
		for (int lX = 0; lX < 16; lX++) {
			
			for (int lY = 0; lY < 8; lY++) {
				
				if (levelSheet.pixels[lX + (lY * 16)] == 0xFF000000) tiles[lX][lY] = new Tile(-1);
				else if (levelSheet.pixels[lX + (lY * 16)] == 0xFFFFFFFF) tiles[lX][lY] = new Tile(0);
				else if (levelSheet.pixels[lX + (lY * 16)] == 0xFF262B44) tiles[lX][lY] = new Tile(1);
				else if (levelSheet.pixels[lX + (lY * 16)] == 0xFF00FF00) {
					
					tiles[lX][lY] = new Tile(-2);
					
					spawnX = lX;
					spawnY = lY;
					
				}
				else if (levelSheet.pixels[lX + (lY * 16)] == 0xFFFF0000) tiles[lX][lY] = new Tile(2);
				else if (levelSheet.pixels[lX + (lY * 16)] == 0xFF5A6988) tiles[lX][lY] = new Tile(3);
				else if (levelSheet.pixels[lX + (lY * 16)] == 0xFFFF4444) tiles[lX][lY] = new Tile(4);
				else if (levelSheet.pixels[lX + (lY * 16)] == 0xFF0099DB) tiles[lX][lY] = new Tile(5);
				else if (levelSheet.pixels[lX + (lY * 16)] == 0xFFFFAAAA) tiles[lX][lY] = new Tile(6);
				else if (levelSheet.pixels[lX + (lY * 16)] == 0xFFAAAAFF) tiles[lX][lY] = new Tile(7);
				else if (levelSheet.pixels[lX + (lY * 16)] == 0xFFFF6666) tiles[lX][lY] = new Tile(8);
				else if (levelSheet.pixels[lX + (lY * 16)] == 0xFF6666FF) tiles[lX][lY] = new Tile(9);
				else tiles[lX][lY] = new Tile(0);
				
			}
			
		}
		
		for (int bX = 0; bX < 16; bX++) {
			
			for (int bY = 0; bY < 8; bY++) {
				
				if (tiles[bX][bY].id != -1) {
					
					if ((bX + bY) % 2 == 0) board.append(bX * 9, (bY * 9) + 3, new Sprite(0xFFC28569, 9, 9));
					else board.append(bX * 9, (bY * 9) + 3, new Sprite(0xFFE8B796, 9, 9));
					
				}
				
			}
			
		}
		
		Sprite stroke = new Sprite(144, 144);
		
		for (int x = -1; x <= 1; x++) {
			
			for (int y = -1; y <= 1; y++) {
				
				stroke.append(x, y, board.tinted(0xFF000000, 1));
				
			}
			
		}
		
		stroke.append(0, 0, board);
		
		stroke.append(0, -2, stroke);
		
		board = stroke;
		
	}
	
	public Level () {
		
		for (int x = 0; x < 16; x++) {
			
			for (int y = 0; y < 8; y++) {
				
				tiles[x][y] = new Tile(0);
				
			}
			
		}
		
	}
	
	public void update (Engine e) {
		
		if (lastTime <= System.currentTimeMillis()) {
			
			if (cardCamX != 0) cardCamX *= 0.95;
			
			if (Math.abs(cardCamX) < 0.5) cardCamX = 0;
			
			if (arrowCamY != 0) arrowCamY *= 0.99;
			
			if (Math.abs(arrowCamY) < 0.5) arrowCamY = 0;
			
			if (cardCamY != 0) cardCamY *= 0.99;
			
			if (Math.abs(cardCamY) < 0.5) cardCamY = 0;
			
			for (int c = 0; c < hand.size(); c++) {
				
				if (hand.get(c).camY != 0) hand.get(c).camY *= 0.99;
				
				if (Math.abs(hand.get(c).camY) < 0.5) hand.get(c).camY = 0;
				
				if (hand.get(c).camX != 0) hand.get(c).camX *= 0.95;
				
				if (Math.abs(hand.get(c).camX) < 0.5) hand.get(c).camX = 0;
				
			}
			
			if (camY != 0) camY *= 0.99;
			if (Math.abs(camY) < 0.5) camY = 0;
			
			if (CardPuzzle.game.timeSinceDoneSomething + 5000 < System.currentTimeMillis() && reminderOpacity < 1) {
				
				reminderOpacity += 0.0005;
				
			} else if (CardPuzzle.game.timeSinceDoneSomething + 5000 >= System.currentTimeMillis() && reminderOpacity > 0) {
				
				reminderOpacity -= 0.0005;
				
			}
			
			if (CardPuzzle.game.currentLevel > 0 && CardPuzzle.game.currentLevel < 2 && instructionOpacity < 1) {
				
				instructionOpacity += 0.0005;
				
			} else if (CardPuzzle.game.currentLevel > 2 && instructionOpacity > 0) {
				
				instructionOpacity -= 0.0005;
				
			}
			
			lastTime = System.currentTimeMillis();
			
		}
		
	}
	
	public void render (Engine e) {
		
		e.screen.in.render(0, (int) (6 + y + camY), board);
		if (CardPuzzle.game.level.hand.size() > 0) e.screen.in.render(69, 133 + (int) cardCamY + cardY + (int) arrowCamY + (int) arrowY, arrow);
		
		for (int ty = 0; ty < 8; ty++) {
			
			for (int tx = 0; tx < 16; tx++) {
				
				if (tiles[tx][ty].id == 1) {
					
					if (getValue(tx, ty - 1) != 1 &&
							getValue(tx + 1, ty) != 1 &&
							getValue(tx, ty + 1) != 1 &&
							getValue(tx - 1, ty) != 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u0r0d0l0);
					
					if (getValue(tx, ty - 1) == 1 &&
							getValue(tx + 1, ty) == 1 &&
							getValue(tx, ty + 1) == 1 &&
							getValue(tx - 1, ty) == 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u1r1d1l1);
					
					if (getValue(tx, ty - 1) == 1 &&
							getValue(tx + 1, ty) != 1 &&
							getValue(tx, ty + 1) == 1 &&
							getValue(tx - 1, ty) != 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u1r0d1l0);
					
					if (getValue(tx, ty - 1) != 1 &&
							getValue(tx + 1, ty) == 1 &&
							getValue(tx, ty + 1) != 1 &&
							getValue(tx - 1, ty) == 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u0r1d0l1);
					
					//
					
					if (getValue(tx, ty - 1) != 1 &&
							getValue(tx + 1, ty) == 1 &&
							getValue(tx, ty + 1) == 1 &&
							getValue(tx - 1, ty) != 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u0r1d1l0);
						
					if (getValue(tx, ty - 1) != 1 &&
							getValue(tx + 1, ty) != 1 &&
							getValue(tx, ty + 1) == 1 &&
							getValue(tx - 1, ty) == 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u0r0d1l1);
					
					if (getValue(tx, ty - 1) == 1 &&
							getValue(tx + 1, ty) != 1 &&
							getValue(tx, ty + 1) != 1 &&
							getValue(tx - 1, ty) == 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u1r0d0l1);
					
					if (getValue(tx, ty - 1) == 1 &&
							getValue(tx + 1, ty) == 1 &&
							getValue(tx, ty + 1) != 1 &&
							getValue(tx - 1, ty) != 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u1r1d0l0);
					
					//
					
					if (getValue(tx, ty - 1) == 1 &&
							getValue(tx + 1, ty) == 1 &&
							getValue(tx, ty + 1) != 1 &&
							getValue(tx - 1, ty) == 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u1r1d0l1);
						
					if (getValue(tx, ty - 1) == 1 &&
							getValue(tx + 1, ty) == 1 &&
							getValue(tx, ty + 1) == 1 &&
							getValue(tx - 1, ty) != 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u1r1d1l0);
					
					if (getValue(tx, ty - 1) != 1 &&
							getValue(tx + 1, ty) == 1 &&
							getValue(tx, ty + 1) == 1 &&
							getValue(tx - 1, ty) == 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u0r1d1l1);
					
					if (getValue(tx, ty - 1) == 1 &&
							getValue(tx + 1, ty) != 1 &&
							getValue(tx, ty + 1) == 1 &&
							getValue(tx - 1, ty) == 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u1r0d1l1);

					//
					
					if (getValue(tx, ty - 1) != 1 &&
							getValue(tx + 1, ty) != 1 &&
							getValue(tx, ty + 1) != 1 &&
							getValue(tx - 1, ty) == 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u0r0d0l1);
						
					if (getValue(tx, ty - 1) != 1 &&
							getValue(tx + 1, ty) != 1 &&
							getValue(tx, ty + 1) == 1 &&
							getValue(tx - 1, ty) != 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u0r0d1l0);
					
					if (getValue(tx, ty - 1) != 1 &&
							getValue(tx + 1, ty) == 1 &&
							getValue(tx, ty + 1) != 1 &&
							getValue(tx - 1, ty) != 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u0r1d0l0);
					
					if (getValue(tx, ty - 1) == 1 &&
							getValue(tx + 1, ty) != 1 &&
							getValue(tx, ty + 1) != 1 &&
							getValue(tx - 1, ty) != 1) 
							e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), wall_u1r0d0l0);
					
				}
				
				if (tiles[tx][ty].id == 2) {
					
					e.screen.on.render((int) ((tx * 9)), (int) (7 + y + camY + (ty * 9)), levelEnd);
					
				}
				
				if (tiles[tx][ty].id == 3) {
					
					e.screen.on.render((int) ((tx * 9)), (int) (7 + y + camY + (ty * 9)), hole);
					
				}
				
				if (tiles[tx][ty].id == 4) {
					
					if (solidColor == 0) {
						
						e.screen.on.render((int) ((tx * 9)), (int) (7 + y + camY + (ty * 9)), solidRed);
						
					}
					if (solidColor == 1) {
						
						e.screen.on.render((int) ((tx * 9)), (int) (7 + y + camY + (ty * 9)), emptyRed);
						
					}
					
					if (getValue(tx, ty + 1) == -1) e.screen.on.render((int) ((tx * 9)), (int) (7 + y + camY + (ty * 9)) + 9, casingRed);
					
				}
				
				if (tiles[tx][ty].id == 5) {
					
					if (solidColor == 1) {
						
						e.screen.on.render((int) ((tx * 9)), (int) (7 + y + camY + (ty * 9)), solidBlue);
						
					}
					
					if (solidColor == 0) {
						
						e.screen.on.render((int) ((tx * 9)), (int) (7 + y + camY + (ty * 9)), emptyBlue);
						
					}
					
					if (getValue(tx, ty + 1) == -1) e.screen.on.render((int) ((tx * 9)), (int) (7 + y + camY + (ty * 9)) + 9, casingBlue);
					
				}
				
				if (tiles[tx][ty].id == 6) {
					
					if (redLocked) e.screen.on.render((int) ((tx * 9)), (int) (5 + y + camY + (ty * 9)), lockedRed);
					if (!redLocked) e.screen.on.render((int) ((tx * 9)), (int) (7 + y + camY + (ty * 9)), unlockedRed);
					
				}
				
				if (tiles[tx][ty].id == 7) {
					
					if (blueLocked) e.screen.on.render((int) ((tx * 9)), (int) (5 + y + camY + (ty * 9)), lockedBlue);
					if (!blueLocked) e.screen.on.render((int) ((tx * 9)), (int) (7 + y + camY + (ty * 9)), unlockedBlue);
					
				}
				
				if (tiles[tx][ty].id == 8) {
					
					e.screen.on.render((int) ((tx * 9)), (int) (7 + y + camY + (ty * 9)), plateRed);
					
				}
				
				if (tiles[tx][ty].id == 9) {
					
					e.screen.on.render((int) ((tx * 9)), (int) (7 + y + camY + (ty * 9)), plateBlue);
					
				}
				
				if (tiles[tx][ty].id == -2) {
					
					e.screen.on.render((int) (tx * 9), (int) (7 + y + camY + (ty * 9)), start);
					
				}
				
			}
			
		}
		
		for (int c = 0; c < hand.size(); c++) {
			
			if (c != CardPuzzle.game.selectedCard) e.screen.in.render(72 - 15 + (c * 32) - (CardPuzzle.game.selectedCard * 32) + (int) cardCamX + (int) hand.get(c).camX + (int) hand.get(c).x, 95 + (int) hand.get(c).camY + hand.get(c).y + (int) cardCamY + cardY, hand.get(c).sprite);
			else e.screen.in.render(72 - 15 + (c * 32) - (CardPuzzle.game.selectedCard * 32) + (int) cardCamX + (int) hand.get(c).camX + (int) hand.get(c).x, 85 + (int) hand.get(c).camY + hand.get(c).y + (int) cardCamY + cardY, hand.get(c).sprite);
			
		}
		
		if (CardPuzzle.game.currentLevel != 0 && CardPuzzle.game.currentLevel != 16) {
			
			if (CardPuzzle.game.currentLevel > 2) e.screen.in.render(0, 1, reminder.opacity(reminderOpacity));
			if (CardPuzzle.game.currentLevel <= 3) e.screen.in.render(0, 1, instruction.opacity(instructionOpacity));
			
		} else {
			
			if (CardPuzzle.game.currentLevel == 0) e.screen.in.render(0, (int) (7 + camY + y), intro);

			if (CardPuzzle.game.currentLevel == 16) e.screen.in.render(0, (int) (7 + camY + y), thanks);
			
		}
		
	}
	
	public int getValue (int x, int y) {
		
		if (x < 0 || x > 15 || y < 0 || y > 15) return 0;
		else return tiles[x][y].id;
		
	}
	
}
