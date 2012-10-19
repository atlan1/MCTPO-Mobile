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
		c.drawBitmap(slotNormal, null, new Rect((int) (x * MCTPO.pixelSize), (int) (y * MCTPO.pixelSize), (int) ((x + width) * MCTPO.pixelSize), (int) ((y + height) * MCTPO.pixelSize)), null);
		if(selected){
			c.drawBitmap(slotSelected, null, new Rect((int) ((x-1) * MCTPO.pixelSize), (int) ((y-1) * MCTPO.pixelSize), (int) ((x + width+2) * MCTPO.pixelSize), (int) ((y + height+2) * MCTPO.pixelSize)), null);
		}
		if(stackSize>0&&material!=Material.AIR){
			c.drawBitmap(Material.terrain.getSubImageById(material.id), null, new Rect((int)((x+inv.itemBorder) * MCTPO.pixelSize), (int)((y+inv.itemBorder) * MCTPO.pixelSize), (int)((x + width-inv.itemBorder) * MCTPO.pixelSize), (int)((y + height-inv.itemBorder) * MCTPO.pixelSize)), null);
			c.drawText(stackSize+"", (x+width-8) * MCTPO.pixelSize, (y+height) * MCTPO.pixelSize, fontPaint);
		}
	}

}
