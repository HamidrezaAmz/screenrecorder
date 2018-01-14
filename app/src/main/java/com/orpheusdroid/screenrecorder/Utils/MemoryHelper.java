package com.orpheusdroid.screenrecorder.Utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Reza Amozadeh on 1/8/2018.
 */

public class MemoryHelper {

    private Context context;

    public MemoryHelper(Context context) {
        this.context = context;
    }

    /**
     * Internal
     */
    public boolean getHaveInternalMemory() {
        return true;
    }

    public String getInternalMemoryAddress() {
        File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        return directory.getPath();
    }

    public String getInternalMemoryFreeStorage() {
        String INTERNAL_PATH = getInternalMemoryAddress();
        String total;

        StatFs statFs = new StatFs(INTERNAL_PATH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
            total = bytesToHuman(((long) statFs.getAvailableBlocksLong() * (long) statFs.getBlockSizeLong()));
        else
            total = bytesToHuman(((long) statFs.getAvailableBlocks() * (long) statFs.getBlockSize()));

        return arabicToDecimal(total).replace("٫", ".");
    }

    public String getInternalMemoryTotalStorage() {
        String INTERNAL_PATH = getInternalMemoryAddress();
        String total;

        StatFs statFs = new StatFs(INTERNAL_PATH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
            total = bytesToHuman(((long) statFs.getBlockCountLong() * (long) statFs.getBlockSizeLong()));
        else
            total = bytesToHuman(((long) statFs.getBlockCount() * (long) statFs.getBlockSize()));

        return arabicToDecimal(total).replace("٫", ".");
    }

    public String getInternalMemoryUsedStorage() {
        String INTERNAL_PATH = getInternalMemoryAddress();
        long totalT, totalF;
        String totalU = "";

        // free & total
        StatFs statFs = new StatFs(INTERNAL_PATH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            totalF = ((long) statFs.getAvailableBlocksLong() * (long) statFs.getBlockSizeLong());
            totalT = ((long) statFs.getBlockCountLong() * (long) statFs.getBlockSizeLong());
        } else {
            totalF = ((long) statFs.getAvailableBlocks() * (long) statFs.getBlockSize());
            totalT = ((long) statFs.getBlockCount() * (long) statFs.getBlockSize());
        }

        totalU = bytesToHuman(totalT - totalF);
        return arabicToDecimal(totalU).replace("٫", ".");
    }

    /**
     * External
     */
    public boolean getHaveExternalMemory() {
        if (getExternalMemoryAddress().equals(""))
            return false;
        return true;
    }

    public String getExternalMemoryAddress() {
        String path = "";
        String[] memoryList = getStorageDirectories(context);
        if (memoryList.length > 0) {
            path = memoryList[0];
        }
        return path;
    }

    public String getExternalMemoryFreeStorage() {
        String ExTERNAL_PATH = getExternalMemoryAddress();
        String total;

        StatFs statFs = new StatFs(ExTERNAL_PATH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
            total = bytesToHuman(((long) statFs.getAvailableBlocksLong() * (long) statFs.getBlockSizeLong()));
        else
            total = bytesToHuman(((long) statFs.getAvailableBlocks() * (long) statFs.getBlockSize()));

        return arabicToDecimal(total).replace("٫", ".");
    }

    public String getExternalMemoryTotalStorage() {
        String ExTERNAL_PATH = getExternalMemoryAddress();
        String total;

        StatFs statFs = new StatFs(ExTERNAL_PATH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
            total = bytesToHuman(((long) statFs.getBlockCountLong() * (long) statFs.getBlockSizeLong()));
        else
            total = bytesToHuman(((long) statFs.getBlockCount() * (long) statFs.getBlockSize()));

        return arabicToDecimal(total).replace("٫", ".");
    }

    public String getExternalMemoryUsedStorage() {
        String ExTERNAL_PATH = getExternalMemoryAddress();
        long totalT, totalF;
        String totalU;

        StatFs statFs = new StatFs(ExTERNAL_PATH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            totalF = ((long) statFs.getAvailableBlocksLong() * (long) statFs.getBlockSizeLong());
            totalT = ((long) statFs.getBlockCountLong() * (long) statFs.getBlockSizeLong());

        } else {
            totalF = ((long) statFs.getAvailableBlocks() * (long) statFs.getBlockSize());
            totalT = ((long) statFs.getBlockCount() * (long) statFs.getBlockSize());
        }

        totalU = bytesToHuman(totalT - totalF);
        return arabicToDecimal(totalU).replace("٫", ".");
    }

    /**
     * Tools
     */
    public void createDirectory(String root, String path) throws FileNotFoundException {
        File file = new File(root + path);
        file.mkdirs();
    }

    public void deleteDirectoty(String root, String path) {
        File file = new File(root + path);
        file.delete();
    }

    /**
     * Helper methods
     */
    public static String[] getStorageDirectories(Context mContext) {
        String[] storageDirectories;
        String rawSecondaryStoragesStr = System.getenv("SECONDARY_STORAGE");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            List<String> results = new ArrayList<String>();
            File[] externalDirs = mContext.getExternalFilesDirs(null);
            for (File file : externalDirs) {
                if (file == null) continue;
                String path = file.getPath().split("/Android")[0];
                if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Environment.isExternalStorageRemovable(file))
                        || rawSecondaryStoragesStr != null && rawSecondaryStoragesStr.contains(path)) {
                    results.add(path);
                }
            }
            storageDirectories = results.toArray(new String[0]);
        } else {
            final Set<String> rv = new HashSet<String>();

            if (!TextUtils.isEmpty(rawSecondaryStoragesStr)) {
                final String[] rawSecondaryStorages = rawSecondaryStoragesStr.split(File.pathSeparator);
                Collections.addAll(rv, rawSecondaryStorages);
            }
            storageDirectories = rv.toArray(new String[rv.size()]);
        }
        return storageDirectories;
    }

    public static String bytesToHuman(float size) {

        int msr = 1024;

        long Kb = 1 * msr;
        long Mb = Kb * msr;
        long Gb = Mb * msr;
        long Tb = Gb * msr;
        long Pb = Tb * msr;
        long Eb = Pb * msr;

        if (size < Kb) return floatForm(size) + " byte";
        if (size >= Kb && size < Mb) return floatForm((double) size / Kb) + " KB";
        if (size >= Mb && size < Gb) return floatForm((double) size / Mb) + " MB";
        if (size >= Gb && size < Tb) return floatForm((double) size / Gb) + " GB";
        if (size >= Tb && size < Pb) return floatForm((double) size / Tb) + " TB";
        if (size >= Pb && size < Eb) return floatForm((double) size / Pb) + " PB";
        if (size >= Eb) return floatForm((double) size / Eb) + " EB";

        return "???";
    }

    public static String floatForm(double d) {
        return new DecimalFormat("#.##").format(d);
    }

    public static String arabicToDecimal(String number) {
        char[] chars = new char[number.length()];
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }

        return new String(chars);
    }

}
