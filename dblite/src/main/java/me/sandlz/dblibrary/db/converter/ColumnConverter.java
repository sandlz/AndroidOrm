package me.sandlz.dblibrary.db.converter;

import android.database.Cursor;

import me.sandlz.dblibrary.db.sqlite.ColumnDbType;

public interface ColumnConverter<T> {

    T getFieldValue(final Cursor cursor, int index);

    Object fieldValue2DbValue(T fieldValue);

    ColumnDbType getColumnDbType();
}
