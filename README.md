# vestie-servey


# 발생한 문제들
## 1. 중첩된 클래스(List)의 값 검증(@Valid)
- List 필드 위에 `@Valid`를 또 붙여주어 해결하였음.
- 참고 사이트(https://stackoverflow.com/questions/25872109/spring-validation-for-list-of-nested-class)


## 2. Controller에서 @WebMvcTest를 사용하는 경우 ArgumentResolver를 사용하여야 할 때
- `@Import(WebConfig.class)` 와 `@MockBean AuthMemberArgumentResolver` 를 통해 해결하였음
- 이때 `WebConfig` 는 AuthMemberArgumentResolver를 등록하는 설정 클래스임

