package com.atlan1.mctpo.mobile;

import com.atlan1.mctpo.mobile.Inventory.Slot;

import android.content.Context; 
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.*;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {
	
	private GameThread gThread;
	
	int mode = 0;
	int rmode = 0;
	
	Paint p = new Paint();
	
	int frames;
	int fps;
	long countTime = System.nanoTime();
	
	int pointerBuildId = -1;
	int pointerFingerId = -1;
	
	public MainGamePanel(Context context) {
		super(context);
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);
		
		gThread = new GameThread(getHolder(), this);
		
		setFocusable(true);
		
		p.setARGB(255, 255, 255, 255);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Canvas canvas = null;
		
		try {
			
			// try locking the canvas for exclusive pixel editing on the surface
			canvas = getHolder().lockCanvas();
			
			canvas.drawARGB(255, 0, 0, 0);
			
			canvas.drawText("Loading...", 30, 60, p);
			
			synchronized (this) {
				// update game state 
				// draws the canvas on the panel 
				onDraw(canvas);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// in case of an exception the surface is not left in 
			// an inconsistent state
			if (canvas != null) {
				getHolder().unlockCanvasAndPost(canvas);
			}
		}
		
		
		if (gThread.getState() == Thread.State.TERMINATED) {
            gThread = new GameThread(getHolder(), this);
            gThread.setRunning(true);
            gThread.start();
        }
        else {
            gThread.setRunning(true);
            gThread.start();
        }

		
		/*gThread.setRunning(true);
		Log.d("alive", String.valueOf(gThread.isAlive()));
		if (!gThread.isAlive()) {
			gThread.start();
		}*/
		
	}

	@Override 
	public void surfaceDestroyed(SurfaceHolder holder) {
		gThread.setRunning(false);
		boolean retry = true;
		while (retry) {
			try {
				gThread.join(); 
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			} 
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
			synchronized (this) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					boolean handled = false;
					float eventX = event.getX();
					float eventY = event.getY();
					Slot[] slots = MCTPO.character.inventory.slots;
					for (int i = 0; i < slots.length; i++) {
						if (slots[i].contains(eventX, eventY)) {
							MCTPO.character.inventory.selected = i;
							handled = true;
							break;
						}
					}
					if (!handled) {	
						if (Math.sqrt(Math.pow(this.getWidth() / 2 - eventX, 2) + Math.pow(this.getHeight() / 2 - eventY, 2)) > 60) {
							MCTPO.fingerDownP = new com.atlan1.mctpo.mobile.Graphics.Point((double)event.getX(), (double)event.getY());
							MCTPO.fingerP = MCTPO.fingerDownP;
							MCTPO.lastFingerP = MCTPO.fingerP;
							MCTPO.fingerDown = true;
						} else {
							MCTPO.fingerBuildDownP = new com.atlan1.mctpo.mobile.Graphics.Point((double)event.getX(), (double)event.getY());
							MCTPO.fingerBuildP = MCTPO.fingerDownP;
							MCTPO.fingerBuildDown = true;
							MCTPO.fingerBuildMoved = false;
						}
					}
					break;
				case MotionEvent.ACTION_UP:
					MCTPO.fingerDown = false;
					MCTPO.fingerBuildDown = false;
					break;
				case MotionEvent.ACTION_MOVE:
					if (MCTPO.fingerDown) {
						MCTPO.lastFingerP = MCTPO.fingerP;
						MCTPO.fingerP = new com.atlan1.mctpo.mobile.Graphics.Point((double)event.getX(), (double)event.getY());
					} else if (MCTPO.fingerBuildDown) {
						MCTPO.fingerBuildP = new com.atlan1.mctpo.mobile.Graphics.Point((double)event.getX(), (double)event.getY());
						MCTPO.fingerBuildMoved = true;
					}

				}
				/*try {
					//this.wait(1000L);
					Thread.sleep(15);
				} catch (InterruptedException e) {
				}*/
			}
			return true;
	}

	@Override 
	protected void onDraw(Canvas canvas) {
		
		canvas.drawText("fps: " + fps, 30, 30, p);
		
		frames ++;
		if (System.nanoTime() - countTime > 1000000000) {
			fps = frames;
			frames = 0;
			countTime = System.nanoTime();
			Log.d("fps", "fps: " + fps);
		}
	} 

}
