package br.com.gtb.libraries.testcore.helper

import java.lang.reflect.Field
import java.lang.reflect.Modifier

object ReflectHelper {

    @Throws(Exception::class)
    inline fun <reified T> setPrivateField(obj: T, fieldName: String, newValue: Any?) {
        val field = T::class.java.getDeclaredField(fieldName)
        field.isAccessible = true
        field.set(obj, newValue)
    }

    @Throws(Exception::class)
    inline fun <reified T, reified R> getPrivateField(obj: T, fieldName: String): R? {
        val field = T::class.java.getDeclaredField(fieldName)
        field.isAccessible = true
        return field.get(obj) as R?
    }

    @Throws(Exception::class)
    inline fun <reified T> setPrivateStaticField(fieldName: String, newValue: Any) {
        val field = T::class.java.getField(fieldName)
        field.isAccessible = true
        Field::class.java.getDeclaredField("modifiers").also {
            it.isAccessible = true
            it.setInt(field, field.modifiers and Modifier.FINAL.inv())
        }
        field.set(null, newValue)
    }

    inline fun <reified T, reified R> callPrivateMethod(
        obj: T,
        methodName: String,
        parameterTypeList: Array<Class<*>>,
        vararg args: Any?)
    : R? {
        val method = T::class.java.getDeclaredMethod(methodName, *parameterTypeList).also {
            it.isAccessible = true
        }
        return method.invoke(obj, *args) as R?
    }

    inline fun <reified T, reified R> callPrivateMethod(obj: T, methodName: String): R? {
        val method = T::class.java.getDeclaredMethod(methodName).also {
            it.isAccessible = true
        }
        return method.invoke(obj) as R?
    }

}