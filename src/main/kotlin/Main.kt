import manufactApi.ManufactApiRepository
import manufactApi.models.LogClass
import manufactApi.models.ManufactLog
import java.text.SimpleDateFormat
import java.util.*

fun main(args: Array<String>){
    val SDF_LOG = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    println("Kotlin example")
    val manufactApiRepository : ManufactApiRepository = ManufactApiRepository("http://192.168.100.162:8000/")
    val response = manufactApiRepository.getLogClassList()
    if (response.isSuccessful) {
        val logClasses = response.body()
        logClasses!!.forEach {
            println(it.class_name)
        }
    }

    val bilResponse = manufactApiRepository.getBaseInfoLog(2)
    if (response.isSuccessful) {
        val bil = bilResponse.body()
        if (bil != null) {
            println(bil.order_number)
            println(bil.order_date!!.toString())
            bil.manufact_logs.forEach{
                println(it.created!!.toString())
            }
            val now = SDF_LOG.format(Date())
            val manufactLog = ManufactLog(
                base_info_log = bil.id,
                created = SDF_LOG.parse(now),
                log_class = 1,
                member = "平野"
            )
            var newLog = manufactApiRepository.addManufactLog(manufactLog)

            manufactApiRepository.deleteManufactLog(203214)
        }

    }
}