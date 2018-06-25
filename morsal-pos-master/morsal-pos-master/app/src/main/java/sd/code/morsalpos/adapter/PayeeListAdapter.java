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
import java.util.Locale;

 import sd.code.morsalpos.R;
import sd.code.morsalpos.Type.PayeeInfo;

//Adapter to handle ListView items (Contacts).
public class PayeeListAdapter extends BaseAdapter {
    ArrayList<PayeeInfo> payees ;

    //Used to create view.
    private LayoutInflater mInflater;
    Context context  ;
    AlertDialog.Builder alertDialog ;
    String lang = Locale.getDefault().getLanguage();


    public PayeeListAdapter(Context context , ArrayList<PayeeInfo> payees){
        this.payees =payees ;
        this.context =context ;
        mInflater = LayoutInflater.from(context);

    }
    /** Get the sd.code.morsalpos.adapter items (Contacts) count */
    public int getCount() {
        return payees.size();
    }
    /** Get item (Contact) by position */
    public Object getItem(int position) {
        return payees.get(position);
    }
    /** Get item (Contact) id by position */
    public long getItemId(int position) {
        return 0;
    }
    /** Get item (Contact) view by position */
    public View getView(final int position, View row, ViewGroup parent) {
        //Create view from contact_list_item.xml layout.
        if(row == null)
            row = mInflater.inflate(R.layout.card_name_item_design, null);
        //Set Contact name.
        if(payees !=null) {

            TextView name = (TextView) row.findViewById(R.id.card_name);


            if(lang.equals("en")){
                name.setText(payees.get(position).getPayeeName());
            }else {
                name.setText(payees.get(position).getPayeeName());
            }


        }
        return row;
    }

}
