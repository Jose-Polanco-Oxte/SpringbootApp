package jpolanco.springbootapp.Shared.Infrastructure;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class ZxingQRService {
    private final String qrCodeFolderPath;

    public ZxingQRService(@Value("${QRCODESPATH}") String qrCodeFolderPath) {
        this.qrCodeFolderPath = qrCodeFolderPath;
    }

    public String generateQRcode(String text) {
        String qrCodePath = qrCodeFolderPath + "\\" + text + ".png";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < 200; x++) {
                for (int y = 0; y < 200; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }
            ImageIO.write(bufferedImage, "png", new File(qrCodePath));
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Error generating QR code", e);
        }
        return qrCodePath;
    }

    public String obtainTextFromQRcode(String path) {
        return "text";
    }

    public boolean existsQRcode(String email) {
        String qrCodePath = qrCodeFolderPath + "\\" + email.split("@")[0] + ".png";
        File file = new File(qrCodePath);
        return file.exists();
    }
}
