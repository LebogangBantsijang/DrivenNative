package com.lebogang.driven.device.database.dao

import androidx.paging.PagingSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lebogang.driven.device.database.models.Category
import com.lebogang.driven.device.database.models.CategoryBridge

interface CategoryAccess {

    /**
     * Get categories
     * */
    @Query("SELECT * FROM Category ORDER BY title ASC")
    fun getCategories():PagingSource<Int,Category>

    /**
     * Get music ids that match the given id
     * */
    @Query("SELECT musicId FROM CategoryBridge WHERE categoryId =:categoryId")
    fun getMusicIds(categoryId:Long):List<Long>

    /**
     * Add category
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Category::class)
    fun addCategory(category: Category):Int

    /**
     * Add categories in the list
     * this not efficient but fuck it
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE,entity = Category::class)
    fun addCategory(categories: List<Category>):Int

    /**
     * Add Category bridge
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE,entity = CategoryBridge::class)
    fun addToBridge(bridge: CategoryBridge):Int

    /**
     * Add Category bridges
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE,entity = CategoryBridge::class)
    fun addToBridge(bridge: List<CategoryBridge>):Int

    /**
     * Remove category
     * - Probably gonna change this and pass in an id, Maybe. {Time will tell}
     * */
    @Delete(entity = Category::class)
    fun removeCategory(category: Category):Int

    /**
     * Remove categories in the list
     * - Probably gonna change this and pass in an id, Maybe. {Time will tell}
     * */
    @Delete(entity = Category::class)
    fun removeCategory(categories: List<Category>):Int

    /**
     * Remove Category bridge
     * */
    @Delete(entity = CategoryBridge::class)
    fun removeBridge(bridge: CategoryBridge):Int

    /**
     * Remove Category bridges
     * */
    @Delete(entity = CategoryBridge::class)
    fun removeBridge(bridge: List<CategoryBridge>):Int

    /**
     * Reset the table
     * */
    @Query("DELETE FROM Category")
    fun clear()

    /**
     * Reset the table
     * */
    @Query("DELETE FROM CategoryBridge")
    fun clearBridge()

}