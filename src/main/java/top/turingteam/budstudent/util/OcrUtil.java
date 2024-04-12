package top.turingteam.budstudent.util;

import cn.hutool.core.util.IdUtil;
import com.benjaminwan.ocrlibrary.OcrResult;
import io.github.mymonstercat.Model;
import io.github.mymonstercat.ocr.InferenceEngine;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.common.constant.FileContentType;
import top.turingteam.budstudent.exception.CustomException;

import java.io.*;

/**
 * @author howe
 */
@Slf4j
public class OcrUtil {
    private static final String WINDOWS = "win";

    private static final String LINUX = "linux";

    /**
     * 查询文件中是否包含指定字符串
     *
     * @param str  要查找的字符串
     * @param file 文件
     * @return 是否包含
     */
    public static boolean exitString(String str, MultipartFile file) {
        try {
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith(FileContentType.IMAGE_TYPE)) {
                throw new CustomException("文件类型不合法");
            }
            String fileName = IdUtil.simpleUUID() + "." + contentType.split("/")[1];
            String tempDirPath = getSystemTempDir();
            File tempDir = new File(tempDirPath);
            File tempFile = getFile(file, tempDir, fileName);
            InferenceEngine engine = InferenceEngine.getInstance(Model.ONNX_PPOCR_V3);
            OcrResult ocrResult = engine.runOcr(tempFile.getAbsolutePath());
            String text = ocrResult.getStrRes().trim();
            boolean delete = tempFile.delete();
            if (!delete) {
                log.error("OCR删除临时文件失败");
            }
            return text.contains(str);
        } catch (Exception e) {
            log.error("文件OCR识别失败", e);
            throw new CustomException("文件OCR识别失败");
        }
    }

    @NotNull
    private static String getSystemTempDir() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains(WINDOWS)) {
            return System.getenv("TEMP");
        } else if (osName.contains(LINUX)) {
            return "/tmp";
        } else {
            throw new RuntimeException("不支持的操作系统");
        }
    }

    @NotNull
    private static File getFile(MultipartFile file, File tempDir, String fileName) throws IOException {
        // 如果images文件夹不存在，则创建它
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }
        File tempFile = new File(tempDir, fileName);
        try (InputStream inputStream = file.getInputStream();
             OutputStream outputStream = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return tempFile;
    }
}
