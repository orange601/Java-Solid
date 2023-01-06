# SRP (Single Responsibility Principle: 단일 책임 원칙) #
- 하나의 클래스는 하나의 책임만 가져야 한다.

## 👎 BAD ##
````java
// Date 객체와 getDate() 함수를 사용해서 불편한 기분은 잠시 잊고, Gym객체에 집중하자
public class Gym {
	private Member member;
	public Gym(Member member) {
		this.member = member;
	}	
	// 헬스장 이용이 가능한 회원인지 확인한다.
	public boolean isNormalUser() {
		// 만료날짜와 블랙컨슈머인지 확인한다.
		if(member.getExpireDate().getDate() > System.currentTimeMillis()
				&& !member.isBlackConsumer()) {
			return true;
		}
		return false;
	}
}
````
````java
public class Member {
	private Date expireDate;
	private boolean isBlackConsumer = false;
	public boolean isBlackConsumer() {
		return isBlackConsumer;
	}
	public Date getExpireDate() {
		return expireDate;
	}
}
````
````java
// 만약 헬스장 회원이 주차장을 이용한다면 주차장에서도 이용이 가능한 회원인지 확인해야 된다.
public class ParkingLot {
	private Member member;
	public ParkingLot(Member member) {
		this.member = member;
	}
	// isNormalUser() 함수를 또 만든다.
	public boolean isNormalUser() {
		if(member.getExpireDate().getDate() > System.currentTimeMillis()
				&& !member.isBlackConsumer()) {
			return true;
		}
		return false;
	}
}
````
1. 헬스장 주인이 블랙컨슈머이든 아니든 이용가능하게 해주겠다고 한다. 
2. 그럼, Gym에서 블랙컨슈머를 삭제하고 주차장에서도 블랙컨슈머를 삭제해야한다.
3. 같은일을 두번작업해야 된다.
4. 또한, 헬스장 주인은 차를 갖고 다니지 않아서 주차장에 관한일은 생각지도 못해 조건을 지우지 못하는 경우도 발생한다.


## 👍 GOOD ##
````java
public class Member {
	private Date expireDate;
	private boolean isBlackConsumer = false;
	public boolean isBlackConsumer() {
		return isBlackConsumer;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public boolean isNormalUser() {
		if(expireDate.getDate() > System.currentTimeMillis()
				&& !isBlackConsumer) {
			return true;
		}
		return false;
	}
}
````
````java
public class Gym {
	private Member member;
	public Gym(Member member) {
		this.member = member;
	}
	// 헬스장은 정상적인 회원인지만 확인한 후 문을 열어주면 된다.
	public boolean open() {
		if(member.isNormalUser()) {
			return true;
		}
		return false;
	}
}
````
````java
public class ParkingLot {
	private Member member;
	public ParkingLot(Member member) {
		this.member = member;
	}
	// 주차장 또한 정상적인 회원인지만 확인한 후 문을 열어주면 된다.
	public boolean open() {
		if(member.isNormalUser()) {
			return true;
		}
		return false;
	}
}
````
