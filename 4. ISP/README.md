# ISP (Interface Segregation Principle: 인터페이스 분리 원칙) #
- **SRP**는 클래스의 단일 책임을 강조한다면, **ISP**는 인터페이스의 단일 책임을 강조한다고 말할 수 있다.
- 상위 클래스는 풍성할수록 좋다

## 👎 BAD ##
````java
public interface SmartPhone {
	void text();
	void music();
	void samsungPay();
}
public class Iphone implements SmartPhone {
	@Override
	public void text() {
	}
	@Override
	public void music() {
	}
	@Override
	public void samsungPay() { // 애플에는 없는 기능이 추가 되었다.
	}

}
````

## 👍 GOOD ##
````java
public interface SmartPhone {
	void text();
	void music();
}
public interface ApplePhone {
	void applePay();
}
public interface SamsungPhone {
	void samsungPay();
}
// 구현체
public class Iphone implements SmartPhone, ApplePhone {
	@Override
	public void applePay() {
	}
	@Override
	public void text() {
	}
	@Override
	public void music() {
	}
}
// 구
public class Galaxy implements SmartPhone, SamsungPhone {
	@Override
	public void samsungPay() {
	}
	@Override
	public void text() {
	}
	@Override
	public void music() {
	}
}
````
