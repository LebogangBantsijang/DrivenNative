package com.lebogang.driven.signin

import java.util.regex.Pattern

class InputValidator {

    fun isPasswordsValid(password:String?,other:String?):Boolean{
        if (doPasswordsMatch(password, other)){
            if (isPasswordValid(password))
                return true
        }
        return false
    }

    /**
     * Check if value is an email
     * @param value
     * @return true if the value is an email
     * */
    fun isEmailValid(value:String?):Boolean{
        if (value.isNullOrBlank())
            return false
        if (value.contains("@") && value.contains(".")){
            val dotIndex = value.indexOf(".")
            val length = value.length
            if ((length- dotIndex) > 0)
                return true
        }
        return false
    }

    /**
     * Check if password is valid
     * @param password
     * @return true if it is valid
     * Criteria
     * - must be at least 8 characters
     * - must contain at least 1 number
     * - must contain at least 1 upper case character
     * - must contain at least 1 lower case character
     * */
    private fun isPasswordValid(password: String?):Boolean{
        if (password.isNullOrBlank())
            return false
        if (password.length >= 8){
            val uppercase = Pattern.compile("[A-Z]")
            val lowercase = Pattern.compile("[a-z]")
            val numbers = Pattern.compile("[0-1]")
            val upperMatcher = uppercase.matcher(password)
            val lowerMatcher = lowercase.matcher(password)
            val numberMatcher = numbers.matcher(password)
            if (upperMatcher.find() && lowerMatcher.find() && numberMatcher.find())
                return true
        }
        return false
    }

    /**
     * Check if passwords match
     * @param password
     * @param other
     * @return true if the passwords match
     * */
    private fun doPasswordsMatch(password:String?,other:String?):Boolean{
        if (password.isNullOrBlank() || other.isNullOrBlank())
            return false
        if (password == other)
            return true
        return false
    }

}
