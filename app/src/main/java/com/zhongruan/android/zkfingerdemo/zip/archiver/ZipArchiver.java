package com.zhongruan.android.zkfingerdemo.zip.archiver;

import android.text.TextUtils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class ZipArchiver extends BaseArchiver {


    /**
     * 使用给定密码压缩指定文件或文件夹到指定位置.
     * <p>
     * dest可传最终压缩文件存放的绝对路径,也可以传存放目录,也可以传null或者"".<br />
     * 如果传null或者""则将压缩文件存放在当前目录,即跟源文件同目录,压缩文件名取源文件名,以.zip为后缀;<br />
     * 如果以路径分隔符(File.separator)结尾,则视为目录,压缩文件名取源文件名,以.zip为后缀,否则视为文件名.
     *
     * @param src         要压缩的文件或文件夹路径
     * @param dest        压缩文件存放路径
     * @param isCreateDir 是否在压缩文件里创建目录,仅在压缩文件为目录时有效.<br />
     *                    如果为false,将直接压缩目录下文件到压缩文件.
     * @param passwd      压缩使用的密码
     */
    @Override
    public void doArchiver(String src, String dest, boolean isCreateDir, String passwd, String FileType, final IArchiverListener listener) {
        ZipFile zipFile = null;
        int reuslt = 1;
        File srcFile = new File(src);
        dest = buildDestinationZipFilePath(srcFile, dest);
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);           // 压缩方式
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);    // 压缩级别

        if (passwd != null) {
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); // 加密方式
            parameters.setPassword(passwd.toCharArray());
        }
        try {

            if (listener != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onStartArchiver();
                    }
                });
            }

            zipFile = new ZipFile(dest);
            if (srcFile.isDirectory()) {
                // 如果不创建目录的话,将直接把给定目录下的文件压缩到压缩文件,即没有目录结构
                if (!isCreateDir) {
                    File[] subFiles = srcFile.listFiles();
                    ArrayList<File> temp = new ArrayList<>();
                    Collections.addAll(temp, subFiles);
                    zipFile.addFiles(temp, parameters);
                }
                zipFile.addFolder(srcFile, parameters);
            } else {
                zipFile.addFile(srcFile, parameters);
            }


            //一下 为 监听过程，不影响 实际压缩过程；
            ProgressMonitor progressMonitor = zipFile.getProgressMonitor();//开启压缩监听
            reuslt = progressMonitor.getResult();

            if (progressMonitor.getResult() == ProgressMonitor.RESULT_ERROR) {
                if (progressMonitor.getException() != null) {
                    progressMonitor.getException().printStackTrace();
                } else {
                    System.err.println("压缩时出现异常");
                }
            }
            if (listener != null && reuslt == 0) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onEndArchiver();
                    }
                });
            }
            System.out.println("---------------------------压缩结束-------------------------------");
            //如果 压缩 不成功 ，则 删除 该文件
            if (progressMonitor.getResult() != 0) {
                zipFile.getFile().delete();
            }
        } catch (ZipException e) {
            //异常状态下 删除 该zip文件
            zipFile.getFile().delete();
            e.printStackTrace();
        }
    }


    /**
     * 使用给定密码解压指定的ZIP压缩文件到指定目录
     *
     * @param srcfile   指定的ZIP压缩文件
     * @param unrarPath 解压目录
     * @param password  ZIP文件的密码
     * @param listener  解压进度更新
     */
    @Override
    public void doUnArchiver(String srcfile, String unrarPath, String password, final IArchiverListener listener) {
        if (TextUtils.isEmpty(srcfile) || TextUtils.isEmpty(unrarPath))
            return;
        File src = new File(srcfile);
        if (!src.exists())
            return;
        try {
            ZipFile zFile = new ZipFile(srcfile);
            zFile.setFileNameCharset("GBK");
            if (!zFile.isValidZipFile())
                throw new ZipException("文件不合法!");

            File destDir = new File(unrarPath);
            if (destDir.isDirectory() && !destDir.exists()) {
                destDir.mkdir();
            }

            if (zFile.isEncrypted()) {
                zFile.setPassword(password);
            }
            if (listener != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onStartArchiver();
                    }
                });
            }

            FileHeader fh = null;
            final int total = zFile.getFileHeaders().size();
            for (int i = 0; i < zFile.getFileHeaders().size(); i++) {
                fh = (FileHeader) zFile.getFileHeaders().get(i);
                zFile.extractFile(fh, unrarPath);
                if (listener != null) {
                    final int finalI = i;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onProgressArchiver(finalI + 1, total);
                        }
                    });
                }
            }
        } catch (ZipException e1) {
            e1.printStackTrace();
        }
        if (listener != null)
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    listener.onEndArchiver();
                }
            });
    }

    /**
     * 构建压缩文件存放路径,如果不存在将会创建
     * 传入的可能是文件名或者目录,也可能不传,此方法用以转换最终压缩文件的存放路径
     *
     * @param srcFile   源文件
     * @param destParam 压缩目标路径
     * @return 正确的压缩文件存放路径
     */
    private static String buildDestinationZipFilePath(File srcFile, String destParam) {
        if (destParam == null) {
            if (srcFile.isDirectory()) {
                destParam = srcFile.getParent() + File.separator + srcFile.getName() + ".zip";
            } else {
                String fileName = srcFile.getName().substring(0, srcFile.getName().lastIndexOf("."));
                destParam = srcFile.getParent() + File.separator + fileName + ".zip";
            }
        } else {
            createDestDirectoryIfNecessary(destParam);  // 在指定路径不存在的情况下将其创建出来
            if (destParam.endsWith(File.separator)) {
                String fileName = "";
                if (srcFile.isDirectory()) {
                    fileName = srcFile.getName();
                } else {
                    fileName = srcFile.getName().substring(0, srcFile.getName().lastIndexOf("."));
                }
                destParam += fileName + ".zip";
            }
        }
        return destParam;
    }

    /**
     * 在必要的情况下创建压缩文件存放目录,比如指定的存放路径并没有被创建
     *
     * @param destParam 指定的存放路径,有可能该路径并没有被创建
     */
    private static void createDestDirectoryIfNecessary(String destParam) {
        File destDir = null;
        if (destParam.endsWith(File.separator)) {
            destDir = new File(destParam);
        } else {
            destDir = new File(destParam.substring(0, destParam.lastIndexOf(File.separator)));
        }
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
    }


    public static int addFilesWithAESEncryption(String doZipFile, ArrayList<File> filesToAdd, String password) {
        int reuslt = 1;
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(doZipFile);
            System.out.println("---------------------------压缩开始-------------------------------");

            if (zipFile.getFile().exists()) {
                System.out.println("文件已存在先删除压缩包！");
                zipFile.getFile().delete();
            }
            zipFile.setRunInThread(true);

            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); //压缩模式
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); //压缩程度 一般压缩

            if (!"".equals(password)) {
                parameters.setEncryptFiles(true); //是否设置密码
                parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
                parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
                parameters.setPassword(password);//设置压缩密码
            }
            //添加要压缩的文件 可以支持多文件压缩，并 开始 压缩；
            zipFile.addFiles(filesToAdd, parameters);

            //一下 为 监听过程，不影响 实际压缩过程；
            ProgressMonitor progressMonitor = zipFile.getProgressMonitor();//开启压缩监听

            //开启 监听后，压缩状态 为 1：正在压缩
            //若 存在异常 ，则 直接跳出 循环；且状态 结果码 为失败 状态 2；
            while (progressMonitor.getState() == ProgressMonitor.STATE_BUSY) {
                System.out.println("状态" + progressMonitor.getState());
                switch (progressMonitor.getCurrentOperation()) {
                    case ProgressMonitor.OPERATION_NONE:
                        //	System.out.println("没有正在执行操作");
                        break;
                    case ProgressMonitor.OPERATION_ADD:
                        //	System.out.println("添加操作");
                        break;
                    case ProgressMonitor.OPERATION_EXTRACT:
                        //	System.out.println("提取操作");
                        break;
                    case ProgressMonitor.OPERATION_REMOVE:
                        //	System.out.println("删除操作");
                        break;
                    case ProgressMonitor.OPERATION_CALC_CRC:
                        //	System.out.println("Calcualting CRC");
                        break;
                    case ProgressMonitor.OPERATION_MERGE:
                        //	System.out.println("合并操作");
                        break;
                    default:
                        //	System.out.println("无效的操作");
                        break;
                }

            }
            //如果 正常压缩，则结果码：为0； 异常则为2 或者其他；
            System.out.println("Result: " + progressMonitor.getResult());
            reuslt = progressMonitor.getResult();

            if (progressMonitor.getResult() == ProgressMonitor.RESULT_ERROR) {
                if (progressMonitor.getException() != null) {
                    progressMonitor.getException().printStackTrace();
                } else {
                    System.err.println("压缩时出现异常");
                }
            }
            System.out.println("---------------------------压缩结束-------------------------------");
            //如果 压缩 不成功 ，则 删除 该文件
            if (progressMonitor.getResult() != 0) {
                zipFile.getFile().delete();
            }
            return reuslt;
        } catch (ZipException e) {
            //异常状态下 删除 该zip文件
            zipFile.getFile().delete();
            e.printStackTrace();
            return 1;
        }
    }


}
