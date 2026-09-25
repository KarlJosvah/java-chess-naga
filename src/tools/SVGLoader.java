package tools;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

import java.awt.image.BufferedImage;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

public class SVGLoader {
	public static final String BOARD_SVG_PATH = "assets/board/";
	public static final String PIECE_SVG_PATH = "assets/pieces/";

	public static BufferedImage loadBoardSvg(String fileName, float width, float height) {
		return SVGLoader.loadSvg(SVGLoader.BOARD_SVG_PATH + fileName, width, height);
	}

	public static BufferedImage loadPieceSvg(String fileName, float width, float height) {
		return SVGLoader.loadSvg(SVGLoader.PIECE_SVG_PATH + fileName, width, height);
	}

	public static BufferedImage loadSvg(String filePath, float width, float height) {
		BufferedImage[] imageHolder = new BufferedImage[1];

		try (InputStream inputStream = new FileInputStream(new File(filePath))) {
			TranscoderInput input = new TranscoderInput(inputStream);

			ImageTranscoder transcoder = new ImageTranscoder() {
				@Override
				public BufferedImage createImage(int w, int h) {
					return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
				}

				@Override
				public void writeImage(BufferedImage img, TranscoderOutput output) {
					imageHolder[0] = img;
				}
			};

			// Set desired output dimensions
			transcoder.addTranscodingHint(ImageTranscoder.KEY_WIDTH, width);
			transcoder.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, height);

			transcoder.transcode(input, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return imageHolder[0];
	}
}