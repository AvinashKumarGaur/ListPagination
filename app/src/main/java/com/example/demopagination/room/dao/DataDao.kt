package com.kent.service.engg.roomNew.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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
import com.kent.service.engg.roomNew.entity.EstimatePaymentHistory
import com.kent.service.engg.roomNew.entity.LeadReceiverData
import com.kent.service.engg.roomNew.entity.ProductListItem
import com.kent.service.engg.roomNew.entity.User

@Dao
interface DataDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

    @Insert
    suspend fun insertUser(user: User)


    //Product List All Items
    @Insert
    suspend fun insertSpareProducts(productListItem: ProductListItem)

    @Query("SELECT * FROM ProductListItem")
    fun getAllProductList(): List<ProductListItem>

    @Insert
    suspend fun insertProductAll(productlist: List<ProductListItem>)


    // Defect Reason
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDefects(defects: Defect)

    @Query("SELECT * FROM Defect")
    fun getAllDefectList(): List<Defect>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDefects(defects: List<Defect>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllOptionResponse(defects: OptionsResponse)


    //Product List
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpareProducts(spareProducts: ProductListResponse)

    @Update
    suspend fun updateProducts(defects: ProductListResponse)

    @Query("SELECT * FROM ProductListResponse")
    suspend fun getAllProductsList(): ProductListResponse?

    @Query("SELECT * FROM OptionsResponse")
    fun getAllOptionResponse(): OptionsResponse?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(defects: List<ProductListResponse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeadsList(leadList: List<LeadReceiverData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductModelList(productModelList: List<ProductModel>)

    @Query("SELECT * FROM leads")
    suspend fun getLeadsList(): List<LeadReceiverData>

    @Query("SELECT * FROM product_model")
    suspend fun getProductModelList(): List<ProductModel>

//    @Query("SELECT * FROM CommonCaseResponse")
//    suspend fun getCommonData(): CommonCaseResponse

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManagerEng(engList: List<ResultManagerGetEng>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManagerBranch(branch: List<Branch>)


    @Query("SELECT * FROM BranchList")
    suspend fun getAllBranchResponseResponse(): List<Branch>?


    @Query("SELECT * FROM ManagerEngList")
    suspend fun getAllManagerEngList(): List<ResultManagerGetEng>?

    @Query("DELETE FROM ManagerEngList")
    suspend fun deleteManagerEng()


    /*Manager Case*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCaseManager(caseResponse: List<CasesManager>)

    @Query("SELECT * FROM case_manager")
    suspend fun getAllManagerCases(): List<CasesManager>?

    @Query("DELETE FROM case_manager")
    suspend fun deleteAllManagerCases()
    /*Manager case*/

    /*Engineer Case*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEngineerCase(case: List<Case>)

    @Query("SELECT * FROM home_case")
    suspend fun getAllCases(): List<Case>?

    @Query("DELETE FROM home_case")
    suspend fun deleteAllHomeCases()
    /*Engineer case*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommonCaseResponse(response: CommonCaseResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpareERPStockResponse(response: SpareERPStockResponseModel)

    @Query("SELECT * FROM common_case_response")
    suspend fun getCommonCaseResponse(): CommonCaseResponse?

    @Query("SELECT * FROM spare_erp_stock")
    suspend fun getSpareERPStockResponse(): SpareERPStockResponseModel


    //EstimatePayment Control

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEstimatePayment(response: EstimatePaymentHistory)

    @Query("SELECT * FROM EstimatePaymentHistory")
    suspend fun getEstimatePayment() : List<EstimatePaymentHistory>

    @Query("UPDATE EstimatePaymentHistory SET amount = :amount WHERE caseId = :caseId")
    suspend fun updateFieldForCaseId(caseId: String, amount: String)

    @Query("SELECT COUNT(*) FROM EstimatePaymentHistory WHERE caseId = :caseId")
    suspend fun doesCaseIdExist(caseId: String): Int

    @Update
    suspend fun updatePaymentHistory(paymentHistory: EstimatePaymentHistory)


}