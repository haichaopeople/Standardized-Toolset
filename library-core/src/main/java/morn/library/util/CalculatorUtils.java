package morn.library.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Optional;
/**
 * 精度的位数16，相当于DECIMAL64，默认舍入模式RoundingMode.HALF_UP .
 * new CalculatorUtils(1000).add(new BigDecimal()).divide().toResult(2)
 *
 * @author 金额计算器
 */
public class CalculatorUtils {

    public static final int FEN_SCALE = 2;
    public static final int SCALE16 = 16;

    private static final MathContext MC_CONTEXT = MathContext.DECIMAL128;
    public static final String PATTERN_NUMBER = ",###,###";
    public static final String PATTERN_NUMBER_RADIX_POINT = ",###,###.00";
    private final BigDecimal result;
    private String resultFormat;

    public BigDecimal getResult() {
        return result;
    }
    public CalculatorUtils() {
        this.result = BigDecimal.ZERO;
    }

    public String getResultFormat() {
        conversion();
        return resultFormat;
    }

    public CalculatorUtils(double val) {
        this.result = new BigDecimal(String.valueOf(val), MC_CONTEXT);
    }

    public CalculatorUtils(int val) {
        this.result = new BigDecimal(val, MC_CONTEXT);
    }

    public CalculatorUtils(long val) {
        this.result = new BigDecimal(val, MC_CONTEXT);
    }

    public CalculatorUtils(String val) {
        this.result = new BigDecimal(val, MC_CONTEXT);
    }

    public CalculatorUtils(BigDecimal val) {
        this.result = val;
    }

    public CalculatorUtils add(BigDecimal augEnd) {
        return new CalculatorUtils(this.result.add(augEnd, MC_CONTEXT));
    }


    public CalculatorUtils subtract(BigDecimal subtrahend) {
        return new CalculatorUtils(this.result.subtract(subtrahend, MC_CONTEXT));
    }

    public CalculatorUtils multiply(BigDecimal multiplicand) {
        return new CalculatorUtils(this.result.multiply(multiplicand, MC_CONTEXT));
    }

    public CalculatorUtils divide(BigDecimal divisor) {
        if (divisor.compareTo(BigDecimal.ZERO) == 0) {
            return new CalculatorUtils(BigDecimal.ZERO);
        }
        return new CalculatorUtils(this.result.divide(divisor, MC_CONTEXT));
    }

    public CalculatorUtils pow(int n) {
        return new CalculatorUtils(this.result.pow(n, MC_CONTEXT));
    }

