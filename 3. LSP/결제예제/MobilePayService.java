package com.solid.lsp;

import java.math.BigDecimal;

public class MobilePayService {
	private PayableDevice payableDevice;
	public MobilePayService(PayableDevice payableDevice) {
		this.payableDevice = payableDevice;
	}
	public void pay(BigDecimal amount) {
		payableDevice.pay(amount);
	}
}
