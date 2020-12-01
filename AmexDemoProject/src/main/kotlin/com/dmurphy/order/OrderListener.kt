package com.dmurphy.order

import org.springframework.stereotype.Component

@Component
class OrderListener {
	fun orderReceived() {
		println("You order has been received. Thank you.")
	}
	
	fun orderSuccessful() {
		println("Your order went through successfully and will arrive in 24 to 48 hours")
	}
	
	fun orderFailed() {
		println("Due to limited stock your order failed")
	}
	
	fun log(message: String) {
		println("Log info: " + message)
	}
}