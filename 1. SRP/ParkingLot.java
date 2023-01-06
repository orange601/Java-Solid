package com.solid.srp;

public class ParkingLot {
	private Member member;
	public ParkingLot(Member member) {
		this.member = member;
	}
	public boolean open() {
		if(member.isNormalUser()) {
			return true;
		}
		return false;
	}
}
