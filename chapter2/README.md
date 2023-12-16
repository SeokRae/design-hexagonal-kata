## 오탈자? 의문점 정리



### IP 클래스

- AS-IS
    - 생성자가 private 으로 선언되어 있는 이유를 잘 모르겠음

```java
public class IP {
    // ...
    private IP(String address) {
    }
}
```

- TO-BE
  - 생성자를 private으로 선언하고, factory method를 통해 객체를 생성하도록 변경

```java
public class IP {
    // ...
    private IP(String address) {
    }
    
    // Factory method
    public static IP withAddress(String address) {
        return new IP(address);
    }
}
```