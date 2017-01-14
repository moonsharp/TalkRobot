/** Copyright © 2015-2020 100msh.com All Rights Reserved */
package kk.qisheng.talkrobot.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by KkQisheng on 14:17  2016/9/5.
 */
public class NetUtils {

	private NetUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 判断是否是wifi连接
	 */
	public static boolean isWifiConnect(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null) {
			return false;
		}

		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info == null) {
			return false;
		}

		return info.getType() == ConnectivityManager.TYPE_WIFI;

	}

	/**
	 * 打开网络设置界面
	 */
	public static void openSetting(Activity activity) {
		Intent intent = new Intent();
		intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
		intent.putExtra("extra_prefs_show_button_bar", true);
		intent.putExtra("wifi_enable_next_on_connect", true);
		activity.startActivityForResult(intent, 0);
	}

	/**
	 * 获取网络连接类型
	 * 
	 * @return
	 */
	public static String getNetType(Context context) {
		String type = "网络不可用";
		ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectMgr.getActiveNetworkInfo();
		if (info != null) {
			if (1 == info.getType()) {
				return "wifi";
			} else {
				int subType = info.getSubtype();
				if (subType == TelephonyManager.NETWORK_TYPE_CDMA || subType == TelephonyManager.NETWORK_TYPE_GPRS
						|| subType == TelephonyManager.NETWORK_TYPE_EDGE) {
					return type = "2g";
				} else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
						|| subType == TelephonyManager.NETWORK_TYPE_HSDPA
						|| subType == TelephonyManager.NETWORK_TYPE_EVDO_A
						|| subType == TelephonyManager.NETWORK_TYPE_EVDO_0
						|| subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
					return type = "3g";
				} else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {
					// LTE是3g到4g的过渡，是3.9G的全球标准
					return type = "4g";
				}
			}
		}
		return type;
	}

	/**
	 * 检测当的网络（WLAN、3G/2G）状态
	 * 
	 * @param context
	 *            Context
	 * @return true 表示网络可用
	 */
	public static boolean isNetAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null && info.isConnected()) {
				// 当前网络是连接的
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					// 当前所连接的网络可用
					return true;
				}
			}
		}
		return false;
	}

}
