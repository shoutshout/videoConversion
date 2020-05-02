package com.example.ffmpeg.demo.util;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hu
 * FFmpeg音视频转码工具类
 */
@Slf4j
public class VideoUtil {


    /**
     * 音频转码
     * @param ffmpegPath ffmpeg路径
     * @param sourcePath 源文件路径
     * @param targetPath 目标文件
     */
    public static void audio(String ffmpegPath, String sourcePath, String targetPath){

        List<String> convertDetailList = new ArrayList<String>();

        convertDetailList.add(ffmpegPath);
        convertDetailList.add("-i");
        convertDetailList.add(sourcePath);
        convertDetailList.add("-acodec");
        convertDetailList.add("libmp3lame");
        convertDetailList.add(targetPath);

        executeConvert(convertDetailList);

    }


    /**
     * 视频转码
     * @param ffmpegPath ffmpeg路径
     * @param sourcePath 源文件路径
     * @param targetPath 目标文件
     */
    public static void video(String ffmpegPath, String sourcePath, String targetPath){

        List<String> convertDetailList = new ArrayList<String>();

        convertDetailList.add(ffmpegPath);
        // 后为源视频文件
        convertDetailList.add("-i");
        convertDetailList.add(sourcePath);
        //codec 强制使用codec编解码方式
        convertDetailList.add("-vcodec");
        convertDetailList.add("libx264");
        // 设置比特率
        convertDetailList.add("-vb");
        convertDetailList.add("384k");
        //  fps 设置帧频 缺省25
        convertDetailList.add("-r");
        convertDetailList.add("18");
        // strictness 跟标准的严格性
        convertDetailList.add("-strict");
        convertDetailList.add("-2");
        // freq 设置音频采样率
        convertDetailList.add("-ar");
        convertDetailList.add("22050");
        // bitrate 设置音频码率
        convertDetailList.add("-ab");
        convertDetailList.add("64k");
        convertDetailList.add("-coder");
        convertDetailList.add("0");
        convertDetailList.add(targetPath);

        executeConvert(convertDetailList);

    }

    /**
     *  视频截图(取第一幁)
     * @param ffmpegPath ffmpeg路径
     * @param sourcePath 源文件路径
     * @param targetPath 目标文件
     */
    public static void videoScreenshot(String ffmpegPath, String sourcePath, String targetPath){

        List<String> convertDetailList = new ArrayList<String>();

        convertDetailList.add(ffmpegPath);
        // 输入文件
        convertDetailList.add("-i");
        convertDetailList.add(sourcePath);
        // 目标文件存在覆盖
        convertDetailList.add("-y");
        // 生成图片
        convertDetailList.add("-f");
        convertDetailList.add("image2");
        // 开始截图时间
//        convertDetailList.add("-ss");
        // 设置截图时间
//        convertDetailList.add("00:05:55");
        // 截图幁数
        convertDetailList.add("-vframes");
        convertDetailList.add("2");
        convertDetailList.add(targetPath);


        executeConvert(convertDetailList);

    }


    @SneakyThrows
    private static void executeConvert(List<String> convertDetailList){
        String line = null;

        Process process = Runtime.getRuntime().exec(convertDetailList.toArray(new String[convertDetailList.size()]));
        @Cleanup InputStream stream = process.getErrorStream();
        @Cleanup InputStreamReader reader = new InputStreamReader(stream);
        @Cleanup BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer stringBuffer = new StringBuffer();
        while((line = bufferedReader.readLine())!=null){
            stringBuffer.append(line).append("\n");
        }
        log.info("打印日志 {}", stringBuffer);
    }

}
