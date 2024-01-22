package org.zerock.mallapi.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_todo")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    private String title;
    private String writer;
    private boolean complete;
    private LocalDate dueDate;


    // 데이터 수정을 위한 메서드
    /// 1. 제목 수정
    public void changeTitle(String title) {
        this.title = title;
    }
    /// 2. complete 수정
    public void changeComplete(boolean complete) {
        this.complete = complete;
    }
    /// 3. 수정 일자 수정
    public void changeDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    
}
