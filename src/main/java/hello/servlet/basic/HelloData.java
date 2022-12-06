package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

// java bean 접근법, java bean property 접근법
@Getter
@Setter
public class HelloData {
    private String username;
    private int age;
}
