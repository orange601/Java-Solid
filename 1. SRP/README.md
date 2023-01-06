# SRP (Single Responsibility Principle: 단일 책임 원칙) #

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
