package com.vinh.demopaypal.data.repo

import com.vinh.demopaypal.data.dao.BeerDao
import com.vinh.demopaypal.data.entinies.Beer
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class ShoppingCartRepo @Inject constructor(private val beerDao: BeerDao) : BaseRepo() {

    fun liveData() = beerDao.liveData()

    fun hardData() {
        val bears = bears()
        execute {
            beerDao.deleteAll()
            beerDao.insertIgnore(bears)
            beerDao.updateIgnore(bears)
        }
    }

    private fun bears(): ArrayList<Beer> {
        return ArrayList(
            Arrays.asList(
                Beer(0, "Heineken", "https://sc01.alicdn.com/kf/UTB8Vds1hpPJXKJkSahVq6xyzFXal/Dutch-Heineken-Beer-For-Export.jpg", 15.4),
                Beer(1, "Saigon Lager", "https://biasaigonmt.com/wp-content/uploads/2018/09/saigon-lager1-696x435-696x435.jpg", 13.4),
                Beer(2, "Beer 333", "https://image.vtcns.com/files/f2/2014/12/12/nhan-hieu-dinh-dam-bia-33-va-bia-333-gio-ra-sao-0.jpg", 12.4),
                Beer(3, "Sapporo", "https://cdn-a.william-reed.com/var/wrbm_gb_food_pharma/storage/images/5/3/7/1/1531735-1-eng-GB/Sapporo-Beer-imported-claims-called-into-question-in-US_wrbm_large.png", 10.4)
            )
        )
    }
}