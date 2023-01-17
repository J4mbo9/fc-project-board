package fc.projectboard.domain;

import java.time.LocalDateTime;

public class Article {

    private Long id;
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private String hastag;// 해시태그


    private LocalDateTime createdAt; // 생성일시
    private String createdBy; // 생성자
    private LocalDateTime modifiedAt; // 수정일시
    private String modifiedBy; // 수정자

}