/*
 * Copyright (C) 2023 Paranoid Android
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.xiaomi.settings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.IBinder;
import android.os.UserHandle;
import android.util.Log;
import android.view.Display;
import android.view.Display.HdrCapabilities;

import com.xiaomi.settings.display.ColorModeService;
import com.xiaomi.settings.refreshrate.RefreshUtils;
import com.xiaomi.settings.touchsampling.TouchSamplingUtils;
import com.xiaomi.settings.touchsampling.TouchSamplingService;
import com.xiaomi.settings.touchsampling.TouchSamplingTileService;

public class BootCompletedReceiver extends BroadcastReceiver {
    private static final String TAG = "XiaomiParts";
    private static final boolean DEBUG = true;

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (!intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            return;
        }
        if (DEBUG)
            Log.d(TAG, "Received boot completed intent");

        // Display
        context.startServiceAsUser(new Intent(context, ColorModeService.class),
                UserHandle.CURRENT);

        // High Touch polling rate
        TouchSamplingUtils.restoreSamplingValue(context);
        context.startServiceAsUser(new Intent(context, TouchSamplingService.class),
                UserHandle.CURRENT);

        // Refreshrate
        RefreshUtils.startService(context);

        // Touch Sampling Tile Service
        context.startServiceAsUser(new Intent(context, TouchSamplingTileService.class),
                UserHandle.CURRENT);

        // Override HDR types to enable Dolby Vision
        final DisplayManager displayManager = context.getSystemService(DisplayManager.class);
        displayManager.overrideHdrTypes(Display.DEFAULT_DISPLAY,
                new int[] {HdrCapabilities.HDR_TYPE_DOLBY_VISION, HdrCapabilities.HDR_TYPE_HDR10,
                        HdrCapabilities.HDR_TYPE_HLG, HdrCapabilities.HDR_TYPE_HDR10_PLUS});

    }
}
