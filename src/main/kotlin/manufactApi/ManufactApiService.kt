package manufactApi

import manufactApi.models.BaseInfoLog
import manufactApi.models.BaseInfoLogDetail
import manufactApi.models.LogClass
import manufactApi.models.ManufactLog
import retrofit2.Call
import retrofit2.http.*

interface ManufactApiService {
    companion object {
        const val API_ROOT = "api/v1/"
    }
    @GET(API_ROOT + "log_classes/")
    fun getLogClassList() : Call<List<LogClass>>

    @GET(API_ROOT + "base_info_logs/{id}/")
    fun getBaseInfoLog(@Path("id") id : Int): Call<BaseInfoLogDetail>

    @POST(API_ROOT + "manufact_logs/")
    fun createManufactLog(@Body manufactLog: ManufactLog): Call<ManufactLog>

    @DELETE(API_ROOT + "manufact_logs/{id}/")
    fun deleteManufactLog(@Path("id") id : Int) : Call<Void>
}