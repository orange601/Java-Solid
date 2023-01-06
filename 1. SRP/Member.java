package com.solid.srp;

import java.util.Date;

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
