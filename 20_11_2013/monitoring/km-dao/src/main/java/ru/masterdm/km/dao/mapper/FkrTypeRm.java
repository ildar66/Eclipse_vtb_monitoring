package ru.masterdm.km.dao.mapper;

import java.sql.SQLException;

import ru.masterdm.km.common.entity.FkrType;
import ru.masterdm.km.util.ResultSetWrapper;

/**
 * map for типу ФКР.
 * 
 * @author Shafgullin Ildar
 * 
 */
public class FkrTypeRm extends BaseRm<FkrType> {

	public FkrTypeRm() {
		super("FT_");
	}

	@Override
	protected FkrType map(ResultSetWrapper rsw) throws SQLException {
		FkrType instanse = new FkrType();
		instanse.setId(rsw.getLong(getAliasPrefix() + "ID"));
		instanse.setName(rsw.getString(getAliasPrefix() + "NAME"));
		instanse.setGroup(new FkrGroupRm().map(rsw));
		instanse.setKind(rsw.getLong(getAliasPrefix() + "KIND"));
		if (rsw.getLong(getAliasPrefix() + "IS_SIGNIFICANT") != null) {
			instanse.setSignificant(rsw.getLong(getAliasPrefix() + "IS_SIGNIFICANT") == 1);
		}

		return instanse;
	}

}
