package com.solid.srp;

public class Gym {
	private Member member;
	public Gym(Member member) {
		this.member = member;
	}
	public boolean open() {
		if(member.isNormalUser()) {
			return true;
		}
		return false;
	}
}
