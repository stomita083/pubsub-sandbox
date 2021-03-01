package com.sora.mq.pubsubsandbox.consumer.application

data class ChargeMessage(

    val userId: String? = null,

    val price: Int = 0,

    val point: Int = 0,

    val productIdAndChargeIds: List<ProductIdAndChargeId>,

    val productName: String? = null,

    val productType: String? = null
)

data class ProductIdAndChargeId(
    val productId: Long,
    val chargeId: Long,
)
