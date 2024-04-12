package top.turingteam.budstudent.middleware.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import top.turingteam.budstudent.pojo.entity.Point;
import top.turingteam.budstudent.support.PointConverter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Raqtpie
 */
@MappedTypes(Point.class)
public class LocationPointTypeHandler extends BaseTypeHandler<Point> {
    PointConverter pointConverter = new PointConverter();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Point parameter, JdbcType jdbcType) throws SQLException {
        byte[] x = pointConverter.to(parameter);
        ps.setBytes(i, x);
    }

    @Override
    public Point getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return pointConverter.from(rs.getBytes(columnName));
    }

    @Override
    public Point getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return pointConverter.from(rs.getBytes(columnIndex));
    }

    @Override
    public Point getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return pointConverter.from(cs.getBytes(columnIndex));
    }
}
