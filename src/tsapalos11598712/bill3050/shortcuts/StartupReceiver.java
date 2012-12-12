package tsapalos11598712.bill3050.shortcuts;


import java.io.IOException;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.widget.Toast;

//TODO I have to rewrite better this class. 
//This will be re-written with the MethodsApplication
public class StartupReceiver extends BroadcastReceiver {

	Context ctx;
	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context ctx, Intent intent) {
		// TODO Auto-generated method stub
		this.ctx=ctx;
		/*Instead of starting the apps i decided to start the 
		  the main activity. Also by starting the main activity I start
		  the service for notifications too.
		 */
		//startChatApps("Να ξεκινήσω τις chat apps?");
		
		Intent activity=new Intent(ctx, CustomShortcutsActivity.class);
		activity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		ctx.startActivity(activity);
		
		//show the notification
		new MyNotification(ctx);
		
	}
	
	/*public void startChatApps(String msg) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
    	builder.setMessage(msg)
    	       .setPositiveButton("Ναι ρε!", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   	//I don't know if this is good
	    	       		//I should try it at the future!!!
	    	       		//new MethodsApplication().startChatApps();
	    	       		
	    	       		//start viber
	    	           	Intent viber = new Intent(Intent.ACTION_MAIN);
	    	           	viber.setClassName("com.viber.voip", "com.viber.voip.IdleActivity"); 
	    	           	viber.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    	           	ctx.startActivity(viber);
	    	           	
	    	           	//start msn
	    	           	Intent ebuddy = new Intent(Intent.ACTION_MAIN);
	    	           	ebuddy.setClassName("com.ebuddy.android", "com.ebuddy.android.ui.StartupActivity"); 
	    	           	ebuddy.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    	           	ctx.startActivity(ebuddy);
	    	           	
	    	           	//start skype    	
	    	       		Intent skype = new Intent(Intent.ACTION_MAIN);
	    	           	skype.setClassName("com.skype.raider", "com.skype.raider.Main"); 
	    	           	skype.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    	           	ctx.startActivity(skype);
	    	           	
	    	           	//start settings
	    	           	Intent settings = new Intent(Intent.ACTION_MAIN);
	    	       		settings.setClassName("com.android.settings", 
	    	           			"com.android.settings.Settings"); 
	    	       		settings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    	       		ctx.startActivity(settings);
    	           }
    	       })
    	       .setNegativeButton("Μπάα!", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   dialog.cancel();
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	alert.setCancelable(true);
    	alert.setCanceledOnTouchOutside(true);
    	alert.show();
    }*/
}
