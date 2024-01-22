
# Paging 동작방식

## Paging 테스트 코드 동작 방식

``` Java
@Test
     public void tesetPaging() {

        // import org.springframework.data.domain.Pageable;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(result.getTotalElements());

        result.getContent().stream().forEach(todo -> log.info(todo));
        
     }
```

1. Pageable pageable

 - 초기 페이지(0), 페이지 크기(10), 정렬 조건(tno 기준으로 내림차순) 을 설정한다.
 - 해당 설정은 PageRequest.of 메서드를 통해 수행된다.
 
2. todoRepository.findAll(pageable)

 - todoRepository.findAll(pageable) 을 통해 DB에서 페이징 된 결과를 가져옴
 - findAll은 Spring Data JPA에서 제공하는 메서드로, Page 객체를 반환함
 
 3. getTotalElements()
 
 - result.getTotalElements()을 통해 페이징 처리된 전체 결과의 총 개수를 얻는다.
 
 4. result.getContent()
  
  - result.getContent().stream().forEach(todo -> log.info(todo)) 을 통해 각 페이지에 대한 데이터를 로그에 출력
  - getContent()는 현재 페이지의 내용(=엔티티의 목록) 반환
  

