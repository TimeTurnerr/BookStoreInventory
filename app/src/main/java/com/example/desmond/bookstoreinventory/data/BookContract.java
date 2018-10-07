package com.example.desmond.bookstoreinventory.data;
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
public class BookContract {
    private BookContract() {}

    public static final String CONTENT_AUTHORITY = "com.example.desmond.bookstoreinventory";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_BOOKS = "books";

        public  static final class BookEntry implements BaseColumns {
            public static final String TABLE_NAME = "books";
            public static final String COLUMN_BOOK_NAME = "bookName";
            public static final String COLUMN_BOOK_PRICE = "price";
            public static final String COLUMN_BOOK_QUANTITY = "quantity";
            public static final String COLUMN_BOOK_SUPPLIER_NAME = "supplierName";
            public static final String COLUMN_BOOK_SUPPLIER_PHONE_NUMBER = "supplierPhoneNumber";
            public static final String _ID = BaseColumns._ID;

            public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);

            public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

            public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        }
    }

