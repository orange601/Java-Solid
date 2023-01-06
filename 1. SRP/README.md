# SRP (Single Responsibility Principle: 단일 책임 원칙) #

````java
public class Gym {
	private Member member;
	
	public Gym(Member member) {
		this.member = member;
	}
	
	public boolean isExpired() {
		if(member.getExpireDate().getDate() < System.currentTimeMillis()) {
			return true;
		}
		return false;
	}
}
````
````java
public class Member {
	private Date expireDate;
	
	public Date getExpireDate() {
		return expireDate;
	}

}
````
