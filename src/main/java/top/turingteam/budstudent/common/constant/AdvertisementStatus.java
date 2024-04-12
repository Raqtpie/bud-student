package top.turingteam.budstudent.common.constant;

/**
 * @author howe
 */
public enum AdvertisementStatus {
    WAITING_FOR_REVIEW("待审核"),
    REVIEW_FAILED("审核未通过"),
    WAITING_FOR_PUBLISH("待发布"),
    PUBLISHING("发布中"),
    EXPIRED("已过期"),
    INPROGRESS("进行中");
    private final String status;

    AdvertisementStatus(String str) {
        this.status = str;
    }

    public String getStatus() {
        return status;
    }
}
