package com.chaseplays.game.card;

import com.chaseplays.engine.Engine;
import com.chaseplays.game.CardPuzzle;
import com.chaseplays.game.level.Level;

public class Action {

	public static volatile boolean warping = false;
	public static volatile boolean returning = false;

	public int id;
	
	public static Action move = new Action() {
		
		public int getID () {
			
			return 0;
			
		}
		
		public boolean doAction(Engine e) {

			if (e.key.up.typed) {

				if (CardPuzzle.game.player.y > 0) {

					if (isMoveable(CardPuzzle.game.player.x, CardPuzzle.game.player.y - 1)) {

						CardPuzzle.game.player.y--;
						CardPuzzle.game.player.camY += 8;
						
						return true;

					}

				}

			}

			if (e.key.down.typed) {

				if (CardPuzzle.game.player.y < 7) {

					if (isMoveable(CardPuzzle.game.player.x, CardPuzzle.game.player.y + 1)) {

						CardPuzzle.game.player.y++;
						CardPuzzle.game.player.camY -= 8;
						
						return true;

					}

				}

			}

			if (e.key.left.typed) {

				if (CardPuzzle.game.player.x > 0) {

					if (isMoveable(CardPuzzle.game.player.x - 1, CardPuzzle.game.player.y)) {

						CardPuzzle.game.player.x--;
						CardPuzzle.game.player.camX += 8;
						
						return true;

					}

				}

			}

			if (e.key.right.typed) {

				if (CardPuzzle.game.player.x < 15) {

					if (isMoveable(CardPuzzle.game.player.x + 1, CardPuzzle.game.player.y)) {
						
						CardPuzzle.game.player.x++;
						CardPuzzle.game.player.camX -= 8;
						
						return true;

					}

				}

			}

			return false;

		}

	};

	public static Action warp_2 = new Action() {
		
		public int getID () {
			
			return 1;
			
		}
		
		public boolean doAction(Engine e) {

			if (e.key.up.typed) {

				if (CardPuzzle.game.player.y > 1) {

					if (isMoveable(CardPuzzle.game.player.x, CardPuzzle.game.player.y - 2)) {

						CardPuzzle.game.player.y -= 2;
						CardPuzzle.game.player.camY += 16;

						new Thread() {

							public void run() {

								warping = true;

								try {
									Thread.sleep(600);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

								warping = false;

							}

						}.start();

						return true;

					}

				}

			}

			if (e.key.down.typed) {

				if (CardPuzzle.game.player.y < 14) {

					if (isMoveable(CardPuzzle.game.player.x, CardPuzzle.game.player.y + 2)) {

						CardPuzzle.game.player.y += 2;
						CardPuzzle.game.player.camY -= 16;

						new Thread() {

							public void run() {

								warping = true;

								try {
									Thread.sleep(600);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

								warping = false;

							}

						}.start();

						return true;

					}

				}

			}

			if (e.key.left.typed) {

				if (CardPuzzle.game.player.x > 1) {

					if (isMoveable(CardPuzzle.game.player.x - 2, CardPuzzle.game.player.y)) {

						CardPuzzle.game.player.x -= 2;
						CardPuzzle.game.player.camX += 16;

						new Thread() {

							public void run() {

								warping = true;

								try {
									Thread.sleep(600);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

								warping = false;

							}

						}.start();

						return true;

					}

				}

			}

			if (e.key.right.typed) {

				if (CardPuzzle.game.player.x < 14) {

					if (isMoveable(CardPuzzle.game.player.x + 2, CardPuzzle.game.player.y)) {

						CardPuzzle.game.player.x += 2;
						CardPuzzle.game.player.camX -= 16;

						new Thread() {

							public void run() {

								warping = true;

								try {
									Thread.sleep(600);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

								warping = false;

							}

						}.start();

						return true;

					}

				}

			}

			return false;

		}

	};

	public static Action color_switch = new Action() {
		
		public int getID () {
			
			return 2;
			
		}
		
		public boolean doAction(Engine e) {

			Level.solidColor += 1;

			Level.solidColor %= 2;

			return true;

		}

	};
	
	public static Action startReturn = new Action() {
		
		public int getID () {
			
			return 3;
			
		}
		
		public boolean doAction(Engine e) {
			
			CardPuzzle.game.player.camX += (CardPuzzle.game.player.x - CardPuzzle.game.level.spawnX) * 9;
			CardPuzzle.game.player.camY += (CardPuzzle.game.player.y - CardPuzzle.game.level.spawnY) * 9;
			
			CardPuzzle.game.player.x -= (CardPuzzle.game.player.x - CardPuzzle.game.level.spawnX);
			CardPuzzle.game.player.y -= (CardPuzzle.game.player.y - CardPuzzle.game.level.spawnY);
			
			new Thread() {

				public void run() {

					returning = true;

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					returning = false;

				}

			}.start();
			
			return true;
			
		}
		
	};
	
	public static Action startGame = new Action() {
		
		public int getID () {
			
			return 4;
			
		}
		
		public boolean doAction (Engine e) {
			
			CardPuzzle.game.timeSinceDoneSomething = System.currentTimeMillis();
			
			CardPuzzle.game.ready = false;
			CardPuzzle.game.card = false;
			CardPuzzle.game.cardInPlay = null;
			
			new Thread () {
				
				public void run () {
					
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					Level.cardCamY = -69;
					Level.cardY = 69;
					
					Level.camY = 82;
					Level.y = -82;
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					Level.cardCamY += 69;
					Level.cardY = 0;
					
					Level.camY = -82;
					Level.y = 0;
					
					CardPuzzle.game.currentLevel++;
					
					CardPuzzle.game.loadLevel(CardPuzzle.game.levels.get(CardPuzzle.game.currentLevel));
					
					CardPuzzle.game.ready = true;
					
					CardPuzzle.game.timeSinceDoneSomething = System.currentTimeMillis();
					
				}
				
			}.start();
			
			return true;
			
		}
		
	};
	
	public static Action exit = new Action() {
		
		public int getID () {
			
			return 5;
			
		}
		
		public boolean doAction (Engine e) {
			
			System.exit(0);
			
			return true;
			
		}
		
	};
	
	public static boolean isMoveable (int x, int y) {
		
		int id;
		
		if (x > 0 && x < 16 && y > 0 & y < 8) id = CardPuzzle.game.level.tiles[x][y].id;
		else return false;
		
		if (id == -1) return false;
		if (id == 0 || id == 2 || id == -2) return true;
		if (id == 1) return false;
		if (id == 3) return false;
		if (id == 4 && Level.solidColor == 0) return true;
		if (id == 4 && Level.solidColor == 1) return false;
		if (id == 5 && Level.solidColor == 1) return true;
		if (id == 5 && Level.solidColor == 0) return false;
		if (id == 6 && Level.redLocked) return false;
		if (id == 6 && !Level.redLocked) return true;
		if (id == 7 && Level.blueLocked) return false;
		if (id == 7 && !Level.blueLocked) return true;
		if (id == 8 || id == 9) return true;
		
		return false;
		
	}
	
	public boolean doAction(Engine e) {

		return false;

	}
	
	public int getID () {
		
		return -1;
		
	}
	
	public boolean pans () {
		
		if (this.getID() == 0 || this.getID() == 1) return true;
		else return false;
		
	}

}
