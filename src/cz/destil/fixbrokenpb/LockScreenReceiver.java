/*
 * Copyright (C) 2010 Haowen Ning
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */
package cz.destil.fixbrokenpb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class LockScreenReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			Log.v("FBPB", "Screen OFF");
		} else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
			Log.v("FBPB", "Screen ON");

			SharedPreferences settings = PreferenceManager
			        .getDefaultSharedPreferences(context);
			if (settings.getBoolean("enable_unlock_screen", true)) {
				Intent myIntent = new Intent(context, UnlockService.class);
				myIntent.setAction("anyunlock_lockscreen_intent");
				context.startService(myIntent);
			}
		}
	}
}
