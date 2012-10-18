package com.atlan1.mctpo.mobile;

import android.graphics.Bitmap;
import android.graphics.Canvas;

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
		int heartsLeft = c.health/(c.maxHealth/maxHearts);
		for(int x=0;x<maxHearts;x++){
			boolean black = heartsLeft-x<0;
			canvas.drawBitmap(black?hblackIcon:hIcon, (MCTPO.pixel.width/2)-((maxHearts * (heartSize + heartSpace))/2)+((x * (heartSize + heartSpace))), MCTPO.pixel.height - (c.inventory.slotSize + c.inventory.borderSpace + invBorder + heartSize), null);
		}
		
	}
}