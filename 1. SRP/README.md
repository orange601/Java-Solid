# SRP (Single Responsibility Principle: 단일 책임 원칙) #
- 클래스를 변경해야 하는 이유는 오직 하나뿐이어야 한다.

## 👎 BAD ##
````java
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
~~Date 객체와 getDate() 함수를 사용해서 불편한 기분은 잠시 잊고, Gym객체에 집중하자~~
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
1. 만약 헬스장 회원이 주차장을 이용한다면 주차장에서도 이용이 가능한 회원인지 확인해야 된다.
2. isNormalUser() 이라는 함수를 또 만든다.
3. 휴 이런 엄청난 일을 2번이나 하다니 난 대단해
````java
public class ParkingLot {
	private Member member;
	public ParkingLot(Member member) {
		this.member = member;
	}
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
2. 그럼, Gym에서 블랙컨슈머를 삭제하고 주차장에서도 블랙컨슈머 확인을 삭제한다.
3. 같은일을 두번했을 뿐만 아니라, 헬스장 주인은 차를 갖고 다니지 않아서 주차장에 관한일은 생각지도 못해 조건을 지우지 못하는 경우도 발생한다.
