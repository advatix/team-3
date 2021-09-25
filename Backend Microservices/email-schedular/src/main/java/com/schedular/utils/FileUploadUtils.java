package com.schedular.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import com.google.common.io.Files;
import com.schedular.exceptions.IllegalArgumentException;

@Component
public class FileUploadUtils {

  @Value("${upload.image.base.url}")
  private String baseUrl;

  @Value("${upload.image.dir.path}")
  private String fileDirectoryPath;

  private Logger log = LogManager.getLogger(getClass());

  public FileUploadResponseDto saveImage(MultipartFile file, List<String> extensions)
      throws IllegalArgumentException {
    if (Objects.isNull(file) || file.isEmpty()) {
      throw new IllegalArgumentException("File Is Empty");
    }
    String fileExtension = Files.getFileExtension(file.getOriginalFilename()).toLowerCase();

    if (!CollectionUtils.isEmpty(extensions) && !extensions.contains(fileExtension))
      throw new IllegalArgumentException("File type is not Acceptable");

    try {
      if (!new File(fileDirectoryPath).exists()) {
        LogManager.getLogger(getClass()).info("Directory doesn't exist");
        new File(fileDirectoryPath).mkdirs();
      }

      LogManager.getLogger(getClass()).info(fileDirectoryPath);

      String filePath = System.currentTimeMillis() + "." + fileExtension;
      File dest = new File(fileDirectoryPath, filePath);
      LogManager.getLogger(getClass()).info("Destiation fetched: {}", dest.getAbsolutePath());
      if (!dest.exists()) {
        LogManager.getLogger(getClass()).info("File Created {}", dest.createNewFile());
      }
      LogManager.getLogger(getClass()).info("Multipart File: {}", file);
      file.transferTo(dest);

      return new FileUploadResponseDto(baseUrl + "/Upload/" + dest.getName(),
          dest.getAbsolutePath(), dest.getName(), fileExtension, dest, null);

    } catch (Exception e) {
      log.error(e, e);
      return new FileUploadResponseDto(null, null, null, null, null, e.getMessage());
    }
  }

  public String convertByteArrayToImage(byte[] imageInByte) {
    try {
      File dest = new File(fileDirectoryPath);
      if (!dest.exists()) {
        dest.mkdirs();
      }
      dest = new File(dest, "IMG_" + System.currentTimeMillis() + ".jpg");
      if (dest.createNewFile()) {
        LogManager.getLogger(getClass()).info(dest.getAbsolutePath());
        InputStream in = new ByteArrayInputStream(imageInByte);
        BufferedImage bImageFromConvert = ImageIO.read(in);
        bImageFromConvert = ensureOpaque(bImageFromConvert);
        ImageIO.write(bImageFromConvert, "jpg", dest);
        return baseUrl + "/Upload/" + dest.getName();
      }
    } catch (Exception e) {
      log.error(e, e);
    }
    return null;
  }

  private BufferedImage ensureOpaque(BufferedImage bi) {
    if (bi.getTransparency() == BufferedImage.OPAQUE)
      return bi;
    int w = bi.getWidth();
    int h = bi.getHeight();
    BufferedImage bi2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    bi2.createGraphics().drawImage(bi, 0, 0, Color.WHITE, null);
    return bi2;
  }

}
