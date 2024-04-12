package top.turingteam.budstudent.common.constant;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 邮政编码对应省份常量
 * @author Raqtpie
 */
@Getter
public enum PostalCodeToProvinceCommon {
    /**
     * 北京
     */
    BEIJING("10", "北京市"),

    /**
     * 天津
     */
    TIANJIN("30", "天津市"),

    /**
     * 河北
     */
    HEBEI("05", "河北省"),

    HEBEI2("06", "河北省"),

    HEBEI3("07", "河北省"),

    /**
     * 山西
     */
    SHANXI("03", "山西省"),

    SHANXI2("04", "山西省"),

    /**
     * 内蒙古
     */
    INNER_MONGOLIA("01", "内蒙古自治区"),

    INNER_MONGOLIA2("02", "内蒙古自治区"),

    /**
     * 辽宁
     */
    LIAONING("11", "辽宁省"),

    LIAONING2("12", "辽宁省"),

    /**
     * 吉林
     */
    JILIN("13", "吉林省"),

    /**
     * 黑龙江
     */
    HEILONGJIANG("15", "黑龙江省"),

    HEILONGJIANG2("16", "黑龙江省"),

    /**
     * 上海
     */
    SHANGHAI("20", "上海市"),

    /**
     * 江苏
     */
    JIANGSU("21", "江苏省"),

    JIANGSU2("22", "江苏省"),

    /**
     * 浙江
     */
    ZHEJIANG("31", "浙江省"),

    ZHEJIANG2("32", "浙江省"),

    /**
     * 安徽
     */
    ANHUI("23", "安徽省"),

    ANHUI2("24", "安徽省"),

    /**
     * 福建
     */
    FUJIAN("35", "福建省"),

    FUJIAN2("36", "福建省"),

    /**
     * 江西
     */
    JIANGXI("33", "江西省"),

    JIANGXI2("34", "江西省"),

    /**
     * 山东
     */
    SHANDONG("25", "山东省"),

    SHANDONG2("26", "山东省"),

    SHANDONG3("27", "山东省"),

    /**
     * 河南
     */
    HENAN("45", "河南省"),

    HENAN2("46", "河南省"),

    HENAN3("47", "河南省"),

    /**
     * 湖北
     */
    HUBEI("43", "湖北省"),

    HUBEI2("44", "湖北省"),

    /**
     * 湖南
     */
    HUNAN("41", "湖南省"),

    HUNAN2("42", "湖南省"),

    /**
     * 广东
     */
    GUANGDONG("51", "广东省"),

    GUANGDONG2("52", "广东省"),

    /**
     * 广西
     */
    GUANGXI("53", "广西壮族自治区"),

    GUANGXI2("54", "广西壮族自治区"),

    /**
     * 海南
     */
    HAINAN("57", "海南省"),

    /**
     * 重庆
     */
    CHONGQING("40", "重庆市"),

    /**
     * 四川
     */
    SICHUAN("61", "四川省"),

    SICHUAN2("62", "四川省"),

    SICHUAN3("63", "四川省"),

    SICHUAN4("64", "四川省"),

    /**
     * 贵州
     */
    GUIZHOU("55", "贵州省"),

    GUIZHOU2("56", "贵州省"),

    /**
     * 云南
     */
    YUNNAN("65", "云南省"),

    YUNNAN2("66", "云南省"),

    YUNNAN3("67", "云南省"),

    /**
     * 西藏
     */
    XIZANG("85", "西藏自治区"),

    /**
     * 陕西
     */
    SHAANXI("71", "陕西省"),

    SHAANXI2("72", "陕西省"),

    /**
     * 甘肃
     */
    GANSU("73", "甘肃省"),

    GANSU2("74", "甘肃省"),

    /**
     * 青海
     */
    QINGHAI("81", "青海省"),

    /**
     * 宁夏
     */
    NINGXIA("75", "宁夏回族自治区"),

    /**
     * 新疆
     */
    XINJIANG("83", "新疆维吾尔自治区"),

    XINJIANG2("84", "新疆维吾尔自治区"),

    /**
     * 香港
     */
    OTHER("99", "香港特别行政区/澳门特别行政区/台湾省/海外");

    /**
     * 邮政编码
     */
    private final String postalCode;

    /**
     * 省份名称
     */
    private final String provinceName;

    PostalCodeToProvinceCommon(String postalCode, String provinceName) {
        this.postalCode = postalCode;
        this.provinceName = provinceName;
    }

    private static final Map<String, String> postalCodeToProvinceMap = new HashMap<>();

    static {
        for (PostalCodeToProvinceCommon entry : PostalCodeToProvinceCommon.values()) {
            postalCodeToProvinceMap.put(entry.getPostalCode(), entry.getProvinceName());
        }
    }

    /**
     * 根据邮政编码查找省份名称
     */
    public static String findProvinceNameByPostalCode(String postalCode) {
        return postalCodeToProvinceMap.get(postalCode);
    }
}
