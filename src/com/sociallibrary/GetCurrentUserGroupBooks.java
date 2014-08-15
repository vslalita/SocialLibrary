package com.sociallibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetCurrentUserGroupBooks implements GetCurrentUserRelatedBooks{

	@Override
	public ResultSet getBooks() {
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			String sql="select * "
					+ "from memberbooks "
					+ "where owner_id in(select mg.member_id "
					                  + "from membergroups m,membergroups mg "
					                  + "where m.member_id="+CurrentMember.cm.current_member.id+" "
					                  + "and m.group_id=mg.group_id)";
			ResultSet getGroupBooks=st.executeQuery(sql);
			if(SqlOperations.getCount(getGroupBooks)>0){
				getGroupBooks.beforeFirst();
				return getGroupBooks;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
