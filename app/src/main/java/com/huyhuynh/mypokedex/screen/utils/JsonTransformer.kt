package com.huyhuynh.mypokedex.screen.utils

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject

class JsonTransformer<T> {

    var gson = Gson()

    fun <T> convertObject(jsonString: String, ofClass: Class<T>): T? {
        return gson.fromJson(jsonString, ofClass)
    }

    /**
     * Convert string json to object <T>
     */
    inline fun <reified T> jsonStringToObject(jsonString: String?): T? {
        return jsonString?.let { convertObject(it, T::class.java) }
    }

    /**
     * use function for cast list json string to array object
     * @sample: JsonTransformer.getInstance<Array<Account>>().jsonStringToArray(stringJsonArray)
     */
    inline fun <reified T> jsonStringToArray(stringJsonArray: String): T {
        return gson.fromJson(stringJsonArray, T::class.java)
    }

    fun arrayToJsonString(array: List<T>): String {
        return gson.toJson(array)
    }

    fun objectToJsonString(model: T): String {
        return gson.toJson(model)
    }

    fun jsonStringToJsonObject(jsonString: String): JsonObject {
        return gson.fromJson(jsonString, JsonObject::class.java) as JsonObject
    }

    fun jsonStringToJSONObject(jsonString: String): JSONObject {
        return gson.fromJson(jsonString, JSONObject::class.java) as JSONObject
    }

    /**
     * T can <Object> or <Array<Object>>
     */
    inline fun <reified T> getObjectFromJsonString(jsonString: String, keyObject : String): T?{
        val jsonObject = jsonStringToJsonObject(jsonString)
        val objectStr = jsonObject.get(keyObject)
        return objectStr?.let { convertObject(it.toString(), T::class.java) }
    }

    companion object {
        // Singleton instance
        private var instance: JsonTransformer<*>? = null

        /**
         * Get singleton instance.
         *
         * @return Singleton instance
         */
        fun <T> getInstance(): JsonTransformer<T> {
            if (instance == null) {
                instance =
                    JsonTransformer<T>()
            }
            @Suppress("UNCHECKED_CAST")
            return instance as JsonTransformer<T>
        }
    }

}