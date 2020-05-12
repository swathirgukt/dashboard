package com.credr.cc.bean.logistics;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FranchiseLogisticsUserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        String firstName = resultSet.getString("user_first_name");
        String lastName = resultSet.getString("user_last_name");
        if(StringUtils.isNotBlank(firstName)){
            if(StringUtils.isNotBlank(lastName)){
                firstName=firstName+" "+lastName;
            }
        }
        user.setName(firstName);
        user.setId(resultSet.getString("user_id"));
        user.setMobileNumber(resultSet.getString("user_mobile_number"));
        return user;
    }
}
