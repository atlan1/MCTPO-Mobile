package com.atlan1.mctpo.mobile.Physics.BlockPhysics;

import com.atlan1.mctpo.mobile.Block;

public class SpreadPhysics extends AbstractBlockPhysics {

	private int tick = 0;
	
	public SpreadPhysics(int t) {
		this.tick = t;
	}
	
	public boolean spread(Block b){
		return false;
	}

	@Override
	public boolean performPhysics(Block b) {
		return spread(b);
	}

}
