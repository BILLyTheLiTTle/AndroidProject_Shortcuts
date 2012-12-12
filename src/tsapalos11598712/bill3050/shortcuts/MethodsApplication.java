package tsapalos11598712.bill3050.shortcuts;

import android.app.Application;
import android.content.Intent;

//TODO This thing maybe sucks! I have to improve it.
//This will be re-written with the StartupReceiver
public class MethodsApplication extends Application {
	
	/* (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 */
	public void onCreate(){
		super.onCreate();
	}
	
	/**
	 * This constructor is used to instantiate an object 
	 * of this class. This should not be used.
	 */
	public MethodsApplication(){
		onCreate();
	}

	/**
	 * Starts settings and chat applications.
	 */
	public void startChatApps(){
		//start viber
    	Intent viber = new Intent(Intent.ACTION_MAIN);
    	viber.setClassName("com.viber.voip", "com.viber.voip.IdleActivity"); 
    	viber.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(viber);
    	
    	//start msn
    	Intent ebuddy = new Intent(Intent.ACTION_MAIN);
    	ebuddy.setClassName("com.ebuddy.android", "com.ebuddy.android.ui.StartupActivity"); 
    	ebuddy.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(ebuddy);
    	
    	//start skype    	
		Intent skype = new Intent(Intent.ACTION_MAIN);
    	skype.setClassName("com.skype.raider", "com.skype.raider.Main"); 
    	skype.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(skype);
    	
    	//start settings
    	Intent settings = new Intent(Intent.ACTION_MAIN);
		settings.setClassName("com.android.settings", 
    			"com.android.settings.Settings"); 
		settings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(settings);
	}
	
	/**
	 * Stop settings and chat applications.
	 */
	public void stopChatApps(){
		//start settings
    	Intent settings = new Intent(Intent.ACTION_MAIN);
		settings.setClassName("com.android.settings", 
    			"com.android.settings.Settings"); 
		settings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(settings);
    	
    	//start msn
    	Intent ebuddy = new Intent(Intent.ACTION_MAIN);
    	ebuddy.setClassName("com.ebuddy.android", "com.ebuddy.android.ui.StartupActivity"); 
    	ebuddy.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(ebuddy);
    	
    	//start skype
    	Intent skype = new Intent(Intent.ACTION_MAIN);
    	skype.setClassName("com.skype.raider", "com.skype.raider.Main"); 
    	skype.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(skype);
    	
    	//start viber
    	Intent viber = new Intent(Intent.ACTION_MAIN);
    	viber.setClassName("com.viber.voip", "com.viber.voip.IdleActivity"); 
    	viber.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(viber);
	}
}
