package board.board.mapstruct;

import board.board.dto.BoardResDto;
import board.board.entity.BoardEntity;
import board.common.mapper.EntityResMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardResMapStruct extends EntityResMapper<BoardEntity, BoardResDto> {

}
