package br.com.spolaorthays.filmoteca.moviedetails.data

import br.com.spolaorthays.filmoteca.moviedetails.data.model.DollarPeriodQuotation
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DollarQuotationService {
    //https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoDolarPeriodo(dataInicial=@dataInicial,dataFinalCotacao=@dataFinalCotacao)?@dataInicial=%2701-01-2023%27&@dataFinalCotacao=%2701-14-2024%27&$format=json

    @GET("/olinda/servico/PTAX/versao/v1/odata/CotacaoDolarPeriodo(dataInicial=@dataInicial,dataFinalCotacao=@dataFinalCotacao)")
    suspend fun getDollarQuotationByPeriod(
        @Query("@dataInicial") initialData: String,
        @Query("@dataFinalCotacao") finalData: String,
        @Query("\$format") format: String
    ): DollarPeriodQuotation
}