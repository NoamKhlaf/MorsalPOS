package sd.code.morsalpos.adapter;

/**
 * Created by master on 11/12/2016.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sd.code.morsalpos.R;
import sd.code.morsalpos.common.TransactionInfo;

//Adapter to handle ListView items (Contacts).
public class TransactionAdapter extends BaseAdapter {
    ArrayList<TransactionInfo> transactions ;

    //Used to create view.
    private LayoutInflater mInflater;
    Context context  ;
    AlertDialog.Builder alertDialog ;

    public TransactionAdapter(Context context , ArrayList<TransactionInfo> transactions){
        this.transactions =transactions ;
        this.context =context ;
        mInflater = LayoutInflater.from(context);

    }
    /** Get the sd.code.morsalpos.adapter items (Contacts) count */
    public int getCount() {
        return transactions.size();
    }
    /** Get item (Contact) by position */
    public Object getItem(int position) {
        return transactions.get(position);
    }
    /** Get item (Contact) id by position */
    public long getItemId(int position) {
        return 0;
    }
    /** Get item (Contact) view by position */
    public View getView(int position, View row, ViewGroup parent) {
        //Create view from contact_list_item.xml layout.
        if(row == null)
            row = mInflater.inflate(R.layout.trans_item_design, null);
        //Set Contact name.
        if(transactions !=null) {
            TextView trans = (TextView) row.findViewById(R.id.trans);
            TextView date = (TextView) row.findViewById(R.id.date);
            TextView pan = (TextView) row.findViewById(R.id.pan);
            TextView amount = (TextView) row.findViewById(R.id.amount);

            trans.setText(transactions.get(position).getTransName());
            date.setText("Trans Date: "+transactions.get(position).getDate());
            pan.setText("PAN: "+transactions.get(position).getPan());
            amount.setText("Amount: "+transactions.get(position).getAmount());

        }
        return row;
    }

}
