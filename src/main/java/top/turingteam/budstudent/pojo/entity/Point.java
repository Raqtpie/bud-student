package top.turingteam.budstudent.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Raqtpie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point implements Serializable {
    /**
     * 纬度
     */
    private double lat;

    /**
     * 经度
     */
    private double lng;
}
