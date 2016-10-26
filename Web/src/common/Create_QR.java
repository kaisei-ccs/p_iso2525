package common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
public class Create_QR {
	public Create_QR(String contents,int width,int height){
		
		//画像ファイルの保存場所
		String filepath ="../";
		try {
		      
		      Hashtable<EncodeHintType, ErrorCorrectionLevel> hints = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
		      hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
	
		      QRCodeWriter writer = new QRCodeWriter();
		      
		      //バーコードにしたいString型,出力フォーマット,横の長さ,縦の長さ、
		      BitMatrix bitMatrix = writer.encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
		      BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
		      
		      //QRコードをファイルへ書き込み
		      ImageIO.write(image, "png", new File(filepath));
		    }
		    catch( IOException e ) {
		      e.printStackTrace();
		    }
		    catch (WriterException e) {
		      e.printStackTrace();
		    }
	}
}
