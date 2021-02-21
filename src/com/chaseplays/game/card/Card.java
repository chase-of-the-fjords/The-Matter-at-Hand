package com.chaseplays.game.card;

import java.util.ArrayList;

import com.chaseplays.engine.screen.Sprite;

public class Card implements Cloneable {

	public Sprite sprite;

	public ArrayList<Action> actions = new ArrayList<Action>();

	public double camY = 0, camX = 0;
	public int x = 0, y = 0;

	public Card(String path) {

		this.sprite = new Sprite(path);

		setup();

	}

	public static Card getCard(String name) {

		if (name.equals("Move 3")) {
			
			return new Card("/cards/move_3.png") {
				
				public int getID () {
					
					return 0;
					
				}
				
				public void setup() {

					this.addAction(Action.move);
					this.addAction(Action.move);
					this.addAction(Action.move);

				}

			};

		}

		if (name.equals("Move 2")) {

			return new Card("/cards/move_2.png") {
				
				public int getID () {
					
					return 1;
					
				}
				
				public void setup() {

					this.addAction(Action.move);
					this.addAction(Action.move);

				}

			};

		}

		if (name.equals("Move 1")) {

			return new Card("/cards/move_1.png") {
				
				public int getID () {
					
					return 2;
					
				}
				
				public void setup() {

					this.addAction(Action.move);

				}

			};

		}
		
		if (name.equals("Warp")) {

			return new Card("/cards/warp_2.png") {
				
				public int getID () {
					
					return 3;
					
				}
				
				public void setup() {

					this.addAction(Action.warp_2);

				}

			};

		}
		
		if (name.equals("Switch")) {
			
			return new Card ("/cards/switch.png") {
				
				public int getID () {
					
					return 4;
					
				}
				
				public void setup () {
					
					this.addAction(Action.color_switch);
					
				}
				
			};
			
		}
		
		if (name.equals("Return")) {
			
			return new Card ("/cards/return.png") {
				
				public int getID () {
					
					return 5;
					
				}
				
				public void setup () {
					
					this.addAction(Action.startReturn);
					
				}
				
			};
			
		}
		
		if (name.equals("Start")) {
			
			return new Card ("/cards/start.png") {
				
				public int getID () {
					
					return 6;
					
				}
				
				public void setup () {
					
					this.addAction(Action.startGame);
					
				}
				
			};
			
		}
		
		if (name.equals("Exit")) {
			
			return new Card ("/cards/exit.png") {
				
				public int getID () {
					
					return 7;
					
				}
				
				public void setup () {
					
					this.addAction(Action.exit);
					
				}
				
			};
			
		}

		return null;

	}

	public void setup() {

	}
	
	public int getID () {
		
		return -1;
		
	}
	
	public void addAction(Action a) {

		actions.add(a);

	}
	
	public boolean pans () {
		
		if (this.getID() == 0 || this.getID() == 1 || this.getID() == 2 ||
				this.getID() == 3) return true;
		
		return false;
		
	}

}
