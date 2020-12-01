package com.dmurphy.order

import com.fasterxml.jackson.annotation.JsonCreator

data class IncomingOrder (val products: Array<String>) {
}


data class Receipt(
	val products: Array<String>,
	val appleCount: Int,
	val orangeCount: Int,
	val successful: Boolean,
	val total: Double) {
	
}