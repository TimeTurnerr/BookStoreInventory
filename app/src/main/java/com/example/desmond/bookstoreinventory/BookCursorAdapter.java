package com.example.desmond.bookstoreinventory;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.desmond.bookstoreinventory.data.BookContract.BookEntry;
public class BookCursorAdapter extends CursorAdapter {
    Cursor mCursor;
    Context mContext;
    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        this.mCursor = cursor;
        this.mContext = context;
        TextView nameTextView = (TextView) view.findViewById(R.id.book_name);
        TextView priceTextView = (TextView) view.findViewById(R.id.book_price);
        TextView quantityTextView = (TextView) view.findViewById(R.id.book_quantity);
        final Button sellButton = view.findViewById(R.id.sale);
        sellButton.setTag(cursor.getPosition());
        int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_NAME);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_QUANTITY);
        String bookName = cursor.getString(nameColumnIndex);
        String bookPrice = cursor.getString(priceColumnIndex);
        int bookQuantity = cursor.getInt(quantityColumnIndex);
        nameTextView.setText(bookName);
        priceTextView.setText(bookPrice);
        quantityTextView.setText(String.valueOf(bookQuantity));
        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCursor == null || mCursor.getCount() < 1) {
                    return;
                }
                if (mCursor.moveToPosition((Integer) sellButton.getTag())) {
                    int columnIdIndex = mCursor.getColumnIndex(BookEntry._ID);
                    int quantityIndex = mCursor.getColumnIndex(BookEntry.COLUMN_BOOK_QUANTITY);
                    String col = mCursor.getString(columnIdIndex);
                    String quan = mCursor.getString(quantityIndex);
                    CatalogActivity catalogActivity = (CatalogActivity) mContext;
                    if (Integer.valueOf(quan) > 0) {
                        catalogActivity.decreaseCount(Integer.valueOf(col), Integer.valueOf(quan));
                    }
                    else {
                        catalogActivity.decreaseCount(Integer.valueOf(col), 1);
                        Toast.makeText(catalogActivity, R.string.negative_quantity_message, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}