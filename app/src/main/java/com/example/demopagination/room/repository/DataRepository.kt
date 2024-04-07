package com.kent.service.engg.roomNew.repository

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val dataDao: DataDao) {
    suspend fun getAllUsers(): List<User> {
        return dataDao.getAllUsers()
    }

    suspend fun insertUser(user: User) {
        dataDao.insertUser(user)
    }

    suspend fun insertProductItemList(productListItem: ProductListItem) {
        dataDao.insertSpareProducts(productListItem)
    }

    suspend fun insertAllProductItemList(productListItem: List<ProductListItem>) {
        dataDao.insertProductAll(productListItem)
    }

    suspend fun getAllProductList(): List<ProductListItem> {
        return dataDao.getAllProductList()
    }


    //defect reasons
    suspend fun insertDefectItemList(defectItem: Defect) {
        dataDao.insertDefects(defectItem)
    }

    suspend fun insertAllDefectList(defectItems: List<Defect>) {
        dataDao.insertAllDefects(defectItems)
    }

  suspend fun insertAllOptionResponse(optionsResponse: OptionsResponse) {
        dataDao.insertAllOptionResponse(optionsResponse)
    }

    suspend fun getAllDefectList(): List<Defect> {
        return dataDao.getAllDefectList()
    }

    suspend fun getAllDefectListFromDB(callback: (response: OptionsResponse?) -> Unit) =
        callback(dataDao.getAllOptionResponse())

    /*Manager case*/
    suspend fun insertCaseManagerResponseToDB(case: List<CasesManager>) {
        dataDao.insertCaseManager(case)
    }

    suspend fun getManagerCaseResponseFromDB(callback: (response: List<CasesManager>) -> Unit) =
        dataDao.getAllManagerCases()?.let { callback(it) }

    suspend fun deleteManagerCaseResponseFromDB() {
        dataDao.deleteAllManagerCases()
    }
    /*Manager case*/

    //product List

    suspend fun insertProductList(productList: ProductListResponse) {
        dataDao.insertSpareProducts(productList)
    }

    suspend fun updateProducts(productList: ProductListResponse) {
        dataDao.updateProducts(productList)
    }


    suspend fun insertProductList(productList: List<ProductListResponse>) {
        dataDao.insertAllProducts(productList)
    }

    suspend fun getAllProductsList(): ProductListResponse? {
        return dataDao.getAllProductsList()
    }

      suspend fun getSpareProductListResponseFromDB(callback: (spareProducts: ProductListResponse?) -> Unit) {
          val result = withContext(Dispatchers.IO){
              dataDao.getAllProductsList()
          }
          callback(result)
      }


    suspend fun insertLeadsListToDB(leadList: List<LeadReceiverData>) {
        dataDao.insertLeadsList(leadList)
    }

    suspend fun insertSpareProductListToDB(spareProducts: ProductListResponse) {
        dataDao.insertSpareProducts(spareProducts)
    }

    suspend fun insertProductModelListToDB(productModelList: List<ProductModel>) {
        dataDao.insertProductModelList(productModelList)
    }

    suspend fun getLeadsListFromDB(callback: (list: List<LeadReceiverData>) -> Unit) =
        callback(dataDao.getLeadsList())

    suspend fun getProductModelListFromDB(callback: (list: List<ProductModel>) -> Unit) =
        callback(dataDao.getProductModelList())

    suspend fun insertManagerEng(managerGetEngList: List<ResultManagerGetEng>) {
        dataDao.insertManagerEng(managerGetEngList)
    }

    suspend fun insertManagerBranch(managerGetBranchist: List<Branch>) {
        dataDao.insertManagerBranch(managerGetBranchist)
    }


    suspend fun getManagerBranchFromDB(callback: (managerResponse: List<Branch>) -> Unit) =
        dataDao.getAllBranchResponseResponse()?.let { callback(it) }

    suspend fun getManagerEngResponseFromDB(callback: (managerResponse: List<ResultManagerGetEng>) -> Unit) =
        dataDao.getAllManagerEngList()?.let { callback(it) }

    suspend fun deleteManagerEngFromDB() {
        dataDao.deleteManagerEng()
    }

    /*Engineer case*/
    suspend fun insertEngineerCaseResponseToDB(case: List<Case>) {
        dataDao.insertEngineerCase(case)
    }

    suspend fun getEngineerCaseResponseFromDB(callback: (response: List<Case>) -> Unit) =
        dataDao.getAllCases()?.let { callback(it) }

    suspend fun deleteEngineerCaseResponseFromDB() {
        dataDao.deleteAllHomeCases()
    }

    /*Engineer case*/
    suspend fun saveCommonCaseResponse(response: CommonCaseResponse) {
        dataDao.insertCommonCaseResponse(response)
    }

    suspend fun getCommonCaseResponse(callback: (response: CommonCaseResponse?) -> Unit) =
        callback(dataDao.getCommonCaseResponse())


    suspend fun saveSpareERPStockResponse(response: SpareERPStockResponseModel) {
        dataDao.insertSpareERPStockResponse(response)
    }

    suspend fun getSpareERPStockResponseFromDB(callback: (response: SpareERPStockResponseModel) -> Unit) =
        callback(dataDao.getSpareERPStockResponse())


    //estimatePayment
    suspend fun insertEstimatePaymentList(productList: EstimatePaymentHistory) {
        dataDao.insertEstimatePayment(productList)
    }

    suspend fun updateEstimatePaymentList(productList: EstimatePaymentHistory) {
        dataDao.updatePaymentHistory(productList)
    }

    suspend fun updateEstimatePayment(productList: EstimatePaymentHistory) {
        dataDao.updatePaymentHistory(productList)
    }

    suspend fun getEstimatePayment(): List<EstimatePaymentHistory> {
        return dataDao.getEstimatePayment()
    }

    suspend fun getEstimatePaymentCaseId(caseId: String): Int {
        return dataDao.doesCaseIdExist(caseId)
    }

    suspend fun updateEstimatePaymentCaseId(caseId: String, newCaseId: String) {
        return dataDao.updateFieldForCaseId(caseId, newCaseId)
    }


    suspend fun getAllEstimateList(): List<EstimatePaymentHistory> {
        return dataDao.getEstimatePayment()
    }

}