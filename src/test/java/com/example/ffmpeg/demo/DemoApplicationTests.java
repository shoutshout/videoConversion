package com.example.ffmpeg.demo;


import com.example.ffmpeg.demo.util.VideoUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;


@Slf4j
@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }


    /**
     * 音频转码为MP3格式
     */
    @Test
    public void mp3() {
        String ffmpegPath = "";
        String sourcePath = "";
        String targetPath = "";

        // 排除MP3的音频
        if (!sourcePath.substring(sourcePath.lastIndexOf(".")+1).toLowerCase().equals("mp3")) {

            log.info("开始转换。。。。。");
            long start = System.currentTimeMillis();

            // 调用方法
            VideoUtil.audio(ffmpegPath, sourcePath, targetPath);

            log.info("转换MP3完成 !");
            long end = System.currentTimeMillis() - start;

            BigDecimal time = new BigDecimal(end).divide(new BigDecimal(1000), 4, BigDecimal.ROUND_HALF_UP);

            log.info("转换MP3耗时：" + time + "s");

        }
    }


    /**
     * 视频转码MP4格式
     */
    @Test
    public void mp4() {
        String ffmpegPath = "";
        String sourcePath = "";
        String targetPath = "";

        // 排除MP4的视频
        if (!sourcePath.substring(sourcePath.lastIndexOf(".")+1).toLowerCase().equals("mp4")) {

            log.info("开始转换。。。。。");
            long start = System.currentTimeMillis();

            // 调用方法
            VideoUtil.video(ffmpegPath, sourcePath, targetPath);

            log.info("转换MP4完成 !");
            long end = System.currentTimeMillis() - start;

            BigDecimal time = new BigDecimal(end).divide(new BigDecimal(1000), 4, BigDecimal.ROUND_HALF_UP);

            log.info("转换MP4耗时：" + time + "s");

        }
    }


    /**
     * 截取视频的截图
     */
    @Test
    public void videoScreenshot() {
        String ffmpegPath = "";
        String sourcePath = "";
        String targetPath = "";

        log.info("开始视频截图。。。。。");
        long start = System.currentTimeMillis();

        // 调用方法
        VideoUtil.videoScreenshot(ffmpegPath, sourcePath, targetPath);

        log.info("视频截图完成 !");
        long end = System.currentTimeMillis() - start;

        BigDecimal time = new BigDecimal(end).divide(new BigDecimal(1000), 4, BigDecimal.ROUND_HALF_UP);

        log.info("视频截图耗时：" + time + "s");


    }

}
