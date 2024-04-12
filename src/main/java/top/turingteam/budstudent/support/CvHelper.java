package top.turingteam.budstudent.support;

/**
 * @author Raqtpie
 */
public interface CvHelper {
    /**
     * 从cv端获取结果
     * @param url 图片url
     * @return 结果
     */
    String getResultFromObjectCv(String url);

    /**
     * 从cv端获取结果
     * @param urlA 实人照片
     * @param urlB 拍照图片
     * @return 结果
     */
    Boolean getResultFromFaceCv(String urlA, String urlB);
}
