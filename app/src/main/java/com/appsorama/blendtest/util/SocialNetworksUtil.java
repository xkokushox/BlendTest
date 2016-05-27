package com.appsorama.blendtest.util;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

/**
 * Created by Jose Torres on 4/20/16.
 */
public class SocialNetworksUtil {
    public static final String TAG = "SocialNetworksUtil";

    public static void openWebUrl(Activity mActivity, String sUrl) {
        try {
            if (sUrl.contains("www.youtube.com"))
                if (!openYoutubeVideo(mActivity, sUrl.replace("https", "http"))) {
                    Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                    urlIntent.setData(Uri.parse(sUrl));
                    mActivity.startActivity(urlIntent);
                }
        } catch (Exception e) {
            DebugUtils.logError(TAG, "OpenWebUrl:: " + e.getLocalizedMessage());
        }
    }

    public static boolean openYoutubeVideo(Activity mActivity, String sUrl) {
        String sVideoId = sUrl.replace("http://www.youtube.com/watch?v=", "");
        String urlVideo = "vnd.youtube://" + sVideoId;

        try {
            Intent pageIntent = new Intent(Intent.ACTION_VIEW);
            pageIntent.setData(Uri.parse(urlVideo));

            // If Youtube application is installed, use that else launch a browser
            final PackageManager packageManager = mActivity.getPackageManager();
            List<ResolveInfo> list = packageManager.queryIntentActivities(
                    pageIntent, PackageManager.MATCH_DEFAULT_ONLY);
            if (list.size() == 0) {
                final String urlBrowser = "https://www.youtube.com/watch?v=" + sVideoId;
                pageIntent.setData(Uri.parse(urlBrowser));
            }

            mActivity.startActivity(pageIntent);
            return true;
        } catch (Exception e) {
            DebugUtils.logError(TAG, "OpenYoutubeVideo:: " + e.getLocalizedMessage());
            return false;
        }
    }
}
