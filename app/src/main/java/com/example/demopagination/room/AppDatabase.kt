package com.kent.service.engg.roomNew

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kent.service.engg.model.ProductModel
import com.kent.service.engg.model.SpareERPStockResponseModel
import com.kent.service.engg.model.response.Branch
import com.kent.service.engg.model.response.CasesManager
import com.kent.service.engg.model.response.Defect
import com.kent.service.engg.model.response.OptionsResponse
import com.kent.service.engg.model.response.ProductListResponse
import com.kent.service.engg.model.response.ResultManagerGetEng
import com.kent.service.engg.model.response.commoncase.CommonCaseResponse
import com.kent.service.engg.model.response.homecase.Case
import com.kent.service.engg.roomNew.dao.DataDao
import com.kent.service.engg.roomNew.entity.EstimatePaymentHistory
import com.kent.service.engg.roomNew.entity.LeadReceiverData
import com.kent.service.engg.roomNew.entity.ProductListItem
import com.kent.service.engg.roomNew.entity.User
import com.kent.service.engg.roomNew.typeconverters.CommonCaseResponseConverter
import com.kent.service.engg.roomNew.typeconverters.DefectConverter
import com.kent.service.engg.roomNew.typeconverters.ListAnyTypeConverter
import com.kent.service.engg.roomNew.typeconverters.OptionsResponseConverter
import com.kent.service.engg.roomNew.typeconverters.ProductItemTypeConverter
import com.kent.service.engg.roomNew.typeconverters.ResultManagerGetEngTypeConverter
import com.kent.service.engg.roomNew.typeconverters.ResultTypeConverter
import com.kent.service.engg.roomNew.typeconverters.SpareERPStockResponseModelTypeConverter
import com.kent.service.engg.roomNew.typeconverters.SpareHistoryListConverter
import com.kent.service.engg.roomNew.typeconverters.SpareTypeConverter
import com.kent.service.engg.roomNew.typeconverters.StringListConverter

@Database(
    entities = arrayOf(
        User::class,
        Case::class,
        CasesManager::class,
        ProductListItem::class,
        Defect::class,
        ProductListResponse::class,
        LeadReceiverData::class,
        ProductModel::class,
        OptionsResponse::class,
        CommonCaseResponse::class,
        Branch::class,
        SpareERPStockResponseModel::class,
        ResultManagerGetEng::class,
        EstimatePaymentHistory::class
    ), version = 2
)
@TypeConverters(
    ListAnyTypeConverter::class,
    StringListConverter::class,
    SpareTypeConverter::class,
    ProductItemTypeConverter::class,
    ResultManagerGetEngTypeConverter::class,
    CommonCaseResponseConverter::class,
    ResultTypeConverter::class,
    DefectConverter::class,
    OptionsResponseConverter::class,
    SpareHistoryListConverter::class,

    SpareERPStockResponseModelTypeConverter::class,
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun DataDao(): DataDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE EstimatePaymentHistory ADD COLUMN fieldValue TEXT NOT NULL DEFAULT '0'")
    }
}

