package com.sociallibrary;

import java.sql.*;

import com.sociallibrary.db.DBHelper;

public class GetUserGroupBooks implements GetUserRelatedBooks{

	@Override
	public ResultSet getBooks(int id) {
		
		String sql="select * "
				+ "from memberbooks mb,books b "
				+ "where mb.book_id=b.id "
				+ "and mb.owner_id in (select mg.member_id "
				                  + "from membergroups m,membergroups mg "
				                  + "where m.member_id="+id+" "
				                  + "and m.group_id=mg.group_id)";
		
		return DBHelper.getQueryResult(sql);
	}

}
