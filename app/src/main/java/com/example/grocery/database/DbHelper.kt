package com.example.grocery.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.grocery.models.OrderSummary
import com.example.grocery.models.Product

class DbHelper(var mContext: Context):
    SQLiteOpenHelper(mContext,DATABASE_NAME,null,DATABASE_VERSION){

    var db : SQLiteDatabase = writableDatabase

    companion object {
        const val DATABASE_NAME = "cart"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "mycart"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PRICE = "price"
        const val COLUMN_QUANTITY = "quantity"
        const val COLUMN_IMAGE_URL = "image_url"
        const val COLUMN_PID = "pid"
        const val COLUMN_MRP = "mrp"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "create table if not exists $TABLE_NAME ("+
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "$COLUMN_PID CHAR(100),"+
                "$COLUMN_NAME CHAR(100),"+
                "$COLUMN_IMAGE_URL CHAR(200),"+
                "$COLUMN_MRP INTEGER,"+
                "$COLUMN_PRICE INTEGER,"+
                "$COLUMN_QUANTITY INTEGER)"

        db?.execSQL(createTable)
    }




    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        Log.d("onupgrade","onUpgrade")
        var dropTable = "drop table $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
    }


    private fun isItemInCart(product : Product):Boolean{
        val query = "Select * from $TABLE_NAME where $COLUMN_PID = ?"

        var cursor = db.rawQuery(query, arrayOf(product._id))
        var count = cursor.count
        return  count!=0
    }

    fun addItemInCart(product : Product){

        if(!isItemInCart(product)){
            val contentValues = ContentValues()
            contentValues.put(COLUMN_PID , product._id)
            contentValues.put(COLUMN_NAME,product.productName)
            contentValues.put(COLUMN_PRICE,product.price)
            contentValues.put(COLUMN_QUANTITY,1)
            contentValues.put(COLUMN_IMAGE_URL,product.image)
            contentValues.put(COLUMN_MRP,product.mrp)

            db.insert(TABLE_NAME , null, contentValues)

            Toast.makeText(mContext,"Added",Toast.LENGTH_SHORT).show()

        }else{

            Toast.makeText(mContext,"Already present in cart",Toast.LENGTH_SHORT).show()

        }


    }

    fun updateQuantityOfItem(product: Product){
        var contentValues = ContentValues()
        contentValues.put(COLUMN_QUANTITY,product.quantity)
        var whereClause = "$COLUMN_PID=?"
        var whereArgs = arrayOf(product._id)
        db.update(TABLE_NAME,contentValues,whereClause,whereArgs)
        Log.d("abc", product.quantity.toString())
    }


    fun deleteItemFromCart(pid: String){
        val whereClause = "$COLUMN_PID = ?"
        val whereArgs = arrayOf(pid)
        db.delete(TABLE_NAME,whereClause,whereArgs)
    }

    @SuppressLint("Range")
    fun getItemQuantityFromCart(product: Product):Int{
        var quantity = 0
        val query = "select * from $TABLE_NAME where $COLUMN_PID = ?"

        val cursor = db.rawQuery(query , arrayOf(product.image))

        if(cursor.moveToFirst()){
            quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY))
            cursor.close()

            return quantity
        }
        return quantity
    }

    @SuppressLint("Range")
    fun getOrderSummary(): OrderSummary {

        var totalMrp = 0
        var totalPrice = 0
        var discount = 0
        var deliveryCharges = 0
        var orderAmount = 0
        var totalItem = 0


        var columns = arrayOf(COLUMN_QUANTITY , COLUMN_MRP,COLUMN_PRICE)

        var cursor = db.query(TABLE_NAME,columns,null,null,null,null,null)
        if(cursor!=null && cursor.moveToFirst()){
            do{
                var t = cursor.getInt(cursor.getColumnIndex(COLUMN_MRP)).toInt()
                var p = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE)).toInt()
                var q = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)).toInt()

                totalItem += q
                totalMrp += t*q
                totalPrice += p*q
            }while(cursor.moveToNext())
        }

        discount = totalMrp-totalPrice

        if(totalPrice<300){
            deliveryCharges = 30
        }

        orderAmount = totalPrice+deliveryCharges

        return OrderSummary(totalMrp = totalMrp,
            totalPrice = totalPrice,
            discount = discount,
            deliveryCharges = deliveryCharges,
            orderAmount = orderAmount,
            totalItems= totalItem
        )

    }


    @SuppressLint("Range")
    fun getALlItemPresentInCart():ArrayList<Product>{
        var sqLiteDatabase = readableDatabase
        var list : ArrayList<Product> = ArrayList()

        var columns = arrayOf(
            COLUMN_PID, COLUMN_NAME, COLUMN_QUANTITY,
            COLUMN_PRICE, COLUMN_MRP, COLUMN_IMAGE_URL
        )

        var cursor = sqLiteDatabase.query(TABLE_NAME, columns, null, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                var pid = cursor.getString(cursor.getColumnIndex(COLUMN_PID))
                var pname = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                var pprice = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE))
                var pmrp = cursor.getInt(cursor.getColumnIndex(COLUMN_MRP))
                var pquantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY))
                var pimage = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL))

                var product = Product(_id=pid, productName = pname, mrp = pmrp.toDouble(), price = pprice.toDouble(), quantity = pquantity, image = pimage)
                list.add(product)
            } while (cursor.moveToNext())
        }
        return list
    }



}