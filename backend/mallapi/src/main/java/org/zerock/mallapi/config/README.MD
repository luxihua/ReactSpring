
# ModelMapper 사용법

---------------


- ModelMapper는 JAVA 객체 간 데이터를 매핑하는데 사용되는 라이브러리 중 하나
- 주로 서로 다른 클래스 간의 데이터 전송이나 반환에 사용됨
- Spring의 경우, DTO와 Entity간의 매핑 작업에서 활용됨


``` Java
@Bean
    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
            .setMatchingStrategy(MatchingStrategies.LOOSE);
            return modelMapper;
    }
    
```

1. Field Matching Enabled (setFieldMatchingEnabled(true))

 - 이 옵션을 활성화하면 ModelMapper는 소스 객체와 대상 객체의 필드 이름이 일치하는 경우 자동으로 매핑을 수행함
 
 2. Field Access Level (setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE))
 
 - 이 옵션은 필드에 접근할 때의 접근 수준을 설정합니다. PRIVATE로 설정하면 private 필드에도 접근할 수 있다. 
 - 일반적으로 DTO나 엔터티 클래스의 private 필드에 접근하여 매핑 작업을 수행할 때 유용함.
 
 3.  Matching Strategy (setMatchingStrategy(MatchingStrategies.LOOSE))
 
 - 매핑 방식을 결정함
 - MatchingStrategies.LOOSE로 설정하면 일부 불일치가 있더라도 매핑이 가능하도록 허용됨.
 
4. return ModelMapper Instance (return modelMapper)

- getMapper 메서드는 설정이 완료된 ModelMapper 인스턴스를 반환함. 
- 이렇게 생성된 ModelMapper는 다른 빈이나 서비스에서 주입되어 사용될 수 있다.
