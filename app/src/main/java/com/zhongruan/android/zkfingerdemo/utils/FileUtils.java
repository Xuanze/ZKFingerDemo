package com.zhongruan.android.zkfingerdemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import net.lingala.zip4j.util.InternalZipConstants;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import java.util.List;

import rx.android.BuildConfig;

public class FileUtils {
    private static final String DST_FOLDER_NAME = "HNZR";
    private static File newFile;

    public static byte[] getBytes(String filePath) {
        if (filePath == null) {
            return null;
        }
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            while (true) {
                int n = fis.read(b);
                if (n != -1) {
                    bos.write(b, 0, n);
                } else {
                    fis.close();
                    bos.close();
                    return bos.toByteArray();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap ChangePathToBitmap(String path) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return (Bitmap) new WeakReference(BitmapFactory.decodeFile(path, opts)).get();
    }

    /**
     * 保存Bitmap到sdcard
     *
     * @param b
     */
    public static void saveBitmap(Bitmap b, String filePath, String fileName) {

        String path = getSDCardPath() + "/" + DST_FOLDER_NAME + "/" + filePath;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String jpegName = path + "/" + fileName + ".jpg";
        LogUtil.i("saveBitmap:jpegName = " + jpegName);
        try {
            FileOutputStream fout = new FileOutputStream(jpegName);
            BufferedOutputStream bos = new BufferedOutputStream(fout);
            b.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            LogUtil.i("saveBitmap成功");
        } catch (IOException e) {
            LogUtil.i("saveBitmap:失败");
            e.printStackTrace();
        }
    }

    public static String getSDCardPath() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

    public static String getAppSavePath() {
        if (getSDCardPath() != null) {
            return getSDCardPath() + "/" + DST_FOLDER_NAME;
        }
        return null;
    }

    public static boolean copyFile(List<String> list) {
        if (list != null) {
            if (list.size() >= 4) {
                String str0 = list.get(0);
                String str1 = list.get(1);

                if (delFolder("DataTemp") && delFolder("bk_ksxp")) {
                    String path = FileUtils.getAppSavePath() + InternalZipConstants.ZIP_FILE_SEPARATOR + "DataTemp";
                    File file = new File(path);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    if (!TextUtils.isEmpty(str0)) {
                        FileUtils.copyFile(str0, path + str0.substring(str0.lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR)));
                    }
                    if (!TextUtils.isEmpty(str1)) {
                        FileUtils.copyFile(str1, path + str1.substring(str1.lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR)));
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean newFolder(String folderPath) {
        try {
            File myFilePath = new File(folderPath);
            if (!(myFilePath.exists() || myFilePath.mkdir())) {
                myFilePath.mkdirs();
            }
            return true;
        } catch (Exception e) {
            LogUtil.i("新建目录操作出错 : ", folderPath + " |");
            e.printStackTrace();
            return false;
        }
    }


    public static void renameFile(String file) {
        File toBeRenamed = new File(file);
        //检查要重命名的文件是否存在，是否是文件
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {
            LogUtil.i("File does not exist: " + file);
            return;
        }
        String newFiles = StringUtils.substringAfterLast(file, "/");
        String newPath = StringUtils.substringBeforeLast(file, "/");
        if (StringUtils.substringAfterLast(newFiles, ".").equals("jpg")) {
            LogUtil.i(StringUtils.substringBeforeLast(newFiles, "."));
            File files = new File(getAppSavePath() + "/bk_ksxp/");
            if (!files.exists()) {
                files.mkdirs();
            }
            newFile = new File(files.getPath() + "/" + StringUtils.substringAfterLast(file, "\\"));
        } else if (StringUtils.substringAfterLast(newFiles, ".").equals("txt")) {
            newFile = new File(newPath + "/" + StringUtils.substringAfterLast(file, "\\"));
        }
        //修改文件名
        if (toBeRenamed.renameTo(newFile)) {
            LogUtil.i("File has been renamed.");
        } else {
            LogUtil.i("Error renmaing file");
        }
    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    public static boolean copyFolder(String oldPath, String newPath) {
        try {
            if (new File(oldPath).exists()) {
                new File(newPath).mkdirs();
                String[] file = new File(oldPath).list();
                for (int i = 0; i < file.length; i++) {
                    File temp;
                    if (oldPath.endsWith(File.separator)) {
                        temp = new File(oldPath + file[i]);
                    } else {
                        temp = new File(oldPath + File.separator + file[i]);
                    }
                    if (temp.isFile()) {
                        FileInputStream input = new FileInputStream(temp);
                        FileOutputStream output = new FileOutputStream(newPath + InternalZipConstants.ZIP_FILE_SEPARATOR + temp.getName().toString());
                        byte[] b = new byte[5120];
                        while (true) {
                            int len = input.read(b);
                            if (len == -1) {
                                break;
                            }
                            output.write(b, 0, len);
                        }
                        output.flush();
                        output.close();
                        input.close();
                    }
                    if (temp.isDirectory()) {
                        copyFolder(oldPath + InternalZipConstants.ZIP_FILE_SEPARATOR + file[i], newPath + InternalZipConstants.ZIP_FILE_SEPARATOR + file[i]);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错 :" + oldPath + " -->" + newPath);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copyFile(String oldPath, String newPath) {
        LogUtil.i("copyFile:oldPath", oldPath);
        LogUtil.i("copyFile:newPath", newPath);
        boolean flag = false;
        try {
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                oldfile.setReadable(true);
                oldfile.setWritable(true);
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while (true) {
                    int byteread = inStream.read(buffer);
                    if (byteread == -1) {
                        break;
                    }
                    fs.write(buffer, 0, byteread);
                }
                fs.flush();
                fs.close();
                inStream.close();
            }
            flag = true;
        } catch (Exception e) {
            LogUtil.i("复制单个文件操作出错:" + oldPath + " -->" + newPath);
            e.printStackTrace();
        }
        LogUtil.i("复制文件" + flag);
        return flag;
    }


    /**
     * 根据文件路径拷贝文件
     *
     * @param tempFolder 源文件
     */

    public static String copyFileToUSB(File tempFolder) {
        LogUtil.i("copyFileToUSB", tempFolder.getPath().toString());
        File[] files;
        String usbPath;

        if (tempFolder.length() == 0) {
            tempFolder.delete();
            return BuildConfig.VERSION_NAME;
        }
        try {
            int byteread;
            usbPath = Utils.getUSBPath() + tempFolder.getName();
            File newfile = new File(usbPath);
            try {
                if (!newfile.exists()) {
                    newfile.createNewFile();
                }
            } catch (Exception e) {
                files = new File(Utils.getUSBPath()).listFiles();
                if (files != null && files.length == 1 && files[0].isDirectory()) {
                    usbPath = files[0].getPath() + File.separator + tempFolder.getName();
                }
                newfile = new File(usbPath);
                if (!newfile.exists()) {
                    newfile.createNewFile();
                }
            }

            LogUtil.i("copyFileToUSB", newfile.getPath().toString());

            InputStream inStream = new FileInputStream(tempFolder);
            FileOutputStream fs = new FileOutputStream(newfile);
            byte[] buffer = new byte[1024];
            while (true) {
                byteread = inStream.read(buffer);
                if (byteread == -1) {
                    break;
                }
                fs.write(buffer, 0, byteread);
            }
            fs.getFD().sync();
            fs.close();
            inStream.close();
            if (newfile.isFile() && newfile.length() == 0) {
                return BuildConfig.VERSION_NAME;
            }
            return usbPath;
        } catch (Exception e32) {
            e32.printStackTrace();
            return BuildConfig.VERSION_NAME;
        }
    }


    // 将字符串写入到文本文件中
    public static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }

    // 生成文件
    public static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }


    /**
     * 删除单个文件
     *
     * @param filePath 被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    public static boolean delFolder(String folderPath) {
        boolean flag = false;
        try {
            File file = new File(getAppSavePath() + "/" + folderPath);
            if (!file.exists()) {
                return true;
            } else {
                delPathFile(file.getPath(), true);
                new File(folderPath).delete();
                flag = true;
            }

        } catch (Exception e) {
            System.out.println("删除文件夹操作出错:" + folderPath);
            e.printStackTrace();
        }
        LogUtil.i("Vince", "删除文件夹" + flag);
        return flag;
    }

    public static boolean delPathFile(String path, boolean allFolder) {
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        String[] tempList = file.list();
        int i = 0;
        while (i < tempList.length) {
            File temp;
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile() && !temp.delete()) {
                return false;
            }
            if (allFolder && temp.isDirectory() && (!delPathFile(path + InternalZipConstants.ZIP_FILE_SEPARATOR + tempList[i], allFolder) || !delFolder(path + InternalZipConstants.ZIP_FILE_SEPARATOR + tempList[i]))) {
                return false;
            }
            i++;
        }
        return true;
    }
}
