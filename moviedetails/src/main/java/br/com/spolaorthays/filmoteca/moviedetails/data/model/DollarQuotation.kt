package br.com.spolaorthays.filmoteca.moviedetails.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DollarData(
    @Json(name = "cotacaoCompra")
    val purchaseQuotation: String?,
    @Json(name = "cotacaoVenda")
    val saleQuotation: String?,
    @Json(name = "dataHoraCotacao")
    val dateHourQuotation: String?,
)

@JsonClass(generateAdapter = true)
data class DollarPeriodQuotation(
    @Json(name = "value")
    val periodQuotation: List<DollarData>
)
