package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MemberDto;

public class Myinfo_Dao {
    DataSource dataSource;

    public Myinfo_Dao() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/apple_store");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MemberDto> list(String cust_id) {
        ArrayList<MemberDto> dtos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT name, cust_id, cust_pw, email, tel, address FROM customer WHERE cust_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cust_id); // 세션에서 받은 cust_id 설정

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MemberDto dto = new MemberDto();
                dto.setName(resultSet.getString("name"));
                dto.setCust_id(resultSet.getString("cust_id"));
                dto.setCust_pw(resultSet.getString("cust_pw"));
                dto.setEmail(resultSet.getString("email"));
                dto.setTel(resultSet.getString("tel"));
                dto.setAddress(resultSet.getString("address"));
                dtos.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dtos;
    }
}
