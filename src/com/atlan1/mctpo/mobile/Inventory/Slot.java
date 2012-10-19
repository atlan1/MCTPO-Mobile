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
		/*Log.d("slotx", String.valueOf((x)));
		Log.d("sloty", String.valueOf((y)));*/
		c.drawBitmap(slotNormal, null, new Rect((int) (x), (int) (y), (int) ((x + width)), (int) ((y + height))), Inventory.transparentPaint);
		if(selected){
			c.drawBitmap(slotSelected, null, new Rect((int) ((x-1)), (int) ((y-1)), (int) ((x + width+2)), (int) ((y + height+2))), Inventory.transparentPaint);
		}
		if(stackSize>0&&material!=Material.AIR){
			c.drawBitmap(Material.terrain.getSubImageById(material.id), null, new Rect((int)((x+inv.itemBorder)), (int)((y+inv.itemBorder)), (int)((x + width-inv.itemBorder)), (int)((y + height-inv.itemBorder))), Inventory.transparentPaint);
			c.drawText(stackSize+"", (x+width-8), (y+height), fontPaint);
		}
	}

}
