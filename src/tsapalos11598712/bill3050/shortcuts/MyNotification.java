/**
 * 
 */
package tsapalos11598712.bill3050.shortcuts;

import com.google.plus.bt11598712305047159305.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

/**
 * @author little
 *
 */
public class MyNotification extends Notification {
	
	private Context ctx;
	private NotificationManager mNotificationManager;
	
	public MyNotification(Context ctx){
		super();
		this.ctx=ctx;
		String ns = Context.NOTIFICATION_SERVICE;
		mNotificationManager = (NotificationManager) ctx.getSystemService(ns);
		CharSequence tickerText = "Shortcuts";
		long when = System.currentTimeMillis();
		Notification.Builder builder = new Notification.Builder(ctx);
		Notification notification=builder.getNotification();
		notification.when=when;
		notification.tickerText=tickerText;
		notification.icon=R.drawable.ic_launcher;
		
		RemoteViews contentView=new RemoteViews(ctx.getPackageName(), R.layout.notification_layout);
		
		//set the button listeners
		setListeners(contentView);
		
		notification.contentView = contentView;
		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		CharSequence contentTitle = "From Shortcuts";
		mNotificationManager.notify(548853, notification);
	}
	
	public void setListeners(RemoteViews view){
		//radio listener
		Intent radio=new Intent(ctx, tsapalos11598712.bill3050.shortcuts.helper.HelperActivity.class);
		radio.putExtra("DO", "radio");
		PendingIntent pRadio = PendingIntent.getActivity(ctx, 0, radio, 0);
		view.setOnClickPendingIntent(R.id.radio, pRadio);
		
		//volume listener
		Intent volume=new Intent(ctx, tsapalos11598712.bill3050.shortcuts.helper.HelperActivity.class);
		volume.putExtra("DO", "volume");
		PendingIntent pVolume = PendingIntent.getActivity(ctx, 1, volume, 0);
		view.setOnClickPendingIntent(R.id.volume, pVolume);
		
		//reboot listener
		Intent reboot=new Intent(ctx, tsapalos11598712.bill3050.shortcuts.helper.HelperActivity.class);
		reboot.putExtra("DO", "reboot");
		PendingIntent pReboot = PendingIntent.getActivity(ctx, 5, reboot, 0);
		view.setOnClickPendingIntent(R.id.reboot, pReboot);
		
		//top listener
		Intent top=new Intent(ctx, tsapalos11598712.bill3050.shortcuts.helper.HelperActivity.class);
		top.putExtra("DO", "top");
		PendingIntent pTop = PendingIntent.getActivity(ctx, 3, top, 0);
		view.setOnClickPendingIntent(R.id.top, pTop);
		
		//app listener
		Intent app=new Intent(ctx, tsapalos11598712.bill3050.shortcuts.helper.HelperActivity.class);
		app.putExtra("DO", "app");
		PendingIntent pApp = PendingIntent.getActivity(ctx, 4, app, 0);
		view.setOnClickPendingIntent(R.id.app, pApp);
	}

}
