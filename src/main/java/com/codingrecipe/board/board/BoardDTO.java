package com.codingrecipe.board.board;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// DTO(Data Transfer Object) , VO, Bean,        Entity

@Getter
@Setter
@ToString
@NoArgsConstructor    //기본 생성자
@AllArgsConstructor   // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime; // 게시글 작성 시간
    private LocalDateTime boardUpdatedTime; // 게시글 수정 시간

    // 파일 관련
    private List<MultipartFile> boardFile; // save.html -> Controller 파일 담는 용도
    private List<String> originalFileName; // 원본 파일 이름
    private List<String> storedFileName;   // 서버 저장용 파일 이름 -> 파일 이름이 중복됬을 때 다르게 저장.
    private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)

    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());

        if(boardEntity.getFileAttached() == 0){
            boardDTO.setFileAttached(boardEntity.getFileAttached()); // 0
        } else {
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            boardDTO.setFileAttached(boardEntity.getFileAttached()); // 1

            for (BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()) {
                originalFileNameList.add(boardFileEntity.getOriginalFileName());
                storedFileNameList.add(boardFileEntity.getStoredFileName());
            }
            boardDTO.setOriginalFileName(originalFileNameList);
            boardDTO.setStoredFileName(storedFileNameList);

        }

        return boardDTO;
    }

    public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }
}
