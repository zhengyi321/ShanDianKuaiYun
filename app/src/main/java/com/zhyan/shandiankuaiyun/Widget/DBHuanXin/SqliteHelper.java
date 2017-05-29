package com.zhyan.shandiankuaiyun.Widget.DBHuanXin;
/*

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.zhyan.shandiankuaiyun.Application.MyApplication;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SqliteHelper extends OrmLiteSqliteOpenHelper {
	
	private static final String DATABASE_NAME = "mcar.db";
	private static final int DATABASE_VERSION = 1;
	
	private static SqliteHelper mInstance;
	
	private Dao<UserApiModel, Integer> mUserInfoDao = null;

	public SqliteHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public static SqliteHelper getInstance(){
		if (mInstance == null) {

			mInstance = new SqliteHelper(MyApplication.getContext());
		}
		
		return mInstance;
	}

	*/
/**
	 * 鍒涘缓SQLite鏁版嵁搴�
	 *//*

	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, UserApiModel.class);
		} catch (SQLException e) {
			Log.e(SqliteHelper.class.getName(), "Unable to create datbases", e);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}

	*/
/**
	 * 鏇存柊SQLite鏁版嵁搴�
	 *//*

	@Override
	public void onUpgrade(
			SQLiteDatabase sqliteDatabase, 
			ConnectionSource connectionSource, 
			int oldVer,
			int newVer) {
		try {
			TableUtils.dropTable(connectionSource, UserApiModel.class, true);
			
			onCreate(sqliteDatabase, connectionSource);
		} catch (SQLException e) {
			Log.e(SqliteHelper.class.getName(),
					"Unable to upgrade database from version " + oldVer + " to new "
					+ newVer, e);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Dao<UserApiModel,Integer> getUserDao() throws SQLException{
		if(mUserInfoDao == null){
			try {
				mUserInfoDao = getDao(UserApiModel.class);
			} catch (java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		return mUserInfoDao;
	}

}
*/
