package com.atlan1.mctpo.mobile;

import android.graphics.Canvas;

public class Sky {

	public int frame=0, time=12000;
	private int dayR=60, dayG=110, dayB=255;
	private int nightR=50, nightG=40, nightB=145;
	private int nowR, nowG, nowB;
	private boolean isDay = true;
	
	public Sky(){
		nowR=dayR; nowG=dayG; nowB=dayB;
	}
	
	public void tick(){
		if(frame>time){
			if(isDay){
				isDay=false;
			}else{
				isDay=true;
			}
			frame=0;
		}else{
			frame++;
		}
		if(isDay){
			if(!(nowR==nightR&&nowG==nightG&&nowB==nightB)){
				if(nowR<nightR)
					nowR++;
				else
					nowR--;
				if(nowG<nightG)
					nowG++;
				else
					nowG--;
				if(nowB<nightB)
					nowB++;
				else
					nowB--;
			}
		}else{
			if(!(nowR==dayR&&nowG==dayG&&nowB==dayB)){
				if(nowR<dayR)
					nowR++;
				else
					nowR--;
				if(nowG<dayG)
					nowG++;
				else
					nowG--;
				if(nowB<dayB)
					nowB++;
				else
					nowB--;
			}
		}
	}
	
	public void render(Canvas c) {
		c.drawARGB(255, nowR, nowG, nowB);
	}
}
