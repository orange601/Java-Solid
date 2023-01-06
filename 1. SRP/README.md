# SRP (Single Responsibility Principle: 단일 책임 원칙) #
- 클래스를 변경해야 하는 이유는 오직 하나뿐이어야 한다.

````java
public class Gym {
	private Member member;
	public Gym(Member member) {
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
