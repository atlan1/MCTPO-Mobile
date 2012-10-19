package com.atlan1.mctpo.mobile;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.atlan1.mctpo.mobile.Inventory.Inventory;
import com.atlan1.mctpo.mobile.Texture.TextureLoader;

public class HealthBar {

	private static Bitmap hIcon;
	private static Bitmap hblackIcon;
	private Character c;
	
	public int maxHearts = 10;
	public int invBorder = 10;
	public int heartSpace = 4;
	public int heartSize = 20;
	
	public HealthBar(Character c){
		this.c = c;
		hIcon = TextureLoader.loadImage("images/heart.png");
		hblackIcon = TextureLoader.loadImage("images/heart_black.png");
	}
	
	public void tick(){
		
	}
	
	public void render(Canvas canvas) {
		if (MCTPO.character.inventory.inflated) {
			int heartsLeft = c.health/(c.maxHealth/maxHearts);
			int xH;
			int yH;
			for(int x=0;x<maxHearts;x++){
				boolean black = heartsLeft-x<0;
				xH = (int) ((MCTPO.size.width/2)-((((maxHearts * (heartSize + heartSpace))/2) - ((x * (heartSize + heartSpace)))) * Inventory.inventoryPixelSize));
				yH = (int) (MCTPO.size.height - (c.inventory.slotSize + invBorder + heartSize) * Inventory.inventoryPixelSize - c.inventory.borderSpace);
				canvas.drawBitmap(black?hblackIcon:hIcon, null, new Rect(xH, yH, (int) (xH + heartSize * Inventory.inventoryPixelSize), (int) (yH + heartSize * Inventory.inventoryPixelSize)), Inventory.transparentPaint);
			}
		}
	}
}
