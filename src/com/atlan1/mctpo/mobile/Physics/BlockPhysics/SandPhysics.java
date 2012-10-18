package com.atlan1.mctpo.mobile.Physics.BlockPhysics;

import com.atlan1.mctpo.mobile.Block;
import com.atlan1.mctpo.mobile.Thing;

public class SandPhysics implements BlockPhysics {

	private AbstractBlockPhysics abs;

	public SandPhysics() {
		abs = new FallPhysics(10);
	}

	@Override
	public boolean performPhysics(Block b) {
		return abs.performPhysics(b);
	}

	@Override
	public boolean performCollision(Block b, Thing ent) {
		return false;
	}
}
