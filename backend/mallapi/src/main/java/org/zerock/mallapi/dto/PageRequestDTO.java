package org.zerock.mallapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

public class PageRequestDTO {

    
     // 해당 페이지의 데이터 불러오기
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;
    
}
