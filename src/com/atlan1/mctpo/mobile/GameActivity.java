package com.atlan1.mctpo.mobile;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.atlan1.mctpo.mobile.Texture.BitmapHelper;

import android.app.*;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.*;
import android.view.*;
import android.widget.Toast;

public class GameActivity extends Activity {
	
    MainGamePanel panel;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		
		panel = new MainGamePanel(this);
		
	    setContentView(panel);
    }
	
	
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			Bitmap  bitmap = Bitmap.createBitmap( panel.getWidth(), panel.getHeight(), Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			MCTPO.mctpo.render(canvas);
			int i = 1;
			String name = "screenshot" + i + ".jpg";
			File dir = new File(Environment.getExternalStorageDirectory() + File.separator + "Screenshots");
			if (!dir.exists()) {
				dir.mkdir();
			}
			String[] fileArray = dir.list();
			if (fileArray != null && fileArray.length != 0) {
				List<String> dirList = Arrays.asList(fileArray);
				while (dirList.contains(name)) {
					i++;
					name = "screenshot" + i + ".jpg";
				}
			}
			BitmapHelper.saveBitmapToSdcard(bitmap, "Screenshots" + File.separator + name);
			
			/*for (int j = 0; j <= 40; j++) {
				BitmapHelper.saveBitmapToSdcard(Material.terrain.getSubImageById(j), "Screenshots/terrain" + j + ".jpg");
			}*/
			
			
			Toast.makeText(this, "Screenshot saved to: " + "Screenshots/" + name, Toast.LENGTH_LONG).show();
			
			return true;
		}
		return super.onKeyUp(keyCode, event);

	}
	
	/*@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}
	*/

}
