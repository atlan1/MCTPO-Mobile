package com.atlan1.mctpo.mobile.Inventory;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.atlan1.mctpo.mobile.MCTPO;
import com.atlan1.mctpo.mobile.Material;
import com.atlan1.mctpo.mobile.Graphics.Rectangle;
import com.atlan1.mctpo.mobile.Texture.TextureLoader;

public class Slot extends Rectangle {
	private Inventory inv;
	
	public static Bitmap slotNormal= TextureLoader.loadImage("images/slot_normal.png");
	public static Bitmap slotSelected= TextureLoader.loadImage("images/slot_selected.png");
	public static Paint fontPaint;
	static {
		fontPaint = new Paint();
		fontPaint.setARGB(255, 255, 255, 200);
		fontPaint.setTypeface(Typeface.createFromAsset(MCTPO.context.getAssets(),
                "fonts/tahoma.ttf"));
	}
	
	public Material material = Material.AIR;
	public int stackSize = 0;
	
	public Slot(Inventory inv, Rectangle rectangle, Material m){
		this.inv = inv;
		setBounds(rectangle);
		material = m;
	}
	
	public void render(Canvas c, boolean selected){
		c.drawBitmap(slotNormal, null, new Rect(x, y, x + width, y + height), null);
		if(selected){
			c.drawBitmap(slotSelected, null, new Rect(x-1, y-1, x + width+2, y + height+2), null);
		}
		if(stackSize>0&&material!=Material.AIR){
			c.drawBitmap(Material.terrain.getSubImageById(material.id), null, new Rect((int)x+inv.itemBorder, (int)y+inv.itemBorder, (int)(x + width-inv.itemBorder), (int)(y + height-inv.itemBorder)), null);
			c.drawText(stackSize+"", x+width-8, y+height, fontPaint);
		}
	}

}
