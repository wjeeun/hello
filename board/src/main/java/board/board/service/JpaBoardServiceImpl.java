package board.board.service;

import java.util.List;
import java.util.Optional;

import board.board.common.exception.DataNotFoundException;
import board.board.dto.BoardFileReqDto;
import board.board.dto.BoardReqDto;
import board.board.dto.BoardResDto;
import board.board.mapstruct.BoardReqMapStruct;
import board.board.mapstruct.BoardResMapStruct;
import board.common.FileJpaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.entity.BoardEntity;
import board.board.entity.BoardFileEntity;
import board.board.repository.JpaBoardRepository;

@Service
public class JpaBoardServiceImpl implements JpaBoardService{
	
	@Autowired
	JpaBoardRepository jpaBoardRepository;

	@Autowired
	BoardReqMapStruct boardReqMapStruct;
	@Autowired
	BoardResMapStruct boardResMapStruct;

	@Autowired
	FileJpaUtils fileJpaUtils;

	@Override
	public List<BoardResDto> selectBoardList() throws Exception {
		List<BoardEntity> boardEntityList = jpaBoardRepository.findAllByOrderByBoardIdxDesc();

		return boardResMapStruct.toDto(boardEntityList);
	}

	@Override
	public void saveBoard(BoardReqDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		board.setCreatorId("admin");
		List<BoardFileReqDto> list = fileJpaUtils.parseFileInfo(multipartHttpServletRequest);
		if(CollectionUtils.isEmpty(list) == false){
			board.setFileList(list);
		}
		BoardEntity boardEntity = boardReqMapStruct.toEntity(board);
		jpaBoardRepository.save(boardEntity);
	}

	//메소드 참조
	//orElseThrow(()=>{ return new DataNotFoundException(); });
	//orElseThrow(DataNotFoundException::new);


	@Override
	public BoardResDto selectBoardDetail(int boardIdx) throws Exception{
		BoardEntity boardEntity = jpaBoardRepository.findById(boardIdx).orElseThrow(DataNotFoundException::new);;
		boardEntity.setHitCnt(boardEntity.getHitCnt() + 1);
		jpaBoardRepository.save(boardEntity);
		System.out.println(boardResMapStruct + "please run normally please");
		BoardResDto boardResDto = boardResMapStruct.toDto(boardEntity);
		return boardResDto;
	}

	@Override
	public void deleteBoard(int boardIdx) {
		jpaBoardRepository.deleteById(boardIdx);
	}

	@Override
	public BoardFileEntity selectBoardFileInformation(int boardIdx, int idx) throws Exception {
		BoardFileEntity boardFile = jpaBoardRepository.findBoardFile(boardIdx, idx);
		return boardFile;
	}
}
