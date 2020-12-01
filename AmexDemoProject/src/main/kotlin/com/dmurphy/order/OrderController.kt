package com.dmurphy.order

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired

@RestController
class OrderController(@Autowired val orderListener: OrderListener) {
	@RequestMapping("/order", method = arrayOf(RequestMethod.POST))
	fun incommingOrder(@RequestBody incommingOrder: IncomingOrder): Receipt {
		orderListener.orderReceived()
		return processOrder(incommingOrder)
	}
	
	fun processOrder(incommingOrder: IncomingOrder): Receipt {
		var appleCount: Int = 0
		var orangeCount: Int = 0
		var success: Boolean = true
		
		for(product in incommingOrder.products) {
			if(product.equals("Apple", true)) {
				appleCount++;
			}
			if(product.equals("Orange", true)) {
				orangeCount++;
			}
		}
		
		if(appleCount > Constants.APPLE_LIMIT || orangeCount > Constants.ORANGE_LIMIT) {
			success = false
			orderListener.orderFailed()
		} else {
			orderListener.orderSuccessful()
		}
		
		
			return Receipt(incommingOrder.products, appleCount, orangeCount,
				success, calculateTotal(Constants.DISCOUNTED,appleCount, orangeCount))
	}
	
	fun calculateTotal(discount: Boolean, appleCount: Int, orangeCount: Int): Double {
		if(discount) {
			return (calculateDiscountedTotal(appleCount, 2.0, 1.0, Constants.APPLE_PRICE)
					+ calculateDiscountedTotal(orangeCount, 3.0, 2.0, Constants.ORANGE_PRICE))
		} else {
			return (appleCount * Constants.APPLE_PRICE) + (orangeCount * Constants.ORANGE_PRICE)
		}
			
	}
	
	fun calculateDiscountedTotal(productCount: Int, productDiscountNumerator: Double,
								 productDiscountDenominator: Double, productPrice: Double): Double{
		var discountedItems: Double = ((productCount - (productCount % productDiscountNumerator)) /
				(productDiscountNumerator/productDiscountDenominator)) * productPrice
		var remainingItems: Double = (productCount % productDiscountNumerator) * productPrice
		
		return discountedItems + remainingItems
	}
	
	
}