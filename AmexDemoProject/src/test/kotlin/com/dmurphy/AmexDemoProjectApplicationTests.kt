package com.dmurphy

import org.junit.jupiter.api.Assertions.assertNotNull
import org.json.JSONObject
import com.dmurphy.order.Receipt
import org.springframework.http.HttpHeaders
import org.springframework.beans.factory.annotation.Autowired
import jdk.nashorn.internal.ir.annotations.Ignore
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.boot.test.web.client.TestRestTemplate
import java.net.URI
import com.dmurphy.order.Constants
import org.springframework.http.MediaType
import com.dmurphy.order.IncomingOrder
import com.dmurphy.order.OrderController
import org.springframework.http.HttpEntity
import org.springframework.test.context.event.annotation.BeforeTestClass
import com.dmurphy.order.OrderListener

@SpringBootTest(classes = arrayOf(AmexDemoProjectApplication::class), 
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AmexDemoProjectApplicationTests {
//	@Autowired
//    lateinit var testRestTemplate: TestRestTemplate
	
	var orderListener = OrderListener()
    var orderController = OrderController(orderListener)
	
//    @Ignore
//	@Test
//	fun validateCorrectTotalRest() {
//		val json = JSONObject()
//		json.put("products", arrayOf("Apple", "Orange", "Apple")) // for whatever reason not seeing property
//		
//		val headers: HttpHeaders = HttpHeaders()
//		headers.setContentType(MediaType.APPLICATION_JSON)
//		val entity : HttpEntity<String> = HttpEntity(json.toString(), headers);
//	
//		val result: Receipt = testRestTemplate.postForObject(URI.create("http://localhost:8080/order"),
//			entity, Receipt::class.java)
//
//        assertNotNull(result)
//		assertEquals(result.appleCount, 2)
//		assertEquals(result.orangeCount, 1)
//        assertEquals(result.total, 0.85)
//	}
	
	@Test
	fun validateCorrectTotal() {
		var receipt: Receipt = orderController.incommingOrder(IncomingOrder(arrayOf("Apple", "Orange", "Apple")))
		
		assertNotNull(receipt)
		assertEquals(receipt.appleCount, 2)
		assertEquals(receipt.orangeCount, 1)
		if(Constants.DISCOUNTED)
			assertEquals(receipt.total, 0.85)
		else
			assertEquals(receipt.total, 1.45)
	}

}
