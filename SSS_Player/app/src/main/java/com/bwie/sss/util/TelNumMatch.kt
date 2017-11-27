package com.bwie.sss.util

/**
 * Created by 苏康泰 on 2017/11/23.
 */
import android.util.Log
import java.util.regex.Pattern;

class TelNumMatch(internal var mobPhnNum: String) {

    init {
        Log.d("tool", mobPhnNum)
    }

    fun matchNum(): Int {
        /**
         * 28. * flag = 1 YD 2 LT 3 DX 29.
         */
        val flag: Int//存储匹配结果
        // 判断手机号码是否是11位
        if (mobPhnNum.length == 11) {
            // 判断手机号码是否符合中国移动的号码规则
            if (mobPhnNum.matches(YD.toRegex())) {
                flag = 1
            } else if (mobPhnNum.matches(LT.toRegex())) {
                flag = 2
            } else if (mobPhnNum.matches(DX.toRegex())) {
                flag = 3
            } else {
                flag = 4
            }// 都不适合，未知֪
            // 判断手机号码是否符合中国电信的号码规则
            // 判断手机号码是否符合中国联通的号码规则
        } else {
            flag = 5
        }// 不是11位
        Log.d("TelNumMatch", "flag" + flag)
        return flag
    }

    companion object {
        /*
 * 10. * 移动: 2G号段(GSM网络)有139,138,137,136,135,134,159,158,152,151,150, 11. *
 * 3G号段(TD-SCDMA网络)有157,182,183,188,187,181 147是移动TD上网卡专用号段. 联通: 12. *
 * 2G号段(GSM网络)有130,131,132,155,156 3G号段(WCDMA网络)有186,185 电信: 13. *
 * 2G号段(CDMA网络)有133,153 3G号段(CDMA网络)有189,180 14.
 */
        internal var YD = "^[1]{1}(([3]{1}[4-9]{1})|([5]{1}[012789]{1})|([8]{1}[23478]{1})|([4]{1}[7]{1})|([7]{1}[8]{1}))[0-9]{8}$"
        internal var LT = "^[1]{1}(([3]{1}[0-2]{1})|([5]{1}[56]{1})|([8]{1}[56]{1})|([7]{1}[056]{1})|([4]{1}[5]{1}))[0-9]{8}$"
        internal var DX = "^[1]{1}(([3]{1}[3]{1})|([5]{1}[3]{1})|([8]{1}[019]{1})|[7]{1}[01237]{1})[0-9]{8}$"

        //手机号码的有效性验证
        fun isValidPhoneNumber(number: String): Boolean {
            var flag = false
            if (number.length == 11 && (number.matches(YD.toRegex()) || number.matches(LT.toRegex()) || number.matches(DX.toRegex()))) {
                flag = true
            }
            return flag
        }

        //判断手机号码是否存在
        fun isExistPhoneNumber(number: String): Boolean {
            return false
        }

        //判断email地址是否有效
        fun isEmail(email: String): Boolean {
            val patternString = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            return isMatcher(patternString, email)
        }

        //是否是数字和字母
        fun isMatchCharOrNumber(str: String): Boolean {
            val patternString = "^[\\d|a-z|A-Z]+$"
            return isMatcher(patternString, str)
        }

        //是否匹配
        fun isMatcher(patternString: String, str: String): Boolean {
            var isValid = false
            val pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(str)
            if (matcher.matches()) {
                isValid = true
            }
            return isValid
        }
    }
}
