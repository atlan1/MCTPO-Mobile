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
	
	public static float inventoryPixelSize = 1.5f;
	
	public Slot[] slots = new Slot[invLength];
	public int selected=0;
	
	public Inventory(Character c){
		for(int i=0;i<slots.length;i++){
			slots[i] = new Slot(this, new Rectangle((int) ((MCTPO.size.width/2) + (-((invLength * (slotSize + slotSpace))/2)+((i * (slotSize + slotSpace)))) * inventoryPixelSize) , (int) (MCTPO.size.height - (slotSize * inventoryPixelSize + borderSpace)), (int) (slotSize * inventoryPixelSize), (int) (slotSize * inventoryPixelSize)), Material.AIR);
		}
	}
	
	public Inventory(Inventory inventory) {
		for(int i=0;i<slots.length;i++){
			slots[i] = new Slot(this, new Rectangle((int) ((MCTPO.size.width/2) + (-((invLength * (slotSize + slotSpace))/2)+((i * (slotSize + slotSpace)))) * inventoryPixelSize) , (int) (MCTPO.size.height - (slotSize * inventoryPixelSize + borderSpace)), (int) (slotSize * inventoryPixelSize), (int) (slotSize * inventoryPixelSize)), inventory.slots[i].material);
			slots[i].stackSize = inventory.slots[i].stackSize;
		}
		selected = inventory.selected;
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
