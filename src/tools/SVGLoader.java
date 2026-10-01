package tools;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

import java.awt.image.BufferedImage;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;

public class SVGLoader {
	public static final String BOARD_SVG_PATH = "assets/board/";
	public static final String PIECE_SVG_PATH = "assets/pieces/";

	public static BufferedImage loadBoardSvg(String fileName, float width, float height) {
		return SVGLoader.loadSvg(SVGLoader.BOARD_SVG_PATH + fileName, width, height, false);
	}

	public static BufferedImage loadPieceSvg(String fileName, float width, float height) {
		return SVGLoader.loadPieceSvg(fileName, width, height, false);
	}

	public static BufferedImage loadPieceSvg(String fileName, float width, float height, boolean withStroke) {
		return SVGLoader.loadSvg(SVGLoader.PIECE_SVG_PATH + fileName, width, height, withStroke);
	}

	public static BufferedImage loadSvg(String filePath, float width, float height) {
		return SVGLoader.loadSvg(filePath, width, height, false);
	}

	public static BufferedImage loadSvg(String filePath, float width, float height, boolean withStroke) {
		BufferedImage[] imageHolder = new BufferedImage[1];

		try {
			TranscoderInput input;
			if (withStroke) {
				String parser = XMLResourceDescriptor.getXMLParserClassName();
				SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
				Document doc = factory.createDocument(new File(filePath).toURI().toString());

				NodeList paths = doc.getElementsByTagName("path");
				for (int i = 0; i < paths.getLength(); i++) {
					Element elem = (Element) paths.item(i);
					elem.setAttribute("stroke", "#1a1a1a");
					elem.setAttribute("stroke-width", "2.5");
					elem.setAttribute("stroke-linejoin", "round");
				}

				input = new TranscoderInput(doc);
			} else {
				InputStream inputStream = new FileInputStream(new File(filePath));
				input = new TranscoderInput(inputStream);
			}

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

			transcoder.addTranscodingHint(ImageTranscoder.KEY_WIDTH, width);
			transcoder.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, height);

			transcoder.transcode(input, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return imageHolder[0];
	}
}