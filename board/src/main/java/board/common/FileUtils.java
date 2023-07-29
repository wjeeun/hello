//수정전
//package board.common;
//
//import java.io.File;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import board.board.dto.BoardFileReqDto;
//
//@Component
//public class FileUtils {
//
//	public List<BoardFileReqDto> parseFileInfo(int boardIdx, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
//		if(ObjectUtils.isEmpty(multipartHttpServletRequest)){
//			return null;
//		}
//
//		List<BoardFileReqDto> fileList = new ArrayList<>();
//
//		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
//    	ZonedDateTime current = ZonedDateTime.now();
//    	String path = "images/"+current.format(format);
//    	File file = new File(path);
//		if(file.exists() == false){
//			file.mkdirs();
//		}
//
//		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
//
//		String newFileName, originalFileExtension, contentType;
//
//		while(iterator.hasNext()){
//			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
//			for (MultipartFile multipartFile : list){
//				if(multipartFile.isEmpty() == false){
//					contentType = multipartFile.getContentType();
//					if(ObjectUtils.isEmpty(contentType)){
//						break;
//					}
//					else{
//						if(contentType.contains("image/jpeg")) {
//							originalFileExtension = ".jpg";
//						}
//						else if(contentType.contains("image/png")) {
//							originalFileExtension = ".png";
//						}
//						else if(contentType.contains("image/gif")) {
//							originalFileExtension = ".gif";
//						}
//						else{
//							break;
//						}
//					}
//
//					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
//					BoardFileReqDto boardFile = new BoardFileReqDto();
//					boardFile.setBoardIdx(boardIdx);
//					boardFile.setFileSize(multipartFile.getSize());
//					boardFile.setOriginalFileName(multipartFile.getOriginalFilename());
//					boardFile.setStoredFilePath(path + "/" + newFileName);
//					fileList.add(boardFile);
//
//					file = new File(path + "/" + newFileName);
//					multipartFile.transferTo(file);
//				}
//			}
//		}
//		return fileList;
//	}
//
//	public List<BoardFileReqDto> parseFileInfo(MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
//		if(ObjectUtils.isEmpty(multipartHttpServletRequest)){
//			return null;
//		}
//
//		List<BoardFileReqDto> fileList = new ArrayList<>();
//
//		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
//    	ZonedDateTime current = ZonedDateTime.now();
//    	String path = "images/"+current.format(format);
//    	File file = new File(path);
//		if(file.exists() == false){
//			file.mkdirs();
//		}
//
//		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
//
//		String newFileName, originalFileExtension, contentType;
//
//		while(iterator.hasNext()){
//			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
//			for (MultipartFile multipartFile : list){
//				if(multipartFile.isEmpty() == false){
//					contentType = multipartFile.getContentType();
//					if(ObjectUtils.isEmpty(contentType)){
//						break;
//					}
//					else{
//						if(contentType.contains("image/jpeg")) {
//							originalFileExtension = ".jpg";
//						}
//						else if(contentType.contains("image/png")) {
//							originalFileExtension = ".png";
//						}
//						else if(contentType.contains("image/gif")) {
//							originalFileExtension = ".gif";
//						}
//						else{
//							break;
//						}
//					}
//
//					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
//					BoardFileReqDto boardFile = new BoardFileReqDto();
//					boardFile.setFileSize(multipartFile.getSize());
//					boardFile.setOriginalFileName(multipartFile.getOriginalFilename());
//					boardFile.setStoredFilePath(path + "/" + newFileName);
//					boardFile.setCreatorId("admin");
//					fileList.add(boardFile);
//
//					file = new File(path + "/" + newFileName);
//					multipartFile.transferTo(file);
//				}
//			}
//		}
//		return fileList;
//	}
//}
//수정후
package board.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.dto.BoardFileDto;
import board.board.entity.BoardFileEntity;

@Component
public class FileUtils {

	public List<BoardFileDto> parseFileInfo(int boardIdx, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)){
			return null;
		}

		List<BoardFileDto> fileList = new ArrayList<>();

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/"+current.format(format);
		File file = new File(path);
		if(file.exists() == false){
			file.mkdirs();
		}

		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		String newFileName, originalFileExtension, contentType;

		while(iterator.hasNext()){
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			for (MultipartFile multipartFile : list){
				if(multipartFile.isEmpty() == false){
					contentType = multipartFile.getContentType();
					if(ObjectUtils.isEmpty(contentType)){
						break;
					}
					else{
						if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						}
						else if(contentType.contains("image/png")) {
							originalFileExtension = ".png";
						}
						else if(contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						}
						else{
							break;
						}
					}

					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					BoardFileDto boardFile = new BoardFileDto();
					boardFile.setFileSize(multipartFile.getSize());
					boardFile.setOriginalFileName(multipartFile.getOriginalFilename());
					boardFile.setStoredFilePath(path + "/" + newFileName);
					fileList.add(boardFile);

					file = new File(path + "/" + newFileName);
					multipartFile.transferTo(file);
				}
			}
		}
		return fileList;
	}

	public List<BoardFileEntity> parseFileInfo(MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)){
			return null;
		}

		List<BoardFileEntity> fileList = new ArrayList<>();

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/"+current.format(format);
		File file = new File(path);
		if(file.exists() == false){
			file.mkdirs();
		}

		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		String newFileName, originalFileExtension, contentType;

		while(iterator.hasNext()){
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			for (MultipartFile multipartFile : list){
				if(multipartFile.isEmpty() == false){
					contentType = multipartFile.getContentType();
					if(ObjectUtils.isEmpty(contentType)){
						break;
					}
					else{
						if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						}
						else if(contentType.contains("image/png")) {
							originalFileExtension = ".png";
						}
						else if(contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						}
						else{
							break;
						}
					}

					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					BoardFileEntity boardFile = new BoardFileEntity();
					boardFile.setFileSize(multipartFile.getSize());
					boardFile.setOriginalFileName(multipartFile.getOriginalFilename());
					boardFile.setStoredFilePath(path + "/" + newFileName);
					boardFile.setCreatorId("admin");
					fileList.add(boardFile);

					file = new File(path + "/" + newFileName);
					multipartFile.transferTo(file);
				}
			}
		}
		return fileList;
	}
}
