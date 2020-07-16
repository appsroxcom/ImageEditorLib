package image.editor.android;

import android.content.Context;

import java.io.File;

public class FileUtil {

    public static File getStorageDirectory(Context context) {
        //return Environment.getExternalStorageDirectory();
        return context.getExternalCacheDir();
    }
}
