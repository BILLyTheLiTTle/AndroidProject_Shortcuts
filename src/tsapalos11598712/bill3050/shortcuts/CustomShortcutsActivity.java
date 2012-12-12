package tsapalos11598712.bill3050.shortcuts;

import java.io.DataOutputStream;
import java.io.IOException;

import com.google.plus.bt11598712305047159305.R;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

public class CustomShortcutsActivity extends Activity {
	
	//private final String TAG="CustomShortcutActivity";
	
	AudioManager mAudioManager;
	//F%#*ing declarations
	private Context customCtx;
	private MethodsApplication mApp;
	
	@SuppressWarnings("deprecation")
	KeyguardLock mLock;
	
	private static final int SHUTDOWN_ACTION=0;
	private static final int REBOOT_ACTION=1;
	private static final int BOOTLOADER_ACTION=2;
	private static final int AFTER_WORK=3;
	private static final int FOR_NIGHT=4;
	private int requestedAction=-1;
	
    /** Called when the activity is first created. */
    @SuppressWarnings("deprecation")
	/* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //registerReceiver(new StartupReceiver(),   
        	    //new IntentFilter(Intent.ACTION_BOOT_COMPLETED));
        customCtx=getApplicationContext();
        setContentView(R.layout.main);
        mApp=(MethodsApplication)this.getApplication();
        
        mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        
        KeyguardManager mKeyGuardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
		mLock = mKeyGuardManager.newKeyguardLock("this.class");
    }
    
    /**
     * This method is executed automatically when 
     * the "Restart" button pressed
     * @param view
     */
    public void restart(View view){
    	//PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
    	//pm.reboot("xxx");
    	showDialog(REBOOT_ACTION, "Να κάνω reboot?");
    }
    
    /**
     * This method is executed automatically when 
     * the "Shutdown" button pressed
     * @param view
     */
    public void shutdown(View view){
    	showDialog(SHUTDOWN_ACTION, "Να κάνω shutdown?");
    }
    
    /**
     * This method is executed automatically when 
     * the "Silent/Vibrate" button pressed
     * @param view
     */
    public void silentNvibrate(View view){
    	if(mAudioManager.getRingerMode()==AudioManager.RINGER_MODE_VIBRATE)
    		mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    	else
    		mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    	finish();
    }
    
    /**
     * This method is executed automatically when 
     * the "Loud" button pressed
     * @param view
     */
    public void loud(View view){
    	mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    	finish();
    }
    
    /**
     * This method is executed automatically when 
     * the "Bootloader" button pressed
     * @param view
     */
    public void bootloader(View view){
    	showDialog(BOOTLOADER_ACTION, "Να κάνω reboot στον bootloader");
    }
    
    /**
     * Starts the "Development" activity from the 
     * "Development.apk" application.
     * @param view
     */
    public void development(View view){
    	Process p;
		try {
			p = Runtime.getRuntime().exec(new String[]{"su", "-c", "system/bin/sh"});
			DataOutputStream sh = new DataOutputStream(p.getOutputStream());
	    	//from here all commands are executed with su permissions
	    	sh.writeBytes("am start -n com.android.development/.Development\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finish();
    }
    
    /**
     * This method is executed automatically when 
     * the "Start Chat Apps" button pressed
     * @param view
     */
    public void startChatApps(View view){    	
    	showDialog(AFTER_WORK, "Να βάλω φωνή?");
    	//finish();
    }
    
    /**
     * This method is executed automatically when 
     * the "Stop Chat Apps" button pressed
     * @param view
     */
    public void stopChatApps(View view){    	
    	showDialog(FOR_NIGHT, "Να κλείσω τη φωνή?");
    	//finish();
    }
    
    /**
     * This method is executed automatically when 
     * the "Battery" button pressed
     * @param view
     */
    public void showNotification(View view){
    	new MyNotification(this);
    	finish();
    }
    
    public void hideNotification(View view){
    	String ns = Context.NOTIFICATION_SERVICE;
    	NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
		mNotificationManager.cancel(548853);
		finish();
    }

    @SuppressWarnings("deprecation")
    public void enableLockscreen(View view){
    	mLock.reenableKeyguard();
		finish();
    }
    
    @SuppressWarnings("deprecation")
    public void disableLockscreen(View view){
		mLock.disableKeyguard();
		finish();
    }
    
    /**
     * Depending the action parameter it does the 
     * appropriate action and shows a dialog message
     * 
     * @param action The Action to do
     * @param msg The message to show
     */
    public void showDialog(int action, String msg) {
    	requestedAction=action;
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage(msg)
    	       .setPositiveButton("Ναι ρε!", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   if (requestedAction==SHUTDOWN_ACTION){
    	        		   try {
    	        			   Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", "reboot -p" });
    	        			   proc.waitFor();
    	        		   } 
    	        		   catch (IOException ioe){
    	        			   Toast.makeText(customCtx, "IOException: Είσαι σίγουρα root?", Toast.LENGTH_LONG).show();
    	        		   }
    	        		   catch (InterruptedException ie){
    	        			   Toast.makeText(customCtx, "InterruptedException: Είσαι σίγουρα root?", Toast.LENGTH_LONG).show();
    	        		   }
    	        	   }        	   	
    	        	   else if (requestedAction==REBOOT_ACTION){
	        	   			try {
	        	   				Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", "reboot" });
	        	   				proc.waitFor();
	        	   			} 
		        	    	catch (IOException ioe){
		        	    		Toast.makeText(customCtx, "IOException: Είσαι σίγουρα root?", Toast.LENGTH_LONG).show();
		        	        }
		        	    	catch (InterruptedException ie){
		        	    		Toast.makeText(customCtx, "InterruptedException: Είσαι σίγουρα root?", Toast.LENGTH_LONG).show();
		        	    	}
    	        	   }    	        	   	
    	        	   else if (requestedAction==BOOTLOADER_ACTION){
	        	   			try {
	        	   				Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", "reboot bootloader" });
    	        	            proc.waitFor();
    	        	        } 
    	        	    	catch (IOException ioe){
    	        	    		Toast.makeText(customCtx, "IOException: Είσαι σίγουρα root?", Toast.LENGTH_LONG).show();
    	        	        }
    	        	    	catch (InterruptedException ie){
    	        	    		Toast.makeText(customCtx, "InterruptedException: Είσαι σίγουρα root?", Toast.LENGTH_LONG).show();
    	        	    	}
    	        	   }
    	        	   else if (requestedAction==AFTER_WORK){
	        	   			mApp.startChatApps();
	        	   			mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	        	   			finish();
    	        	   }
    	        	   else if (requestedAction==FOR_NIGHT){
	        	   			mApp.stopChatApps();
	        	   			mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	        	   			finish();
    	        	   }
    	        	   else{
    	        	   		dialog.cancel();    	        	   	
    	        	   }
    	           }
    	       })
    	       .setNegativeButton("Μπάα!", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   if (requestedAction==AFTER_WORK){
    	        		   mApp.startChatApps();
    	   				   finish();
	        	   	   }
	   	        	   else if (requestedAction==FOR_NIGHT){
	   	        		   mApp.stopChatApps();
	   	        		   finish();
	   	        	   }
	   	        	   else{
	   	        	   		dialog.cancel();    	        	   	
	   	        	   }
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	alert.setCancelable(true);
    	alert.setCanceledOnTouchOutside(true);
    	alert.show();
    }
    
    /* (non-Javadoc)
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy(){
    	super.onDestroy();
    }
}