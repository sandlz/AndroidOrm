package me.sandlz.dblite.db.converter;

import android.database.Cursor;

import me.sandlz.dblite.db.sqlite.ColumnDbType;

public interface ColumnConverter<T> {

    T getFieldValue(final Cursor cursor, int index);

    Object fieldValue2DbValue(T fieldValue);

    ColumnDbType getColumnDbType();
}
