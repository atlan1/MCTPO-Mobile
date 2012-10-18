package com.atlan1.mctpo.mobile.Inventory;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import com.atlan1.mctpo.mobile.Character;
import com.atlan1.mctpo.mobile.MCTPO;
import com.atlan1.mctpo.mobile.Material;
import com.atlan1.mctpo.mobile.Graphics.Rectangle;

public class Inventory {
	
	public int invLength = 8;
	public int slotSize = 25;
	public int slotSpace = 5;
	public int borderSpace = 20;
	public int itemBorder = 3;
	public int maxStackSize = 64;
	
	public Slot[] slots = new Slot[invLength];
	public int selected=0;
	
	public Inventory(Character c){
		int x;
		int y;
		for(int i=0;i<slots.length;i++){
			x = (MCTPO.pixel.width/2)-((invLength * (slotSize + slotSpace))/2)+((i * (slotSize + slotSpace)));
			y = MCTPO.pixel.height - (slotSize + borderSpace);
			slots[i] = new Slot(this, new Rectangle(x, y, slotSize, slotSize), Material.AIR);
		}
	}
	
	public void render(Canvas c){
		for(int i=0;i<slots.length;i++){
			slots[i].render(c, i==selected);
		}
	}

	public void tick() {
		
	}
	
	public boolean containsMaterial(Material m){
		for(Slot s : slots){
			if(m==s.material) return true;
		}
		return false;
	}
	
	public Slot getSlot(Material m){
		for(Slot s : slots){
			if(m==s.material) return s;
		}
		return null;
	}
	
	public Slot[] getSlotsContaining(Material m){
		List<Slot> slots2 = new ArrayList<Slot>();
		for(int i=0;i<slots.length;i++){
			if(slots[i].material == m)
				slots2.add(slots[i]);
		}
		return slots2.toArray(new Slot[slots2.size()]);
	}
	
	public void clear(){
		for(Slot s : slots){
			s.material = Material.AIR;
			s.stackSize = 0;
		}
			
	}
}
