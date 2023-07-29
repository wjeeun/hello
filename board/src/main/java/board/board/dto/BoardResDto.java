package board.board.dto;

import java.util.List;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardResDto {


    private int boardIdx;


    private String title;


    private String contents;


    private int hitCnt;

    private String creatorId;


    private String createdDatetime;

    private String updaterId;

    private String updatedDatetime;

    private List<BoardFileResDto> fileList;
}
