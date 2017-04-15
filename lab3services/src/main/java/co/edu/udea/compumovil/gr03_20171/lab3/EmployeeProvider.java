package co.edu.udea.compumovil.gr03_20171.lab3;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import data.DatabaseHelper;

/**
 * Created by admin on 14/03/2017.
 */

public class EmployeeProvider extends ContentProvider {
    DatabaseHelper db;
    ArrayList<String> users;

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        db = new DatabaseHelper(getContext());

        String UserName;
        users = db.getUsersName();
        Log.d(String.valueOf(users), "users: ");

        MatrixCursor matrixCursor = new MatrixCursor(new String[] {
                BaseColumns._ID,
                    SearchManager.SUGGEST_COLUMN_TEXT_1,
                    SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID
        });
        if (users != null){
            String query = uri.getLastPathSegment().toString();
            int limit = Integer.parseInt(uri.getQueryParameter(SearchManager.SUGGEST_PARAMETER_LIMIT));
            int length = users.size();

            for (int i = 0; i < length && matrixCursor.getCount() < limit; i++) {
                String user = users.get(i);

                if (user.contains(query)) {
                    matrixCursor.addRow(new Object[] {i, user, i});
                }
            }
        }

        return matrixCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
