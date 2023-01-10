# ISP (Interface Segregation Principle: 인터페이스 분리 원칙) #

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
````