    /**
     * 返回四舍五入结果
     *
     * @param scale 保留小数点几位
     * @see RoundingMode#HALF_UP
     */
    public BigDecimal toResult(int scale) {
        return this.result.setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 返回四舍五入结果
     */
    public BigDecimal toResult() {
        return this.result.setScale(FEN_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * 返回大于或者等于结果的最小整数(分制)
     *
     * @see RoundingMode#CEILING
     */
    public BigDecimal toCeil() {
        return this.toCeil(CalculatorUtils.FEN_SCALE);
    }

    /**
     * 返回小于或者等于结果的最大整数(分制)
     *
     * @see RoundingMode#FLOOR
     */
    public BigDecimal toFloor() {
        return this.toFloor(CalculatorUtils.FEN_SCALE);
    }

    /**
     * 返回大于或者等于结果的最小整数
     *
     * @param scale 保留小数点几位
     * @see RoundingMode#CEILING
     */
    public BigDecimal toCeil(int scale) {
        return this.result.setScale(scale, RoundingMode.CEILING);
    }

    /**
     * 返回小于或者等于结果的最大整数
     *
     * @param scale 保留小数点几位
     * @see RoundingMode#FLOOR
     */
    public BigDecimal toFloor(int scale) {
        return this.result.setScale(scale, RoundingMode.FLOOR);
    }

    public BigDecimal getInstance() {
        if (this.result.scale() > SCALE16) {
            return this.result.setScale(SCALE16, RoundingMode.HALF_EVEN);
        }
        return this.result;
    }

    /**
     * 舍弃分后面的所有位值
     */
    public CalculatorUtils downToRes() {
        return new CalculatorUtils(this.result.setScale(2, RoundingMode.DOWN));
    }

    /**
     * 返回四舍五入Money对象
     */
    public CalculatorUtils upToRes() {
        return new CalculatorUtils(this.result.setScale(2, RoundingMode.HALF_UP));
    }

    /**
     * 返回舍掉的字符串
     */
    public String downToResToStr() {
        return this.downToRes().getInstance().toString();
    }

    /**
     * 返回四舍五入的字符串
     */
    public String upToResToStr() {
        return this.upToRes().getInstance().toString();
    }


    /**
     * 返回小数点后2位以后的数字 并转为数字
     */
    public BigDecimal getScaleRightNumber() {
        String number = BigDecimal.ZERO.toPlainString();
        if (this.result.scale() > 2) {
            String str = this.getInstance().toPlainString();
            int index = str.indexOf('.');
            number = number + str.substring(index + 3);
        }
        return new BigDecimal(number);
    }

    /**
     * 三位分解法 元
     */
    public String covertNumber(BigDecimal decimal) {
        if (hasRadixPoint(decimal)) {
            return parseNumber(PATTERN_NUMBER_RADIX_POINT, decimal);
        }
        return parseNumber(PATTERN_NUMBER, decimal);
    }

    public String conversion() {
        return covertNumber(this.result.divide(ConvertMoneyEnum.DEFAULT.getDecimal(), 2, RoundingMode.HALF_UP));
    }

    /**
     * 三位分解法
     * @param covertMoney 自定义转换 元、万元、亿元
     * @return 三位分解法转换后的金额
     */
    public String conversion(ConvertMoneyEnum covertMoney) {
        return covertNumber(this.result.divide(covertMoney.getDecimal(), 2, RoundingMode.HALF_UP));
    }

    public String conversion(ConvertMoneyEnum covertMoney, int scale) {
        return covertNumber(this.result.divide(covertMoney.getDecimal(), scale, RoundingMode.HALF_UP));
    }

    public String conversion(ConvertMoneyEnum covertMoney, int scale, RoundingMode roundingMode) {
        return covertNumber(this.result.divide(covertMoney.getDecimal(), scale, roundingMode));
    }

    /**
     * 是否大于0
     */
    public static boolean hasBiggerThanZero(BigDecimal val) {
        if (val == null) {
            return false;
        }
        return val.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * 是否小于0
     */
    public static boolean hasLessThanZero(BigDecimal val) {
        if (val == null) {
            return false;
        }
        return val.compareTo(BigDecimal.ZERO) < 0;
    }


    /**
     * 是否小于等于0
     */
    public static boolean hasLessThanOrEqualZero(BigDecimal val) {
        if (val == null) {
            return false;
        }
        return val.compareTo(BigDecimal.ZERO) < 1;
    }


    /**
     * 是否等于0
     */
    public static boolean hasEqualZero(BigDecimal val) {
        if (val == null) {
            return false;
        }
        return val.compareTo(BigDecimal.ZERO) == 0;
    }

    /**
     * val1 > val2 返回 true 否则 false
     */
    public static boolean hasBiggerThan(BigDecimal val1, BigDecimal val2) {
        return val1.compareTo(val2) > 0;
    }


    /**
     * val1 >= val2 返回 true 否则 false
     */
    public static boolean hasBiggerThanOrEqual(BigDecimal val1, BigDecimal val2) {
        return val1.compareTo(val2) >= 0;
    }


    /**
     * val1 < val2 返回 true 否则 false
     */
    public static boolean hasLessThan(BigDecimal val1, BigDecimal val2) {
        return val1.compareTo(val2) < 0;
    }

    /**
     * val1 != val2 返回 true 否则 false
     */
    public static boolean hasNotEqual(BigDecimal val1, BigDecimal val2) {
        return val1.compareTo(val2) != 0;
    }

    /**
     * 判断两个值是否相等
     * @return boolean
     */
    public static boolean hasEqual(BigDecimal val, BigDecimal val1) {
        return val.compareTo(val1) == 0;
    }

    public static boolean ge(BigDecimal val, BigDecimal val1) {
        return val.compareTo(val1) >= 0;
    }

    public static boolean le(BigDecimal val, BigDecimal val1) {
        return val.compareTo(val1) <= 0;

    }

    /**
     * 是否有小数点
     */
    public boolean hasRadixPoint(BigDecimal decimal) {
        return getNumberOfDecimalPlaces(decimal) > 0;
    }

    /**
     * 金额格式化
     */
    private String parseNumber(String pattern, BigDecimal bd) {
        String format = new DecimalFormat(pattern).format(bd);
        this.resultFormat = format;
        return format;
    }


    public int getNumberOfDecimalPlaces(BigDecimal decimal) {
        String string = Optional.ofNullable(decimal)
                .orElse(BigDecimal.ZERO)
                .stripTrailingZeros()
                .toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }

//isLessThanZERO

    
}

 enum ConvertMoneyEnum {
    DEFAULT(new BigDecimal(1)),
    WAN_YUAN(new BigDecimal(10000)),
    YI_YUAN(new BigDecimal(100000000));

    private final BigDecimal decimal;

     ConvertMoneyEnum(BigDecimal decimal){
        this.decimal = decimal;
    }

    public BigDecimal getDecimal() {
        return decimal;
    }
}