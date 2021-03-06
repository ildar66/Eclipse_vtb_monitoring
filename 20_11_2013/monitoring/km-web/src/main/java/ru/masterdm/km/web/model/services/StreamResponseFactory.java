package ru.masterdm.km.web.model.services;

import ru.masterdm.km.web.model.services.util.*;

/**
 * Factory for StreamResponse.
 * 
 * @author Shafigullin Ildar
 * 
 */
public class StreamResponseFactory {
	private static final String DEFAULT_EXTENSION = "txt";

	public static InlineStreamResponse getInline(java.io.InputStream is, String fileName) {
		return new InlineStreamResponse(is, fileName);
	}

	public static AttachmentStreamResponse getAttachment(java.io.InputStream is, String file) {
		String fileExt = getFileExtention(file);
		String fileName = getFileName(file);
		if (fileExt.equals("pdf")) {
			return new PDFAttachment(is, fileName);
		} else if (fileExt.equals("doc")) {
			return new MsWordAttachment(is, fileName);
		} else if (fileExt.equals("xls")) {
			return new XLSAttachment(is, fileName);
		} else if (fileExt.equals("jpeg")) {
			return new JPEGAttachment(is, fileName);
		} else {
			return new AttachmentStreamResponse(is, fileName);
		}
	}

	private static String getFileName(String file) {
		String fileName = file;
		int indexOfExt = file.lastIndexOf(".");
		if (indexOfExt > 0) {
			fileName = fileName.substring(0, indexOfExt);
		}
		return fileName;
	}

	private static String getFileExtention(String fileName) {
		String fileExt = DEFAULT_EXTENSION;
		int indexOfExt = fileName.lastIndexOf(".");
		if (indexOfExt > 0) {
			fileExt = fileName.substring(indexOfExt + 1).toLowerCase();
		}
		return fileExt;
	}
}
